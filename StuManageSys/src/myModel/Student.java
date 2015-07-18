package myModel;

import java.sql.Date;

import javax.xml.crypto.Data;

import myDBUtil.VO;

enum sex {
	MALE, FEMALE;

}

public class Student implements VO {
	private int studentID;
	private String studentName;
	private boolean sSex;
	private Date sBirthday;
	private int sCno;
	private int sGrade;
	private String sMajor;
	private int sClass;
	private String sWhere;

	public int getStudentID() {
		return studentID;
	}

	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public boolean getsSex() {
		return sSex;
	}

	public void setsSex(boolean sSex) {
		this.sSex = sSex;
	}

	public java.sql.Date getsBirthday() {
		return sBirthday;
	}

	public void setsBirthday(Date sBirthday) {
		this.sBirthday = sBirthday;
	}

	public int getsCno() {
		return sCno;
	}

	public void setsCno(int sCno) {
		this.sCno = sCno;
	}

	public int getsGrade() {
		return sGrade;
	}

	public void setsGrade(int sGrade) {
		this.sGrade = sGrade;
	}

	public String getsMajor() {
		return sMajor;
	}

	public void setsMajor(String sMajor) {
		this.sMajor = sMajor;
	}

	public int getsClass() {
		return sClass;
	}

	public void setsClass(int sClass) {
		this.sClass = sClass;
	}

	public String getsWhere() {
		return sWhere;
	}

	public void setsWhere(String sWhere) {
		this.sWhere = sWhere;
	}

	public String toString() {
		String s = "  ";
		return new String(getStudentID() + s + getStudentName() + s + getsSex() + s
				+ getsBirthday() + s + getsCno() + s + getsGrade() + s
				+ getsMajor() + s + getsClass() + s + getsWhere());
	}
}
