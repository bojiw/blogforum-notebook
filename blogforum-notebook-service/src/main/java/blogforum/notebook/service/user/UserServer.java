package blogforum.notebook.service.user;

import blogforum.sso.facade.model.UserTO;

/**
 * 用户接口
 * @author wwd
 *
 */
public interface UserServer {

	/**
	 * 判断用户是否登录
	 * @param token
	 * @return
	 * @author: wwd
	 * @time: 2017年11月4日
	 */
	public UserTO isLogin (String token);
	

}
