package com._520it.crm.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SqlConnect {
    private Long id;
    private String ip;
    private String port;
    private String username;
    private String password;
    private String database;
    private String bakName;

}
