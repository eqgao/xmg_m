package com._520it.crm.listener;

import com._520it.crm.listener.even.StudentFeeComplateEven;
import com._520it.crm.domain.Accountflow;
import com._520it.crm.mapper.AccountflowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by hasee on 2017/11/12.
 */
@Component
@Transactional
public class StudentFeeComplateListener implements ApplicationListener<StudentFeeComplateEven> {

    @Autowired
    private AccountflowMapper accountflowMapper;

    @Override
    public void onApplicationEvent(StudentFeeComplateEven event) {
        StudentFeeComplateEven.StudentFeeFlowObject sffo = event.getSffo();
        Accountflow accountflow = new Accountflow();
        accountflow.setActionmoney(sffo.getMoney());
        accountflow.setActiontime(new Date());
        accountflow.setNote("社员" + sffo.getStu().getName() + "交费：" + sffo.getMoney() + "元");
        accountflow.setRemainmoney(sffo.getRemainMoney());
        accountflowMapper.insert(accountflow);
    }
}
