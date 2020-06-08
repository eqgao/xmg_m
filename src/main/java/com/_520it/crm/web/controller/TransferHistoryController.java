package com._520it.crm.web.controller;

import com._520it.crm.domain.PotenStudent;
import com._520it.crm.domain.TransferHistory;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.TransferHistoryQuery;
import com._520it.crm.service.IPotenStudentService;
import com._520it.crm.service.ITransferHistoryService;
import com._520it.crm.util.AjaxResult;
import com._520it.crm.util.PermissionName;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.UUID;

@Controller
public class TransferHistoryController extends BaseController {

    @Autowired
    private ITransferHistoryService service;
    /*============================================*/
    @Autowired
    private IPotenStudentService potenStudentService;
    /*============================================*/

    @RequestMapping("/transferHistory")
    @RequiresPermissions("transferHistory:view")
    @PermissionName("移交历史主页")
    public String index() {
        return "transferHistory";
    }

    @RequestMapping("/transferHistory_list")
    @ResponseBody
    @RequiresPermissions("transferHistory:list")
    @PermissionName("移交历史列表")
    public PageResult list(TransferHistoryQuery qo) {

        return service.queryPageResult(qo);
    }

    @RequestMapping("/transfer_save")
    @ResponseBody
    @RequiresPermissions("transferHistory:save")
    @PermissionName("保存移交记录")
    public AjaxResult list(PotenStudent potenStudent, MultipartFile file, HttpServletRequest request) {
        FileOutputStream out = null;
        try {

            String fileName = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            fileName = uuid + fileType;


            String path = request.getSession().getServletContext().getRealPath("/upload");

            out = new FileOutputStream(new File(path + "/" + fileName));
            IOUtils.copy(file.getInputStream(), out);

            String imgUrl = "/upload/" + fileName;

            //获取该潜在学员的id
            Long potenStudentId = potenStudent.getId();
            //查询该潜在学员
            PotenStudent potenStudent1 = potenStudentService.selectByPrimaryKey(potenStudentId);

            //移交表添加数据
            TransferHistory history = new TransferHistory();
            history.setOldEmployee(
                    potenStudent1.getEmployee());
            history.setCause(potenStudent.getRemark());
            history.setPotenStudent(potenStudent);
            history.setNewEmployee(potenStudent.getEmployee());
            history.setTransTime(new Date());
            history.setImgUrl(imgUrl);
            service.insert(history);


            //潜在学员表咨询师更改
            potenStudentService.updateEmp(potenStudent);

            return new AjaxResult(true, "移交成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return new AjaxResult(false, "移交失败");
    }


}
