package com._520it.crm.service.impl;

import com._520it.crm.domain.Accountflow;
import com._520it.crm.domain.Student;
import com._520it.crm.mapper.AccountflowMapper;
import com._520it.crm.service.IAccountFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by hasee on 2017/11/7.
 */
@Service
@Transactional
public class AccountFlowServiceImpl implements IAccountFlowService {
    @Autowired
    private AccountflowMapper accountflowMapper;

    @Override
    public void studentfee(Student e, BigDecimal money, BigDecimal remainMoney) {
        Accountflow accountflow = new Accountflow();
        accountflow.setActionmoney(money);
        accountflow.setActiontime(new Date());
        accountflow.setNote("社员" + e.getName() + "交费：" + money + "元");
        accountflow.setRemainmoney(remainMoney);
        accountflowMapper.insert(accountflow);
    }
}
