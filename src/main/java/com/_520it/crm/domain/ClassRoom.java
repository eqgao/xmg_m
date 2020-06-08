package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;

//教室管理
@Getter@Setter
public class ClassRoom {

    private Long id;
    //教室名称
    private String name;
    //教室位置
    private String location;
    //作为数
    private Integer num;

    private Boolean status;
}