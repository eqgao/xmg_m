package com._520it.crm.web.controller;

import com._520it.crm.domain.Address;
import com._520it.crm.service.IAddressService;
import com._520it.crm.util.PermissionName;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class AddressController extends BaseController {

    @Autowired
    private IAddressService service;


    @RequestMapping("/address_selectByCode")
    @ResponseBody
    @RequiresPermissions("address:selectByCode")
    @PermissionName("地址查询")
    public List<Address> list(String parentCode) {
        if (parentCode != null) {
            return service.selectByCode(parentCode);
        }
        return service.selectAll();
    }

    @RequestMapping("/address_selectListByPrimaryKey")
    @ResponseBody
    public List<Address> list(Long addressId) {
        System.out.println(addressId);
        if (addressId != null) {
            return service.selectListByPrimaryKey(addressId);
        }
        return null;
    }

}
