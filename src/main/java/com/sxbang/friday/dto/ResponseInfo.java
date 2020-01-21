package com.sxbang.friday.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseInfo implements Serializable {

	private static final long serialVersionUID = -4417715614021482064L;

	private String code;
	private String message;

	public ResponseInfo(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

}
