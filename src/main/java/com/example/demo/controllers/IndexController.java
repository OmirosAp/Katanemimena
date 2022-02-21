package com.example.demo.controllers;
import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//kanei route sto /home
@Controller
public class IndexController {
	
	@RequestMapping(value = "")	//Me auth thn synarthsh ginetai routing sto /files/downloads
	public String showFiles(Model model) {
		
		return "preview"; //epistrefei html template pou onomazetai showfiles.html
	}
}