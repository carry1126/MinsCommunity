package com.study.springboot.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.study.springboot.dao.IBoardDao;

@Service
public class BoardServiceImpl implements BoardService{
	@Autowired
	private IBoardDao boardDao;

	@Override
	public List<Map<String, Object>> writeList(String user) throws Exception{
		
		List<Map<String, Object>> result = boardDao.writeList(user);
		return result;
	}

}
