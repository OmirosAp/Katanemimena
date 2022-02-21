package com.example.demo.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.SQLConnect;
import com.example.demo.models.Student;

//kanei route sto /student
@Controller
@RequestMapping("students")
public class StudentController {

	//add student
	//kanei route sto /addstudent
	@RequestMapping(value = "add", method = RequestMethod.POST)
	@ResponseBody
	public boolean addStudent(@ModelAttribute Student newStudent) {//pairnai apo to post ta data kai dimiourgei enan kainourgio user
		boolean added=newStudent.add_student_to_db();
		
		return added;
		
	}
	
	//remove student
	//kanei route sto /removestudent
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	@ResponseBody
	public boolean deleteStudent(@RequestParam(value="username",required=false,defaultValue="nouser") String username ){
		
		boolean deleted=SQLConnect.delete_student(username);
		return deleted;
		
	}
	//update student
	//kanei route sto /updatestudent
	@RequestMapping(value = "update", method = RequestMethod.POST)
	@ResponseBody
	public boolean updateStudent(@RequestParam(value="username",required=false,defaultValue="nouser") String username, @RequestParam(value="password",required=false, defaultValue="nopassword") String password, @RequestParam(value="semester",required=false,defaultValue="-1") int semester ) {
		boolean updated=false;
		if(!password.equals("nopassword") && semester==-1) {
			updated=SQLConnect.updateStudent(username, password);
		}
		else if(password.equals("nopassword") && semester>-1) {
			updated=SQLConnect.updateStudent(username, semester);
		}
		else if(!password.equals("nopassword") && semester>-1) {
			updated=SQLConnect.updateStudent(username,password, semester);
		}
		return updated;
	}
	
	//get all students
	//kanei route sto /getallstudent
	@RequestMapping(value = "getall", method = RequestMethod.GET)
	@ResponseBody
	public ArrayList <Student> getAll(){
		ArrayList<Student> students = SQLConnect.getAllStudents();
		return students;
	}
}
