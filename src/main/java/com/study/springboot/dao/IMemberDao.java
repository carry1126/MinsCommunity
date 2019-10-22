package com.study.springboot.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.study.springboot.dto.MemberDto;

@Mapper
public interface IMemberDao {

	public MemberDto validateLogin(Map<String, String> data);

}
