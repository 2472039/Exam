package bean;

public class Class_num implements java.io.Serializable{
	private School school_cd;
	private String class_num;

	public School getSchool_cd(){
		return school_cd;
}
	public String getClass_num(){
		return class_num;
}
	public void setSchool_cd(School school_cd){
		this.school_cd = school_cd;
	}
	public void setClass_num(String class_num){
		this.class_num = class_num;
	}
}