package com.myAPI.APIproject.api;

import lombok.Data;

@Data
public class AddUserRequest {
	private String name;
	private String password;
	private String employeeNo;
}
