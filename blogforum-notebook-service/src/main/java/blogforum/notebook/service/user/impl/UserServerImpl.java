package blogforum.notebook.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blogforum.notebook.interfaces.sso.UserClient;
import blogforum.notebook.service.user.UserServer;
import blogforum.sso.facade.model.UserTO;

@Service
public class UserServerImpl implements UserServer {

	@Autowired
	private UserClient userClient;
	
	@Override
	public UserTO isLogin(String token) {
		return userClient.isLogin(token);
	}

}
