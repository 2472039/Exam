package bean;

import java.util.Map;

public class TestListSubject implements java.io.Serializable{
	int entYear;
	String studentNo;
	String studentName;
	String classNum;
	Map<Integer, Integer> points;

	public int getEntYear() {
		return entYear;
	}

	public String getStudentNo() {
		return studentNo;
	}

	public String getStudentName() {
		return studentName;
	}

	public String getClassNum() {
		return classNum;
	}

	public Integer getFirstPoint() {
	    return points.get(1);
	}

	public Integer getSecondPoint() {
	    return points.get(2);
	}


	public void setEntYear(int entYear) {
		this.entYear = entYear;
	}

	public void setStudentNo(String studentNo) {
		this.studentNo = studentNo;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public void setClassNum(String classNum) {
		this.classNum = classNum;
	}

	public void setPoints(Map<Integer, Integer> points) {
		this.points = points;
	}
}
