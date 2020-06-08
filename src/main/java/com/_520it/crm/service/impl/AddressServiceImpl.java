package com._520it.crm.service.impl;

import com._520it.crm.domain.Address;
import com._520it.crm.mapper.AddressMapper;
import com._520it.crm.service.IAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements IAddressService {
    @Autowired
    private AddressMapper mapper;

    @Override
    public int deleteByPrimaryKey(Long id) {

        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Address record) {

        return mapper.insert(record);

    }

    @Override
    public Address selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Address> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public List<Address> selectByCode(String parentCode) {
        return mapper.selectByCode(parentCode);
    }
    @Override
    public List<Address> selectListByPrimaryKey(Long addressId) {
        return mapper.selectListByPrimaryKey(addressId);
    }

    @Override
    public int updateByPrimaryKey(Address record) {

        return mapper.updateByPrimaryKey(record);
    }


}
