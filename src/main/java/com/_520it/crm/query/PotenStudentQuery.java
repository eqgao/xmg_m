package com._520it.crm.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PotenStudentQuery extends QueryObject {
    private String keyword;

    private Long statusId;

    public String getKeyword() {
        return empty4null(keyword);
    }
}
