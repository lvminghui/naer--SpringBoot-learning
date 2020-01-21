package com.sxbang.friday.base.result;

public enum ResponseCode {

	// 公共请求信息
	SUCCESS(200, "请求成功"),
	TABLE_SUCCESS(0, "请求成功"),
	FAIL(500, "请求失败，请联系管理员"),
	PARAMETER_MISSING(600,"参数缺失"),
	UNAUTHORIZED(401,"未授权"),
	// ..一真往后面加

	//用户信息
	//5000100 - 5000200
	USERNAME_REPEAT(5000100,"用户名已存在"),
	PHONE_REPEAT(5000101,"手机号已存在"),
	EMAIL_REPEAT(5000102,"邮箱已存在"),
	//用户-角色
	//5000201 - 5000300
	USER_ROLE_NO_CLEAR(5000201,"该角色存在用户关联，无法删除")

;
	private Integer code;
	
	private String message;
	
	ResponseCode(Integer code, String message) {
		this.code = code;
		this.message = message;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static String getMessage(String name) {
		for (ResponseCode item : ResponseCode.values()) {
			if (item.name().equals(name)) {
				return item.message;
			}
		}
		return null;
	}

	public static Integer getCode(String name) {
		for (ResponseCode item : ResponseCode.values()) {
			if (item.name().equals(name)) {
				return item.code;
			}
		}
		return null;
	}
}
