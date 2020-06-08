package com._520it.crm.page;

import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class PageResult {
	//总结果集数
	private Long total;
	//结果集
	private List<?> rows;
}
