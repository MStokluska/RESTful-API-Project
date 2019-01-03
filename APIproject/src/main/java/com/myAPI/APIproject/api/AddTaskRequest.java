package com.myAPI.APIproject.api;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AddTaskRequest {
	String title;
	String description;
	int priority;
	LocalDate date;
	String userId;
}
