package com._520it.crm.web.controller;

import com._520it.crm.domain.Classinfo;
import com._520it.crm.domain.Jobinfo;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.JobinfoQueryObject;
import com._520it.crm.service.IClassinfoService;
import com._520it.crm.service.IJobinfoService;
import com._520it.crm.util.AjaxResult;
import com._520it.crm.util.PermissionName;
import com.alibaba.fastjson.JSON;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by hasee on 2017/8/26.
 */
@Controller
public class JobinfoController {

    @Autowired
    private IJobinfoService service;
    @Autowired
    private IClassinfoService classinfoService;

    @RequestMapping("/jobinfo")
    @RequiresPermissions("jobinfo:view")
    @PermissionName("就业信息主页")
    public String index(HttpServletRequest req) {
        req.setAttribute("day", "date_format(j.time,'%Y-%m')");
        req.setAttribute("month", "date_format(j.time,'%Y-%m-%d')");
        return "jobinfo";
    }

    @RequestMapping("/jobinfo_chart")
    @RequiresPermissions("jobinfo:chart")
    @PermissionName("就业信息主页")
    public String chart(HttpServletRequest req, JobinfoQueryObject qo) {

        List<Map<String, Object>> maps = service.querySelectChart(qo);
        List<String> before = new ArrayList<>();
        List<String> after = new ArrayList<>();
        List<String> type = new ArrayList<>();
        List<String> avg = new ArrayList<>();
        BigDecimal max = new BigDecimal("0");
        BigDecimal avgMax = new BigDecimal("0");
        for (Map<String, Object> map : maps) {
            BigDecimal b = (BigDecimal) map.get("brforeSalary");
            BigDecimal a = (BigDecimal) map.get("afterSalary");
            BigDecimal av = (BigDecimal) map.get("avgSalary");
            if (a.compareTo(max) > 0) {
                max = a;
            }
            if (b.compareTo(max) > 0) {
                max = b;
            }
            if (av.compareTo(avgMax) > 0) {
                avgMax = av;
            }
            before.add(b.toString());
            after.add(a.toString());
            avg.add(av.toString());
            type.add(map.get("groupType").toString());
        }
        req.setAttribute("max", max.multiply(new BigDecimal("5")).divide(new BigDecimal("4")));
        req.setAttribute("type", JSON.toJSONString(type));
        req.setAttribute("before", JSON.toJSONString(before));
        req.setAttribute("after", JSON.toJSONString(after));
        req.setAttribute("avgMax", JSON.toJSONString(avgMax.multiply(new BigDecimal("5")).divide(new BigDecimal("4"))));
        req.setAttribute("avg", JSON.toJSONString(avg));

        req.setAttribute("listData", maps);
        return "chart/jobchart";
    }

    @RequestMapping("/jobinfo_listClass")
    @RequiresPermissions("jobinfo:listClass")
    @PermissionName("员工主页")
    @ResponseBody
    public List<Classinfo> listClass() {
        List<Classinfo> classinfos = classinfoService.selectAll();
        return classinfos;
    }

    @RequestMapping("/jobinfo_update")
    @ResponseBody
    @RequiresPermissions("jobinfo:update")
    @PermissionName("就业信息更新")
    public AjaxResult update(Jobinfo jobinfo) {
        try {
            service.updateByPrimaryKey(jobinfo);
            return new AjaxResult(true, "更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "更新失败");
        }
    }


    @RequestMapping("/jobinfo_del")
    @ResponseBody
    @RequiresPermissions("jobinfo:del")
    @PermissionName("就业信息删除")
    public AjaxResult del(Long id) {
        try {
            service.deleteByPrimaryKey(id);
            return new AjaxResult(true, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new AjaxResult(false, "删除失败");
        }
    }

    @RequestMapping("/jobinfo_list")
    @ResponseBody
    @RequiresPermissions("jobinfo:list")
    @PermissionName("就业信息列表")
    public PageResult list(JobinfoQueryObject qo) {
        PageResult pageResult = service.queryPageResult(qo);
        System.out.println(pageResult);
        return pageResult;
    }

    @RequestMapping("/jobinfo_export")
    @ResponseBody
    public void export(HttpServletResponse response) throws Exception {
//        //文件下载响应头
//        response.setHeader("Content-Disposition", "attachment;filename=a.xls");
//        //响应到浏览器
//        WritableWorkbook workbook = Workbook.createWorkbook(response.getOutputStream());
//
//        //创建本地xls文件
//        //WritableWorkbook workbook = Workbook.createWorkbook(new File("F:/a.xls"));
//        //创建工作簿sheet
//        WritableSheet sheet = workbook.createSheet("就业信息表", 0);
//
//		/*//创建文本格式单元格
//        Label cell = new Label(0,0,"hello");
//		//把单元格添加到工作簿中
//		sheet.addCell(cell);
//		//创建日期格式单元格
//		DateTime dateTime = new DateTime(1, 0, new Date());
//		//把单元格添加到工作簿中
//		sheet.addCell(dateTime);*/
//
//
//        //设置列标题
//        sheet.addCell(new Label(0, 0, "编号"));
//        sheet.addCell(new Label(1, 0, "姓名"));
//        sheet.addCell(new Label(2, 0, "QQ"));
//        sheet.addCell(new Label(3, 0, "电话"));
//        sheet.addCell(new Label(4, 0, "营销人员"));
//        sheet.addCell(new Label(5, 0, "升班/留级时间"));
//        sheet.addCell(new Label(6, 0, "流入班级"));
//        sheet.addCell(new Label(7, 0, "升级/留级"));
//
//
//        //把员工的数据填充到工作簿中
//        List<Jobinfo> list = service.selectAll();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        for (int i = 0, j = 1; i < list.size(); i++, j++) {
//            Jobinfo jobinfo = list.get(i);
//            sheet.addCell(new Label(0, j, jobinfo.getId() + ""));
//            sheet.addCell(new Label(1, j, jobinfo.getStudent().getName()));
//            sheet.addCell(new Label(2, j, jobinfo.getStudent().getQq()));
//            sheet.addCell(new Label(3, j, jobinfo.getStudent().getTel()));
//            sheet.addCell(new Label(4, j, jobinfo.getEmp().getRealname()));
//            sheet.addCell(new Label(5, j, sdf.format(jobinfo.getJobinfotime())));
//            sheet.addCell(new Label(6, j, jobinfo.getClassinfo().getName()));
//            sheet.addCell(new Label(7, j, jobinfo.getStatus() == 0 ? "留级" : "升班"));
//        }
//
//        //写入数据
//        workbook.write();
//        //关闭资源
//        workbook.close();
    }


}
