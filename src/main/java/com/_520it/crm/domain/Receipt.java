package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
public class Receipt {

    public final static Integer NO_AUDITOR = 0;
    public final static Integer HAS_AUDITOR = 1;

    private Long id;

    private Student stu;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date receipttime;

    private BigDecimal receiptamount;

    private String bills;

    private String receipt;

    private Employee auditor;

    private String remark;

    private Integer status = NO_AUDITOR;

}