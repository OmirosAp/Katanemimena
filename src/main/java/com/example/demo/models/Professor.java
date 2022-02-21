package com.example.demo.models;

import java.util.ArrayList;
//h classh elegxou tou professor

public class Professor extends User{

	ArrayList <String> courses = new ArrayList <String>();

	public Professor(String username, String password, String name, String lastname) {
		super(username, password, name, lastname,"Professor");
		
	}
	public Professor() {
		
	}

		
	public Professor(String username, String password, String name, String lastname,String role) {
		super(username, password, name, lastname,role);
		
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
