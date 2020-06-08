package com._520it.crm.util;

import java.util.Iterator;
import java.util.List;

import com._520it.crm.domain.Menu;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

public class CheckMenuUtil {

	/**
	 * 根据权限过滤菜单
	 * @param menus
	 */
	 public static void checkMenu(List<Menu> menus) {
		 //如果当前用户拥有该菜单对应的权限,则可以显示
		 Iterator<Menu> iterator = menus.iterator();
		 while(iterator.hasNext()){
			 Menu menu = iterator.next();
			 //判断该menu是否关联着权限对象
			 if(menu.getPermission()!=null){
				 //如果关联则需要进行判断
				 Subject subject = SecurityUtils.getSubject();
				 //判断登录用户是否拥有该权限
				 if(!subject.isPermitted(menu.getPermission().getResource())){
					 //没有权限则从菜单集合中移除
					 iterator.remove();
					 continue;
				 }
			 }
			 //如果该菜单有子菜单,则继续进行判断
			 if(menu.getChildren().size()>0){
				 //递归操作
				 checkMenu(menu.getChildren());
			 }
		 }
	 }
}
