package com._520it.crm.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 学生
 */
@Getter
@Setter
@ToString
public class Student {
	/**
	 * 表示已经毕业
	 */
	public static final Integer STATUS_GRADUATION = 4;
	/**
	 * 表示已经就业的状态
	 */
	public static final Integer STATUS_GRADUATE = 3;
	/**
	 * 表示已经退学的状态
	 */
	public static final Integer STATUS_DROP = 2;
	/**
	 * 表示正式学员的状态
	 */
	public static final Integer STATUS_OFFICIAL = 1;
	/**
	 * 表示非正式学员的状态
	 */
	public static final Integer STATUS_NO_OFFICIAL = 0;
	/**
	 * 表示待审核状态
	 */
	public static final Integer FEESS_STATUS_AUDIT = 2;
	/**
	 * 表示已经交清学费状态
	 */
	public static final Integer FEESS_STATUS_OK = 1;
	/**
	 * 表示已经未交清学费状态
	 */
	public static final Integer FEESS_STATUS_NO = 0;
	private Long id;

	private String name;

	private String qq;

	private String tel;
	// 销售人员
	private Employee employee;
	// 教室
	private Classinfo classinfo;
	// 课程
	private Course course;
	// 缴费金额
	private BigDecimal tuition;
	// 缴费时间
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date paytime;
	// 学生状态(是否正式)
	private Integer status = STATUS_NO_OFFICIAL;
	// 人学时间
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date entrancetime;
	// 缴费 状态
	private Integer feesstatus = -1;
	// 备注
	private String remark;
	// 学生的详细详细
	private StudentInfo studentinfo;
	//是够系统社员
	private boolean isEmployee;

	private Long departmentId;
	private Long roleId;

}