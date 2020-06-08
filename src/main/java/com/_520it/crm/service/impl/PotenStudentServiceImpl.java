package com._520it.crm.service.impl;

import com._520it.crm.domain.PotenStudent;
import com._520it.crm.domain.StudentInfo;
import com._520it.crm.mapper.PotenStudentMapper;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.QueryObject;
import com._520it.crm.service.IPotenStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PotenStudentServiceImpl implements IPotenStudentService {
    @Autowired
    private PotenStudentMapper mapper;

    @Override
    public int deleteByPrimaryKey(Long id) {

        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(PotenStudent record, Long infoId) {
        //判断是否同一个员工
        List<PotenStudent> potenStudents = mapper.selectAll();
        if (potenStudents.size() > 0) {
            for (PotenStudent potenStudent : potenStudents) {
                if (potenStudent.getQq().equals(record.getQq()) || potenStudent.getTel().equals(record.getTel())) {
                    throw new RuntimeException("禁止保存同一个人");
                }
            }
        }
        StudentInfo info = new StudentInfo();
        info.setId(infoId);
        record.setStudentInfo(info);
        int count = mapper.insert(record);
        return count;
    }

    @Override
    public PotenStudent selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<PotenStudent> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(PotenStudent record, Long infoId) {
        //判断是否同一个员工
        List<PotenStudent> potenStudents = mapper.selectAll();
        if (potenStudents.size() > 0) {
            for (PotenStudent potenStudent : potenStudents) {
                if (!potenStudent.getId().equals(record.getId())) {
                    if (potenStudent.getQq().equals(record.getQq()) || potenStudent.getTel().equals(record.getTel())) {
                        throw new RuntimeException("禁止保存同一个人");
                    }
                }
            }
        }
        StudentInfo info = new StudentInfo();
        info.setId(infoId);
        record.setStudentInfo(info);
        return mapper.updateByPrimaryKey(record);
    }

    @Override
    public PageResult queryPageResult(QueryObject qo) {
        //查询总结果数
        Long count = mapper.queryPageCount(qo);
        if (count == 0) {
            return new PageResult(count, Collections.EMPTY_LIST);
        }

        //查询结果集
        return new PageResult(count, mapper.queryPageResult(qo));
    }

    @Override
    public List<PotenStudent> list() {
        return mapper.list();
    }

    @Override
    public void change(Long id) {
        mapper.change(id);

    }

    @Override
    public List<PotenStudent> selectStatus() {

        return mapper.selectStatus(18L);
    }

    //潜在学员流失
    @Override
    public String changeState(Long id) {
        //判断当前学员状态是否为厌恶,只有当态度为厌恶才可以流失,
        //我们盈利,哦不 培训机构不放过任何一个有志之士
        PotenStudent potenStudent = mapper.selectByPrimaryKey(id);
        if (potenStudent.getLevel().getId() == 5) {
            mapper.changeState(id);
            return "成功";
        }
        return "失败";
    }

    //跟进方法
    @Override
    public String follow(Long id) {
        //给一个随机数
        PotenStudent potenStudent = mapper.selectByPrimaryKey(id);
        Long level = potenStudent.getLevel().getId();
        double random = Math.random();
        if (random < 0.5) {
            up(id, 6, 5l);
            if (level != 5 && level != 6 && level != 8 && level != 9) {
                //除了官方给的非一般全部当作是一般处理
                long lv = 6l;
                mapper.changeLevel(lv, id);
            }
            up(id, 8, 7l);
            mapper.follow(id);
            if (level == 6) {
                return "最低";
            } else {
                return "失败";
            }
        }
        up(id, 8, 9l);
        if (level != 5 && level != 6 && level != 8 && level != 9) {
            //除了官方给的非一般全部当作是一般处理
            long lv = 8l;
            mapper.changeLevel(lv, id);
        }
        up(id, 6, 7l);
        mapper.follow(id);
        if (level == 8) {
            return "最高";
        } else {
            return "成功";
        }
    }

    //跟进方法内部好感度改变工具方法
    public void up(Long id, int i, long lv) {
        PotenStudent potenStudent = mapper.selectByPrimaryKey(id);
        Long level = potenStudent.getLevel().getId();
        if (level == i) {
            mapper.changeLevel(lv, id);
        }
    }

    /*=======================================*/
    @Override
    public void formal(Long id) {
        mapper.formal(id);
    }

    @Override
    public void updateEmp(PotenStudent potenStudent) {
        mapper.updateEmp(potenStudent);
    }
/*=======================================*/

    /*-----------------------------------------------*/
    @Override
    public PotenStudent selectPpotenStudentByTel(String tel) {
        return mapper.selectPpotenStudentByTel(tel);
    }

    @Override
    public void changeStatus(Long statusId, Long potenStudentId) {
        mapper.changeStatus(statusId, potenStudentId);
    }
    /*-----------------------------------------------*/

}
