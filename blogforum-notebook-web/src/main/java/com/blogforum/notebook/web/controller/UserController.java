package com.blogforum.notebook.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blogforum.common.tools.blogforumResult;

import blogforum.notebook.service.user.UserServer;
import blogforum.sso.facade.model.UserTO;

@Controller
@RequestMapping("/user")
public class UserController {
	
//	@Autowired
//	private UserServer userServer;
//	
//	@RequestMapping("/get")
//	@ResponseBody
//	public blogforumResult get(){
//		//生成七牛云上传token
//		UserTO token = userServer.isLogin("1");
//		return blogforumResult.ok(token);
//	}
	

}
