package com.poscoict.mysite.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice // advice 가 붙으면 aop 라고 생각하면 됨
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public String ExceptionHandler(Model model, Exception e) {
		// 1. 로깅
		// String errors = e.toString();
		
		StringWriter errors = new StringWriter();
		e.printStackTrace(new PrintWriter(errors));
		System.out.println(errors.toString());
		
		
		// 2. 사과 페이지 ( HTML응답, 정상 종료 )
		model.addAttribute("exception", errors.toString());

		return "error/exception";
	}
}
