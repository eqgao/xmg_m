package com._520it.crm.mapper;

import com._520it.crm.domain.PotenStudent;
import com._520it.crm.query.QueryObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PotenStudentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PotenStudent record);

    PotenStudent selectByPrimaryKey(Long id);

    List<PotenStudent> selectAll();

    int updateByPrimaryKey(PotenStudent record);

    Long queryPageCount(QueryObject qo);

    List<PotenStudent> queryPageResult(QueryObject qo);

    List<PotenStudent> list();

    void change(Long id);

    List<PotenStudent> selectStatus(Long statusId);

    void follow(Long id);

    void changeLevel(@Param("lv") Long lv, @Param("id") Long id);

    void changeState(Long id);

    void formal(Long id);

    PotenStudent selectPpotenStudentByTel(String tel);

    void updateEmp(PotenStudent potenStudent);

    void changeStatus(@Param("statusId") Long statusId, @Param("potenStudentId") Long potenStudentId);
}