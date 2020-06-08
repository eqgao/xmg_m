package com._520it.crm.web.controller;

import com._520it.crm.domain.Menu;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.MenuQuery;
import com._520it.crm.service.IMenuService;
import com._520it.crm.util.AjaxResult;
import com._520it.crm.util.CheckMenuUtil;
import com._520it.crm.util.PermissionName;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MenuController {

	@Autowired
	private IMenuService service;


	@RequestMapping("/menu")
	@RequiresPermissions("menu:view")
	@PermissionName("菜单主页")
	public String index() {
		return "menu";
	}

	@RequestMapping("/menu_list")
	@ResponseBody
	public List<Menu> list(){
		//把菜单缓存起来
		Session session = SecurityUtils.getSubject().getSession();
		//判断session中是否已经存在该用户的菜单,如果存在就直接取,不存在就查询数据库再放进去
		List<Menu> menus = (List<Menu>) session.getAttribute("MENU_IN_SESSION");
		if(menus==null){
			//查询所有的菜单
			menus = service.getRootMenu();
			//过滤菜单,如果没有权限的菜单,就从集合中移除掉
			CheckMenuUtil.checkMenu((List)menus);
			//放入session中
			session.setAttribute("MENU_IN_SESSION",menus);
		}
		return menus;
	}

	@RequestMapping("/menu_listAll")
	@ResponseBody
	public PageResult listAll(MenuQuery qo) {
		return service.queryPageResult(qo);
	}

	@RequestMapping("/menu_save")
	@ResponseBody
	@RequiresPermissions("menu:save")
	@PermissionName("菜单新增")
	public AjaxResult save(Menu menu) {
		//默认是在职状态
		try {
			service.insert(menu);
			return new AjaxResult(true, "保存成功!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AjaxResult(false, "保存失败!");
	}

	@RequestMapping("/menu_update")
	@ResponseBody
	@PermissionName("菜单编辑")
	@RequiresPermissions("menu:update")
	public AjaxResult update(Menu menu) {
		try {
			service.updateByPrimaryKey(menu);
			return new AjaxResult(true, "更新成功!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new AjaxResult(false, "更新失败!");
	}

    @RequestMapping("/menu_delete")
    @ResponseBody
    @PermissionName("删除菜单")
    @RequiresPermissions("menu:del")
    public AjaxResult changeState(Long id) {
        try {
            service.deleteByPrimaryKey(id);
            return new AjaxResult(true, "删除成功!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new AjaxResult(false, "删除失败!");
    }

    @RequestMapping("/menu_listParent")
    @ResponseBody
    public List<Menu> listAll() {
        return service.getRootMenu();
    }
}
