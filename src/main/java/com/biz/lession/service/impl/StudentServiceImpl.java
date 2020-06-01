package com.biz.lession.service.impl;

import ch.qos.logback.core.joran.util.beans.BeanUtil;
import com.biz.lession.dao.GradeRepository;
import com.biz.lession.dao.ScoreRepository;
import com.biz.lession.dao.StudentRepository;
import com.biz.lession.dao.SubjectRepository;
import com.biz.lession.entity.Grade;
import com.biz.lession.entity.Score;
import com.biz.lession.entity.Student;
import com.biz.lession.entity.Subject;
import com.biz.lession.service.StudentService;
import com.biz.lession.vo.StudentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    /**
     * 查询学生数据
     * @return
     */
    @Override
    public List<Student> selectAll(Page<Student> page) {

        List<Student> content = page.getContent();
        for (Student student : content) {
            student.setSubjectNum(student.getSubjectSet().size());
        }
        //调用avgScore方法将平均分属性注入到实体中
        return avgScore(content);
    }


    /**
     * 学生平均分
     * @return
     */
    public List<Student> avgScore(List<Student> list){
        for (Student student : list) {
            Integer avgScores = scoreRepository.findAvgScoresByStuId(student.getStuId());
            if (avgScores!=null)
            student.setAvgScores(avgScores);
            else {
                student.setAvgScores(0);
            }
        }
        return list;
    }




    /**
     * 修改学生数据
     * @param student
     * @return
     */
//    @Transactional(propagation = Propagation.REQUIRED)
//    @Override
//    public int updateStudent(Student student) {
//        Student one = studentRepository.getOne(student.getStuId());
//        one.setStuName(student.getStuName());
//        one.setStuNum(student.getStuNum());
//        one.setStuSex(student.getStuSex());
//        one.setStuBirth(student.getStuBirth());
//        Grade grade=new Grade();
//        grade.setGradeId(student.getGId());
//        one.setGrade(grade);
//        return 1;
//    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public void updateStudent(Student student) {
        Student one = studentRepository.getOne(student.getStuId());
        one.setStuName(student.getStuName());
        one.setStuNum(student.getStuNum());
        one.setStuSex(student.getStuSex());
        one.setStuBirth(student.getStuBirth());
//        Grade grade=new Grade();
//        grade.setGradeId(student.getGId());
//        one.setGrade(grade);
    }
    /**
     * 添加学生
     * @param student
     * @return
     */
    @Transactional
    @Override
    public Student insertStudent(Student student){
//        Grade grade=new Grade();
//        grade.setGradeId(student.getGId());
//        student.setGrade(grade);
        Student save = studentRepository.save(student);
        return save;
    }

    /**
     * 查询班级
     * @return
     */
    @Override
    public List<Grade> selectGrade() {
        return gradeRepository.findAll();
    }


    /**
     *查询录入分的学科
     * @param
     * @return
     */
    @Override
    public List<Subject> findSubject(Integer stuId) {
        List<Integer> stuIds = studentRepository.findSubjectId(stuId);
        if (stuIds.size()>0){
            List<Subject> subjectBy = subjectRepository.findSubjectBy(stuIds);
            return subjectBy;
        }
        return null;

    }

    /**
     * 录入分数
     * @param
     */
    @Transactional
    @Override
    public Integer saveScores(Integer subjectId,Integer stuId,Integer scores) {
        System.out.println("ss:"+subjectId+"fads；"+stuId+"fad:"+scores);
        Integer integer = scoreRepository.updateScoreadd(subjectId, stuId, scores);
        System.out.println("fadfdsfa"+integer);
        return integer;
    }

    /**
     * 选课
     * @param subjectId
     * @param stuId
     * @return
     */
    @Override
    public Score selecrtSubject(Integer subjectId, Integer stuId) {
        Subject subject=new Subject();
        subject.setSubjectId(subjectId);
        Student student=new Student();
        student.setStuId(stuId);
        Score score=new Score();
        score.setSubject(subject);
        score.setStudent(student);
        Score save = scoreRepository.save(score);
        return save;
    }

    /**
     *查询未被选择的学科
     * @param stuId
     * @return
     */
    @Override
    public List<Subject> findSubjectNotyet(Integer stuId) {
        List<Integer> subjectIds = studentRepository.findSubjectIds(stuId);
        if(subjectIds.size()>0){
            List<Subject> subjectBy2 = subjectRepository.findSubjectBy2(subjectIds);
            return subjectBy2;
        }else {
            return  subjectRepository.findAll();
        }

    }

    /**
     * 删除学生
     * @param studentId
     */
    @Override
    public void deleteStudent(Integer studentId) {
        studentRepository.deleteById(studentId);
    }


    /**
     * 模糊查询
     * @param student
     * @return
     */
    @Override
    public Page<Student> findRecordList(final Student student,int page,int limit) {

//        Sort sort = new Sort(Sort.Direction.ASC,"stuId");   //根据id排序
        Pageable pageable = new PageRequest(page-1, limit);
      Page<Student> content=studentRepository.findAll(new Specification<Student>() {
           @Override
           public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
               List<Predicate> predicates = new ArrayList<>();
               if (!StringUtils.isEmpty(student.getStuNum())) {
                   predicates.add(cb.like(root.get("stuNum").as(String.class), student.getStuNum() + "%"));
               }
               if (!StringUtils.isEmpty(student.getStuName())) {
                   predicates.add(cb.like(root.get("stuName").as(String.class), "%" + student.getStuName() + "%"));
               }
               if (student.getBeginTime() != null || student.getOverTime() != null) {
                   predicates.add(cb.between(root.get("stuBirth").as(Timestamp.class), student.getBeginTime(), student.getOverTime()));
               }
               cq.where(predicates.toArray(new Predicate[predicates.size()]));
               return null;
           }
       },pageable);
     return content;
    }

    /**
     * 返回总记录数
     * @param page
     * @return
     */
    public Long total(Page<Student> page) {

        return page.getTotalElements();
    }
//
//    @Override
//    public Page<Student> listStudent(final String[] strings) {
//        final Student student=new Student();
//        Integer i = Integer.parseInt(strings[0]);
//        Date data= null;//将日期字符串转换位date
//        Date data2= null;
//        try {
//            data = new SimpleDateFormat("yyyy-MM-dd").parse(strings[1]);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        try {
//            data2 = new SimpleDateFormat("yyyy-MM-dd").parse(strings[2]);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        Timestamp birth=new Timestamp(data.getTime());
//        Timestamp birth2=new Timestamp(data2.getTime());
//        student.setBeginTime(birth);
//        student.setOverTime(birth2);
//        student.setStuNum(i);
//        student.setStuName(strings[3]);
//        return studentRepository.findAll(new Specification<Student>() {
//            @Override
//            public Predicate toPredicate(Root<Student> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
//                List<Predicate> predicates = new ArrayList<>();
//                Integer studentId = student.getStuNum();
//                String studentName = student.getStuName();
//                Date startDate = student.getBeginTime();
//                Date endDate = student.getOverTime();
////                if (!StringUtils.isEmpty(studentId)) {
////                    predicates.add(cb.like(root.get("stuNum"), studentId + "%"));
////                }
//                if (!StringUtils.isEmpty(studentName)) {
//                    predicates.add(cb.like(root.<String>get("stu_name"), "%" + studentName + "%"));
//                }
//                if (startDate != null || endDate != null) {
//                    predicates.add(cb.between(root.get, startDate, endDate));
//                }
//                cq.where(predicates.toArray(new Predicate[predicates.size()]));
//                return null;
//            }
//        }, pageable);;
//    }

    /**
     * 模糊查询
     * @param strings
     * @return
     */
//    @Override
//    public List<Student> searchQeury(String[] strings) {
//        int i = Integer.parseInt(strings[1]);
//        List<Student> allBySearch1 = studentRepository.findAllBySearch1(i,strings[2],strings[3]);
//        for (Student student : allBySearch1) {
//            student.setSubjectNum(student.getSubjectSet().size());
//        }
//        return avgScore(allBySearch1);
//    }


    //
//    /**
//     * 将student取出来的值封装进studentVo中
//     * @param list
//     * @return
//     */
//    public List<StudentVo> change(List<Student> list){
//        List<StudentVo> studentVoList=new ArrayList<>();
//        StudentVo studentVo=new StudentVo();
//        for (Student student : list) {
//            studentVo.setStuId(student.getStuId());
//            studentVo.setStuName(student.getStuName());
//            studentVo.setStuSex(student.getStuSex());
//            studentVo.setStuBirth(student.getStuBirth());
//            studentVo.setGrade(student.getGrade());
//            studentVo.setSubjectSet(student.getSubjectSet());
//        }
//        return studentVoList;
//    }

}
