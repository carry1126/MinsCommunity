package com.study.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.study.springboot.dto.BoardDto;
import com.study.springboot.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	//private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	@RequestMapping("/list")
	public String listPage(Model model, HttpServletRequest req) throws Exception{
		String id = req.getParameter("id");
		List<BoardDto> list = boardService.writeList(id);
		model.addAttribute("id", id);
		model.addAttribute("list", list);
		return "list";
	}
	
	@RequestMapping("/writeDetail")
	public String writeDetail(Model model, HttpServletRequest req) throws Exception{
		String name = req.getParameter("user");
		String idx = req.getParameter("idx");
		String mode = req.getParameter("mode");
		//logger.info("idx : " + idx);
		log.info("name : " + name);
		log.info("idx : " + idx);
		log.info("mode : " + mode);
		Map<String, Object> result = new HashMap<>();
		result.put("mode", mode);
		if(idx != null) {
			result = boardService.writeDetail(idx); 
			result.put("mode", mode);
			result.put("writer", name);
			log.info("result : " + result);
		}else {
			result.put("writer", name);
		}
		model.addAttribute("writeInfo", result);
		return "/writeDetail";
	}
	
	@RequestMapping("/registerInfo")
	public ModelAndView registerInfo(HttpServletRequest req) throws Exception{

		String title = req.getParameter("subject");
		String writer = req.getParameter("writer");
		String content = req.getParameter("content");
		
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("title", title);
		info.put("writer", writer);
		info.put("content", content);	
		
		int result = boardService.registerInfo(info);
		ModelAndView mv = new ModelAndView("list");
		if(result == 1) {
			//getListInfo(mv, writer);
		}
		
		//사용자 id확인
		return mv;
	}
	
	//리팩토링 처리
	private void getListInfo(ModelAndView mv, String id) throws Exception {
		List<BoardDto> writeList = boardService.writeList(id);
		if(!writeList.isEmpty()) {
			mv.addObject("id", id);
			mv.addObject("list", writeList);
		}
	}	
}
