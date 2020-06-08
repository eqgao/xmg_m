package com._520it.crm.mapper;

import com._520it.crm.domain.ClassRoom;
import com._520it.crm.domain.Course;
import com._520it.crm.query.CourseQuery;

import java.util.List;

public interface CourseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Course record);

    Course selectByPrimaryKey(Long id);

    /*==========================================*/
    Course selectCourseByClassId(Long classId);
    /*==========================================*/

    List<Course> selectAll();

    int updateByPrimaryKey(Course record);

    //总数据数
    Long queryPageCount(CourseQuery qo);

    //结果集
    List<ClassRoom> queryPageResult(CourseQuery qo);
    
    Course selectIdByName(String courseName);
}