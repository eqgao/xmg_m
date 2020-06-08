package com._520it.crm.web.controller;

import com._520it.crm.domain.Employee;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.EmployeeQuery;
import com._520it.crm.service.IDepartmentService;
import com._520it.crm.service.IEmployeeService;
import com._520it.crm.util.AjaxResult;
import com._520it.crm.util.PermissionName;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.Iterator;
import java.util.List;

@Controller
public class EmployeeController extends BaseController {

    @Autowired
    private IEmployeeService service;
    @Autowired
    private IDepartmentService departmentService;

    @RequestMapping("/employee")
    @RequiresPermissions("employee:view")
    @PermissionName("员工主页")
    public String index() {
        return "employee";
    }

    @RequestMapping("/employee_list")
    @ResponseBody
    @RequiresPermissions("employee:list")
    @PermissionName("员工列表")
    public PageResult list(EmployeeQuery qo) {
        //获取当前登录的员工对象
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        //数据权限控制
        //如果是经理则可以查询所有数据
        if (!subject.hasRole("admin")) {
            //如果不是,则只能查询自己的数据
            qo.setId(employee.getId());
        }

        return service.queryPageResult(qo);
    }

    @RequestMapping("/employee_listAll")
    @ResponseBody
    @RequiresPermissions("employee:listAll")
    @PermissionName("员工全查")
    public List<Employee> listAll() {
        return service.selectAll();
    }

    /*====================移交新负责人下拉列表=========================*/
    @RequestMapping("/employee_listRemoveOwn")
    @ResponseBody
    public List<Employee> listRemoveOwn(Long id) {
        List<Employee> employees = service.selectAll();
        Iterator<Employee> iterator = employees.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().getId() == id) {
                iterator.remove();
            }
        }
        return employees;
    }
    /*=============================================*/

    @RequestMapping("/employee_save")
    @ResponseBody
    @RequiresPermissions("employee:save")
    @PermissionName("新增员工")
    public AjaxResult save(Employee emp) {
        //默认是在职状态
        emp.setState(true);
        try {
        	Boolean state = departmentService.selectByPrimaryKey(emp.getDept().getId()).getState();
        	if (!state) {
				return new AjaxResult(false,"所在部门已取消,保存失败");
			}
            service.insert(emp);
            return new AjaxResult(true, "保存成功!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AjaxResult(false, "保存失败!");
    }

    @RequestMapping("/employee_update")
    @ResponseBody
    @PermissionName("更新员工")
    @RequiresPermissions("employee:update")
    public AjaxResult update(Employee emp) {
        try {
            service.updateByPrimaryKey(emp);
            return new AjaxResult(true, "更新成功!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AjaxResult(false, "更新失败!");
    }

    @RequestMapping("/employee_changeState")
    @ResponseBody
    @PermissionName("设置离职")
    @RequiresPermissions("employee:changeState")
    public AjaxResult changeState(Long id) {
        try {
            service.changeState(id);
            return new AjaxResult(true, "设置成功!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AjaxResult(false, "设置失败!");
    }

    @RequestMapping("/employee_export")
    @ResponseBody
    public void export(HttpServletResponse response) throws Exception {
        //文件下载响应头
        response.setHeader("Content-Disposition", "attachment;filename=a.xls");
        //响应到浏览器
        WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());

        //创建本地xls文件
        //WritableWorkbook workbook = Workbook.createWorkbook(new File("F:/a.xls"));
        //创建工作簿sheet
        WritableSheet sheet = workbook.createSheet("day01", 0);

		/*//创建文本格式单元格
        Label cell = new Label(0,0,"hello");
		//把单元格添加到工作簿中
		sheet.addCell(cell);
		//创建日期格式单元格
		DateTime dateTime = new DateTime(1, 0, new Date());
		//把单元格添加到工作簿中
		sheet.addCell(dateTime);*/


        //设置列标题
        sheet.addCell(new Label(0, 0, "用户名"));
        sheet.addCell(new Label(1, 0, "真实姓名"));
        sheet.addCell(new Label(2, 0, "电话"));
        sheet.addCell(new Label(3, 0, "邮箱"));


        //把员工的数据填充到工作簿中
        List<Employee> list = service.selectAll();

        for (int i = 0, j = 1; i < list.size(); i++, j++) {
            Employee employee = list.get(i);
            sheet.addCell(new Label(0, j, employee.getUsername()));
            sheet.addCell(new Label(1, j, employee.getRealname()));
            sheet.addCell(new Label(2, j, employee.getTel()));
            sheet.addCell(new Label(3, j, employee.getEmail()));
        }

        //写入数据
        workbook.write();
        //关闭资源
        workbook.close();
    }


    @RequestMapping("/employee_import")
    @ResponseBody
    public void importFile(MultipartFile file) throws Exception {
        //获取用户上传的文件
        Workbook workbook = Workbook.getWorkbook(file.getInputStream());
        //获取工作簿sheet
        Sheet sheet = workbook.getSheet(0);
        /*
        //获取总列数
		int columns = sheet.getColumns();
		//获取单元格内容
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				Cell cell = sheet.getCell(j, i);
				System.out.println(cell.getContents());
			}
		}*/
        //获取总行数
        int rows = sheet.getRows();
        for (int i = 1; i < rows; i++) {
            Employee employee = new Employee();
            employee.setUsername(sheet.getCell(0, i).getContents());
            employee.setRealname(sheet.getCell(1, i).getContents());
            employee.setTel(sheet.getCell(2, i).getContents());
            employee.setEmail(sheet.getCell(3, i).getContents());
            //添加到数据库中
            service.insert(employee);
        }
        //关闭资源
        workbook.close();
    }


}
