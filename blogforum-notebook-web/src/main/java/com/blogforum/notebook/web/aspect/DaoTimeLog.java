package com.blogforum.notebook.web.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.blogforum.common.tools.LoggerUtil;

/**
 * 数据库调用日志类
 * 
 * @author wwd
 *
 */
@Aspect
public class DaoTimeLog {
	private final static Logger LOGGER = LoggerFactory.getLogger(DaoTimeLog.class);

	@Pointcut("execution(* com.blogforum.notebook.dao.mapper.*.*(..))")
	public void log() {
	}

	/**
	 * 获取请求日志
	 */
	@Before("log()")
	public void doBefore(JoinPoint point) {
		LoggerUtil.info(LOGGER, "开始查询数据库目标方法为:{0}.{1},入参为{2}",
							point.getSignature().getDeclaringTypeName(), point.getSignature().getName(),
							JSON.toJSONString(point.getArgs()));
	}

	/**
	 * 获取返回日志
	 */
	@AfterReturning(returning = "object", pointcut = "log()")
	public void doAfterReturning(Object object) {
		LoggerUtil.info(LOGGER, "返回参数为：{0}", JSON.toJSONString(object));
	}

}
