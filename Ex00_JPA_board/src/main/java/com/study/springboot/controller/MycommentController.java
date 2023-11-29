package com.study.springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.study.springboot.service.MycommentService;

@Controller
public class MycommentController {
	@Autowired
	private MycommentService mycomentService;
}
