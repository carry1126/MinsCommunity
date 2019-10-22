package com.study.springboot.dto;

import lombok.Data;

@Data
public class MemberDto {
	private String memid;
	private String mempw;
	private String memmail;
	private int mempurcnum;
}
