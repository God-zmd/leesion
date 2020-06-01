package com.biz.lession.controller;

import com.alibaba.fastjson.JSON;
import com.biz.lession.dao.GradeRepository;
import com.biz.lession.entity.Grade;
import com.biz.lession.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/grade")
public class GradeController {


    @Autowired
    private GradeService gradeService;


    /**
     * 分页查询班级信息
     * @param page
     * @param limit
     * @return
     */
    @RequestMapping("/findAll")
    @ResponseBody
    public Map<String,Object> findAll(int page,int limit){
        Page<Grade> grades = gradeService.getmyPage(page, limit);
        Long count = gradeService.selectCount(grades);
        List<Grade> list = gradeService.findAll(grades);
        Map<String,Object> map=new HashMap<>();
        System.out.println("list的长度："+list.size());
        map.put("code",0);
        map.put("msg","");
        map.put("count",count);
        map.put("data",list);
        return map;
    }


    /**
     * 班级修改
     * @param gradeName
     * @param gradeId
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public String updateGrade(String gradeName,Integer gradeId){
        Integer row=gradeService.updateGrade(gradeName,gradeId);
        if (row>0){
            return "{\"status\":\"success\"}";
        }
        return "{\"status\":\"failed\"}";
    }

    /**
     * 添加班级信息
     * @param grade
     * @return
     */
    @RequestMapping("/insertGrade")
    @ResponseBody
    public String insertGrade(Grade grade){ ;
        Grade grade1= gradeService.insertGrade(grade);
        System.out.println(grade1);
        if (grade1!=null){
            return "{\"status\":\"success\"}";
        }
        return "{\"status\":\"failed\"}";
    }

//

    /**
     * 批量删除grade
     * @param gradeId
     * @return
     */
    @RequestMapping("/deleteGrade")
    @ResponseBody
    public String deleteGrade(@RequestParam("gradeId") Integer gradeId){
        System.out.println("前端返回的gradeid"+gradeId);
        if (gradeService.isGradeExit(gradeId)){
           gradeService.deleteGrade(gradeId);

                return "{\"status\":\"success\"}";

        }
        return "{\"status\":\"failed\"}";


    }


}
