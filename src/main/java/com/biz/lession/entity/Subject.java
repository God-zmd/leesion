package com.biz.lession.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Getter
@Setter
public class Subject implements Serializable {
//    private static final long serialVersionUID = 1L;

    //学科表id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "subject_id",nullable = false,length = 11)
    private  Integer subjectId;

    //学科表名称
    @Column(nullable = false,length = 20)
    private String subjectName;

    //状态
    @Column(name = "status",columnDefinition = "bit(1) default 1")
    private Boolean status=true;


    //学科选修人数
    private Integer subjectNum;

    //学科平均分
    private Integer avgScores;


//    //学科和学生是多对多的关系
//    @ManyToMany(mappedBy = "subjectSet",fetch = FetchType.LAZY)
//    private Set<Student> studentSet=new HashSet<>();
}
