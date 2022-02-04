package com.example.demo.models;

public class Student extends User {
	

	private int semester;
	private int am;
	
	public Student(String username, String password, String name, String lastname, int semester, int am) {
		super(username, password, name, lastname);
		this.semester = semester;
		this.am = am;
	}
	public Student(String username, String password, String name, String lastname) {
		super(username, password, name, lastname);
		
	}


	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public int getAm() {
		return am;
	}

	public void setAm(int am) {
		this.am = am;
	}

	@Override
	public String toString() {
		return super.toString()+ " Student [semester=" + semester + ", am=" + am + "]";
	}
	
	public  boolean add_student_to_db() {
		boolean added=SQLConnect.add_student(super.getUsername(),super.getPassword(),super.getName(),super.getLastname(),semester,am);
		return added;
		
	}
	
	
	
	
	
	

}
