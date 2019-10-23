package com.study.springboot.service;

import java.util.List;
import java.util.Map;

public interface BoardService {

	List<Map<String, Object>> writeList(String user) throws Exception;

}
