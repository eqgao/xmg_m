package com._520it.crm.web.controller;

import com._520it.crm.domain.ClassRoom;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.ClassRoomQuery;
import com._520it.crm.service.IClassRoomService;
import com._520it.crm.util.AjaxResult;
import com._520it.crm.util.PermissionName;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class ClassRoomController {

	@Autowired
	private IClassRoomService service;


	@RequestMapping("/classroom")
	@PermissionName("教室主页")
	public String index(){
		return "classroom";
	}

	@RequestMapping("/classRoom_listAll")
	@ResponseBody
	public List<ClassRoom> list(){
		return service.selectAll();
	}

	@RequestMapping("/classRoom_list")
	@ResponseBody
	@RequiresPermissions("classRoom:list")
	@PermissionName("教室管理")
	public PageResult list(ClassRoomQuery qo){
		return service.queryPageResult(qo);
	}

	@RequestMapping("/classroom_save")
	@ResponseBody
	@RequiresPermissions("classroom:save")
	@PermissionName("新增教室")
	public AjaxResult save(ClassRoom room){
		//默认是未启用状态
		room.setStatus(false);
		try{
			service.insert(room);
			return new AjaxResult(true,"保存成功!");
		}catch(Exception e){
			e.printStackTrace();
		}
		return new AjaxResult(false,"保存失败!");
	}

	@RequestMapping("/classroom_update")
	@ResponseBody
	@PermissionName("更新教室")
	@RequiresPermissions("classroom:update")
	public AjaxResult update(ClassRoom room){
		try{
			service.updateByPrimaryKey(room);
			return new AjaxResult(true,"更新成功!");
		}catch(Exception e){
			e.printStackTrace();
		}
		return new AjaxResult(false,"更新失败!");
	}

	@RequestMapping("/classroom_delete")
	@ResponseBody
	@PermissionName("删除教室")
	@RequiresPermissions("classroom:del")
	public AjaxResult changeState(Long id) {
		try {
			service.deleteByPrimaryKey(id);
			return new AjaxResult(true, "删除成功!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AjaxResult(false, "删除失败!");
	}

	//导出
	@RequestMapping("/classroom_export")
	@ResponseBody
	public void export(HttpServletResponse response) throws Exception {
		//文件下载响应头
		response.setHeader("Content-Disposition", "attachment;filename=a.xls");
		//响应到浏览器
		WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());

		//创建工作簿sheet
		WritableSheet sheet = workbook.createSheet("day01", 0);

		//设置列标题
		sheet.addCell(new Label(0, 0, "用户名"));
		sheet.addCell(new Label(1, 0, "真实姓名"));
		sheet.addCell(new Label(2, 0, "电话"));
		sheet.addCell(new Label(3, 0, "状态"));

		//把员工的数据填充到工作簿中
		List<ClassRoom> list = service.selectAll();

		for (int i = 0, j = 1; i < list.size(); i++, j++) {
			ClassRoom room = list.get(i);
			sheet.addCell(new Label(0, j, room.getName()));
			sheet.addCell(new Label(1, j, room.getLocation()));
			sheet.addCell(new Label(2, j, String.valueOf(room.getNum())));
			sheet.addCell(new Label(3, j, room.getStatus()?"启用":"未启用"));
		}

		//写入数据
		workbook.write();
		//关闭资源
		workbook.close();
	}

	//导入
	@RequestMapping("/classroom_import")
	@ResponseBody
	public void importFile(MultipartFile file) throws Exception {
		//获取用户上传的文件
		Workbook workbook = Workbook.getWorkbook(file.getInputStream());
		//获取工作簿sheet
		Sheet sheet = workbook.getSheet(0);

		//获取总行数
		int rows = sheet.getRows();
		for (int i = 1; i < rows; i++) {
			ClassRoom room = new ClassRoom();
			room.setName(sheet.getCell(0, i).getContents());
			room.setLocation(sheet.getCell(1, i).getContents());
			room.setStatus(Boolean.valueOf(sheet.getCell(1, i).getContents()));
			room.setNum(Integer.valueOf(sheet.getCell(3, i).getContents()));
			//添加到数据库中
			service.insert(room);
		}
		//关闭资源
		workbook.close();
	}
}
