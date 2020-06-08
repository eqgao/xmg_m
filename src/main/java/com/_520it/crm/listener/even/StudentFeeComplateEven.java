package com._520it.crm.listener.even;

import com._520it.crm.domain.Student;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.context.ApplicationEvent;

import java.math.BigDecimal;

/**
 * Created by hasee on 2017/11/12.
 */
@Setter
@Getter
@ToString
public class StudentFeeComplateEven extends ApplicationEvent {
    private StudentFeeFlowObject sffo;

    public StudentFeeComplateEven(Object source) {
        super(source);
        this.sffo = (StudentFeeFlowObject) source;
    }

    @Setter
    @Getter
    public class StudentFeeFlowObject {
        private Student stu;
        private BigDecimal money;
        private BigDecimal remainMoney;
    }
}
