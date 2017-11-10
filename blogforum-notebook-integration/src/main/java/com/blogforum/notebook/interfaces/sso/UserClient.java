package com.blogforum.notebook.interfaces.sso;

import blogforum.sso.facade.model.UserVO;

/**
 * user消费者接口类
 * 
 * @author wwd
 *
 */
public interface UserClient {

	/**
	 * 判断用户是否登录
	 * 
	 * @param token
	 * @return
	 * @author: wwd
	 * @time: 2017年11月4日
	 */
	public Boolean isLogin(String token);

	/**
	 * 根据Token获取用户
	 * 
	 * @param token
	 * @return
	 * @author: wwd
	 * @time: 2017年11月4日
	 */
	public UserVO getUserByToken(String token);

}
