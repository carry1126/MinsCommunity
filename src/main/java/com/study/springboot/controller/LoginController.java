package com.study.springboot.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.study.springboot.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	static Logger log = LogManager.getLogger();
	
	//@RquestMapping("/") redirect 사용시 문제발생!
	@RequestMapping("/login")
	public String loginPage() throws Exception{
		return "/login";	
	}
	
	@RequestMapping(value="/loginValidate", method=RequestMethod.POST)
	@ResponseBody
	public ModelAndView loginValidate(HttpServletRequest req) throws Exception{
		
		  String id = req.getParameter("id"); 
		  String pw = req.getParameter("pw");
		  
		  Map<String, String> data = new HashMap<String, String>(); 
		  data.put("id", id);
		  data.put("pw", pw);
		  
		  HashMap<String, String> result = loginService.validateLogin(data);
		  
		  ModelAndView mv = new ModelAndView("list");
		  if(result.isEmpty()) {
			  mv.setViewName("login");
		  }else {
			  mv.addObject("id", result.get("id"));
		  }
		  
		  return mv;
	}

	@RequestMapping("/list")
	public String listPage(Model model) throws Exception{
		model.addAttribute("id", "m001");
		return "/list";
	}
}
