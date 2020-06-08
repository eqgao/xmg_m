package com._520it.crm.web.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com._520it.crm.domain.Classinfo;
import com._520it.crm.domain.Upgrade;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.UpgradeQueryObject;
import com._520it.crm.service.IClassinfoService;
import com._520it.crm.service.IUpgradeService;
import com._520it.crm.util.AjaxResult;
import com._520it.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * Created by hasee on 2017/8/26.
 */
@Controller
public class UpgradeController {

    @Autowired
    private IUpgradeService service;
    @Autowired
    private IClassinfoService classinfoService;

    @RequestMapping("/upgrade")
    @RequiresPermissions("upgrade:view")
    @PermissionName("升留级主页")
    public String index() {
        return "upgrade";
    }


    @RequestMapping("/upgrade_listClass")
    @RequiresPermissions("upgrade:listClass")
    @PermissionName("员工主页")
    @ResponseBody
    public List<Classinfo> listClass() {
        List<Classinfo> classinfos = classinfoService.selectAll();
        return classinfos;
    }


    @RequestMapping("/upgrade_update")
    @ResponseBody
    @RequiresPermissions("upgrade:update")
    @PermissionName("升留级更新")
    public AjaxResult update(Upgrade upgrade) {
        try {
            service.updateByPrimaryKey(upgrade);
            return new AjaxResult(true, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "更新失败");
        }

    }

    @RequestMapping("/upgrade_del")
    @ResponseBody
    @RequiresPermissions("upgrade:del")
    @PermissionName("升留级删除")
    public AjaxResult del(Long id) {
        try {
            service.deleteByPrimaryKey(id);
            return new AjaxResult(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "删除失败");
        }
    }

    @RequestMapping("/upgrade_list")
    @ResponseBody
    @RequiresPermissions("upgrade:list")
    @PermissionName("升留级列表")
    public PageResult list(UpgradeQueryObject qo) {
        PageResult pageResult = service.queryPageResult(qo);
        System.out.println(pageResult);
        return pageResult;
    }

    @RequestMapping("/upgrade_export")
    @ResponseBody
    public void export(HttpServletResponse response) throws Exception {
        //文件下载响应头
        response.setHeader("Content-Disposition", "attachment;filename=a.xls");
        //响应到浏览器
        WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());

        //创建本地xls文件
        //WritableWorkbook workbook = Workbook.createWorkbook(new File("F:/a.xls"));
        //创建工作簿sheet
        WritableSheet sheet = workbook.createSheet("升留级表", 0);

		/*//创建文本格式单元格
        Label cell = new Label(0,0,"hello");
		//把单元格添加到工作簿中
		sheet.addCell(cell);
		//创建日期格式单元格
		DateTime dateTime = new DateTime(1, 0, new Date());
		//把单元格添加到工作簿中
		sheet.addCell(dateTime);*/


        //设置列标题
        sheet.addCell(new Label(0, 0, "编号"));
        sheet.addCell(new Label(1, 0, "姓名"));
        sheet.addCell(new Label(2, 0, "QQ"));
        sheet.addCell(new Label(3, 0, "电话"));
        sheet.addCell(new Label(4, 0, "营销人员"));
        sheet.addCell(new Label(5, 0, "升班/留级时间"));
        sheet.addCell(new Label(6, 0, "流入班级"));
        sheet.addCell(new Label(7, 0, "升级/留级"));


        //把员工的数据填充到工作簿中
        List<Upgrade> list = service.selectAll();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0, j = 1; i < list.size(); i++, j++) {
            Upgrade upgrade = list.get(i);
            sheet.addCell(new Label(0, j, upgrade.getId() + ""));
            sheet.addCell(new Label(1, j, upgrade.getStudent().getName()));
            sheet.addCell(new Label(2, j, upgrade.getStudent().getQq()));
            sheet.addCell(new Label(3, j, upgrade.getStudent().getTel()));
            sheet.addCell(new Label(4, j, upgrade.getEmp().getRealname()));
            sheet.addCell(new Label(5, j, sdf.format(upgrade.getUpgradetime())));
            sheet.addCell(new Label(6, j, upgrade.getClassinfo().getName()));
            sheet.addCell(new Label(7, j, upgrade.getStatus() == 0 ? "留级" : "升班"));
        }

        //写入数据
        workbook.write();
        //关闭资源
        workbook.close();
    }


}
