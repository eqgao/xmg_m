package com._520it.crm.domain;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class Systemlog {
    private Long id;

    private Employee opuser;

    private Date optime;

    private String opip;

    private String function;

    private String params;


}