package com._520it.crm.service;

import com._520it.crm.domain.Checkon;
import com._520it.crm.query.CheckonQuery;

import java.text.ParseException;
import java.util.List;

/**
 * Created by fjz on 2017/8/26.
 */
public interface ICheckonService {
    int deleteByPrimaryKey(Long id);

    /**
     * return:受影响的行数
     * record:被保存的对象
     * @param salary
     * @return
     */
    int insert(Checkon salary) throws ParseException;




    /**
     * return:返回Salary对象
     * id:根据id取查询
     * @param id
     * @return
     */
    Checkon selectByPrimaryKey(Long id);

    /**
     * return:返回Salary所有对象
     * @return
     */

    List<Checkon> selectAll(CheckonQuery qo);

    /**
     *
     * record:被修改的对象
     * @param record
     * @return
     */
    int updateByPrimaryKey(Checkon record) throws ParseException;
}
