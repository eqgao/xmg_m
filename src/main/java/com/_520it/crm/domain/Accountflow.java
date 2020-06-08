package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
@Setter
@Getter
@ToString
public class Accountflow {
    private Long id;

    private BigDecimal remainmoney;

    private BigDecimal actionmoney;

    private String note;

    private Date actiontime;


}