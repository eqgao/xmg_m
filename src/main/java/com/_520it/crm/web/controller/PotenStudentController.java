package com._520it.crm.web.controller;

import com._520it.crm.domain.PotenStudent;
import com._520it.crm.domain.Test;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.PotenStudentQuery;
import com._520it.crm.service.IPotenStudentService;
import com._520it.crm.service.ITestService;
import com._520it.crm.util.AjaxResult;
import com._520it.crm.util.PermissionName;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class PotenStudentController extends BaseController {

    @Autowired
    private IPotenStudentService service;
    @Autowired
    private ITestService testService;

    @RequestMapping("/potenStudent")
    @RequiresPermissions("potenStudent:view")
    @PermissionName("潜在学员主页")
    public String index() {
        return "potenStudent";
    }

    @RequestMapping("/potenStudent_list")
    @ResponseBody
    @RequiresPermissions("potenStudent:list")
    @PermissionName("潜在学员列表")
    public PageResult list(PotenStudentQuery qo) {
        return service.queryPageResult(qo);
    }


    @RequestMapping("/potenStudent_save")
    @ResponseBody
    @RequiresPermissions("potenStudent:save")
    @PermissionName("新增潜在学员")
    public AjaxResult save(PotenStudent potenStudent, Long infoId) {
        try {
        	service.insert(potenStudent,infoId);
        	Test test = new Test();
        	test.setCourse(potenStudent.getCourse());
        	test.setPotenStudent(service.selectPpotenStudentByTel(potenStudent.getTel()));
        	test.setSn(potenStudent.getQq());
        	test.setTestTime(new Date());
        	double score = (Math.random()) * 100;
        	test.setTestResult(score);
			testService.insert(test );
            return new AjaxResult(true, "保存成功!");
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("禁止保存同一个人")) {
                return new AjaxResult(false, "禁止保存同一个人!");

            }
        }
        return new AjaxResult(false, "保存失败!");
    }

    @RequestMapping("/potenStudent_update")
    @ResponseBody
    @PermissionName("更新潜在学员")
    @RequiresPermissions("potenStudent:update")
    public AjaxResult update(PotenStudent potenStudent,Long infoId) {
        try {
            service.updateByPrimaryKey(potenStudent,infoId);
            return new AjaxResult(true, "更新成功!");
        } catch (Exception e) {
            e.printStackTrace();
            if (e.getMessage().contains("禁止保存同一个人")) {
                return new AjaxResult(false, "禁止保存同一个人!");

            }
        }
        return new AjaxResult(false, "更新失败!");
    }

    @RequestMapping("/potenStudent_changeState")
    @ResponseBody
    @PermissionName("设置流失")
    @RequiresPermissions("potenStudent:changeState")
    public AjaxResult changeState(Long id) {
        try {
            String ret = service.changeState(id);
            if (ret.equals("成功")) {
                return new AjaxResult(true, "设置成功,该学员已经说了再见!");
            }
            return new AjaxResult(true, "他还没有彻底厌恶你们,再努力一下吧!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AjaxResult(false, "出现了意外,想不到吧!");
    }

    @RequestMapping("/potenStudent_follow")
    @ResponseBody
    public AjaxResult follow(Long id) {
        try {
            String follow = service.follow(id);
            if (follow.equals("成功")) {
                return new AjaxResult(true, "跟进成功,该学员好感提升啦!");
            } else if (follow.equals("最高")) {
                return new AjaxResult(true, "该学员好感已经最高拉,可以转正啦");
            }
            if (follow.equals("最低")) {
                return new AjaxResult(true, "抱歉,该学员已经离我们而去");
            } else {
                return new AjaxResult(true, "跟进失败,该学员好感下降啦!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AjaxResult(false, "跟进异常!");
    }


    @RequestMapping("/potenStudent_listAll")
    @ResponseBody
    @RequiresPermissions("potenStudent:listAll")
    @PermissionName("潜在学员列表")
    public List<PotenStudent> listAll() {
        return service.list();
    }


    @RequestMapping("/potenStudent_export")
    public void export(HttpServletResponse response) throws Exception {
        //设置下载的相应头
        response.setHeader("Content-Disposition", "attachment;filename=PotenStudent.xls");
        //创建工作簿,因为是下载所以使用流的方式
        WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());
        //创建内页面,第一个sheet
        WritableSheet sheet1 = workbook.createSheet("potenStudent", 0);
        //设置格式第一行各种表头
        sheet1.addCell(new Label(0, 0, "姓名"));
        sheet1.addCell(new Label(1, 0, "qq"));
        sheet1.addCell(new Label(2, 0, "电话"));
        sheet1.addCell(new Label(3, 0, "咨询员"));
        sheet1.addCell(new Label(4, 0, "最后约见时间(格式:0000-00-00)"));
        sheet1.addCell(new Label(5, 0, "下次约见时间(格式:0000-00-00)"));
        sheet1.addCell(new Label(6, 0, "来源"));
        sheet1.addCell(new Label(7, 0, "意向程度"));
        sheet1.addCell(new Label(8, 0, "意向课程"));
        sheet1.addCell(new Label(9, 0, "备注"));
        sheet1.addCell(new Label(10, 0, "状态"));

        List<PotenStudent> potenStudents = service.selectAll();
        for (int i = 0; i < potenStudents.size(); i++) {
            PotenStudent potenStudent = potenStudents.get(i);
            sheet1.addCell(new Label(0, i + 1, potenStudent.getName()));
            sheet1.addCell(new Label(1, i + 1, potenStudent.getQq()));
            sheet1.addCell(new Label(2, i + 1, potenStudent.getTel()));
            sheet1.addCell(new Label(3, i + 1, potenStudent.getEmployee().getRealname()));
            sheet1.addCell(new Label(4, i + 1, DateFormat.getDateInstance().format(potenStudent.getLastTime()).toString()));

            //时间处理
            sheet1.addCell(new Label(5, i + 1, DateFormat.getDateInstance().format(potenStudent.getNextTime()).toString()));

            sheet1.addCell(new Label(6, i + 1, potenStudent.getSource().getName()));

            sheet1.addCell(new Label(7, i + 1, potenStudent.getLevel().getName()));
            sheet1.addCell(new Label(8, i + 1, potenStudent.getCourse().getName()));
            sheet1.addCell(new Label(9, i + 1, potenStudent.getRemark()));
            sheet1.addCell(new Label(10, i + 1, potenStudent.getStatus().getName()));

        }
        workbook.write();
        workbook.close();
    }

}
