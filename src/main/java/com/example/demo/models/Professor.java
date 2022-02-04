package com.example.demo.models;

import java.util.ArrayList;
//port 3306
//username: root, password:huastudents1!
//windows service nane: MySQL80

public class Professor extends User{

	ArrayList <String> courses = new ArrayList <String>();

	public Professor(String username, String password, String name, String lastname) {
		super(username, password, name, lastname);
		
	}

	public ArrayList<String> getCourses() {
		return courses;
	}

	public void setCourses(ArrayList<String> courses) {
		this.courses = courses;
	}

	@Override
	public String toString() { 
		return super.toString() + " Professor [courses=" + courses + "]";
	}
	
	public void addCourse(String course) {
		courses.add(course);
	}
	public  boolean add_professor_to_db() {
		boolean added=SQLConnect.add_professor(super.getUsername(),super.getPassword(),super.getName(),super.getLastname());
		return added;
		
	}
	
}
