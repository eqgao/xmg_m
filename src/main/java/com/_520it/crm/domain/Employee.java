package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 系统员工
 */
@Setter
@Getter
@ToString
public class Employee {
    private Long id;

    private String username;

    private String realname;

    private String password;

    private String tel;

    private String email;

    private Department dept;

    private Long studentId;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date inputtime;

    private Boolean state;

    private Boolean admin;

    //角色集合 role[0].id = 1
    private List<Role> roles = new ArrayList<>();

}