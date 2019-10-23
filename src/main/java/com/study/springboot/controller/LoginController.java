package com.study.springboot.controller;

import java.util.HashMap;
import java.util.List;
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

import com.study.springboot.service.BoardService;
import com.study.springboot.service.LoginService;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	@Autowired
	private BoardService boardService;
	
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
			  //글 목록 가져오기
			  String user= result.get("id");
			  List<Map<String, Object>> writeList = boardService.writeList(user);
			  mv.addObject("id", user);
			  if(!writeList.isEmpty()) {
				  mv.addObject("list", writeList);
			  }
		  }
		  
		  return mv;
	}

	@RequestMapping("/list")
	public String listPage(Model model) throws Exception{
		model.addAttribute("id", "m001");
		return "/list";
	}
}
