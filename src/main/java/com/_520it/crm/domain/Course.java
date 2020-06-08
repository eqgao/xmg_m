package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

//课程管理
@Getter@Setter@ToString
public class Course {
    private Long id;
    //上课时间
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date schooltime;
    //课程名称
    private String name;

    private Boolean status;
    //备注
    private String remark;

    //讲师
    private Employee lecturer;

    //班主任
    private Employee teacher;

    //班级
    private Classinfo classinfo;

    //教室
    private ClassRoom classRoom;
}