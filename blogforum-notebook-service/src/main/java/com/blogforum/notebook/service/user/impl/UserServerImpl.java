package com.blogforum.notebook.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogforum.notebook.interfaces.sso.UserClient;
import com.blogforum.notebook.service.user.UserServer;

import blogforum.sso.facade.model.UserVO;

@Service("userServer")
public class UserServerImpl implements UserServer {

	@Autowired
	private UserClient userClient;
	
	@Override
	public UserVO getUserByToken(String token) {
		return userClient.getUserByToken(token);
	}

}
