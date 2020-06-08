package com._520it.crm.web.controller;

import com._520it.crm.domain.Bigcustomer;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.BigcustomerQuery;
import com._520it.crm.service.IBigcustomerService;
import com._520it.crm.util.AjaxResult;
import com._520it.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/8/27.
 */
@Controller
public class BigcustomerController {
    @Autowired
    private IBigcustomerService service;

    //大客户主页
    @RequestMapping("/bigcustomer")
    @PermissionName("客户主页")
    public String index(){
        return "bigcustomer";
    }

    //大客户列表
    @RequestMapping("/bigcustomer_list")
    @ResponseBody
    @RequiresPermissions("bigcustomer:list")
    @PermissionName("客户列表")
    public PageResult bigcustomer_list(BigcustomerQuery qo){
        return service.queryPageResult(qo) ;
    }


    @RequestMapping("/bigcustomer_save")
    @ResponseBody
    @RequiresPermissions("bigcustomer:save")
    @PermissionName("新增客户")
    public AjaxResult save(Bigcustomer bigcustomer){

        try{
            service.insert(bigcustomer);
            return new AjaxResult(true,"保存成功!");
        }catch(Exception e){
            e.printStackTrace();
        }
        return new AjaxResult(false,"保存失败!");
    }

    @RequestMapping("/bigcustomer_update")
    @ResponseBody
    @PermissionName("更新客户")
    @RequiresPermissions("bigcustomer:update")
    public AjaxResult update(Bigcustomer bigcustomer){
        try{
            service.updateByPrimaryKey(bigcustomer);
            return new AjaxResult(true,"更新成功!");
        }catch(Exception e){
            e.printStackTrace();
        }
        return new AjaxResult(false,"更新失败!");
    }

    @RequestMapping("/bigcustomer_delete")
    @ResponseBody
    @PermissionName("删除客户")
    @RequiresPermissions("bigcustomer:del")
    public AjaxResult delete(Long id) {
        try {
            service.deleteByPrimaryKey(id);
            return new AjaxResult(true, "删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AjaxResult(false, "删除失败!");
    }
}
