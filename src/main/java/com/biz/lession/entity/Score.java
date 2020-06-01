package com.biz.lession.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table
@Getter
@Setter
public class Score implements Serializable {

    //成绩表id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,length = 11,columnDefinition="int default 0")
    private Integer scoreId=0;

    //成绩
    @Column(length = 11)
    private Integer scores;

    //状态
    @Column(name = "status",columnDefinition = "bit(1) default 1")
    private Boolean status=true;

    //学生对象
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stu_id",nullable = false)
    private Student student;

    //学科对象
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id",nullable = false)
    private Subject subject;
}
