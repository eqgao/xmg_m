package com._520it.crm.web.controller;

import com._520it.crm.domain.Classinfo;
import com._520it.crm.query.ClassinfoQuery;
import com._520it.crm.service.IClassinfoService;
import com._520it.crm.util.AjaxResult;
import com._520it.crm.util.PermissionName;
import com._520it.crm.page.PageResult;
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
public class ClassinfoController {

	@Autowired
	private IClassinfoService service;


	@RequestMapping("/classinfo")
	@PermissionName("课程主页")
	public String index(){
		return "classinfo";
	}

	@RequestMapping("/classinfo_listAll")
	@ResponseBody
	public List<Classinfo> list(){
		return service.selectAll();
	}

	@RequestMapping("/classinfo_list")
	@ResponseBody
	@RequiresPermissions("classinfo:list")
	@PermissionName("课程管理")
	public PageResult list(ClassinfoQuery qo){
		return service.queryPageResult(qo);
	}

	@RequestMapping("/classinfo_save")
	@ResponseBody
	@RequiresPermissions("classinfo:save")
	@PermissionName("新增班级")
	public AjaxResult save(Classinfo info){

		try{
			service.insert(info);
			return new AjaxResult(true,"保存成功!");
		}catch(Exception e){
			e.printStackTrace();
		}
		return new AjaxResult(false,"保存失败!");
	}

	@RequestMapping("/classinfo_update")
	@ResponseBody
	@PermissionName("更新班级")
	@RequiresPermissions("classinfo:update")
	public AjaxResult update(Classinfo info){
		try{
			service.updateByPrimaryKey(info);
			return new AjaxResult(true,"更新成功!");
		}catch(Exception e){
			e.printStackTrace();
		}
		return new AjaxResult(false,"更新失败!");
	}

	@RequestMapping("/classinfo_delete")
	@ResponseBody
	@PermissionName("删除班级")
	@RequiresPermissions("classinfo:delete")
	public AjaxResult delete(Long id) {
		try {
			service.deleteByPrimaryKey(id);
			return new AjaxResult(true, "删除成功!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AjaxResult(false, "删除失败!");
	}

	@RequestMapping("/classinfo_export")
	@ResponseBody
	public void export(HttpServletResponse response) throws Exception {
		//文件下载响应头
		response.setHeader("Content-Disposition", "attachment;filename=a.xls");
		//响应到浏览器
		WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());

		WritableSheet sheet = workbook.createSheet("班级表", 0);

		//设置列标题
		sheet.addCell(new Label(0, 0, "班级名称"));
		sheet.addCell(new Label(1, 0, "班级编码"));
		sheet.addCell(new Label(2, 0, "开班时间"));
		sheet.addCell(new Label(3, 0, "班主任"));
		sheet.addCell(new Label(4, 0, "教室"));
		sheet.addCell(new Label(5, 0, "状态"));
		sheet.addCell(new Label(6, 0, "备注"));


		//把员工的数据填充到工作簿中
        List<Classinfo> list = service.selectAll();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (int i = 0, j = 1; i < list.size(); i++, j++) {
            Classinfo classinfo =list.get(i);
            Integer status = classinfo.getStatus();
            String statusName = "基础班";
            if (status==1){
                statusName = "基础班";
            }else if(status ==2){
                statusName = "大神班";
            }else {
                statusName = "超神班";
            }
            sheet.addCell(new Label(0, j, classinfo.getName()));
			sheet.addCell(new Label(1, j, classinfo.getSn()));
			sheet.addCell(new Label(2, j, dateFormat.format(classinfo.getBegintime())));
			sheet.addCell(new Label(3, j, classinfo.getEmployee().getRealname()));
            sheet.addCell(new Label(4, j, classinfo.getClassRoom().getName()));
            sheet.addCell(new Label(5, j, statusName ));
            sheet.addCell(new Label(6, j, classinfo.getRemark()));
		}
		//写入数据
		workbook.write();
		//关闭资源
		workbook.close();
	}
}
