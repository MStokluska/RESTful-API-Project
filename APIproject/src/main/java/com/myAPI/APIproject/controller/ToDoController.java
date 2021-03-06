package com.myAPI.APIproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myAPI.APIproject.api.AddTaskRequest;
import com.myAPI.APIproject.api.DeleteTaskRequest;
import com.myAPI.APIproject.api.TaskCompletedRequest;
import com.myAPI.APIproject.db.TaskDao;
import com.myAPI.APIproject.task.Task;

@RestController
public class ToDoController {
	private TaskDao taskDao;
	@Autowired
	public ToDoController(TaskDao taskDao) {
		super();
		this.taskDao = taskDao;
	}
	/** Supervisor can assign a task to an employee */
	@RequestMapping(value = "/supervisor/assignTask", method = RequestMethod.POST)
	public void supervisorAssignTask(@RequestBody AddTaskRequest taskIn) {
	taskDao.assignTask(taskIn.getTitle(), taskIn.getDescription(), taskIn.getPriority(), taskIn.getDate(),
				taskIn.getUserId());
	}

	/** Supervisor can view list of tasks */
	@RequestMapping(value = "/supervisor/TasksForEmployee", method = RequestMethod.GET)
	public List<Task> supervisorGetTasks(@RequestParam(value = "empId") String empIdIn) {
		return taskDao.supervisorGetTasks(empIdIn);
	}

	/** Supervisor can delete a task by taskId */
	@RequestMapping(value = "/supervisor/deleteTask", method = RequestMethod.DELETE)
	public void deleteTask(@RequestBody DeleteTaskRequest taskIn) {
		taskDao.supervisorDeleteTask(taskIn.getTaskId());
	}

	/** Employee can view his list of all tasks, employee can also filter by task priority or date - requires priority/taskDate param */
	@RequestMapping(value = "/employee/TasksForEmployeeByFilter", method = RequestMethod.GET)
	public List<Task> employeeGetTasksByPriority(@RequestParam(value = "filterOption") String filterIn) {
		return taskDao.getEmployeeTasksByFilter(filterIn);
	}

	/** Employee can confirm task completion */
	@RequestMapping(value = "/employee/confirmCompletion", method = RequestMethod.POST)
	public void confirmCompletion(@RequestBody TaskCompletedRequest taskIn) {
		taskDao.confirmCompletion(taskIn.getTaskId());
	}
}
