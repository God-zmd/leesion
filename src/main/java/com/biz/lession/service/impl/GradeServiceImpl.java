package com.biz.lession.service.impl;

import com.biz.lession.dao.GradeRepository;
import com.biz.lession.dao.StudentRepository;
import com.biz.lession.entity.Grade;
import com.biz.lession.entity.Student;
import com.biz.lession.service.GradeService;
import jdk.nashorn.internal.ir.IdentNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private StudentRepository studentRepository;


//    public List<Grade> findAlls(int page, int limit) {
//
//        Pageable pageable=new PageRequest((page-1)*limit,limit);
//        System.out.println("总记录数："+pageable.getPageSize());
////        Page<Grade> all = gradeRepository.findAll(pageable);
//        List<Grade> content = gradeRepository.findAll(pageable).getContent();
//        return content;
//    }
    /**
     * 分页查询
     * @param
     * @param
     * @return
     */
    @Override
    public List<Grade> findAll(Page<Grade> page) {


//        Page<Grade> all = gradeRepository.findAll(pageable);
        List<Grade> content = page.getContent();
        List<Grade> list=new ArrayList<>();
        for (Grade grade : content) {
            Integer num = gradeRepository.findNum(grade.getGradeId());
            grade.setGradeNum(num);
            List<Integer> stuIds = gradeRepository.findStuId(grade.getGradeId());
            Integer sumScores=null;
            if (stuIds.size()!=0) {
                sumScores = gradeRepository.findSumScoresByStuId(stuIds);
            }
            System.out.println(sumScores);
            if (sumScores!=null)
            grade.setAvgScores(sumScores/num);
            else {
                grade.setAvgScores(0);
            }
            list.add(grade);
        }
        return list;
    }

    /**
     * 查询总记录数
     * @return
     */
    @Override
    public Long selectCount(Page<Grade> page) {

        return page.getTotalElements();
    }


    /**
     * 修改班级信息
     * @param gradeName
     * @param gradeId
     * @return
     */
    @Override
    public Integer updateGrade(String gradeName,Integer gradeId){
        return gradeRepository.updateGrade(gradeName,gradeId);

    }

    /**
     * 添加班级信息
     * @param grade
     * @return
     */
    @Override
    public Grade insertGrade(Grade grade) {
        return gradeRepository.save(grade);
    }

    /**
     * 判断班级是否被引用
     * @param gradeIds
     * @return
     */
    public boolean isGradeExit(Integer gradeIds) {
        Integer num=gradeRepository.findNum(gradeIds);
        System.out.println("查询出来的学生id："+num);
        if (num>0){
            System.out.println("被引用");
            return false;  //被引用则返回false
        }
        System.out.println("未被引用");
        return true;
    }


    /**
     * 批量删除grade
     * @param gradeIds
     */
    @Transactional
    @Override
    public void deleteGrade(Integer gradeIds){
//            Grade grade=new Grade();
//            List<Grade> list=new ArrayList<>();
//            for (Integer gradeId : gradeIds) {
//                grade.setGradeId(gradeId);
//                list.add(grade);
//            }
//        for (Integer gradeId : gradeIds) {
//             gradeRepository.delete(grade);
//        }

        gradeRepository.deleteById(gradeIds);
        System.out.println("删除的行数为:");
//        return integer;

    }

    @Override
    public Page<Grade> getmyPage(int page, int limit) {
//        Sort sort=new Sort(Sort.Direction.ASC,"gradeId");
        Pageable pageable=new PageRequest(page-1,limit);
        return gradeRepository.findAll(pageable);
    }


}
