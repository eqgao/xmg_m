package test;

import com._520it.crm.domain.Account;
import com._520it.crm.domain.Checkon;
import com._520it.crm.domain.Employee;
import com._520it.crm.domain.Salary;
import com._520it.crm.mapper.AccountMapper;
import com._520it.crm.query.CheckonQuery;
import com._520it.crm.service.ICheckonService;
import org.apache.shiro.SecurityUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fjz on 2017/8/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application.xml")
public class SalaryTest {
    @Autowired
    private ICheckonService service;
    @Autowired
    private AccountMapper accountMapper;

    @Test
    public void testAccountInsert() {
        List<Account> list = new ArrayList<>();
        for (int i = 1; i < 3; i++) {
            Account account = new Account();
            account.setNum(new BigDecimal("100" + 1));
            list.add(account);
        }
        int insertCount = accountMapper.insertBatch(list);
        System.out.println(insertCount);
    }

    @Test
    public void testAccountUpdate() {
        List<Account> list = new ArrayList<>();
        Account account = new Account();
        account.setId(5L);
        account.setNum(new BigDecimal("2001"));
        list.add(account);
        Account account1 = new Account();
        account1.setId(6L);
        account1.setNum(new BigDecimal("2002"));
        list.add(account1);
        int updateCount = accountMapper.updateBatch(list);
        System.out.println(updateCount);
    }

    @Before
    public void testBefore() {
        String resource = "application-shiro.xml";
        ClassPathXmlApplicationContext appCtx = new ClassPathXmlApplicationContext(resource);
        org.apache.shiro.mgt.SecurityManager securityManager =
                (org.apache.shiro.mgt.SecurityManager) appCtx.getBean("securityManager");
        SecurityUtils.setSecurityManager(securityManager);
    }

    @Test
    public void testSave() throws Exception {
        Employee e = new Employee();
        e.setId(1L);
        Salary s = new Salary();
        s.setCard("2345678");
        s.setSalary(new BigDecimal("100.00"));
        s.setStatus(true);
        s.setRemark("我要保存");
        s.setEmployee(e);
//        service.insert(s);
    }

    @Test
    public void get() throws Exception {
        CheckonQuery qo = new CheckonQuery();
        //qo.setKeyword("w");
        List<Checkon> salaries = service.selectAll(qo);
        System.out.print(salaries);
    }

    @Test
    public void update() throws Exception {
        Employee e = new Employee();
        e.setId(1L);
        Salary s = new Salary();
        s.setId(18L);
        s.setCard("2345678");
        s.setSalary(new BigDecimal("100.00"));
        s.setStatus(true);
        s.setRemark("我要失败");
        s.setEmployee(e);

//        service.updateByPrimaryKey(s);
    }
}
