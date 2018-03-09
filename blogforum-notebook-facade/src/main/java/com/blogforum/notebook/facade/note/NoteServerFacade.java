package com.blogforum.notebook.facade.note;

import java.util.Date;

import com.blogforum.common.model.Result;
import com.blogforum.notebook.common.enums.IsDelFlagEnum;

/**
 * 笔记服务
 * @author wwd
 *
 */
public interface NoteServerFacade {
	
	
	

	/**
	 * 获取对应状态笔记总数或者所有笔记总数
	 * @param delFlag 如果为空获取所有状态笔记总数
	 * @return
	 * @author: wwd
	 * @time: 2018年3月3日
	 */
	Result<Integer> getAllNoteCount(IsDelFlagEnum delFlag);
	
	
	/**
	 * 获取指定时间段的笔记数
	 * @param staterDate 开始时间
	 * @param endDate 结束时间
	 * @param delFlag 如果为空获取所有在线和删除笔记总数
	 * @return
	 * @author: wwd
	 * @time: 2018年3月3日
	 */
	Result<Integer> getDateInNote(Date startDate,Date endDate,IsDelFlagEnum delFlag);

}
