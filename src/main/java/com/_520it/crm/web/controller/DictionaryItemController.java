package com._520it.crm.web.controller;

import com._520it.crm.domain.DictionaryItem;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.DictionaryItemQuery;
import com._520it.crm.service.IDictionaryItemService;
import com._520it.crm.util.AjaxResult;
import com._520it.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class DictionaryItemController extends BaseController {

    @Autowired
    private IDictionaryItemService service;

    @RequestMapping("/dictionary")
    @RequiresPermissions("dictionary:view")
    @PermissionName("字典主页")
    public String index() {
        return "dictionary";
    }

    @RequestMapping("/dictionaryItem_list")
    @ResponseBody
    @RequiresPermissions("dictionaryItem:list")
    @PermissionName("字典明细列表")
    public PageResult list(DictionaryItemQuery qo) {
        return service.queryPageResult(qo);
    }

    //==========================================
    //为潜在学员添加,修改提供下拉框
    @RequestMapping("/dictionaryItem_listLevel")
    @ResponseBody
    public List<DictionaryItem> selectLevel() {

        return service.selectLevel();
    }

    @RequestMapping("/dictionaryItem_listStatus")
    @ResponseBody
    public List<DictionaryItem> selectStatus() {
        return service.selectStatus();
    }

    @RequestMapping("/dictionaryItem_listAllStatus")
    @ResponseBody
    public List<DictionaryItem> selectAllStatus() {
        return service.selectAllStatus();
    }

    @RequestMapping("/dictionaryItem_listSource")
    @ResponseBody
    public List<DictionaryItem> selectSource() {
        return service.selectSource();
    }
//==========================================

    @RequestMapping("/dictionaryItem_save")
    @ResponseBody
    @RequiresPermissions("dictionaryItem:save")
    @PermissionName("新增字典明细")
    public AjaxResult save(DictionaryItem dictionaryItem) {
        try {
            service.insert(dictionaryItem);
            return new AjaxResult(true, "保存成功!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AjaxResult(false, "保存失败!");
    }

    @RequestMapping("/dictionaryItem_update")
    @ResponseBody
    @PermissionName("更新字典明细")
    @RequiresPermissions("dictionaryItem:update")
    public AjaxResult update(DictionaryItem dictionaryItem) {
        try {
            service.updateByPrimaryKey(dictionaryItem);
            return new AjaxResult(true, "更新成功!");
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("官方数据请勿动,要玩自己添加再玩")) {
                return new AjaxResult(false, "官方数据请勿动,要玩自己添加再玩");
            }
        }
        return new AjaxResult(false, "更新失败!");
    }

    @RequestMapping("/dictionaryItem_changeState")
    @ResponseBody
    @PermissionName("删除字典明细")
    @RequiresPermissions("dictionaryItem:changeState")
    public AjaxResult changeState(Long id) {
        try {
            service.deleteByPrimaryKey(id);
            return new AjaxResult(true, "删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("官方数据请勿动,要玩自己添加再玩")) {
                return new AjaxResult(false, "官方数据请勿动,要玩自己添加再玩");
            }
        }
        return new AjaxResult(false, "删除失败!");
    }


}
