package com.blogforum.notebook.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blogforum.notebook.web.constant.ViewConstant;

/**
 * 首页
 * @author wwd
 *
 */
@Controller
public class IndexController {
	
	@RequestMapping("/")
	public String index(){
		return ViewConstant.INDEX;
	}

}
