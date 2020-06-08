package com._520it.crm.web.controller;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Salary;
import com._520it.crm.query.SalaryQuery;
import com._520it.crm.service.ISalaryService;
import com._520it.crm.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by fjz on 2017/8/26.
 */
@Controller
public class SalaryController {

    @Autowired
    private ISalaryService service;

    @RequestMapping("/salary")
    public String index(){
        return "salary";
    }

    @RequestMapping("/salary_listAll")
    @ResponseBody
    public List<Salary> selectAll(SalaryQuery qo){

        return service.selectAll(qo);
    }
   //employee_listAll.do
    public Employee employeeListName(){

        return null;
    }

    @RequestMapping("/salary_update")
    @ResponseBody
    public AjaxResult update(Salary salary){
        try{
            service.updateByPrimaryKey(salary);
            return new AjaxResult(true,"更新成功!");
        }catch(Exception e){
            e.printStackTrace();
        }
        return new AjaxResult(false,"更新失败!");
    }
    @RequestMapping("/salary_save")
    @ResponseBody
    public AjaxResult save(Salary salary){
        try{
            service.insert(salary);
            return new AjaxResult(true,"保存成功!");
        }catch(Exception e){
            e.printStackTrace();
        }
        return new AjaxResult(false,"保存失败!");
    }
}
