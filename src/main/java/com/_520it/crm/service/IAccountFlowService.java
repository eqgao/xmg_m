package com._520it.crm.service;

import com._520it.crm.domain.Student;

import java.math.BigDecimal;

/**
 * Created by hasee on 2017/11/7.
 */
public interface IAccountFlowService {
    /**
     * 正式社员交费
     */
    public void studentfee(Student e, BigDecimal money, BigDecimal remainMoney);


}
