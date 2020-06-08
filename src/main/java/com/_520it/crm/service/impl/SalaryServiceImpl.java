package com._520it.crm.service.impl;

import com._520it.crm.domain.Salary;
import com._520it.crm.mapper.SalaryMapper;
import com._520it.crm.query.SalaryQuery;
import com._520it.crm.service.ISalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by fjz on 2017/8/26.
 */
@Service
public class SalaryServiceImpl implements ISalaryService {
    @Autowired
    private SalaryMapper mapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Salary salary) {
       if (salary.getSalary()!=null){
           salary.setStatus(true);
       }else {
           salary.setStatus(false);
       }
        return mapper.insert(salary);
    }

    @Override
    public Salary selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Salary> selectAll(SalaryQuery qo) {

        return mapper.selectAll(qo);
    }

    @Override
    public int updateByPrimaryKey(Salary salary) {
        if (salary.getSalary()!=null){
            salary.setStatus(true);
        }else {
            salary.setStatus(false);
        }
        return mapper.updateByPrimaryKey(salary);
    }
}
