package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class StudentInfo {

    /**
     * 表示博士
     */
    public static final Integer EDUCATION_DOCTOR = 1;
    /**
     * 表示硕士
     */
    public static final Integer EDUCATION_MASTER = 2;
    /**
     * 表示本科
     */
    public static final Integer EDUCATION_BENKE = 3;
    /**
     * 表示大专
     */
    public static final Integer EDUCATION_BIGJUNIOR = 4;
    /**
     * 表示中专
     */
    public static final Integer EDUCATION_MIDJUNIOR = 5;
    /**
     * 表示初中
     */
    public static final Integer EDUCATION_JHS = 6;
    /**
     * 表示计算机专业
     */
    public static final Boolean COMPUTER_YES = true;
    /**
     * 表示非计算机专业
     */
    public static final Boolean COMPUTER_NO = false;

    /**
     * 表示男
     */
    public static final Boolean GENDER_YES = true;

    /**
     * 表示女
     */
    public static final Boolean GENDER_NO = false;


    private Long id;
    //年龄
    private Integer age;
    //性别
    private Boolean gender;

    //学校
    private String school;
    //专业
    private String major;
    //学历
    private Integer education;
    // 是否计算机或者信息专业相关
    private Boolean computerOrNot;

    //原职业
    private String profession;
    //原薪资
    private BigDecimal salary;
    //工作城市
    private Address address;

    //头像
    private String image;
}
