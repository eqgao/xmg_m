package com._520it.crm.web.controller;

import com._520it.crm.domain.Checkon;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.CheckonQuery;
import com._520it.crm.service.ICheckonService;
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
public class CheckonController {

    @Autowired
    private ICheckonService service;

    @RequestMapping("/checkon")
    public String index(){
        return "checkon";
    }

    @RequestMapping("/checkon_listAll")
    @ResponseBody
    public PageResult selectAll(CheckonQuery qo){
        List<Checkon> checkons = service.selectAll(qo);

        return new PageResult((long) checkons.size(),checkons);

    }
    @RequestMapping("/index_sign.do")
    @ResponseBody
    public AjaxResult save(Checkon checkon){

        try{
            service.insert(checkon);
            return new AjaxResult(true,"保存成功!");
        }catch(Exception e){
            e.printStackTrace();
        }
        return new AjaxResult(false,"保存失败!");
    }


}
