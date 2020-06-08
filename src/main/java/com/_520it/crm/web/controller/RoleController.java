package com._520it.crm.web.controller;

import com._520it.crm.domain.Role;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.EmployeeQuery;
import com._520it.crm.service.IRoleService;
import com._520it.crm.util.AjaxResult;
import com._520it.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class RoleController {

	@Autowired
	private IRoleService service;

	@RequestMapping("/role")

	public String index(){
		return "role";
	}

	@RequestMapping("/role_save")
	@ResponseBody
	@PermissionName("保存角色")
	@RequiresPermissions("role:save")
	public AjaxResult save(Role role){
		try{
			service.insert(role);
			return new AjaxResult(true,"保存成功!");
		}catch(Exception e){
			e.printStackTrace();
		}
		return new AjaxResult(false,"保存失败!");
	}

	@RequestMapping("/role_list")
	@ResponseBody
	@PermissionName("角色页面")
	@RequiresPermissions("role:list")

	public PageResult list(EmployeeQuery qo){
		return service.queryPageResult(qo);
	}
	@RequestMapping("/role_listAll")
	@ResponseBody
	public List<Role> selectAll( ){
		return service.selectAll();
	}

	@RequestMapping("/role_update")
	@ResponseBody
	@PermissionName("跟新角色")
	@RequiresPermissions("role:update")
	public AjaxResult update(Role role){
		try{
			service.updateByPrimaryKey(role);
			return new AjaxResult(true,"更新成功!");
		}catch(Exception e){
			e.printStackTrace();
		}
		return new AjaxResult(false,"更新失败!");
	}
	@RequestMapping("/role_delete")
	@ResponseBody
	@PermissionName("删除角色")
	@RequiresPermissions("role:delete")
	public AjaxResult delete(Long id){
		try{
			service.deleteByPrimaryKey(id);
			return new AjaxResult(true,"删除成功!");
		}catch(Exception e){
			e.printStackTrace();
		}
		return new AjaxResult(false,"删除失败!");
	}

	@RequestMapping("/getRolesIdByEmployeeId")
	@ResponseBody
	public List<Long> getRolesIdByEmployeeId(Long employeeId)
	{
		return service.getRolesIdByEmployeeId(employeeId);
	}
}
