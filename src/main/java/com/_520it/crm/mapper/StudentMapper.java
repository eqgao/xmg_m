package com._520it.crm.mapper;

import com._520it.crm.domain.Student;
import com._520it.crm.query.StudentQuery;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface StudentMapper {
	 int deleteByPrimaryKey(Long id);

	    int insert(Student record);

	    Student selectByPrimaryKey(Long id);

	    Student selectBySid(Long Sid);

	    List<Student> selectAll();

	    int updateByPrimaryKey(Student record);

	    Long queryPageCount(StudentQuery qo);

	    List<Student> queryPageResult(StudentQuery qo);

	    void changeState(Long sId);

	    void changeDrop(Long sId);

		void changefeesState(Long id);

		void updateClassinfo(@Param("cid")Long cid,@Param("sid") Long sid);

		void changeGraduiteStatus(Long id);


}