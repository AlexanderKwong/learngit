package myDBUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.omg.PortableServer.ServantRetentionPolicyValue;

import myDBUtil.DBFactory;

public class DAOimpl  {



		/**
		 * 加载后的实体类
		 */
		@SuppressWarnings("unchecked")
		private Class voClass = null;

		private String voName = null;
		/** 表名 */
		private String tableName = null;

		/** 表中的字段名 */
		private ArrayList<String> fieldName = new ArrayList<String>();

		/** VO类中的方法名 */
		private ArrayList<String> methodName = new ArrayList<String>();

		/**
		 * 构造方法
		 * @param voName VO类的类名
		 * @throws ClassNotFoundException 类没有找到，抛出异常
		 * @throws VOClassIllegalException 如果VO类不符合规则，抛出异常
		 */
		public DAOimpl(String voName) 
				throws ClassNotFoundException, VOClassIllegalException {
			
			this.voName = voName;
			//加载VO类
			voClass = Class.forName(voName);

			//读取方法名
			for (Method me : voClass.getDeclaredMethods()) {
				methodName.add(me.getName());
			}

			//读取数据库表名
			StringBuffer buffer = new StringBuffer();
			int i = voName.length()-1;
			while (i >= 0 && voName.charAt(i) != '.') {
				buffer.insert(0, voName.charAt(i--));
			}
			tableName = buffer.toString();

			//获取字段名
			for (int i1=0; i1 < methodName.size();i1++){
					if (( methodName.get(i1)).startsWith("get")) {
						String f = (methodName.get(i1)).substring(3);
						if (methodName.contains("set" + f)) {
							fieldName.add(f);
//							System.out.println(f);
					}		
				}
			}

			//检查是否存在主键，并移到末尾
			boolean keyExists = false;
			//对于视图，含有ID的数目
			int count =0;
			
			for (i=0; i<fieldName.size();i++){
					if ((fieldName.get(i)).compareToIgnoreCase(tableName + "ID") == 0) {
					keyExists = true;
					break;
				}
					if ((fieldName.get(i)).contains("ID") ){
						count++;
					}
			}
			
			if (!keyExists && count<2) {
				throw new VOClassIllegalException();
			} else if(keyExists){
//				System.out.println("主键是："+ fieldName.get(i));
				fieldName.add(fieldName.remove(i));
			} else {
				System.out.println("查询视图");
			}

			
	
		}


		/**
		 * 从表单获取所有记录
		 * @return 保存所有记录的ArrayList
		 * @throws Exception 
		 */
		public ArrayList getAll() throws Exception {
			String sql = "select * from " + tableName;
			return sqlQuery(sql);
		}


		/**
		 * 查询一条记录
		 * @param id 记录ID
		 * @return 表示记录的VO对象
		 * @throws Exception 
		 */
		public VO getById(String id) 
				throws Exception {
			String sql = "Select * from " + tableName + " where " + tableName + "id = '" + id + "'";
			ArrayList list = null;
			list = sqlQuery(sql);

			if (list.size() == 0) 
				return null;
			else 
				return (VO) list.get(0);
		}

		/**
		 * 查询一条记录
		 * @param name 记录姓名
		 * @return 表示记录的VO对象
		 * @throws Exception
		 */
		public List<VO> getByName(String name)
				throws Exception {
			String sql = "Select * from " + tableName + " where " + tableName + "Name = '" + name + "'";
			ArrayList list = null;
			list = sqlQuery(sql);

			if (list.size() == 0) 
				return null;
			else 
				return list;
		}

		/**
		 * 插入一条数据
		 * @param o 要插入的数据
		 * @throws Exception 
		 */
		public void insert(VO o) 
				throws Exception {
			//创建插入的SQL语句
			StringBuffer buffer = new StringBuffer();
			buffer.append("insert into " + tableName + " (");
			for (String s : fieldName) {
				buffer.append(s + " ,");
			}
			buffer.deleteCharAt(buffer.length()-1);
			buffer.append(")");
			buffer.append(" values ( ");
			for (String s : fieldName) {
				buffer.append("? ,");
			}
			buffer.deleteCharAt(buffer.length()-1);
			buffer.append(")");
			String sql = buffer.toString();
			//执行SQL语句
			Connection conn = DBFactory.getCon();
			PreparedStatement pstat = conn.prepareStatement(sql);
			setValue(pstat, o);
			pstat.executeUpdate();
			DBFactory.close(conn);

		}


		/**
		 * 更新一条记录
		 * @param o 要更新的VO对象，其中主键保存要更新的主键，其它值替代数据库中的当前值
		 * @throws Exception 
		 */
		public void update(VO o) 
				throws Exception {
			//创建插入的SQL语句
			StringBuffer buffer = new StringBuffer();
			buffer.append("update " + tableName + " set ");
			for (int i=0; i< fieldName.size();i++ ){
				buffer.append(fieldName.get(i)+ "=?,");
			}
			buffer.deleteCharAt(buffer.length()-1);
			//找出主键的值
			int i ;
			for ( i=0; i<methodName.size();i++){
				if (methodName.get(i).equals("get" + fieldName.get(fieldName.size()-1)))
				break;
			}
			Integer oId = (Integer)(voClass.getMethods()[i].invoke(o));
			buffer.append(" where " + fieldName.get(fieldName.size()-1) + "=" + oId );
			String sql = buffer.toString();
			//执行SQL语句
			Connection conn = DBFactory.getCon();
			PreparedStatement pstat = conn.prepareStatement(sql);
			setValue(pstat, o);
			pstat.executeUpdate();
			DBFactory.close(conn);
		}
		
		/**
		 * 从数据库删除一条记录
		 * @param id 记录的ID
		 * @throws Exception 
		 */
		public void delete(String id) throws Exception {
			String sql = "delete from " + tableName + " where " + tableName + "id = '" + id + "'";
			//System.out.println(sql);
			sqlUpdate(sql);
		}


		/**
		 * 从结果集向对象设置值
		 * @param rs 从数据库中查询得到的结果集
		 * @return 结果集设置到的对象
		 * @throws InstantiationException 
		 * @throws IllegalAccessException
		 * @throws IllegalArgumentException
		 * @throws InvocationTargetException
		 * @throws SQLException
		 */
		protected VO getValue(ResultSet rs) 
		throws InstantiationException, IllegalAccessException, 
		IllegalArgumentException, InvocationTargetException, 
		SQLException {
			VO o = (VO) voClass.newInstance();
			//每次得到一个字段名
			for (String s : fieldName) {
				//计数器
				int i=0;
				//从方法名里查找当前字段的set方法
				for (i=0; i<methodName.size();i++){
						if (methodName.get(i).equals("set" + s))
						break;
				}
				//得到找到的方法名的Method对象
				Method me = voClass.getMethods()[i];
				//获得当前对象set方法的描述信息
				String gstr = me.toGenericString();
				//解析描述信息，得到参数类型
				for (i=0; i<gstr.length()-1;i++){
						if (gstr.charAt(i) == '(') {
						i++;
						break;
					}
				}
				StringBuffer buffer = new StringBuffer();
				while (i < gstr.length() && gstr.charAt(i) != ')') {
					buffer.append(gstr.charAt(i++));
				}
				String rstr = buffer.toString();
//				System.out.println(rstr);
				//根据参数类型，调用结果集不同的方法获取值
				if (rstr.equals("java.lang.String")) {
					me.invoke(o, rs.getString(s));
				} else if (rstr.equals("int")) {
					me.invoke(o, rs.getInt(s));
				} else if (rstr.equals("float") || rstr.equals("double")) {
					me.invoke(o, rs.getFloat(s));
				} else if (rstr.equals("java.sql.Date")){
					me.invoke(o, rs.getDate(s));
				} else if (rstr.equals("boolean")){
					me.invoke(o, rs.getBoolean(s));
				}
				else{
					System.out.println(rstr);
				}
			}
			return o;
		}


		/**
		 * 将一个VO对象的值设置到PreparedStatement对象中
		 * @param pstmt PreparedStatement对象
		 * @param o VO对象
		 * @throws IllegalArgumentException
		 * @throws IllegalAccessException
		 * @throws InvocationTargetException
		 * @throws SQLException
		 * @throws VOClassIllegalException 
		 */
		protected void setValue(PreparedStatement pstmt, VO o)
		throws IllegalArgumentException, IllegalAccessException,
		InvocationTargetException, SQLException, VOClassIllegalException {
			if(o.getClass().getName() != voName )
				throw new VOClassIllegalException();
			
			for (int index=0;index< fieldName.size() ;index++){//计数器
				int i=0;
				//从方法名里查找当前字段的set方法
				for (i=0; i<methodName.size();i++){
						if (methodName.get(i).equals("get" + fieldName.get(index)))
						break;
				}
				//得到找到的方法名的Method对象
				Method me = voClass.getMethods()[i];
				//获得当前对象set方法的描述信息
				String gstr = me.toGenericString();
				//解析描述信息，得到参数类型
	//			System.out.println(gstr);
				for (i=0; i<gstr.length()-1;i++)
						if (gstr.charAt(i++) == ' ') {
						break;
					}
				
				StringBuffer buffer = new StringBuffer();
				
				//根据VO中get方法返回值调用不同函数
				while (gstr.charAt(i) != ' '){
					buffer.append(gstr.charAt(i));
					i++;
				}
				String rstr = buffer.toString();
			//	System.out.println(rstr);
				if (rstr.equals("java.lang.String")) {
					String str = (String) me.invoke(o);
	//				pstmt.setString(index+1, "'" + str + "'");
					pstmt.setString(index+1,   str );
				} else if (rstr.equals("int")) {
					Integer in = (Integer) me.invoke(o);
					pstmt.setInt(index+1, in);
				} else if (rstr.equals("float") || rstr.equals("double")) {
					Float flo = (Float) me.invoke(o);
					pstmt.setFloat(index+1, flo);
				} else if (rstr.equals("boolean")){
					boolean bool = (boolean) me.invoke(o);
					pstmt.setBoolean(index+1, bool);
				} else if (rstr.equals("java.sql.Date")){
					Date date = (Date) me.invoke(o);
					pstmt.setDate(index+1, date);
				}else {
					pstmt.setObject(index+1, null);
				}
			}
		}


		/**
		 * 执行一条查询的SQL语句
		 * @param sql 要执行的SQL语句
		 * @return 得到的结果的实体类列表
		 * @throws Exception 
		 */
		public ArrayList sqlQuery(String sql) 
				throws Exception {
			ArrayList data = new ArrayList();
			Connection conn = DBFactory.getCon();
			PreparedStatement pstat = conn.prepareStatement(sql);
			ResultSet rs = pstat.executeQuery();
			while (rs.next()) {
				VO o = getValue(rs);
				data.add(o);
			}			
			DBFactory.close(conn);
			return data;
		}


		/**
		 * 执行一条用来修改的SQL语句
		 * @param sql 用来执行的SQL语句
		 * @throws Exception 
		 */
		public void sqlUpdate(String sql) throws Exception {
			Connection conn = DBFactory.getCon();
			PreparedStatement pstat = conn.prepareStatement(sql);
			pstat.executeUpdate();
			DBFactory.close(conn);
		}
	}

