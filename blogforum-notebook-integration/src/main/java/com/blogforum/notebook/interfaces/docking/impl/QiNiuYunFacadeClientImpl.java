package com.blogforum.notebook.interfaces.docking.impl;

import com.blogforum.common.model.Result;
import com.blogforum.common.tools.rpc.BaseInvocation;
import com.blogforum.common.tools.rpc.ServiceTemplate;
import com.blogforum.docking.facade.qiniuyun.QiNiuYunFacade;
import com.blogforum.notebook.interfaces.docking.QiNiuYunFacadeClient;

public class QiNiuYunFacadeClientImpl implements QiNiuYunFacadeClient {

	private QiNiuYunFacade qiNiuYunFacade;
	
	@Override
	public String getUpToken() {
		return ServiceTemplate.invoke(new BaseInvocation<String>() {

			@Override
			public Result<String> execute() {
				return qiNiuYunFacade.getUpToken();
			}
		});
	}

	public void setQiNiuYunFacade(QiNiuYunFacade qiNiuYunFacade) {
		this.qiNiuYunFacade = qiNiuYunFacade;
	}

	
}
