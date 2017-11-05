package blogforum.notebook.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blogforum.notebook.interfaces.sso.UserClient;
import blogforum.notebook.service.user.UserServer;

public class UserServerImpl implements UserServer {


	private UserClient userClient;
	
	@Override
	public Boolean isLogin(String token) {
		return userClient.isLogin(token);
	}


	public void setUserClient(UserClient userClient) {
		this.userClient = userClient;
	}
	

}
