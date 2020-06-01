package com.biz.lession.service.impl;

import com.biz.lession.dao.GradeRepository;
import com.biz.lession.dao.StudentRepository;
import com.biz.lession.dao.SubjectRepository;
import com.biz.lession.entity.Grade;
import com.biz.lession.entity.Student;
import com.biz.lession.entity.Subject;
import com.biz.lession.service.GradeService;
import com.biz.lession.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectServiceImpl implements SubjectService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private SubjectRepository subjectRepository;


    /**
     * 分页查询
     * @param page
     * @param
     * @return
     */
    @Override
    public List<Subject> selectAll(Page<Subject> page) {

        List<Subject> content = page.getContent();

        List<Subject> list=new ArrayList<>();
        for (Subject subject : content) {
            Integer num = subjectRepository.findNum(subject.getSubjectId());
            subject.setSubjectNum(num);
            Integer sumScores = subjectRepository.findSumScores(subject.getSubjectId());
            if (sumScores!=null)
                subject.setAvgScores(sumScores/num);
            else {
                subject.setAvgScores(0);
            }
            list.add(subject);
        }
        return list;
    }

//    @Override
//    public List<Subject> selectAll(Page<Subject> page) {
//        return null;
//    }

    /**
     * 修改学科信息
     * @param
     * @return
     */
    @Override
    public int updateSubject(String subjectName,Integer subjectId) {
        return subjectRepository.updateSubject(subjectName,subjectId);
    }


    /**
     * 添加学科信息
     * @param subject
     * @return
     */
    @Override
    public Subject insertSubject(Subject subject) {
        return subjectRepository.save(subject);
    }

    /**
     * 查询是否有学生引用subject
     * @param subjectId
     * @return
     */
    @Override
    public boolean findStuIdScore(Integer subjectId) {
       Integer stuIdScore = subjectRepository.findStuIdScore(subjectId);
        if (stuIdScore>0){
            return false;  //被引用
        }
        return true; //未被引用
    }

    /**
     * 删除学科
     * @param subjectId
     */
    public void deleteSubject(Integer subjectId){

        subjectRepository.deleteById(subjectId);
    }

    @Override
    public Page<Subject> getmyPage(int page, int limit) {
//        Sort sort=new Sort(Sort.Direction.ASC,"");
        Pageable pageable=new PageRequest(page-1,limit);
        return subjectRepository.findAll(pageable);
    }

    @Override
    public Long selectcount(Page<Subject> page) {
        return page.getTotalElements();
    }

}
