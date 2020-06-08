package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 地址实体类
 * Created by hasee on 2017/8/30.
 */
@Setter
@Getter
@ToString
public class Address {

    private Long id;
    private String code;
    private String name;
    private String parentCode;
}