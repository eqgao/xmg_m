package com._520it.crm.web.controller;

import com._520it.crm.domain.SqlConnect;
import com._520it.crm.service.IMenuService;
import com._520it.crm.util.AjaxResult;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Controller
public class MysqlController {

    @Autowired
    private IMenuService service;

    @RequestMapping("/sql_in")
    public String mysqlIn(MultipartFile file, HttpServletRequest request, SqlConnect sqlConnect) {
        /*==========================================*/
        FileOutputStream out = null;
        try {
            String fileName = file.getOriginalFilename();
            String uuid = UUID.randomUUID().toString();
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            fileName = uuid + fileType;

            String path = request.getSession().getServletContext().getRealPath("/sql");

            out = new FileOutputStream(new File(path + "/" + fileName));
            IOUtils.copy(file.getInputStream(), out);

            String filepath = "/sql/" + fileName;
            String sqlPath = path + "/" + fileName;
        /*==========================================*/
            String ip = sqlConnect.getIp();//ip
            if ("".equals(ip)) {
                ip = "localhost";
            }
            String port = sqlConnect.getPort();//端口
            if ("".equals(port)) {
                port = "3306";
            }
            String user = sqlConnect.getUsername(); // 数据库帐号
            String password = sqlConnect.getPassword(); // 登陆密码
            String database = sqlConnect.getDatabase(); // 需要备份的数据库名
            String stmt1 = "mysql -h " + ip + " -P " + port + " -u" + user + " -p" + password + " " + database + " < " + sqlPath;



            // 备份的路径地址
            // String filepath = "C:/Users/Administrator/Desktop/"+fileName+".sql";
            // String filepath = "C:\Users\Administrator\Desktop\testcopy.sql";
            String[] cmd = {"cmd", "/c", stmt1};
            //Runtime.getRuntime().exec(stmt1);
            Runtime.getRuntime().exec(cmd);
            System.out.println("导入成功!!数据已从 " + sqlPath + " 导入到数据库中");
            return "permission";
            // System.out.println("数据已从 " + filepath + " 导入到数据库中");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("导入失败!");
            return "permission";
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    @RequestMapping("/sql_bak")
    @ResponseBody
    public AjaxResult mysqlBak(SqlConnect sqlConnect) {
        // 数据库导出
        String ip = sqlConnect.getIp();//ip
        if ("".equals(ip)) {
            ip = "localhost";
        }
        String port = sqlConnect.getPort();//端口
        if ("".equals(port)) {
            port = "3306";
        }
        String user = sqlConnect.getUsername(); // 数据库帐号
        String password = sqlConnect.getPassword(); // 登陆密码
        String database = sqlConnect.getDatabase(); // 需要备份的数据库名
        String filepath = "C:/" + sqlConnect.getBakName() + ".sql"; // 备份的路径地址
        String stmt1 = "mysqldump -h " + ip + " -P " + port + " -u" + user + " -p" + password + " " + database + " --result-file=" + filepath;
        System.out.println(stmt1);

        /*
          String mysql="mysqldump test -u root -proot
		  --result-file=d:\\test.sql";
		 */
        try {
            Runtime.getRuntime().exec(stmt1);
            return new AjaxResult(true, "备份成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new AjaxResult(false, "备份失败");
    }

}
