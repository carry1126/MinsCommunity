package com.study.springboot.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.study.springboot.dto.BoardDto;

@Mapper
public interface IBoardDao {

	List<BoardDto> writeList(@Param("writer") String user) throws Exception;
	List<BoardDto> writeListPage(Map hm) throws Exception;
	HashMap<String, Object> writeDetail(@Param("idx") String idx) throws Exception;
	int registerInfo(Map<String, Object> info) throws Exception;
	int modifyInfo(Map<String, Object> info) throws Exception;
	int deleteInfo(HashMap idxArr) throws Exception;
	int selectWriteListCnt() throws Exception;

}
