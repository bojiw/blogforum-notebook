package com.blogforum.notebook.web.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import com.alibaba.fastjson.JSONObject;
import com.blogforum.common.tools.CookieUtils;
import com.blogforum.common.tools.LoggerUtil;
import com.blogforum.notebook.service.session.SessionServer;
import com.blogforum.sso.facade.model.UserVO;


public class SessionFilter extends OncePerRequestFilter {

	private static final Logger logger = LoggerFactory.getLogger(SessionFilter.class);    
	
	private SessionServer sessionServer;
	
	/**登录地址*/
	private String	ssoUrl;
	
	/**html页面请求*/
	private String htmlRequest;
	

	public void setSsoUrl(String ssoUrl) {
		this.ssoUrl = ssoUrl;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
						throws ServletException, IOException {
		try {
			//获取cookie中的token
			String token = CookieUtils.getCookie(request, "COOKIE_TOKEN");
			//通过token获取登录的用户
			UserVO user = sessionServer.getUserByToken(token);
			//如果用户为空代表没有登录返回跳转页面
			if (user == null) {
				// 判断是否ajax请求
				if (!(request.getHeader("accept").indexOf("application/json") > -1 || (request
									.getHeader("X-Requested-With") != null && request.getHeader(
														"X-Requested-With").indexOf("XMLHttpRequest") > -1)) || requestAjaxHtml(request)) {
					//页面请求返回跳转提示
					loginAgain(request, response);
				}else {
					ajaxLoginAgain(response);
				}
				
				return ;
			}
			if (logger.isInfoEnabled()) {
				logger.info("成功获取到user:" + user.toString());
			}
			//把查询到的用户保存到request中 方便后面获取
			request.setAttribute("user", user);
		} catch (Exception e) {
			logger.error("sessionFilter用户登录判断过滤器异常:",e);
			loginAgain(request, response);
			return ;
		}
		//业务逻辑不包异常
		filterChain.doFilter(request, response);
		
	}
	
	/**
	 * 如果是ajax请求html页面 则返回true
	 * @param request
	 * @return
	 * @author: wwd
	 * @time: 2018年5月19日
	 */
	private Boolean requestAjaxHtml(HttpServletRequest request){
		if (StringUtils.isEmpty(htmlRequest)) {
			return false;
		}
		String requestURI = request.getRequestURI();
		String[] htmls = htmlRequest.split(",");
		for (String html : htmls) {
			if (StringUtils.equals("/" + html, requestURI)) {
				return true;
			}
		}
		return false;
	}
	
	
	/**
	 * 返回登录页面地址 前端直接跳转
	 * 
	 * @author: wwd
	 * @time: 2018年2月24日
	 */
	private void ajaxLoginAgain(HttpServletResponse response){
		//ajax返回提示
		//设置json格式返回 浏览器以UTF-8格式进行编码
		response.setHeader("Content-type", "application/json;charset=UTF-8");  
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;
		try {
			
			out = response.getWriter();
			Map<String, Object> map = new HashMap<String, Object>(); 
		    
		    map.put("status","702");  
		    map.put("msg","登录已过期，请重新登录"); 
		    JSONObject res = new JSONObject(map);  
			out.print(res);
		} catch (IOException e) {
			LoggerUtil.error(logger, e, "跳转登录页面异常");
		}
	}
	
	/**
	 * 返回用戶為登錄提醒 跳轉到登錄頁面
	 * 
	 * @param request
	 * @param response
	 * @throws IOException
	 * @author wwd
	 * @date 2017年3月12日上午12:50:39
	 * @version V1.0
	 */
	private void loginAgain(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String loginPage = ssoUrl;
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		StringBuilder builder = new StringBuilder();
		// 因为请求直接拦截 浏览器默认是GBK 所以需要设置返回页面的编码为utf-8作统一 防止有些浏览器GBK 有些UTF-8
		builder.append("<html>");
		builder.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />");
		builder.append("<title>Insert title here</title>");
		builder.append("<script>");
		builder.append("alert('网页过期，请重新登录！');");
		builder.append("window.location.href='");
		builder.append(loginPage);
		builder.append("';");
		builder.append("</script>");
		builder.append("</html>");
		out.print(builder.toString());
	}

	public void setSessionServer(SessionServer sessionServer) {
		this.sessionServer = sessionServer;
	}

	public void setHtmlRequest(String htmlRequest) {
		this.htmlRequest = htmlRequest;
	}
	

}
