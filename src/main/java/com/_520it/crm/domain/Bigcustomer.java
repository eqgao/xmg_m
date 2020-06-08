package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Getter@Setter
public class Bigcustomer {
    private Long id;

    private String school;

    private String area;

    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date creattime;

    private String tel;

    private String eamil;

    private Employee employee;

    private String linkman;

    private Integer tailafter;

    private String agreeornot;

    private String starlevel;
}