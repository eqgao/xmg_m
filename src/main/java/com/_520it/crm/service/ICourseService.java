package com._520it.crm.service;

import com._520it.crm.domain.Course;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.CourseQuery;

import java.util.List;

public interface ICourseService {

	int deleteByPrimaryKey(Long id);

	int insert(Course record);

	Course selectByPrimaryKey(Long id);

	List<Course> selectAll();

	int updateByPrimaryKey(Course record);

	PageResult queryPageResult(CourseQuery qo);

	
	Course selectIdByName(String courseName);
}
