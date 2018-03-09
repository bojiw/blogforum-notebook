package com.blogforum.notebook.pojo.vo;

import java.util.Date;

public class NoteDateIn {

	/**
	 * 开始时间
	 */
	Date	startDate;
	/**
	 * 结束时间
	 */
	Date	endDate;
	/**
	 * 是否删除 默认不删除
	 */
	String	delFlag = "N";

	public NoteDateIn() {
	}

	public NoteDateIn(Date startDate, Date endDate, String delFlag) {
		this.endDate = endDate;
		this.startDate = startDate;
		this.delFlag = delFlag;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(String delFlag) {
		this.delFlag = delFlag;
	}

}
