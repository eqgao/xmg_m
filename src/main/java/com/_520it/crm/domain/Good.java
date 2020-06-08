package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Good {
    private Long id;

    private String name;

    private Integer num;

    private Long price;
    /**
     * 单位
     */
    private String unit;

}