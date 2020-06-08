package com._520it.crm.web.controller;

import java.util.Date;
import java.util.List;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.PotenClient;
import com._520it.crm.domain.PotenStudent;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.PotenClientQuery;
import com._520it.crm.service.IPotenClientService;
import com._520it.crm.service.IPotenStudentService;
import com._520it.crm.util.AjaxResult;
import com._520it.crm.util.PermissionName;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PotenClientController extends BaseController {

    @Autowired
    private IPotenClientService service;
    @Autowired
    private IPotenStudentService psservice;

    @RequestMapping("/potenClient")
    @RequiresPermissions("potenClient:view")
    @PermissionName("客户池主页")
    public String index() {
        return "potenClient";
    }

    @RequestMapping("/potenClient_list")
    @ResponseBody
    @RequiresPermissions("potenClient:list")
    @PermissionName("客户池全查")
    /*public PageResult list(PotenClientQuery qo) {
        return service.query(qo);
	}*/
    public List<PotenStudent> list() {
//		return psservice.selectAll();
        return psservice.selectStatus();
    }

    @SuppressWarnings("deprecation")
    @RequestMapping("/potenClient_change")
    @ResponseBody
    @RequiresPermissions("potenClient:change")
    @PermissionName("强势拜访")
    public AjaxResult change(Long id) {
        try {
            PotenStudent potenStudent = service.selectByPrimaryKey(id).getPotenStudent();
            Long id2 = potenStudent.getId();
            potenStudent.setEmployee((Employee) SecurityUtils.getSubject().getPrincipal());
            potenStudent.setLastTime(new Date());
            //下次约见时间
            long time = new Date().getTime();
            double random = (Math.random()) * 86400000 * 5;
            time = (long) (time + random);
            Date date = new Date(time);
            potenStudent.setNextTime(date);
            psservice.updateByPrimaryKey(potenStudent, 1L);
            psservice.change(id2);
            service.deleteByPrimaryKey(id);
            return new AjaxResult(true, "招收成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AjaxResult(false, "招收失败");
    }


    @RequestMapping("/potenClient_listAll")
    @ResponseBody
    @RequiresPermissions("potenClient:listAll")
    @PermissionName("潜在客户")
    public PageResult query(PotenClientQuery qo) {

        return service.query(qo);
    }

    @RequestMapping("/potenClient_insert")
    @ResponseBody
    @RequiresPermissions("potenClient:save")
    @PermissionName("保存移入客户池信息")
    public AjaxResult insert(PotenStudent potenStudent) {
        try {
            //客户池数据添加
            System.out.println(potenStudent);
            PotenClient potenClient = new PotenClient();
            potenClient.setPotenStudent(potenStudent);
            potenClient.setEmployee(potenStudent.getEmployee());
            potenClient.setPutTime(new Date());
            potenClient.setRemark(potenStudent.getRemark());
            service.insert(potenClient);

            //潜在学员状态更改
            psservice.changeStatus(18L, potenStudent.getId());

            return new AjaxResult(true, "放进客户池成功");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AjaxResult(false, "放进客户池失败");
    }
}
