package blogforum.notebook.interfaces.sso.impl;

import org.springframework.beans.factory.annotation.Autowired;

import blogforum.notebook.interfaces.sso.UserClient;
import blogforum.sso.facade.facade.user.UserServerFacade;
public class UserClientImpl implements UserClient {
	
	@Autowired
	private UserServerFacade userServerFacade;
	
	@Override
	public Boolean isLogin(String token) {
		return userServerFacade.isLogin(token);
	}


	public void setUserServerFacade(UserServerFacade userServerFacade) {
		this.userServerFacade = userServerFacade;
	}
	
	

}
