package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@ToString
public class Expend {

    public final static Integer STATUS_NO_AUDITOR = 0;
    public final static Integer STATUS_HAS_AUDITOR = 1;

    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date paytime;

    private String item;

    private String bills;

    private BigDecimal amount;

    private Employee operator;

    private Employee auditor;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date auditortime;

    private Integer status = STATUS_NO_AUDITOR;

}