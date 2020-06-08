package com._520it.crm.service.impl;

import com._520it.crm.domain.Course;
import com._520it.crm.mapper.CourseMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.CourseQuery;
import com._520it.crm.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {
	@Autowired
	private CourseMapper mapper;

	@Override
	public int deleteByPrimaryKey(Long id) {
		return mapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Course record) {
        //保存班级
        int count = mapper.insert(record);
        return count;
	}

	@Override
	public Course selectByPrimaryKey(Long id) {
		return mapper.selectByPrimaryKey(id);
	}

	@Override
	public List<Course> selectAll() {
		return mapper.selectAll();
	}

	@Override
	public int updateByPrimaryKey(Course record) {
		return mapper.updateByPrimaryKey(record);
	}

	@Override
	public PageResult queryPageResult(CourseQuery qo) {
		//查询总结果数
		Long count = mapper.queryPageCount(qo);
		if (count == 0) {
			return new PageResult(count, Collections.EMPTY_LIST);
		}

		//查询结果集
		return new PageResult(count, mapper.queryPageResult(qo));
	}

	@Override
	public Course selectIdByName(String courseName) {
		return mapper.selectIdByName(courseName);
	}
}
