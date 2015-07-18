package myModel;

import myDBUtil.VO;

public class Descipline implements VO {
	
	private int desciplineID;
	private String desciplineName;
	private int dTime;
	private int dCredit;
	
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
	public int getdTime() {
		return dTime;
	}
	public void setdTime(int dTime) {
		this.dTime = dTime;
	}
	public int getdCredit() {
		return dCredit;
	}
	public void setdCredit(int dCredit) {
		this.dCredit = dCredit;
	}
	
	public String toString() {
		String s = "  ";
		return new String(getDesciplineID() + s + getDesciplineName() + s + getdTime() + s + getdCredit());
	}
}
