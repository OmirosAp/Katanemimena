package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import com.example.demo.models.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.models.SQLConnect;

@Controller
@RequestMapping("login") // h classh kanei routing sto /professors
public class LoginController {
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public User addCourse(@RequestParam(value="username",required=false,defaultValue="nousere") String username,@RequestParam(value="password",required=false,defaultValue="nopass") String password) {
		System.out.println(username+" "+password);
		User exists=SQLConnect.valid_credentials( username, password);
		
		return exists;
		
	}

}
