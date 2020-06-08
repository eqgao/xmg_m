package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class DictionaryItem {

    private Long id;

    private String sn;

    private String name;

    private String intro;

    private Dictionary details;


}