package blogforum.notebook.interfaces.sso.impl;

import blogforum.notebook.interfaces.sso.UserClient;
import blogforum.sso.facade.model.UserTO;
import blogforum.sso.facade.user.UserServerFacade;

public class UserClientImpl implements UserClient {
	
	private UserServerFacade userServerFacade;
	
	@Override
	public UserTO isLogin(String token) {
		return userServerFacade.isLogin(token);
	}


	public void setUserServerFacade(UserServerFacade userServerFacade) {
		this.userServerFacade = userServerFacade;
	}

}
