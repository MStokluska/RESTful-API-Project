package com.myAPI.APIproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myAPI.APIproject.api.AddUserRequest;
import com.myAPI.APIproject.api.DeleteUserRequest;
import com.myAPI.APIproject.db.UserDao;
import com.myAPI.APIproject.user.ToDoAppUser;

@RestController
public class UserController {
	private UserDao userDao;

	@Autowired
	public UserController(UserDao userDao) {
		super();
		this.userDao = userDao;
	}

	/** Admin can register new Supervisors */
	@RequestMapping(value = "/admin/addSupervisor", method = RequestMethod.POST)
	public void adminInsertUser(@RequestBody AddUserRequest userIn) {
		userDao.adminInsertUser(userIn.getName(), userIn.getPassword(), userIn.getEmployeeNo());
	}

	/** Admin can view users by their positions */
	@RequestMapping(value = "/admin/viewAllEmployees", method = RequestMethod.GET)
	public List<ToDoAppUser> getUsersByPosition(@RequestParam(value = "position") String positionIn) {
		return userDao.getUsersByPosition(positionIn);
	}

	/** Admin can delete supervisors or employees */
	@RequestMapping(value = "/admin/deleteUser", method = RequestMethod.DELETE)
	public void deleteUser(@RequestBody DeleteUserRequest userIn) {
		userDao.adminDeleteUser(userIn.getUserId());
	}

	/** Supervisor can add new employees */
	@RequestMapping(value = "/supervisor/addEmployee", method = RequestMethod.POST)
	public void supervisorInsertUser(@RequestBody AddUserRequest userIn) {
		userDao.supervisorInsertUser(userIn.getName(), userIn.getPassword(), userIn.getEmployeeNo());
	}

	/** Supervisor can view employees */
	@RequestMapping(value = "/supervisor/viewAllEmployees", method = RequestMethod.GET)
	public List<ToDoAppUser> getEmployees() {
		return userDao.getEmployees();
	}

	/** Supervisor can delete employees */
	@RequestMapping(value = "/supervisor/deleteEmployee", method = RequestMethod.DELETE)
	public void deleteEmployees(@RequestBody DeleteUserRequest userIn) {
		userDao.supervisorDeleteEmployee(userIn.getUserId());
	}
}
