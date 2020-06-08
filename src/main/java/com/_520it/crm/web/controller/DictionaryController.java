package com._520it.crm.web.controller;

import com._520it.crm.domain.Dictionary;
import com._520it.crm.service.IDictionaryService;
import com._520it.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DictionaryController extends BaseController {

    @Autowired
    private IDictionaryService service;


    @RequestMapping("/dictionary_listAll")
    @ResponseBody
    @RequiresPermissions("dictionary:listAll")
    @PermissionName("字典全查")
    public List<Dictionary> listAll() {
        return service.selectAll();
    }


}
