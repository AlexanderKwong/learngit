	package myJDBC;

import java.sql.Date;
import java.util.List;

import myDBUtil.DAOimpl;
import myDBUtil.VO;
import myModel.Descipline;
import myModel.Learning;
import myModel.LearningView;
import myModel.Student;


public class tset {

	public static void main(String[] args) throws Exception {
		//DAOimpl dao = new DAOimpl("myModel.Student");
		
//		TestVO tv = new TestVO();
//		tv.setTestVoId("id");
//		tv.setUsername("name");
//		tv.setPassword("password");
//		dao.insert(tv);
		
/*		List<VO> a = dao.getAll();
		for (VO vo : a) {
			System.out.println(vo);
		}
*/
//		dao.delete("ASD");
	/*	List<VO> a = dao.getAll();
		for (VO vo : a) {
			System.out.println(vo);
		}
	*/	
	/*	VO b = dao.getById("1");
		System.out.println(b);
	*/
	
	/*	List<VO> c = dao.getByName("超级锯掉顺");
		for (VO vo : c) {
			System.out.println(vo);
		}
	*/
	/*	
		Student d = new Student();
		d.setStudentID(11);
		Date date = new Date(95, 5, 2) ;
		d.setStudentName("打掉顺");
		d.setsBirthday(date);
		d.setsSex(false);
		d.setsCno(1);
		d.setsClass(3);
		d.setsMajor("物联网");
		d.setsGrade(2012);
		dao.update(d);
	
	*/	
	/*	
		
		List<VO> a = dao.getAll();
		for (VO vo : a) {
			System.out.println(vo);
		}
	*/
	/*	dao.delete("11");
		
		List<Student> a = dao.getAll();
		for (Student vo : a) {
			System.out.println(vo);
		}
	*/
	/*	
		DAOimpl dao = new DAOimpl("myModel.LearningView");
		List<LearningView> a = dao.getAll();
		for (LearningView vo : a) {
			System.out.println(vo);
		}
		
	*/
	/*	DAOimpl dao1 = new DAOimpl("myModel.Learning");
		Learning ln = new Learning();
		ln.setStudentID(3);
		ln.setDesciplineID(2);
		ln.setScore(15);
		dao1.insert(ln);
		List<Learning> a = dao1.getAll();
		for (Learning vo : a) {
			System.out.println(vo);
		}
		*/
		DAOimpl dao = new DAOimpl("myModel.Descipline");
		List<Descipline> a = dao.getAll();
		for (Descipline vo : a) {
			System.out.println(vo);
		}
	}

}
