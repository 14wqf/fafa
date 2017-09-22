package com.GG.test.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.GG.test.model.t_user;
import com.GG.test.repository.SetUserRepositroller;

	@Controller
	public class SetUserController {
		@Autowired
		private SetUserRepositroller setUserRepositroller;
		
		@RequestMapping(value="/")
		public String index(Model model){
			
			List<t_user> user=setUserRepositroller.getUser();
			model.addAttribute("pu",user);
			return "index";
		}
		
		@PostMapping(value="/upload")
		public String dupload(@RequestParam("files") List<MultipartFile> files,HttpServletRequest req){
			String realpath = req.getSession().getServletContext().getRealPath("/");
			Path path = Paths.get(realpath);
			for (MultipartFile file : files) {
				Path filename = path.resolve(file.getOriginalFilename());//getOriginalFilename()这个方法是获取上传时的文件名
				try {
					Files.copy(file.getInputStream(), filename);
					t_user user = new t_user();
					
			
					user.setName(file.getOriginalFilename());
					user.setUrl("/upload/"+file.getOriginalFilename());
					setUserRepositroller.save(user);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			return "redirect:/";
		}
}
