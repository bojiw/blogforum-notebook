/**
 * Alipay.com Inc.
 * Copyright (c) 2004-2018 All Rights Reserved.
 */
package com.blogforum.notebook.web.controller;

import com.blogforum.common.tools.blogforumResult;
import com.blogforum.notebook.pojo.entity.NoteBody;
import com.blogforum.notebook.pojo.entity.NoteTitle;
import com.blogforum.notebook.service.note.NoteBodyService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 *
 * @author wangwendi
 * @version $Id: TestController.java, v 0.1 2018年12月13日 上午11:05 wangwendi Exp $
 */

@Controller
@RequestMapping("test")
public class TestController {

    private final static Logger LOGGER = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private NoteBodyService noteBodyService;

    @RequestMapping(value = "/updatenode")
    @ResponseBody
    public blogforumResult updatenode(NoteTitle noteTitle, HttpServletRequest request) {

        List<NoteBody> noteBodies = noteBodyService.queryList(new NoteBody());
        for (NoteBody noteBody : noteBodies){
            String noteBody1 = noteBody.getNoteBody();
            if (StringUtils.isNotEmpty(noteBody1)){
                String newNoteBody = noteBody1.replaceAll("ouqhxmwfh.bkt.clouddn.com", "qiniu.bojiw.com");
                noteBody.setNoteBody(newNoteBody);
            }
            String mdNoteBody = noteBody.getMdNoteBody();
            if (StringUtils.isNotEmpty(mdNoteBody)){
                String newMdNoteBody = mdNoteBody.replaceAll("ouqhxmwfh.bkt.clouddn.com", "qiniu.bojiw.com");
                noteBody.setMdNoteBody(newMdNoteBody);
            }
            noteBodyService.update(noteBody);
        }
        return blogforumResult.ok(noteBodies.size());
    }

}