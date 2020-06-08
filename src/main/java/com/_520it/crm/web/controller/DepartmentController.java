package com._520it.crm.web.controller;

import java.util.List;

import com._520it.crm.domain.Department;
import com._520it.crm.domain.Employee;
import com._520it.crm.service.IDepartmentService;
import com._520it.crm.service.IEmployeeService;
import com._520it.crm.util.AjaxResult;
import com._520it.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DepartmentController {

	@Autowired
	private IDepartmentService service;
	@Autowired
	private IEmployeeService employeeService;

	@RequestMapping("/department")
	@RequiresPermissions("department:view")
	@PermissionName("部门主页")
	public String index() {
		return "department";
	}

	/*
	 * @RequestMapping("/department_listAll")
	 * 
	 * @ResponseBody
	 * 
	 * @RequiresPermissions("department:list")
	 * 
	 * @PermissionName("部门列表") public List<Department> listAll(){
	 * 
	 * return service.selectAll(); }
	 */

	@RequestMapping("/department_list")
	@ResponseBody
	@RequiresPermissions("department:list")
	@PermissionName("部门列表")
	public List<Department> list() {

		return service.selectList();
	}

	@RequestMapping("/department_save")
	@ResponseBody
	@RequiresPermissions("department:save")
	@PermissionName("部门列表")
	public AjaxResult save(Department dept) {
		try {
			String name = dept.getName();
			String sn = dept.getSn();
			Department dept2 = service.selectDepartmentBySn(sn, name);
			if (dept2 == null) {
				// 默认正常状态
				dept.setState(true);
				service.insert(dept);
				return new AjaxResult(true, "保存成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AjaxResult(false, "不能保存同一个部门!");
	}

	@RequestMapping("/department_update")
	@ResponseBody
	@RequiresPermissions("department:update")
	@PermissionName("部门列表")
	public AjaxResult update(Department dept) {
		try {
			// 默认正常状态
			dept.setState(true);
			String name = dept.getName();
			String sn = dept.getSn();
			Department oleDept = service.selectDepartmentBySn(sn, name);
			if (oleDept != null) {
				service.updateByPrimaryKey(dept);
				return new AjaxResult(true, "编辑成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AjaxResult(false, "编辑失败");
	}

	@RequestMapping("/department_changeState")
	@ResponseBody
	@RequiresPermissions("department:changeState")
	@PermissionName("取消部门")
	public AjaxResult changeState(Department dept) {
		try {
			List<Department> deptList = service.selectChildDept(dept.getId());
			if (deptList.size() > 0) {
				return new AjaxResult(false, "该部门下面还有其他部门,不能取消!");
			}
			List<Employee> list = employeeService.selectEmployeeByDept(dept.getId());
			if (list.size() < 1) {
				service.changeDept(dept.getId());
				return new AjaxResult(true, "您成功取消了一个部门");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AjaxResult(false, "该部门还有任劳任怨的员工,不能取消!");
	}

}
