package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IndexController {
	@RequestMapping(value="")
	@ResponseBody
	public String getIndex() {
		return "Home Page";
	}
}