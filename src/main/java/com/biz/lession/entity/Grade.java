package com.biz.lession.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter

public class Grade  implements Serializable{
//    private static final long serialVersionUID = -1L;

    //班级表id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,length = 11)
    private Integer gradeId;

    //班级名称
    @Column(nullable = false,length = 20)
    private String gradeName;

    //状态
    @Column(name = "status",columnDefinition = "bit(1) default 1")
    private Boolean status=true;

    //班级学生数量
    private Integer gradeNum;

    //班级平均分
    private Integer avgScores;
//    //班级和学生是一对多的关系
//    @OneToMany(mappedBy = "grade",fetch = FetchType.LAZY)
//    private List<Student> studentList=new ArrayList<>();


}
