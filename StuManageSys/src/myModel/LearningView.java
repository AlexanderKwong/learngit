package myModel;

import myDBUtil.VO;

public class LearningView implements VO {

	private int studentID;
	private String studentName;
	private int desciplineID;
	private String desciplineName;
	private int sGrade;
	private String sMajor;
	private int sClass;
	private float score;
	
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
	public int getDesciplineID() {
		return desciplineID;
	}
	public void setDesciplineID(int desciplineID) {
		this.desciplineID = desciplineID;
	}
	public String getDesciplineName() {
		return desciplineName;
	}
	public void setDesciplineName(String desciplineName) {
		this.desciplineName = desciplineName;
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
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	
	public String toString() {
		String s = "  ";
		return new String(getStudentID() + s + getStudentName() + s + getDesciplineID() + s
				+ getDesciplineName() + s + getsGrade() + s 
				+ getsMajor() + s + getsClass() + s + getScore());
	}
}
