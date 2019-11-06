package com.study.springboot.dto;

import java.util.Date;

import lombok.Data;

@Data
public class BoardDto {
	private int rnum;
	private int idx;
	private String writer;
	private String subject;
	private String content;
	private Date reg_date;
}
