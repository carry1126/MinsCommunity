package com.study.springboot.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.study.springboot.dto.BoardDto;
import com.study.springboot.service.BoardService;
import com.study.springboot.util.Pagination;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class BoardController {
	
	@Autowired
	private BoardService boardService;

	//private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	//페이징 처리 2019.11.06 MJ
	@RequestMapping("/list")
	public String listPage(@RequestParam String id, @RequestParam(defaultValue="1") int curPage, Model model) throws Exception{
		//List<BoardDto> list = boardService.writeList(id);
		  //전체 글개수 구하기
		  int listCnt = boardService.selectWriteListCnt();
		  Pagination pagination = new Pagination(listCnt, curPage);
		  int startIndex = pagination.getStartIndex() + 1;
		  int endIndex = startIndex + pagination.getPageSize() -1;				  
		  model.addAttribute("pagination", pagination);
		  //getListInfo(mv, id);
		  Map hm = new HashMap();
		  hm.put("id", id);
		  hm.put("startIndex", startIndex);
		  hm.put("endIndex", endIndex);		  
		 List<BoardDto> writeList = boardService.writeListPage(hm);
		if(!writeList.isEmpty()) {
			model.addAttribute("id", id);
			model.addAttribute("list", writeList);
		}		
		return "list";
	}
	
	@RequestMapping("/writeDetail")
	public String writeDetail(Model model, HttpServletRequest req) throws Exception{
		String user = req.getParameter("user");
		String idx = req.getParameter("idx");
		String mode = req.getParameter("mode");
		//logger.info("idx : " + idx);
		log.info("user : " + user);
		log.info("idx : " + idx);
		log.info("mode : " + mode);
		Map<String, Object> result = new HashMap<>();
		model.addAttribute("mode", mode);
		model.addAttribute("user", user);
		if(mode.equals("M")) {
			result = boardService.writeDetail(idx); 
			model.addAttribute("writeInfo", result);
			log.info("result : " + result);
		}
		
		return "writeDetail";
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
			List<BoardDto> writeList = boardService.writeList(writer);
			if(!writeList.isEmpty()) {
				mv.addObject("id", writer);
				mv.addObject("list", writeList);
			}			
		}
		//사용자 id확인
		return mv;
	}
	
	//글수정 2019.11.05 MJ
	@RequestMapping(value="/modifyInfo", method=RequestMethod.POST)
	public ModelAndView modifyInfo(HttpServletRequest req) throws Exception {
		int idx = Integer.parseInt(req.getParameter("idx"));
		String subject = req.getParameter("subject");
		String writer = req.getParameter("writer");
		String content = req.getParameter("content");
		
		Map<String, Object> info = new HashMap<String, Object>();
		info.put("idx", idx);
		info.put("subject", subject);
		info.put("content", content);
		int result = boardService.modifyInfo(info);
		ModelAndView mv = new ModelAndView("list");
		if(result > 0) {
			List<BoardDto> writeList = boardService.writeList(writer);
			if(!writeList.isEmpty()) {
				mv.addObject("id", writer);
				mv.addObject("list", writeList);
			}			
		}
		
		return mv;
	}
	
	//글삭제 2019.11.05 MJ
	@RequestMapping(value="/deleteInfo", method=RequestMethod.GET)
	public String deleteInfo(Model model, HttpServletRequest req) throws Exception {
		String id = req.getParameter("id");
		String delIdx = req.getParameter("delIdx");
		log.info("삭제할 idx : " + delIdx);
		String[] idxArr = delIdx.split("-");
		HashMap hm = new HashMap();
		hm.put("idxArr", idxArr);
		int result = boardService.deleteInfo(hm);
		log.info("삭제 건수 : " + result);
		if(result > 0) {
			List<BoardDto> list = boardService.writeList(id);
			model.addAttribute("id", id);
			model.addAttribute("list", list);			
		}
		return "list";
	}
	
}
