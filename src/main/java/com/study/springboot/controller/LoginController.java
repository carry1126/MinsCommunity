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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.study.springboot.dto.BoardDto;
import com.study.springboot.service.BoardService;
import com.study.springboot.service.LoginService;
import com.study.springboot.util.Pagination;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	@Autowired
	private BoardService boardService;
	
	//static Logger log = LogManager.getLogger();
	
	//@RquestMapping("/") redirect 사용시 문제발생!
	@RequestMapping("/login")
	public ModelAndView loginPage(@RequestParam(defaultValue="1") int curPage, HttpServletRequest req, HttpServletResponse res) throws Exception{
		ModelAndView mv = new ModelAndView();
		String msg = "";
		if(req.getSession().getAttribute("loginInfo") != null) {
			//로그인 되었을때 로그인 정보 가져오는 것 처리!
			String id = (String) req.getSession().getAttribute("id");
			//전체 글개수 구하기
			int listCnt = boardService.selectWriteListCnt();
			Pagination pagination = new Pagination(listCnt, curPage);
			int startIndex = pagination.getStartIndex() + 1;
			int endIndex = startIndex + pagination.getPageSize() -1;				
			mv.addObject("pagination", pagination);
			//getListInfo(mv, id);
			HashMap hm = new HashMap();
			hm.put("id", id);
			hm.put("startIndex", startIndex);
			hm.put("endIndex", endIndex);
//			List<BoardDto> writeList = boardService.writeList(id);
			List<BoardDto> writeList = boardService.writeListPage(hm);
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
	public ModelAndView loginValidate(@RequestParam(defaultValue="1") int curPage, HttpServletRequest req) throws Exception{
		
		  String id = req.getParameter("id"); 
		  String pw = req.getParameter("pw");
		  log.info("loginValidate id : " + id);
		  log.info("loginValidate pw : " + pw);
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
			  //getListInfo(mv, user);
			  try {
				  //전체 글개수 구하기
				  int listCnt = boardService.selectWriteListCnt();
				  Pagination pagination = new Pagination(listCnt, curPage);
				  int startIndex = pagination.getStartIndex() + 1;
				  int endIndex = startIndex + pagination.getPageSize() -1;				  
				  mv.addObject("pagination", pagination);
				  //getListInfo(mv, id);
				  HashMap hm = new HashMap();
				  hm.put("id", id);
				  hm.put("startIndex", startIndex);
				  hm.put("endIndex", endIndex);		  
				 List<BoardDto> writeList = boardService.writeListPage(hm);
				 if(!writeList.isEmpty()) {
					mv.addObject("id", id);
				 	mv.addObject("list", writeList);
				 }
			  }catch(Exception e) {
				  e.printStackTrace();
			  }
		  }
		  return mv;
	}
}
