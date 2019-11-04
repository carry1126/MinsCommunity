package com.study.springboot.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.study.springboot.dto.BoardDto;

public interface BoardService {

	List<BoardDto> writeList(String user) throws Exception;

	HashMap<String, Object> writeDetail(String idx) throws Exception;

	int registerInfo(Map<String, Object> info) throws Exception;

}
