package com.study.springboot.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.study.springboot.dao.IMemberDao;
import com.study.springboot.dto.MemberDto;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private IMemberDao memDao;
	
	@Override
	public HashMap validateLogin(Map<String, String> data	) {
		MemberDto result = memDao.validateLogin(data);
		HashMap<String, String> mem = new HashMap<>();
		
		if(result != null) {
			mem.put("id", result.getMemid());
		} 
		return mem;
	}
	
}
