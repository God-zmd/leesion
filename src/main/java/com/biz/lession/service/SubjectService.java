package com.biz.lession.service;

import com.biz.lession.entity.Student;
import com.biz.lession.entity.Subject;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SubjectService {

    /**
     * 查询学科数据
     */
    List<Subject> selectAll(Page<Subject> page);


    /**
     * 修改学科数据
     * @param
     * @return
     */
    int updateSubject(String subjectName,Integer subjectId);


    /**
     * 添加学科信息
     * @param subject
     * @return
     */
    Subject insertSubject(Subject subject);


    /**
     * 查询是否有学生引用subject
     * @param subjectId
     * @return
     */
    boolean findStuIdScore(Integer subjectId);

    /**
     * 删除学科
     * @param subjectId
     */
    void deleteSubject(Integer subjectId);



    Page<Subject> getmyPage(int page,int limit);

    Long selectcount(Page<Subject> page);



}
