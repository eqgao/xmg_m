package com._520it.crm.query;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by hasee on 2017/8/30.
 */
@Setter
@Getter
@ToString
public class JobinfoQueryObject extends QueryObject {

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date beginTime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endTime;

    private Boolean gender;
    private Integer age;
    private Boolean computerOrNot;
    private Long city;
    private String groupType = "si.gender";

    //分组类型
    public static final Map<String, String> GROUP_BY_TYPES = new LinkedHashMap<>();

    static {
        GROUP_BY_TYPES.put("date_format(j.time,'%Y-%m')", "入职日期（月）");
        GROUP_BY_TYPES.put("date_format(j.time,'%Y-%m-%d')", "入职日期（日）");
        GROUP_BY_TYPES.put("si.gender", "性别");
        GROUP_BY_TYPES.put("si.age", "年龄");
        GROUP_BY_TYPES.put("si.computerOrNot", "是否电脑专业");
        GROUP_BY_TYPES.put("j.address_id", "地址");
    }
}
