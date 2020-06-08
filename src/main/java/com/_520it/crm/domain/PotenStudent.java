package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@ToString
public class PotenStudent {
    //潜在学员编号
    private Long id;

    //潜在学员姓名
    private String name;

    //潜在学员qq
    private String qq;

    //潜在学员电话
    private String tel;

    //销售人员 (关联员工)
    private Employee employee;

    //最后联系时间
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date lastTime;

    //下次联系时间
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date nextTime;

    //来源 (关联字典)
    private DictionaryItem source;

    //意向程度 (关联字典)
    private DictionaryItem level;

    //学员状态 (关联字典)
    private DictionaryItem status;

    //意向学科 (关联课程表)
    private Course course;


    //备注
    private String remark;

    /*=========================添加学生原信息方便以后统计===========================*/
    private StudentInfo studentInfo;
    /*========================================================================*/
}