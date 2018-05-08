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
		String ip1 = request.getHeader("x-forwarded-for");
		String ip2 = request.getHeader("Proxy-Client-IP");
		String ip3 = request.getHeader("WL-Proxy-Client-IP");
		String ip4 = request.getRemoteAddr();
		String ip5 = request.getHeader("X-Forwarded-For");
        String ip6 = request.getHeader("X-Real-IP");
		
		LoggerUtil.info(LOGGER, "请求ip地址为：{0}",ip);
		LoggerUtil.info(LOGGER, "请求ip1地址为：{0}",ip1);
		LoggerUtil.info(LOGGER, "请求ip2地址为：{0}",ip2);
		LoggerUtil.info(LOGGER, "请求ip3地址为：{0}",ip3);
		LoggerUtil.info(LOGGER, "请求ip4地址为：{0}",ip4);
		LoggerUtil.info(LOGGER, "请求ip5地址为：{0}",ip5);
		LoggerUtil.info(LOGGER, "请求ip6地址为：{0}",ip6);
		//LoggerUtil.info(LOGGER, "入参为：{0}",JSON.toJSONString(request.getParameterMap()));
		LoggerUtil.info(LOGGER, "请求路径为：{0}",request.getPathInfo());
	}
	

	/**
	 * 获取返回日志
	 */
	@AfterReturning(returning = "object",pointcut = "log()")
	public void doAfterReturning(Object object){
		//LoggerUtil.info(LOGGER, "返回参数为：{0}",JSON.toJSONString(object));
		LoggerUtil.info(LOGGER, "调用成功",JSON.toJSONString(object));
	}
	

	

}
