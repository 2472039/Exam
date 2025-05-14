package bean;

public class Class_num implements java.io.Serializable{
	private School school;
	private String class_num;

	public School getSchool(){
		return school;
}
	public String getClass_num(){
		return class_num;
}
	public void setSchool(School school){
		this.school = school;
	}
	public void setClass_num(String class_num){
		this.class_num = class_num;
	}
}