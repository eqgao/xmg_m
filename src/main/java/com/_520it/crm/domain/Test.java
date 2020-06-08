package com._520it.crm.domain;


import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Test {
    private Long id;

    private String sn;

    private PotenStudent potenStudent;

    private Course course;

    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date testTime;

    private Double testResult;

   
}