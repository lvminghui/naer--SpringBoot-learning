package com.sxbang.friday.model;

import lombok.Data;

@Data
public class SysRole extends BaseEntity<Integer> {
	private static final long serialVersionUID = -6525908145032868837L;

	private String name;
	private String description;

	@Override
	public String toString() {
		return "SysRole{" +
				"name='" + name + '\'' +
				", description='" + description + '\'' +
				'}';
	}
}
