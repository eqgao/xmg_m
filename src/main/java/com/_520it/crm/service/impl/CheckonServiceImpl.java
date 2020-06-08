package com._520it.crm.service.impl;

import com._520it.crm.domain.Checkon;
import com._520it.crm.domain.Employee;
import com._520it.crm.mapper.CheckonMapper;
import com._520it.crm.query.CheckonQuery;
import com._520it.crm.service.ICheckonService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by fjz on 2017/8/26.
 */
@Service
public class CheckonServiceImpl implements ICheckonService {
    @Autowired
    private CheckonMapper mapper;


    public int deleteByPrimaryKey(Long id) {
        return mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Checkon checkon) throws ParseException {
        SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        SimpleDateFormat FF = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        Subject subject = SecurityUtils.getSubject();
        Employee employee = (Employee) subject.getPrincipal();
        checkon.setEmployee(employee);

        Date p = f.parse("09:00:00");
        Date p1 = f.parse("13:00:00");//早上还是下午打卡时间
        Date p2 = f.parse("17:30:00");
        Date signintime = new Date();
        String F1 = FF.format(signintime);
        checkon.setCheckondate(FF.parse(F1));
        //早上签到时间
        Date d1 = f.parse(f.format(signintime));
        Date signouttime = new Date();
        //晚上签到时间
        Date d2 = f.parse(f.format(signouttime));

        if ((d1.getTime() < p1.getTime())) {
            checkon.setSignintime(signintime);
            if (mapper.get(checkon) == null) {
                checkon.setStatus(1);
                mapper.insert(checkon);
                return 0;
            } else {
                return 0;
            }
        } else if ((d2.getTime() > p1.getTime())) {
            checkon.setSignouttime(signouttime);
            if (mapper.get(checkon) == null) {
                checkon.setStatus(2);
                mapper.insert(checkon);
                return 0;
            } else {
                if(!(d2.getTime() > p2.getTime())){
                    checkon.setStatus(2);
                }
                updateByPrimaryKey(checkon);
                return 0;
            }
        }
        return mapper.insert(checkon);
    }

    @Override
    public Checkon selectByPrimaryKey(Long id) {
        return mapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Checkon> selectAll(CheckonQuery qo) {

        return mapper.selectAll(qo);
    }

    @Override
    public int updateByPrimaryKey(Checkon checkon) throws ParseException {
        return mapper.updateByPrimaryKey(checkon);
    }

}
