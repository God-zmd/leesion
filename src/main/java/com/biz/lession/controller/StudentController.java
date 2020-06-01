package com.biz.lession.controller;

import com.biz.lession.entity.Grade;
import com.biz.lession.entity.Score;
import com.biz.lession.entity.Student;
import com.biz.lession.entity.Subject;
import com.biz.lession.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/student")
public class StudentController {


    @Autowired
    private StudentService studentService;


    /**
     * 分页查询所有学生数据
     * @return
     */
//    @RequestMapping("/selectAll")
//    @ResponseBody
//    public Map<String,Object> selectAll(Integer page, Integer limit){
//        Page<Student> content = studentService.findRecordList(student,page,limit);
//        Integer total = studentService.total(content);
//        List<Student> students = studentService.selectAll(content);
//        Map<String,Object> map=new HashMap<>();
//        map.put("code",0);
//        map.put("msg","");
//        map.put("count",total);
//        map.put("data",students);
//
//        return map;
//    }


    /**
     * 修改学生数据
     * @param
     * @return
     */
    @RequestMapping("/update")
    @ResponseBody
    public String updateStudent(Integer stuId,String stuName,Integer stuNum,String stuSex,String stuBirth){
        System.out.println("fdafasdfdsafasd:"+stuBirth);
        Student student=new Student();

        Date data= null;//将日期字符串转换位date
        try {
            data = new SimpleDateFormat("yyyy-MM-dd").parse(stuBirth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp birth=new Timestamp(data.getTime());
        student.setStuBirth(birth);
        student.setStuNum(stuNum);
        student.setStuName(stuName);
        student.setStuSex(stuSex);
        student.setStuId(stuId);
        studentService.updateStudent(student);

            return "{\"status\":\"success\"}";

    }


    /**
     * 添加学生信息
     * @param
     * @return
     */
//    @RequestParam("stuName") String stuName,@RequestParam("stuNum")Integer stuNum,@RequestParam("stuSex")String stuSex,
    @RequestMapping("/insertStudent")
    @ResponseBody
    public String insertStudent(String stuName,Integer stuNum,String stuSex,String stuBirth,Integer gId){

        Student student=new Student();

        Date data= null;//将日期字符串转换位date
        try {
            data = new SimpleDateFormat("yyyy-MM-dd").parse(stuBirth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Timestamp birth=new Timestamp(data.getTime());
        student.setStuBirth(birth);
        student.setStuNum(stuNum);
        student.setStuName(stuName);
        student.setStuSex(stuSex);
        Grade grade=new Grade();
        grade.setGradeId(gId);
        student.setGrade(grade);
        Student student1 = studentService.insertStudent(student);
        if (student1!=null) {
            return "{\"status\":\"success\"}";
        }
        return "{\"status\":\"failed\"}";
    }


    /**
     * 查询录入分数的学科
     * @param
     * @return
     */
    @RequestMapping("/findScore")
    @ResponseBody
    public List<Subject> findScore(Integer stuId){
        return studentService.findSubject(stuId);
    }

    /**
     * 录入分数
     * @param subjectId
     * @param stuId
     * @param scores
     * @return
     */
    @RequestMapping("/enterScores")
    @ResponseBody
    public String enterScores(Integer subjectId,Integer stuId,Integer scores){
        System.out.println("fdsafadfadsfasf:"+subjectId+"fadfa"+stuId+"daf"+scores);
        Integer integer = studentService.saveScores(subjectId, stuId, scores);
        System.out.println(integer);
        if (integer>0) {
            return "{\"status\":\"success\"}";
        }
        return "{\"status\":\"failed\"}";
    }


    /**
     * 查询未被选择的学科
     * @param
     * @param stuId
     * @return
     */
    @RequestMapping("/selectSubject")
    @ResponseBody
    public List<Subject> selectSubject(Integer stuId){

        List<Subject> list=studentService.findSubjectNotyet(stuId);
       return list;
    }




    /**
     * 下拉框赋值
     * @return
     */
    @RequestMapping("/myGrade")
    @ResponseBody
    public List<Grade> myGrade(){
        List<Grade> list = studentService.selectGrade();
        System.out.println(list.size());
        return list;
    }


    /**
     * 选课
     * @param subjectId
     * @param stuId
     * @param
     * @return
     */
    @RequestMapping("/selectSubjectBystu")
    @ResponseBody
    public String selectSubjectBystu(Integer subjectId,Integer stuId){
        System.out.println("subjectId:"+subjectId+"   stuId  "+stuId);
        Score score = studentService.selecrtSubject(subjectId, stuId);
        if (score!=null) {
            return "{\"status\":\"success\"}";
        }
        return "{\"status\":\"failed\"}";
    }


    /**
     * 删除学生
     * @param stuId
     * @return
     */
    @RequestMapping("/deleteStudent")
    @ResponseBody
    public String deleteStudent(Integer stuId){
        studentService.deleteStudent(stuId);
        return "{\"status\":\"success\"}";
    }


    /**
     * 模糊查询
     * @return
     */
    @RequestMapping("/searchQeury")
    @ResponseBody
    public Map<String,Object> searchQeury(String[] searchNames,int page,int limit){
        System.out.println(searchNames);
        Student student=new Student();
//        String[] strings = searchNames.split("\\.");
        System.out.println("数组的长度："+searchNames.length);
        for (String searchName : searchNames) {
            System.out.println("遍历数组："+searchName);
        }
        Integer i = null;
        if (!"".equals(searchNames[0])){
            i = Integer.parseInt(searchNames[0]);
        }

        Date data= null;//将日期字符串转换位date
        Date data2= null;
        Timestamp birth=null;
        Timestamp birth2=null;
        //不为空才转化
       if (!"".equals(searchNames[1])) {
           try {
               data = new SimpleDateFormat("yyyy-MM-dd").parse(searchNames[1]);
               //将date转换成timestamp
                birth=new Timestamp(data.getTime());

           } catch (ParseException e) {
               e.printStackTrace();
           }

       }
       if (!"".equals(searchNames[2])){
           try {
               data2 = new SimpleDateFormat("yyyy-MM-dd").parse(searchNames[2]);
                birth2=new Timestamp(data2.getTime());

           } catch (ParseException e) {
               e.printStackTrace();
           }


       }
        student.setBeginTime(birth);
        student.setOverTime(birth2);
        student.setStuNum(i);
        student.setStuName(searchNames[3]);
        Page<Student> content = studentService.findRecordList(student,page,limit);
        Long total = studentService.total(content);
        List<Student> students = studentService.selectAll(content);
        Map<String,Object> map=new HashMap<>();
        map.put("code",0);
        map.put("msg","");
        map.put("count",total);
        map.put("data",students);
        return map;
    }
}
