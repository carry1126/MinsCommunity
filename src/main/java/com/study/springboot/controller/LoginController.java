package com.study.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
	
	//static Logger log = LogManager.getLogger();
	
	//@RquestMapping("/") redirect 사용시 문제발생!
	@RequestMapping("/login")
	public ModelAndView loginPage(HttpServletRequest req, HttpServletResponse res) throws Exception{
		ModelAndView mv = new ModelAndView();
		String msg = "";
		if(req.getSession().getAttribute("loginInfo") != null) {
			//로그인 되었을때 로그인 정보 가져오는 것 처리!
			String id = (String) req.getSession().getAttribute("id");
			List<Map<String, Object>> writeList = boardService.writeList(id);
			if(!writeList.isEmpty()) {
			  mv.addObject("id", id);
			  mv.addObject("list", writeList);
			}			
			msg= "이미 로그인된 상태입니다.";
			mv.addObject("msg", msg);
			mv.setViewName("list");
		}else {
			msg= "로그인 하시겠습니까?";
			mv.addObject("msg", msg);
			mv.setViewName("login");
		}
		return mv;	
	}
	
	@RequestMapping("/logout")
	public ModelAndView logoutPage(HttpServletRequest req) throws Exception{
		req.getSession().removeAttribute("loginInfo");
		ModelAndView mv = new ModelAndView("login");
		mv.addObject("msg", "로그아웃 되었습니다.");
		return mv;
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
			  mv.addObject("msg", "로그인에 실패하였습니다.");
			  mv.setViewName("login");
		  }else {
			  req.getSession().setAttribute("loginInfo", result);
			  req.getSession().setAttribute("id", id);
			  req.getSession().setMaxInactiveInterval(60*30);
			  mv.addObject("msg", "로그인에 성공했습니다.");
			  //글 목록 가져오기
			  String user= result.get("id");
			  List<Map<String, Object>> writeList = boardService.writeList(user);
			  if(!writeList.isEmpty()) {
				  mv.addObject("id", user);
				  mv.addObject("list", writeList);
			  }
		  }
		  
		  return mv;
	}


}
