package com.example.demo.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.models.Professor;

@Controller
@RequestMapping("files")
public class FilesController {
	//String folderPath = "C:\\Users\\User\\eclipse-workspace\\SpringProjects\\Katanemimena\\src\\main\\resources\\static\\Files\\";
	String folderPath=System.getProperty("user.dir")+"\\src\\main\\resources\\static\\Files\\";
	@RequestMapping(value = "/downloads")	
	public String showFiles(Model model) {
		System.out.println(folderPath);
		File folder =new File(folderPath);
		File [] file_list=folder.listFiles();
		model.addAttribute("file_list",file_list);
		return "showFiles";
	}
		
	@RequestMapping(value = "/downloads/{filename}")	
	public void downLoadFile(@PathVariable("filename") String filename, HttpServletResponse response) {
		
		if(filename.indexOf(".pdf")>-1) {
			response.setContentType("application/pdf");
		}
		
		response.setHeader("Content-Disposition", "attachment: filename="+ filename);
		response.setHeader("Content-Transfer-Encoding", "binary");
		try {
			BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
			FileInputStream in = new FileInputStream(folderPath+ filename);
			int len;
			byte[] buff = new byte[1024];
			while((len=in.read(buff))>0) {
				out.write(buff,0,len);
			}
			out.close();
			response.flushBuffer();
		}catch(IOException error) {
			error.printStackTrace();
		}
	}
	@RequestMapping(value = "/uploads")	
	public String uploadFiles(Model model) {//stelnei to html	
		return "uploadFiles";
	}
	
	@RequestMapping(value = "/upload")	
	public String uploadFiles(Model model,@RequestParam("files") MultipartFile[] files) {//anebazeiga arxeia
		StringBuilder filenames = new StringBuilder();
		for(MultipartFile file : files) {
			Path filenamePath= Paths.get( folderPath+file.getOriginalFilename());
			filenames.append(file.getOriginalFilename()+" ");
			try {
				Files.write(filenamePath, file.getBytes());
				model.addAttribute("msg","Succesfully uploaded files "+filenames.toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				model.addAttribute("msg","Uploaded files "+filenames.toString()+" failed");
			}
			
		}
		return "uploadStatus";
	}
	
}
