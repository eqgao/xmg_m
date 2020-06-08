package com._520it.crm.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Student;
import com._520it.crm.domain.StudentLoss;
import com._520it.crm.mapper.StudentLossMapper;
import com._520it.crm.mapper.StudentMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.StudentLossQuery;
import com._520it.crm.service.IStudentLossService;

@Service
public class StudentLossServiceImpl implements IStudentLossService{
	@Autowired
	private StudentLossMapper mapper;
	@Autowired
	private StudentMapper studentmapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}
	@Override
	public int insert(Long id,String cause) {
		Student student = studentmapper.selectBySid(id);
		StudentLoss studentloss = new StudentLoss();
		studentloss.setStudentname(student.getName());
		studentloss.setClassinfoname(student.getClassinfo().getName());
		studentloss.setQq(student.getQq());
		studentloss.setTel(student.getTel());
		studentloss.setLosttime(new Date());
		//获取当前登录的员工对象
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        studentloss.setHandler(employee.getRealname());
        studentloss.setCause(cause);
      //如果退学成功则,改变状态为退学
        studentmapper.changeDrop(id);
		return mapper.insert(studentloss);
	}

	@Override
	public StudentLoss selectByPrimaryKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<StudentLoss> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(StudentLoss record) {
		return mapper.updateByPrimaryKey(record);
		
	}
	@Override
	public PageResult queryPageResult(StudentLossQuery qo) {
		//查询总结果数
		Long count = mapper.queryPageCount(qo);
		if (count == 0) {
			return new PageResult(count, Collections.EMPTY_LIST);
		}

		//查询结果集
		return new PageResult(count, mapper.queryPageResult(qo));
	}

}
