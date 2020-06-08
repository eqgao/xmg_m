package com._520it.crm.service.impl;

import com._520it.crm.domain.Account;
import com._520it.crm.domain.Receipt;
import com._520it.crm.domain.Student;
import com._520it.crm.mapper.AccountMapper;
import com._520it.crm.mapper.ReceiptMapper;
import com._520it.crm.mapper.StudentMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IAccountFlowService;
import com._520it.crm.service.IReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by hasee on 2017/8/27.
 */
@Service
@Transactional
public class ReceiptServiceImpl implements IReceiptService {
    @Autowired
    private ReceiptMapper mapper;

    @Autowired
    private StudentMapper stuMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private IAccountFlowService accountFlowService;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Receipt record) {
        return mapper.insert(record);
    }

    @Override
    public Receipt selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Receipt> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Receipt record) {
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult queryPageResult(QueryObject qo) {
        //查询总结果数
        Long count = mapper.queryPageCount(qo);
        if (count == 0) {
            return new PageResult(count, Collections.EMPTY_LIST);
        }
        return new PageResult(count, mapper.queryPageResult(qo));
    }

    @Override
    public void auditor(Receipt receipt) {
        receipt = mapper.selectByPrimaryKey(receipt.getId());
        if (receipt.getStatus() == Receipt.HAS_AUDITOR) {
            return;
        }
        Student student = receipt.getStu();
        //总金额
        BigDecimal totalMoney = student.getClassinfo().getTotalMoney();
        //已交金额
        BigDecimal tuition = student.getTuition() == null ? new BigDecimal("0") : student.getTuition();
        //更新已交金额
        BigDecimal currentMoney = tuition.add(receipt.getReceiptamount());
        if (totalMoney.compareTo(currentMoney) > 0) { //还没有交够费用
            student.setFeesstatus(Student.FEESS_STATUS_AUDIT);
        } else { //已经交够费用
            student.setFeesstatus(Student.FEESS_STATUS_OK);
            student.setPaytime(new Date());
            //账户资金增加
            accountMapper.addAccount(receipt.getReceiptamount());
            Account account = accountMapper.select();
            //生成流水
            accountFlowService.studentfee(student, receipt.getReceiptamount(), account.getNum());
        }
        receipt.setStatus(Receipt.HAS_AUDITOR);
        student.setTuition(currentMoney);
        stuMapper.updateByPrimaryKey(student);
        mapper.updateByPrimaryKey(receipt);


    }
}
