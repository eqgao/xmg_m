package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

@Setter
@Getter
@ToString
public class Goodauditor {
    private Long id;

    private String name;

    private BigDecimal price;

    private Integer number;

    private String unit;

    private String remark;

    private BigDecimal totalprice;

    private Long applierId;

    private Long auditorId;

    private Integer state;

    private Date applierTime;

    private Date audiotrTime;


}