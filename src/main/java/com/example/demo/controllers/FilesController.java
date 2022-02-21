package com.example.demo.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

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
@RequestMapping("files") //Me auth thn klassh ginetai routing sto /files
public class FilesController {
	//String folderPath = "C:\\Users\\User\\eclipse-workspace\\SpringProjects\\Katanemimena\\src\\main\\resources\\static\\Files\\";
	String folderPath=System.getProperty("user.dir")+"\\src\\main\\resources\\static\\Files\\"; //to folder path tou project
	
	/*
	@RequestMapping(value = "/downloads")	//Me auth thn synarthsh ginetai routing sto /files/downloads
	public String showFiles(Model model) {
		System.out.println(folderPath);
		File folder =new File(folderPath);
		File [] file_list=folder.listFiles();
		model.addAttribute("file_list",file_list);
		return "showFiles"; //epistrefei html template pou onomazetai showfiles.html
	}
		*/
	@RequestMapping(value = "/downloads")	//Me auth thn synarthsh ginetai routing sto /files/downloads
	@ResponseBody
	public ArrayList<String> getStudentFiles(@RequestParam("student") String st) {
		System.out.println(folderPath);
		File folder =new File(folderPath);
		File [] file_list=folder.listFiles();
		ArrayList<String> stu_siles=new ArrayList<String>();
		for(int i=0;i<file_list.length;i++) {
			String[] filename_list=file_list[i].getName().split("_");
			if(filename_list.length>=3) {
				if(filename_list[1].equals(st)) {
					
					stu_siles.add(file_list[i].getName());
				}
			
			}
		}
		
		return stu_siles; //epistrefei html template pou onomazetai showfiles.html
	}
	
	@RequestMapping(value = "/downloads/{filename}")	// Se auth th synartisi kanoume download ta files
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
	@RequestMapping(value = "/uploads")	 //Kanoume routing sto /files/uploads kai epistrefei uploadfiles.html
	public String uploadFiles(Model model) {//stelnei to html	
		return "uploadFiles";
	}
	
	@RequestMapping(value = "/upload")	// Files/upload gia anevasma arxeiwn kai epistrefei .html gia na doume an anevhkan ta arxeia
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
	@RequestMapping(value = "/upload2")	// Files/upload gia anevasma arxeiwn kai epistrefei .html gia na doume an anevhkan ta arxeia
	@ResponseBody
	public boolean uploadFiles(@RequestParam("files") MultipartFile[] files,@RequestParam("professor") String pro,@RequestParam("student") String stu) {//anebazeiga arxeia
		StringBuilder filenames = new StringBuilder();
		boolean up=false;
		System.out.println(pro+" " +stu);
		for(MultipartFile file : files) {
			Path filenamePath= Paths.get( folderPath+stu+"_"+pro+"_"+file.getOriginalFilename());
			filenames.append(stu+"_"+pro+"_"+file.getOriginalFilename()+" ");
			try {
				Files.write(filenamePath, file.getBytes());
				up =true;
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				up= false;
			}
			
		}
		return up;
		
	}
}
