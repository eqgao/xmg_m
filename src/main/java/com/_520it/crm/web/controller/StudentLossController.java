package com._520it.crm.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com._520it.crm.domain.StudentLoss;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.StudentLossQuery;
import com._520it.crm.service.IStudentLossService;
import com._520it.crm.util.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import lombok.Getter;
import lombok.Setter;

@Controller
public class StudentLossController {
	@Setter
	@Getter
	private String cause;
	@Autowired
	private IStudentLossService service;

	@RequestMapping("/studentloss")
	public String index() {
		return "studentloss";
	}

	// 学员流失
	@RequestMapping("/studentloss_loss")
	@ResponseBody
	public AjaxResult loss(Long id, String cause) {
		try {
			service.insert(id,cause);
			return new AjaxResult(true, "该学员学员已经退学成功,详情请到学员流失管理查看");
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResult(true, "该学员学员已经退学成功,详情请到学员流失管理查看");
		}
	}
	
	
	
	// 学员信息
	@RequestMapping("/studentloss_list")
	@ResponseBody
	public PageResult list(StudentLossQuery qo) {
		return service.queryPageResult(qo);
	}

	// 学员信息
	@RequestMapping("/studentloss_delete")
	@ResponseBody
	public AjaxResult delete(Long id) {
		try {
			service.deleteByPrimaryKey(id);
			return new AjaxResult(true, "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new AjaxResult(false, "删除失败");
		}
	}
	//流失学生表格导出功能
		@RequestMapping("/studentloss_export")
		@ResponseBody
		 public void export(HttpServletResponse response) throws Exception {
			//文件下载响应头
	        response.setHeader("Content-Disposition", "attachment;filename=a.xls");
	        //响应到浏览器
	        WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());
	      //创建工作簿sheet
	        WritableSheet sheet = workbook.createSheet("day01", 0);
	      //设置列标题
	        sheet.addCell(new Label(0, 0, "学生姓名"));
	        sheet.addCell(new Label(1, 0, "QQ"));
	        sheet.addCell(new Label(2, 0, "电话"));
	        sheet.addCell(new Label(3, 0, "流失班级"));
	        sheet.addCell(new Label(4, 0, "流失时间"));
	        sheet.addCell(new Label(5, 0, "经办人"));
	        sheet.addCell(new Label(6, 0, "流失原因"));
	        //把所有的数据填充到工作薄中
	        List<StudentLoss> list = service.selectAll();
	        for (int i = 0, j = 1; i < list.size(); i++, j++) {
	        	StudentLoss Studentloss = list.get(i);
	            sheet.addCell(new Label(0, j, Studentloss.getStudentname()));
	            sheet.addCell(new Label(1, j, Studentloss.getQq()));
	            sheet.addCell(new Label(2, j, Studentloss.getTel()));
	            sheet.addCell(new Label(3, j, Studentloss.getClassinfoname()));
	            sheet.addCell(new Label(4, j, Studentloss.getLosttime().toLocaleString()));
	            sheet.addCell(new Label(5, j, Studentloss.getHandler()));
	            sheet.addCell(new Label(6, j, Studentloss.getCause()));
	        }
	        //写入数据
	        workbook.write();
	        //关闭资源
	        workbook.close();
			
		}

	
}
