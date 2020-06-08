package com._520it.crm.mapper;

import com._520it.crm.domain.Address;

import java.util.List;

public interface AddressMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Address record);

    Address selectByPrimaryKey(Long id);

    List<Address> selectAll();

    List<Address> selectByCode(String parentCode);

    List<Address> selectListByPrimaryKey(Long addressId);

    int updateByPrimaryKey(Address record);
}