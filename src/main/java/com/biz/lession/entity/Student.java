package com.biz.lession.entity;



import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javafx.scene.control.DatePicker;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
public class Student implements Serializable {
//    private static final long serialVersionUID = 1L;

    //学生表id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stu_id",nullable = false,length = 11)
    private Integer stuId;

    //学号
    @Column(nullable = false,length = 10)
    private Integer stuNum;

    //姓名
    @Column(nullable = false,length = 20)
    private String stuName;

    //照片
    @Column(nullable = true,length = 50)
    private String stuImgUrl;

    //性别
    @Column(nullable = false,length = 2)
    private String stuSex;

    //出生日期
    @Column(nullable = false)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Timestamp stuBirth;


    //状态
    @Column(name = "status",columnDefinition = "bit(1) default 1")
    private Boolean status=true;

    //学生和班级是多对一的关系
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "grade_id", nullable = false)
    private Grade grade;

    //学生和学科是多对多
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name="score",
            inverseJoinColumns=@JoinColumn(name="subject_id"),
            joinColumns=@JoinColumn(name="stu_id")
    )
    private Set<Subject> subjectSet=new HashSet<>();

//    平均分
    private Integer avgScores;

    //学科数
    private Integer subjectNum;

    //班级id
    private Integer gId;


    //开始日期
    private Timestamp beginTime;

    //结束日期
    private Timestamp overTime;
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
