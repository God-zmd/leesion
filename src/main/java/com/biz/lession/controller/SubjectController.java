package com.biz.lession.controller;

import com.alibaba.fastjson.JSON;
import com.biz.lession.entity.Grade;
import com.biz.lession.entity.Subject;
import com.biz.lession.service.GradeService;
import com.biz.lession.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/subject")
public class SubjectController {


    @Autowired
    private SubjectService subjectService;


    /**
     * 分页查询学科信息
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/findAll")
    @ResponseBody
    public Map<String,Object> findAll(int page,int limit){
        Page<Subject> myPage = subjectService.getmyPage(page, limit);
        List<Subject> list = subjectService.selectAll(myPage);
        Long selectcount = subjectService.selectcount(myPage);
        Map<String,Object> map=new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",selectcount);
        map.put("data",list);
        return map;
    }


    /**
     * 添加学科信息
     * @param subject
     * @return
     */
    @RequestMapping("/insertSubject")
    @ResponseBody
    public String insertSubject(Subject subject){
        Subject subject1 = subjectService.insertSubject(subject);
        if (subject1!=null){
            return "{\"status\":\"success\"}";
        }
        return "{\"status\":\"failed\"}";
    }


    /**
     * 修改学科信息
     * @param subjectName
     * @param subjectId
     * @return
     */
    @RequestMapping("/updateSubject")
    @ResponseBody
    public String updateSubject(String subjectName,Integer subjectId){
        int row=subjectService.updateSubject(subjectName,subjectId);
        if (row>0){
            return "{\"status\":\"success\"}";
        }
        return "{\"status\":\"failed\"}";

    }

    /**
     * 删除学科
     * @param subjectId
     * @return
     */
    @RequestMapping("/deleteSubject")
    @ResponseBody
    public String deleteSubject(Integer subjectId){
        if (subjectService.findStuIdScore(subjectId)){
            subjectService.deleteSubject(subjectId);
            return "{\"status\":\"success\"}";
        }
        return "{\"status\":\"failed\"}";

    }
}
