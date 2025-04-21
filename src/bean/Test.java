package bean;

public class Test implements java.io.Serializable{
	private Student student;
	private Subject subject;
	private School school;
	private int no;
	private int point;
	private String class_num;

	public Student getStudent(){
		return student;
	}

	public Subject getSubject(){
		return subject;
	}

	public School getSchool(){
		return school;
	}

	public int getNo(){
		return no;
	}

	public int getPoint(){
		return point;
	}
	public String getClass_num(){
		return class_num;
	}



	public void setStudent(Student student) {
		this.student=student;
	}

	public void setSubject(Subject subject) {
		this.subject=subject;
	}

	public void setSchool(School school) {
		this.school=school;
	}

	public void setNo(int no) {
		this.no=no;
	}

	public void setPoint(int point) {
		this.point=point;
	}

	public void setClass_num(String class_num) {
		this.class_num=class_num;
	}
}