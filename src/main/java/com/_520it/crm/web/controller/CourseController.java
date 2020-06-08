package com._520it.crm.web.controller;

import com._520it.crm.domain.Course;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.CourseQuery;
import com._520it.crm.service.ICourseService;
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
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class CourseController {

	@Autowired
	private ICourseService service;


	@RequestMapping("/course")
	@PermissionName("课程表主页")
	public String index(){
		return "course";
	}

	@RequestMapping("/course_listAll")
	@ResponseBody
	public List<Course> list(){
		return service.selectAll();
	}

	@RequestMapping("/course_list")
	@ResponseBody
	@RequiresPermissions("course:list")
	@PermissionName("课程表管理")
	public PageResult list(CourseQuery qo){
		return service.queryPageResult(qo);
	}

	@RequestMapping("/course_save")
	@ResponseBody
	@RequiresPermissions("course:save")
	@PermissionName("新增课程表")
	public AjaxResult save(Course course){
		//设置默认状态是该课程为上
		course.setStatus(false);
		try{
			service.insert(course);
			return new AjaxResult(true,"保存成功!");
		}catch(Exception e){
			e.printStackTrace();
		}
		return new AjaxResult(false,"保存失败!");
	}

	@RequestMapping("/course_update")
	@ResponseBody
	@PermissionName("更新课程表")
	@RequiresPermissions("course:update")
	public AjaxResult update(Course course){
		try{
			service.updateByPrimaryKey(course);
			return new AjaxResult(true,"更新成功!");
		}catch(Exception e){
			e.printStackTrace();
		}
		return new AjaxResult(false,"更新失败!");
	}

	@RequestMapping("/course_delete")
	@ResponseBody
	@PermissionName("删除课程表")
	@RequiresPermissions("course:delete")
	public AjaxResult delete(Long id) {
		try {
			service.deleteByPrimaryKey(id);
			return new AjaxResult(true, "删除成功!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AjaxResult(false, "删除失败!");
	}

	@RequestMapping("/course_export")
	@ResponseBody
	public void export(HttpServletResponse response) throws Exception {
		//文件下载响应头
		response.setHeader("Content-Disposition", "attachment;filename=b.xls");
		//响应到浏览器
		WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());

		WritableSheet sheet = workbook.createSheet("班级表", 0);

		//设置列标题
		sheet.addCell(new Label(0, 0, "课程名称"));
		sheet.addCell(new Label(1, 0, "课程时间"));
		sheet.addCell(new Label(2, 0, "讲师"));
		sheet.addCell(new Label(3, 0, "班主任"));
		sheet.addCell(new Label(4, 0, "班级"));
		sheet.addCell(new Label(5, 0, "教室"));
		sheet.addCell(new Label(6, 0, "状态"));
        sheet.addCell(new Label(7, 0, "备注"));

        //把员工的数据填充到工作簿中
		List<Course> list = service.selectAll();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 0, j = 1; i < list.size(); i++, j++) {
			Course course =list.get(i);
            Boolean status = course.getStatus();
            String statusName = "基础班";
			if (status){
				statusName = "已上";
			}else{
				statusName = "未上";
			}
			sheet.addCell(new Label(0, j, course.getName()));
			sheet.addCell(new Label(1, j, dateFormat.format(course.getSchooltime())));
			sheet.addCell(new Label(2, j, course.getLecturer().getRealname()));
			sheet.addCell(new Label(3, j, course.getTeacher().getRealname()));
			sheet.addCell(new Label(4, j, course.getClassinfo().getName()));
			sheet.addCell(new Label(5, j, course.getClassRoom().getName()));
			sheet.addCell(new Label(6, j, statusName ));
			sheet.addCell(new Label(7, j, course.getRemark()));
		}
		//写入数据
		workbook.write();
		//关闭资源
		workbook.close();
	}
}
