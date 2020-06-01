package com.biz.lession.vo;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;



@Getter
@Setter

@ToString
public class SubjectVo implements Serializable{


    private  Integer subjectId;

    private String subjectName;


    private Integer stuId;

    public SubjectVo(Integer subjectId, String subjectName, Integer stuId) {
        this.subjectId = subjectId;
        this.subjectName = subjectName;
        this.stuId = stuId;
    }

    public SubjectVo() {
    }
}
