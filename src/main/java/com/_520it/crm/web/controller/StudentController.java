package com._520it.crm.web.controller;

import com._520it.crm.domain.Jobinfo;
import com._520it.crm.domain.PotenStudent;
import com._520it.crm.domain.Receipt;
import com._520it.crm.domain.Student;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.StudentQuery;
import com._520it.crm.service.IEmployeeService;
import com._520it.crm.service.IPotenStudentService;
import com._520it.crm.service.IStudentService;
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
import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private IStudentService service;
    /* ================================== */
    @Autowired
    private IPotenStudentService potenStudentService;
    @Autowired
    private IEmployeeService employeeService;

    /* ================================== */
    @RequestMapping("/student")
    public String index() {
        return "student";
    }

    @RequestMapping("/student_list")
    @ResponseBody
    @RequiresPermissions("student:list")
    @PermissionName("学生列表")
    public PageResult list(StudentQuery qo) {
        return service.queryPageResult(qo);
    }

    @RequestMapping("/student_update")
    @ResponseBody
    @RequiresPermissions("student:update")
    @PermissionName("学生编辑")
    public AjaxResult update(Student student) {
        try {
            service.updateByPrimaryKey(student);
            return new AjaxResult(true, "编辑成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "编辑失败");
        }
    }

    @RequestMapping("/student_remark")
    @ResponseBody
    @RequiresPermissions("student:remark")
    @PermissionName("学生备注")
    public AjaxResult remark(Long id, String remark) {
        try {
            Student student = new Student();
            student.setId(id);
            student.setRemark(remark);
            service.updateByPrimaryKey(student);

//			System.out.println("--------------" + id);
//			System.out.println("--------------" + remark);
            return new AjaxResult(true, "备注成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "备注失败");
        }
    }

    // 学员就业操作
    @RequestMapping("/student_graduation")
    @ResponseBody
    public AjaxResult graduation(Jobinfo job, Long sid) {
        try {
            service.graduation(job, sid);
            return new AjaxResult(true, "毕业操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "毕业操作失败");
        }
    }

    // 学生交清学费
    @RequestMapping("/studentfees_insert")
    @ResponseBody
    public AjaxResult insert(Receipt receipt, Long Sid) {
        try {
            service.insertfees(receipt, Sid);
            return new AjaxResult(true, "提交成功,待收款管理员审核");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "操作失败");
        }
    }

    // 学生毕业操作
    @RequestMapping("/changestatus")
    @ResponseBody
    public AjaxResult changeGraduiteStatus(Long id) {
        try {
            service.changeGraduiteStatus(id);
            return new AjaxResult(true, "提交成功,待收款管理员审核");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "操作失败");
        }
    }

    // 学生表格导出功能
    @RequestMapping("/student_export")
    @ResponseBody
    public void export(HttpServletResponse response) throws Exception {
        // 文件下载响应头
        response.setHeader("Content-Disposition", "attachment;filename=a.xls");
        // 响应到浏览器
        WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());
        // 创建工作簿sheet
        WritableSheet sheet = workbook.createSheet("day01", 0);
        // 设置列标题
        sheet.addCell(new Label(0, 0, "学生姓名"));
        sheet.addCell(new Label(1, 0, "QQ"));
        sheet.addCell(new Label(2, 0, "电话"));
        sheet.addCell(new Label(3, 0, "销售人员"));
        sheet.addCell(new Label(4, 0, "总学费"));
        sheet.addCell(new Label(5, 0, "缴费金额"));
        sheet.addCell(new Label(6, 0, "缴费时间"));
        sheet.addCell(new Label(7, 0, "状态"));
        sheet.addCell(new Label(8, 0, "入学时间"));
        sheet.addCell(new Label(9, 0, "缴费状态"));
        sheet.addCell(new Label(10, 0, "所在班级"));
        sheet.addCell(new Label(11, 0, "课程"));
        sheet.addCell(new Label(12, 0, "备注"));
        // 把所有的数据填充到工作薄中
        List<Student> list = service.selectAll();
        for (int i = 0, j = 1; i < list.size(); i++, j++) {
            Student student = list.get(i);
            sheet.addCell(new Label(0, j, student.getName()));
            sheet.addCell(new Label(1, j, student.getQq()));
            sheet.addCell(new Label(2, j, student.getTel()));
            sheet.addCell(new Label(3, j, student.getEmployee().getUsername()));
            sheet.addCell(new Label(4, j, student.getClassinfo().getTotalMoney().toString()));
            sheet.addCell(new Label(5, j, student.getTuition().toString()));
            sheet.addCell(new Label(6, j, student.getPaytime().toLocaleString()));
            sheet.addCell(new Label(7, j, student.getStatus().toString()));
            sheet.addCell(new Label(8, j, student.getEntrancetime().toLocaleString()));
            sheet.addCell(new Label(9, j, student.getFeesstatus().toString()));
            sheet.addCell(new Label(10, j, student.getClassinfo().getName()));
            sheet.addCell(new Label(11, j, student.getCourse().getName()));
            sheet.addCell(new Label(12, j, student.getRemark()));
        }
        // 写入数据
        workbook.write();
        // 关闭资源
        workbook.close();

    }

    /* ======================================= */
    @RequestMapping("/student_save")
    @ResponseBody
    @RequiresPermissions("student:save")
    @PermissionName("潜在学员转正")
    public AjaxResult save(PotenStudent potenStudent, Student student) {
        try {
            potenStudentService.formal(potenStudent.getId());
            service.insert(potenStudent, student);
            return new AjaxResult(true, "转正成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "转正失败");
        }
    }

    /* ======================================= */
    @RequestMapping("/student_toSystemMenber")
    @ResponseBody
    @RequiresPermissions("student:toSystemMenber")
    @PermissionName("转成系统社员")
    public AjaxResult studentToMumber(Long studentId) {
        try {
            Student student = service.selectByPrimaryKey(studentId);
            return new AjaxResult().setMsg("设置系统社员失败");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult().setMsg(e.getMessage());

        }
    }


}
