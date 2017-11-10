package com.blogforum.notebook.interfaces.sso.impl;



import com.blogforum.common.model.Result;
import com.blogforum.notebook.interfaces.sso.UserClient;
import com.blogforum.notebook.interfaces.utils.BaseInvocation;
import com.blogforum.notebook.interfaces.utils.ServiceTemplate;

import blogforum.sso.facade.model.UserVO;
import blogforum.sso.facade.user.UserServerFacade;

public class UserClientImpl implements UserClient {
	
	private UserServerFacade userServerFacade;
	
	@Override
	public Boolean isLogin(final String token) {
		return ServiceTemplate.invoke(new BaseInvocation<Boolean>() {
			@Override
			public Result<Boolean> execute() {
				return userServerFacade.isLogin(token);
			}
		});
	}

	@Override
	public UserVO getUserByToken(final String token) {
		return ServiceTemplate.invoke(new BaseInvocation<UserVO>() {

			@Override
			public Result<UserVO> execute() {
				return userServerFacade.getUserByToken(token);
			}
		});
	}
	
	public void setUserServerFacade(UserServerFacade userServerFacade) {
		this.userServerFacade = userServerFacade;
	}
}
