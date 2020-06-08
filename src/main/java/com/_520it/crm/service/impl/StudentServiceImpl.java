package com._520it.crm.service.impl;

import com._520it.crm.domain.*;
import com._520it.crm.mapper.*;
import com._520it.crm.page.PageResult;
import com._520it.crm.query.StudentQuery;
import com._520it.crm.service.IAccountFlowService;
import com._520it.crm.service.IStudentService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class StudentServiceImpl implements IStudentService {
    @Autowired
    private StudentMapper mapper;
    @Autowired
    private ReceiptMapper recemapper;
    @Autowired
    private JobinfoMapper jobmapper;
    /*============================================*/
    @Autowired
    private CourseMapper courseMapper;
    /*============================================*/
    @Autowired
    private EmployeeMapper employeeMapper;
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private IAccountFlowService accountFlowService;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Student record) {
        return mapper.insert(record);
    }

    @Override
    public Student selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Student> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Student record) {
        return mapper.updateByPrimaryKey(record);

    }

    @Override
    public PageResult queryPageResult(StudentQuery qo) {
        //查询总结果数
        Long count = mapper.queryPageCount(qo);
        if (count == 0) {
            return new PageResult(count, Collections.EMPTY_LIST);
        }

        //查询结果集
        return new PageResult(count, mapper.queryPageResult(qo));
    }


    @Override
    public Student selectBySid(Long Sid) {
        return mapper.selectBySid(Sid);
    }

    @Override
    public void insertfees(Receipt receiptfees, Long Sid) {
        Long id = Sid;
        Student student = mapper.selectByPrimaryKey(id);
        Receipt receipt = new Receipt();
        receipt.setReceipttime(new Date());
        //获取当前登录操作的员工对象
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        receipt.setAuditor(employee);
        receipt.setBills(receiptfees.getBills());
        receipt.setReceipt(receiptfees.getReceipt());
        receipt.setRemark(receiptfees.getRemark());
        receipt.setStu(student);
        receipt.setReceiptamount(receiptfees.getReceiptamount());
        //把数据提交过去以后同时改变状态为待审核

        mapper.changefeesState(id);
        recemapper.insert(receipt);

    }

    /*===================================================*/
    @Override
    public int insert(PotenStudent reAcord, Student studentO) {
        //获的potenStudent相关数据
        Student student = new Student();
        student.setName(reAcord.getName());
        student.setRemark(reAcord.getRemark());
        student.setQq(reAcord.getQq());
        student.setTel(reAcord.getTel());
        student.setEmployee(reAcord.getEmployee());
        student.setStatus(0);
        student.setEntrancetime(new Date());
        student.setFeesstatus(0);
        StudentInfo studentInfo = reAcord.getStudentInfo();
        student.setStudentinfo(studentInfo);
        //获得班级相关
        student.setClassinfo(studentO.getClassinfo());
        //获取学科
        Long classId = studentO.getClassinfo().getId();
        Course course = courseMapper.selectCourseByClassId(classId);
        student.setCourse(course);
        int i = mapper.insert(student);

        // 转成社员成为系统社员
        Employee e = new Employee();
        e.setUsername(student.getName());
        e.setPassword(new Md5Hash(e.getUsername(), student.getName()).toString());
        Department d = new Department();
        d.setId(student.getId());
        e.setDept(d);
        e.setTel(student.getTel());
        e.setAdmin(false);
        e.setRealname(student.getName());
        e.setInputtime(new Date());
        employeeMapper.insert(e);
        employeeMapper.insertRelation(e.getId(), student.getRoleId());
        return i;
    }
    /*===================================================*/

    @Override
    public void graduation(Jobinfo jobFrom, Long sId) {
        //通过id获取当前所有学员的信息
        Student student = mapper.selectByPrimaryKey(sId);
        //学生毕业后把信息保存到就业信息表
        Jobinfo job = new Jobinfo();
        job.setStu(student);
        job.setAddress(jobFrom.getAddress());
        job.setProfession(jobFrom.getProfession());
        job.setCompany(jobFrom.getCompany());
        job.setSalary(jobFrom.getSalary());
        job.setTime(jobFrom.getTime());
        job.setWelfare(jobFrom.getWelfare());
        jobmapper.insert(job);
        //毕业成功以后把学员状态设置为已毕业状态
        mapper.changeState(sId);

    }

    @Override
    public void changeGraduiteStatus(Long id) {
        mapper.changeGraduiteStatus(id);

    }

}
