package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Setter
@Getter
@ToString
public class Upgrade {
    /**
     * 学生留级
     */
    public static final Integer STILL_GRADE = 0;
    /**
     * 学生升级
     */
    public static final Integer UP_GRADE = 1;

    private Long id;

    private Student student;

    private Employee emp;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date upgradetime;

    private Classinfo classinfo;

    private Integer status = STILL_GRADE;

}