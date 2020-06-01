package com.biz.lession.vo;



import com.biz.lession.entity.Grade;
import com.biz.lession.entity.Subject;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class StudentVo {


    //学生表id
    private Integer stuId;

    //学号)
    private Integer stuNum;

    //姓名
    private String stuName;

    //照片
    private String stuImgUrl;

    //性别
    private String stuSex;

    //出生日期
    private Timestamp stuBirth;

    //学生和班级是多对一的关系
    private Grade grade;

    //学生和学科是多对多
    private Set<Subject> subjectSet=new HashSet<>();

//    平均分
    private Integer avgScores;

    //学科数
    private Integer subjectNum;

//    @Override
//    public String toString() {
//        return "Student{" +
//                "stuId=" + stuId +
//                ", stuNum=" + stuNum +
//                ", stuName='" + stuName + '\'' +
//                ", stuImgUrl='" + stuImgUrl + '\'' +
//                ", stuSex='" + stuSex + '\'' +
//                ", stuBirth=" + stuBirth +
//                ", grade=" + grade +
//                ", subjectSet=" + subjectSet +
//                '}';
//    }
}
