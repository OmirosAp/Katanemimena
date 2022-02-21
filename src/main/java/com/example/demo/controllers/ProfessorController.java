package com.example.demo.controllers;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.models.Professor;
import com.example.demo.models.SQLConnect;
import com.example.demo.models.Student;

@Controller
@RequestMapping("professors") // h classh kanei routing sto /professors
public class ProfessorController {
	//add professor
	
	@RequestMapping(value = "add", method = RequestMethod.POST) // kanei routing sto /professors/add
	@ResponseBody
	public boolean addProfessor(@ModelAttribute Professor newProfessor) {//pairnai apo to post ta data kai dimiourgei enan kainourgio user
		boolean added=newProfessor.add_professor_to_db();
		
		return added;
		
	}
	//add course to professor
	// kanei routing sto /professors/addcourse
	@RequestMapping(value = "addcourse", method = RequestMethod.POST)
	@ResponseBody
	public boolean addCourse(@RequestParam(value="username",required=false,defaultValue="nousere") String username,@RequestParam(value="course",required=false,defaultValue="nocourse") String course) {
		boolean added=SQLConnect.add_course(username, course);
		
		return added;
		
	}
	//remove course
	// kanei routing sto /professors/removecourse
	@RequestMapping(value = "removecourse", method = RequestMethod.POST)
	@ResponseBody
	public boolean removeCourse(@RequestParam(value="username",required=false,defaultValue="nousere") String username,@RequestParam(value="course",required=false,defaultValue="nocourse") String course) {
		boolean removed=SQLConnect.remove_course(username, course);
		
		return removed;
	}
	//remove professor
	// kanei routing sto /professors/removeprofessor
		@RequestMapping(value = "delete", method = RequestMethod.POST)
		@ResponseBody
		public boolean deleteProfessor(@RequestParam(value="username",required=false,defaultValue="nouser") String username ){
			
			boolean deleted=SQLConnect.delete_professor(username);
			return deleted;
			
		}	
		
	
	//update professor
	// kanei routing sto /professors/updateprofessor
		@RequestMapping(value = "update", method = RequestMethod.POST)
		@ResponseBody
		public boolean updateProfessor(@RequestParam(value="username",required=false,defaultValue="nouser") String username, @RequestParam(value="password",required=false, defaultValue="nopassword") String password ) {
			boolean updated=false;
			
			updated=SQLConnect.updateProfessor(username, password);
			
			return updated;
		}
	
	//get all professor
		// kanei routing sto /professors/getallprofessor
		@RequestMapping(value = "getall", method = RequestMethod.GET)
		@ResponseBody
		public ArrayList <Professor> getAll(){
			ArrayList<Professor> professors = SQLConnect.getAllProfessors();
			return professors;
		}

	
}
