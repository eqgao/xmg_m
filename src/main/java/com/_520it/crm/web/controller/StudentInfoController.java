package com._520it.crm.web.controller;

import com._520it.crm.domain.Address;
import com._520it.crm.domain.StudentInfo;
import com._520it.crm.service.IStudentInfoService;
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
import java.util.List;
import java.util.UUID;

@Controller
public class StudentInfoController extends BaseController {

    @Autowired
    private IStudentInfoService service;

    @RequestMapping("/studentInfo_listAll")
    @ResponseBody
    @RequiresPermissions("studentInfo:listAll")
    @PermissionName("潜在学员信息全查")
    public List<StudentInfo> list() {
        return service.selectAll();
    }

    @RequestMapping("/studentInfo_save")
    @RequiresPermissions("studentInfo:save")
    @PermissionName("潜在学员信息保存")
    @ResponseBody
    public StudentInfo save(StudentInfo studentInfo, Address address, MultipartFile file, HttpServletRequest request) {

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

            studentInfo.setImage(imgUrl);
            service.insert(studentInfo, address);
            return studentInfo;

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
        return null;
    }

    @RequestMapping("/studentInfo_update")
    @RequiresPermissions("studentInfo:update")
    @PermissionName("潜在学员信息修改")
    @ResponseBody
    public StudentInfo update(StudentInfo studentInfo, MultipartFile file, HttpServletRequest request) {
        FileOutputStream out = null;
        System.out.println(studentInfo);
        try {
            Long id = studentInfo.getId();
            StudentInfo studentInfo1 = service.selectByPrimaryKey(id);
            if (studentInfo.getAddress().getId() == null) {
                Address address = studentInfo1.getAddress();
                studentInfo.setAddress(address);
            }
            if ("".equals(file.getOriginalFilename())) {
                String image = studentInfo1.getImage();
                studentInfo.setImage(image);
                service.updateByPrimaryKey(studentInfo);
                return studentInfo;

            }
            String fileName = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            fileName = uuid + fileType;


            String path = request.getSession().getServletContext().getRealPath("/upload");

            out = new FileOutputStream(new File(path + "/" + fileName));
            IOUtils.copy(file.getInputStream(), out);

            String imgUrl = "/upload/" + fileName;
            studentInfo.setImage(imgUrl);
            service.updateByPrimaryKey(studentInfo);
            return studentInfo;
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
        return null;
    }

}
