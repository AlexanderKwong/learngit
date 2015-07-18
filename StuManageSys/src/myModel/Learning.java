package myModel;

import myDBUtil.VO;

public class Learning implements VO {
	
	
	private int learningID;
	private int studentID;
	private int desciplineID;
	private float score;
	
	public int getLearningID() {
		return learningID;
	}
	public void setLearningID(int learningID) {
		this.learningID = learningID;
	}
	public int getStudentID() {
		return studentID;
	}
	public void setStudentID(int studentID) {
		this.studentID = studentID;
	}
	public int getDesciplineID() {
		return desciplineID;
	}
	public void setDesciplineID(int desciplineID) {
		this.desciplineID = desciplineID;
	}
	public float getScore() {
		return score;
	}
	public void setScore(float score) {
		this.score = score;
	}
	
	public String toString() {
		String s = "  ";
		return new String(getLearningID() + s + getStudentID() + s +  getDesciplineID() + s + getScore());
	}
}
