package com._520it.crm.service.impl;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Role;
import com._520it.crm.domain.Student;
import com._520it.crm.mapper.EmployeeMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.EmployeeQuery;
import com._520it.crm.service.IEmployeeService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {
	@Autowired
	private EmployeeMapper mapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		//先删除中间表关系
		mapper.deleteRelation(id);

		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Employee record) {
		//密码加密(直接使用用户名当做盐)
		record.setPassword(new Md5Hash(record.getPassword(),record.getUsername()).toString());

		int count = mapper.insert(record);

		//处理中间表关联关系
		List<Role> roles = record.getRoles();
		if (roles.size() > 0) {
			for (Role role : roles) {
				mapper.insertRelation(record.getId(), role.getId());
			}
		}
		return count;
	}

	@Override
	public Employee selectByPrimaryKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Employee> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(Employee record) {
		//先删除中间表关系
		mapper.deleteRelation(record.getId());

		//重新关联关系
		List<Role> roles = record.getRoles();
		if (roles.size() > 0) {
			for (Role role : roles) {
				mapper.insertRelation(record.getId(), role.getId());
			}
		}

		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryPageResult(EmployeeQuery qo) {
		//查询总结果数
		Long count = mapper.queryPageCount(qo);
		if (count == 0) {
			return new PageResult(count, Collections.EMPTY_LIST);
		}

		//查询结果集
		return new PageResult(count, mapper.queryPageResult(qo));
	}

	@Override
	public void changeState(Long id) {
		mapper.changeState(id);
	}

	@Override
	public Employee getEmployeeByUsername(String username) {
		return mapper.getEmployeeByUsername(username);
	}

	@Override
	public List<Employee> selectEmployeeByDept(Long id) {
		return mapper.selectEmployeeByDept(id);
	}

	@Override
	public void setSystemMumber(Student student) {
		Employee e = new Employee();

	}

}
