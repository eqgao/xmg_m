package com._520it.crm.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AjaxResult {
    private boolean success;
    private String msg;

    public AjaxResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

}
