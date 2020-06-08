package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

//班级管理
@Getter@Setter
public class Classinfo {
    private Long id;

    private String name;

    private String sn;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date begintime;
    //备注
    private String remark;

    private Integer status;

    //班主任
    private Employee employee;

    //班级
    private ClassRoom classRoom;

    //总学费
    private BigDecimal totalMoney;
}