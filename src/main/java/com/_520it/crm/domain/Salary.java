package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
@Setter
@Getter
@ToString
public class Salary {

    private Long id;

    private Employee employee;

    private String card;
    /**
     * salary:工资
     */
    private BigDecimal salary;

    private boolean status;

    private String remark;


}