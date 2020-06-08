package com._520it.crm.web.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com._520it.crm.domain.Course;
import com._520it.crm.domain.PotenStudent;
import com._520it.crm.domain.Test;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.TestQuery;
import com._520it.crm.service.ICourseService;
import com._520it.crm.service.IPotenStudentService;
import com._520it.crm.service.ITestService;
import com._520it.crm.util.AjaxResult;
import com._520it.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

@Controller
public class TestController extends BaseController {

	@Autowired
	private ITestService service;
	@Autowired
	private ICourseService courseService;
	@Autowired
	private IPotenStudentService psService;

	@RequestMapping("/test")
	@RequiresPermissions("test:view")
	@PermissionName("考试成绩主页")
	public String index() {
		return "test";
	}

	@RequestMapping("/test_list")
	@ResponseBody
	@RequiresPermissions("test:list")
	@PermissionName("成绩表")
	public PageResult list(TestQuery qo) {
		return service.query(qo);
	}

	@RequestMapping("/test_listAll")
	@ResponseBody
	@RequiresPermissions("test:listAll")
	@PermissionName("所有成绩")
	public List<Test> listAll() {
		return service.selectAll();
	}

	@RequestMapping("/test_save")
	@ResponseBody
	@RequiresPermissions("test:save")
	@PermissionName("保存成绩单")
	public AjaxResult save(Test test) {
		try {
			if (test.getTestResult() >= 60) {
				service.insert(test);
				return new AjaxResult(true, "保存成功!");
			}
			List<Test> list = service.selectTestByName(test.getPotenStudent().getId());
			if (list.size() > 2) {
				double score = 0;
				for (Test test2 : list) {
					if (score < test2.getTestResult()) {
						score = test2.getTestResult();
					}
				}
				if (score < 60) {
					return new AjaxResult(false, "该学员资质有限,建议另寻出路");
				}
			}
			service.insert(test);
			return new AjaxResult(true, "保存成功!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AjaxResult(false, "保存失败!");
	}

	@RequestMapping("/test_delete")
	@ResponseBody
	@RequiresPermissions("test:delete")
	@PermissionName("删除成绩单")
	public AjaxResult delete(Long id) {
		try {
			service.deleteByPrimaryKey(id);
			return new AjaxResult(true, "删除成功!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AjaxResult(false, "删除失败!");
	}

	@RequestMapping("/test_update")
	@ResponseBody
	@PermissionName("更新成绩单")
	@RequiresPermissions("test:update")
	public AjaxResult update(Test test) {
		try {
			service.updateByPrimaryKey(test);
			return new AjaxResult(true, "更新成功!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AjaxResult(false, "更新失败!");
	}

	/* ================================================== */
	@RequestMapping("/test_selectTestByName")
	@ResponseBody
	public List<Test> selectTestByName(Long id) {
		try {
			return service.selectTestByName(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("/test_selectByResult")
	@ResponseBody
	public List<Test> selectByResult(Long id) {
		try {
			return service.selectByResult(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/* ================================================= */

	/*---------------------------------------------------*/
	@RequestMapping("/test_export")
	@ResponseBody
	@PermissionName("导出成绩单")
	@RequiresPermissions("test:export")
	public void export(HttpServletResponse response) throws Exception {
		// 文件下载响应头
		response.setHeader("Content-Disposition", "attachment;filename=test.xls");
		WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());
		// 创建工作薄
		WritableSheet sheet = workbook.createSheet("test01", 0);
		// 设置列标题
		sheet.addCell(new Label(0, 0, "考试编号"));
		sheet.addCell(new Label(1, 0, "考试科目"));
		sheet.addCell(new Label(2, 0, "学员名称"));
		sheet.addCell(new Label(3, 0, "QQ"));
		sheet.addCell(new Label(4, 0, "电话"));
		sheet.addCell(new Label(5, 0, "考试时间"));
		sheet.addCell(new Label(6, 0, "考试结果"));
		List<Test> list = service.selectAll();

		for (int i = 0, j = 1; i < list.size(); i++, j++) {
			Test test = list.get(i);
			sheet.addCell(new Label(0, j, test.getSn()));
			sheet.addCell(new Label(1, j, test.getCourse().getName()));
			sheet.addCell(new Label(2, j, test.getPotenStudent().getName()));
			sheet.addCell(new Label(3, j, test.getPotenStudent().getQq()));
			sheet.addCell(new Label(4, j, test.getPotenStudent().getTel()));
			sheet.addCell(new Label(5, j, test.getTestTime().toLocaleString()));
			sheet.addCell(new Label(6, j, test.getTestResult() + ""));
		}
		// 写入数据
		workbook.write();
		// 关闭资源
		workbook.close();
	}

	/**
	 * @param file
	 * @throws Exception
	 */
	@RequestMapping("/test_import")
	@ResponseBody
	public AjaxResult importFile(MultipartFile file) throws Exception {
		Workbook workbook = Workbook.getWorkbook(file.getInputStream());
		// 获取工作薄
		Sheet sheet = workbook.getSheet(0);
		// 获取总行数
		int rows = sheet.getRows();
		for (int i = 1; i < rows; i++) {
			Test test = new Test();
			test.setSn(sheet.getCell(0, i).getContents());
			// 测试科目
			String courseName = sheet.getCell(1, i).getContents();
			Course course = courseService.selectIdByName(courseName);
			test.setCourse(course);
			// 测试学员
			String psName = sheet.getCell(2, i).getContents();
			String qq = sheet.getCell(3, i).getContents();
			String tel = sheet.getCell(4, i).getContents();
			PotenStudent potenStudent = psService.selectPpotenStudentByTel(tel);
			// 测试时间
			String testTime = sheet.getCell(5, i).getContents();
			test.setTestTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(testTime));
			// 测试结果
			String testResult = sheet.getCell(6, i).getContents();
			test.setTestResult(Double.parseDouble(testResult));
			try {
				if (potenStudent.getId() == null) {
					return new AjaxResult(false, "该学员不具备考试资格");
				} else {
					potenStudent.setName(psName);
					potenStudent.setQq(qq);
					potenStudent.setTel(tel);
					test.setPotenStudent(potenStudent);
					service.insert(test);
					return new AjaxResult(true, "导入成功");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		workbook.close();
		return new AjaxResult(false, "该学员不具备考试资格");
	}

	/*---------------------------------------------------*/
}
