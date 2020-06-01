package com.biz.lession.service;

import com.biz.lession.entity.Grade;
import com.biz.lession.entity.Student;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GradeService {

    /**分页查询班级信息
     *
     * @return
     */
    List<Grade> findAll(Page<Grade> page);


    /**
     * 查询总记录数
     * @return
     */
    Long selectCount(Page<Grade> page);

    /**
     * 修改班级信息
     * @param gradeName
     * @param gradeId
     * @return
     */
    Integer updateGrade(String gradeName,Integer gradeId);

    /**
     * 添加班级信息
     * @param grade
     * @return
     */
    Grade insertGrade(Grade grade);


    /**
     * 判断grade是否被引用
     * @param gradeIds
     * @return
     */
    boolean isGradeExit(Integer gradeIds);

    /**
     * 批量删除grade
     * @param gradeIds
     */
     void deleteGrade(Integer gradeIds);

     Page<Grade> getmyPage(int page,int limit);
}
