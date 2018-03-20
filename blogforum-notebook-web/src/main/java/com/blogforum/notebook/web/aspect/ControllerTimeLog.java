package com.blogforum.notebook.web.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSON;
import com.blogforum.common.tools.IpUtils;
import com.blogforum.common.tools.LoggerUtil;

/**
 * controller接口调用时间和入参出参打印
 * @author wwd
 *
 */
@Aspect
public class ControllerTimeLog {
	private final static Logger LOGGER =  LoggerFactory.getLogger(ControllerTimeLog.class);

	

	@Pointcut("execution(* com.blogforum.notebook.web.controller.*.*(..))")
	public void log(){
	}
	
	/**
	 * 获取请求日志
	 */
	@Before("log()")
	public void doBefore(){
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		//获取真实ip
		String ip = IpUtils.getIp(request);
		LoggerUtil.info(LOGGER, "请求ip地址为：{0}",ip);
		LoggerUtil.info(LOGGER, "入参为：{0}",JSON.toJSONString(request.getParameterMap()));
	}
	

	/**
	 * 获取返回日志
	 */
	@AfterReturning(returning = "object",pointcut = "log()")
	public void doAfterReturning(Object object){
		LoggerUtil.info(LOGGER, "返回参数为：{0}",JSON.toJSONString(object));
	}
	

	

}
