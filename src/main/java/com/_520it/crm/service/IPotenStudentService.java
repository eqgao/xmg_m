package com._520it.crm.service;

import com._520it.crm.domain.PotenStudent;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;

import java.util.List;

public interface IPotenStudentService {
    int deleteByPrimaryKey(Long id);

    int insert(PotenStudent record, Long id);

    PotenStudent selectByPrimaryKey(Long id);

    List<PotenStudent> selectAll();

    int updateByPrimaryKey(PotenStudent record, Long infoId);

    //查询分页结果
    PageResult queryPageResult(QueryObject qo);

    //查询名字,id,qq,tel
    List<PotenStudent> list();

    void change(Long id);

    List<PotenStudent> selectStatus();

    String follow(Long id);

    String changeState(Long id);

    /*=============================*/
    void formal(Long id);

    /*=============================*/
    /*==============移交以后潜在学员咨询师更改===============*/
    void updateEmp(PotenStudent potenStudent);
    /*=============================*/

    /*---------------------------------*/
    PotenStudent selectPpotenStudentByTel(String tel);
    /*---------------------------------*/

    /*=======================更改潜在学员状态通用方法===============================*/
    void changeStatus(Long statusId, Long potenStudentId);

}
