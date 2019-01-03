package com.myAPI.APIproject.task;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Task {
	private int id;
	private String title;
	private String description;
	private int priority;
	private LocalDate date;
	private String status;
	private String userId;

}
