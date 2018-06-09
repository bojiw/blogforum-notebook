package com.blogforum.notebook.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.blogforum.common.tools.blogforumResult;
import com.blogforum.notebook.interfaces.docking.QiNiuYunFacadeClient;

/**
 * 七牛云存储类
 * @author wwd
 *
 */
@Controller
@RequestMapping("/qiniuyun")
public class QiNiuYunController {
	@Autowired
	private QiNiuYunFacadeClient qiNiuYunFacadeClient;
	
	@RequestMapping("/upload")
	@ResponseBody
	public blogforumResult upload(){
		//生成七牛云上传token
		return blogforumResult.ok(qiNiuYunFacadeClient.getUpToken());
	}

	


}
