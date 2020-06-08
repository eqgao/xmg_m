package com._520it.crm.query;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeQuery extends QueryObject{
	private String keyword;

	private Long id;
}
