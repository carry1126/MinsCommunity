package com.study.springboot.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.study.springboot.service.BoardService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	//private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	@RequestMapping("/list")
	public String listPage(Model model) throws Exception{
		model.addAttribute("id", "m001");
		return "/list";
	}
	
	@RequestMapping("/writeDetail")
	public String writeDetail(Model model, HttpServletRequest req) throws Exception{
		String idx = req.getParameter("idx");
		String mode = req.getParameter("mode");
		//logger.info("idx : " + idx);
		log.info("idx : " + idx);
		log.info("mode : " + mode);
		HashMap<String, Object> result = boardService.writeDetail(idx);
		result.put("mode", mode);
		log.info("result : " + result);
		model.addAttribute("writeInfo", result);
		return "/writeDetail";
	}
}
