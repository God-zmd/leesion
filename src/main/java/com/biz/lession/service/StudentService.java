package com.biz.lession.service;

import com.biz.lession.entity.Grade;
import com.biz.lession.entity.Score;
import com.biz.lession.entity.Student;
import com.biz.lession.entity.Subject;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {

    /**
     * 查询学生数据
     */
    List<Student> selectAll(Page<Student> page);


    /**
     * 修改学生数据
     * @param student
     * @return
     */
    void updateStudent(Student student);


    /**
     * 添加学生
     * @param student
     * @return
     */
    Student insertStudent(Student student);

    /**
     * 查询班级
     * @return
     */
    List<Grade> selectGrade();


    /**
     * 查询学科信息
     * @param stuId
     * @return
     */
    List<Subject> findSubject(Integer stuId);


    /**
     * 录入分数
     * @param
     */
    Integer saveScores(Integer subjectId,Integer stuId,Integer scores);

    /**
     * 选课
     * @param subjectId
     * @param stuId
     * @return
     */
    Score selecrtSubject(Integer subjectId,Integer stuId);

    /**
     * 查询学生还未选的课程
     * @param stuId
     * @return
     */
    List<Subject> findSubjectNotyet(Integer stuId);

    /**
     * 删除学生
     * @param studentId
     */
    void deleteStudent(Integer studentId);

    /**
     * 模糊查询
     * @return
     */
      Page<Student> findRecordList(Student student,int page,int limit);

    /**
     * 总记录数
     * @return
     */
    Long total(Page<Student> page);
}
