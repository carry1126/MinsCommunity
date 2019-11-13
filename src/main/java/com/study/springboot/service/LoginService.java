package com.study.springboot.service;

import java.util.HashMap;
import java.util.Map;

public interface LoginService {
	HashMap validateLogin(HashMap<String, String> data);
}
