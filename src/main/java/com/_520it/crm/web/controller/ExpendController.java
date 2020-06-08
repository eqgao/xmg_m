package com._520it.crm.web.controller;

import com._520it.crm.domain.Classinfo;
import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Expend;
import com._520it.crm.query.ExpendQueryObject;
import com._520it.crm.service.IClassinfoService;
import com._520it.crm.service.IExpendService;
import com._520it.crm.util.AjaxResult;
import com._520it.crm.util.PermissionName;
import com._520it.crm.page.PageResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by hasee on 2017/8/26.
 */
@Controller
public class ExpendController {

    @Autowired
    private IExpendService service;
    @Autowired
    private IClassinfoService classinfoService;

    @RequestMapping("/expend")
    @RequiresPermissions("expend:view")
    @PermissionName("支出主页")
    public String index() {
        return "expend";
    }

    @RequestMapping("/expend_listClass")
    @RequiresPermissions("expend:listClass")
    @PermissionName("员工主页")
    @ResponseBody
    public List<Classinfo> listClass() {
        List<Classinfo> classinfos = classinfoService.selectAll();
        return classinfos;
    }

    @RequestMapping("/expend_add")
    @ResponseBody
    @RequiresPermissions("expend:add")
    @PermissionName("支出新增")
    public AjaxResult add(Expend expend) {
        try {
            Subject subject = SecurityUtils.getSubject();
            Employee emp = (Employee) subject.getPrincipal();
            expend.setOperator(emp);
            expend.setPaytime(new Date());
            service.insert(expend);
            return new AjaxResult(true, "新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "新增失败");
        }

    }

    @RequestMapping("/expend_auditor")
    @ResponseBody
    @RequiresPermissions("expend:auditor")
    @PermissionName("支出审核")
    public AjaxResult auditor(Expend expend) {
        try {
            service.auditor(expend);
            return new AjaxResult(true, "审核成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "审核失败");
        }

    }

//    @RequestMapping("/expend_update")
//    @ResponseBody
//    @RequiresPermissions("expend:update")
//    @PermissionName("支出更新")
//    public AjaxResult update(Expend expend) {
//        try {
//            service.updateByPrimaryKey(expend);
//            return new AjaxResult(true, "更新成功");
//        } catch (Exception e) {
//            e.printStackTrace();
//            return new AjaxResult(false, "更新失败");
//        }
//
//    }

    @RequestMapping("/expend_del")
    @ResponseBody
    @RequiresPermissions("expend:del")
    @PermissionName("支出删除")
    public AjaxResult del(Long id) {
        try {
            service.deleteByPrimaryKey(id);
            return new AjaxResult(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "删除失败");
        }
    }

    @RequestMapping("/expend_list")
    @ResponseBody
    @RequiresPermissions("expend:list")
    @PermissionName("支出列表")
    public PageResult list(ExpendQueryObject qo) {
        PageResult pageResult = service.queryPageResult(qo);
        System.out.println(pageResult);
        return pageResult;
    }

    @RequestMapping("/expend_export")
    @ResponseBody
    public void export(HttpServletResponse response) throws Exception {
//        //文件下载响应头
//        response.setHeader("Content-Disposition", "attachment;filename=a.xls");
//        //响应到浏览器
//        WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());
//
//        //创建本地xls文件
//        //WritableWorkbook workbook = Workbook.createWorkbook(new File("F:/a.xls"));
//        //创建工作簿sheet
//        WritableSheet sheet = workbook.createSheet("支出表", 0);
//
//		/*//创建文本格式单元格
//        Label cell = new Label(0,0,"hello");
//		//把单元格添加到工作簿中
//		sheet.addCell(cell);
//		//创建日期格式单元格
//		DateTime dateTime = new DateTime(1, 0, new Date());
//		//把单元格添加到工作簿中
//		sheet.addCell(dateTime);*/
//
//
//        //设置列标题
//        sheet.addCell(new Label(0, 0, "编号"));
//        sheet.addCell(new Label(1, 0, "姓名"));
//        sheet.addCell(new Label(2, 0, "QQ"));
//        sheet.addCell(new Label(3, 0, "电话"));
//        sheet.addCell(new Label(4, 0, "营销人员"));
//        sheet.addCell(new Label(5, 0, "升班/留级时间"));
//        sheet.addCell(new Label(6, 0, "流入班级"));
//        sheet.addCell(new Label(7, 0, "升级/留级"));
//
//
//        //把员工的数据填充到工作簿中
//        List<Expend> list = service.selectAll();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        for (int i = 0, j = 1; i < list.size(); i++, j++) {
//            Expend expend = list.get(i);
//            sheet.addCell(new Label(0, j, expend.getId() + ""));
//            sheet.addCell(new Label(1, j, expend.getStudent().getName()));
//            sheet.addCell(new Label(2, j, expend.getStudent().getQq()));
//            sheet.addCell(new Label(3, j, expend.getStudent().getTel()));
//            sheet.addCell(new Label(4, j, expend.getEmp().getRealname()));
//            sheet.addCell(new Label(5, j, sdf.format(expend.getExpendtime())));
//            sheet.addCell(new Label(6, j, expend.getClassinfo().getName()));
//            sheet.addCell(new Label(7, j, expend.getStatus() == 0 ? "留级" : "升班"));
//        }
//
//        //写入数据
//        workbook.write();
//        //关闭资源
//        workbook.close();
    }


}
