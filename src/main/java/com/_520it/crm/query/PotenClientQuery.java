package com._520it.crm.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PotenClientQuery extends QueryObject {
    private String keyword;

    //查询已入客户池的潜在学员
    private Long id = 18L;
    public String getKeyword() {
        return empty4null(keyword);
    }
}
