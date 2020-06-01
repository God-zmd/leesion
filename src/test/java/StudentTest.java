import com.biz.lession.MyController;
import com.biz.lession.dao.GradeRepository;
import com.biz.lession.dao.ScoreRepository;
import com.biz.lession.dao.StudentRepository;
import com.biz.lession.dao.SubjectRepository;
import com.biz.lession.entity.Grade;
import com.biz.lession.entity.Score;
import com.biz.lession.entity.Student;
import com.biz.lession.entity.Subject;
import com.biz.lession.service.GradeService;
import com.biz.lession.service.StudentService;
import com.biz.lession.service.SubjectService;
import com.biz.lession.vo.StudentVo;
import com.biz.lession.vo.SubjectVo;
import lombok.AllArgsConstructor;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.criteria.CriteriaBuilder;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MyController.class)
public class StudentTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private GradeRepository gradeRepository;

    @Autowired
    private GradeService gradeService;
    @Autowired
    private SubjectRepository subjectRepository;
    @Autowired
    private SubjectService subjectService;

    @Test
    public void test1(){
        Pageable pageable=new PageRequest(0,1);
        Page<Student> list = studentRepository.findAll(pageable);
        for (Student student : list) {
            System.out.println(student);
        }
    }


    @Test
    public void test(){
        Integer byIdIn = scoreRepository.findAvgScoresByStuId(1);
        System.out.println(byIdIn);
    }

//    @Test
//    public void test3(){
//       Student student=new Student();
//       student.setStuId(1);
//       student.setStuName("NIHAO");
//       student.setStuNum(1611030059);
//       student.setStuSex("女");
//       int row=studentRepository.updateStudet(student);
//        System.out.println(row);
//    }


    @Test
    public void test4(){
//        Pageable pageable=new PageRequest(0,1);
//        Page<Grade> all = gradeRepository.findAll(pageable);
//        Page<Grade> all = gradeRepository.findAll(0, 1);
////        List<Grade> list=all.getContent();
//        System.out.println(all.getContent());
    }

    @Test
    public void test5() {

        int row = gradeRepository.updateGrade("四班", 1);
        System.out.println(row);
    }
//    @Test
//    public void test78() {
//
//        List<Score> subject = studentService.findSubject(1);
//        for (Score score : subject) {
//            System.out.println(score.getStudent().getStuId());
//        }
//
//    }
    @Test
    public void test9() {
        Grade grade=new Grade();
        grade.setGradeName("8班");
        Grade grade1 = gradeService.insertGrade(grade);
//        List<Grade> all = gradeService.findAll(1,10);
//        for (Grade grade2 : all) {
//            System.out.println(grade2);
//        }
        System.out.println(grade);
    }

    @Test
    public void test6() {

        Subject subject=new Subject();
        subject.setSubjectName("马克思主义");
        Subject sub=subjectService.insertSubject(subject);
        System.out.println(sub);
    }

    @Test
    public void test65() {

        Integer subjectId=3;
        Integer stuId=1;
        Integer scores=94;
        Integer integer =studentService.saveScores(subjectId,stuId,scores);
        System.out.println(integer);

    }


    @Test
    public void test658() {
        Grade grade=new Grade();
        grade.setGradeName("集合学");
        Grade save = gradeRepository.save(grade);
        System.out.println(save.getGradeId());
//        List<Student> allBySearch = studentRepository.findAllBySearch(1);
//        for (Student bySearch : allBySearch) {
//            System.out.println(bySearch.getStuId());
//        }



//        List<Integer> gradeIds=new ArrayList<>();
//        gradeIds.add(13);
//        gradeIds.add(14);
//        Grade grade=new Grade();
//        List<Grade> list=new ArrayList<>();
//        for (Integer gradeId : gradeIds) {
//            grade.setGradeId(gradeId);
//            list.add(grade);
//        }

//       gradeService.deleteGrade(gradeIds);



    }
}
