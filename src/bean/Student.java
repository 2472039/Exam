package bean;

public class Student implements java.io.Serializable{
	private String no;
	private String name;
	private int ent_year;
	private String class_num;
	private boolean is_attend;
	private School school;

	public String getNo(){
		return no;
}
	public String getName(){
		return name;
}
	public int getEnt_year(){
		return ent_year;
}
	public School getSchool(){
		return school;
}
	public boolean isAttend(){
		return is_attend;
}
	public String getClass_num(){
		return class_num;
}

	public void setNo(String no){
		this.no = no;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setEnt_year(int ent_year){
		this.ent_year = ent_year;
	}
	public void setSchool(School school){
		this.school = school;
	}
	public void setAttend(boolean is_attend){
		this.is_attend = is_attend;
	}
	public void setClass_num(String class_num){
		this.class_num = class_num;
	}
}