package com._520it.crm.domain;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
public class StudentLoss {
    private Long id;
    //学员姓名
    private String studentname;
   //流失班级
    private String classinfoname;
    
    private String qq;

    private String tel;
    //流失时间
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date losttime;
    //经办人
    private String handler;
    //流失原因
    private String cause;

}