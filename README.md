# RESTful-API-Project

Project idea:
This is my first RESTful API Project, purpose of this project is to allow admin to create supervisors, supervisors then can create employees and later, assign tasks to existing employees. Employees then can view tasks assigned and mark them as completed.
Admin can also delete supervisors, supervisors can delete employees or tasks.

Project architecture:
I have used Maven to build my RESTful API. API is build using Spring Framework. 
My controllers are GET, POST and DELETE for Users and Tasks, writing the controllers and methods for them was great exercise but only 
after completing few of them I have found out that GET is the only request that can contain PARAMS so I have to re-do it all, 
it was great learning experience. Persistence layer is in DAO and I have chosen JDBC to connect to my MySQL database (as MySQL is what
I am familiar with), nearly everywhere I looked I was recommended JPA, however I decided not to go with JPA as for now, it seems too
difficult for me to understand, and instead I have used JDBC.
I have added Spring Security for user authorisation with 3 types of authorities – admin, supervisor and employee along with password encoder
which uses BCrypt ( blowfish ) algorithm to cipher passwords stored.
I have also used Lombok. 

How to run:
1. Download file from git repository
2. Run my sql init
3. Run run.bat
4. API is listening on port 9000
5. Use Postman or any other tool to test web services
6. Use admin credentials to start adding supervisors to the database
Admin ID: Admin
Password: asd123

Web Services:

Name: Add Supervisor

Function:
Admin is authorized to add a new supervisor to the system by entering his name, password and unique employee number
Method: POST
URL: {host}:{port}/admin/addSupervisor
Authorization: Basic Authorization 
Content Type: application/json
RequestBody: 
{
"name": “{name of new supervisor}”,
"password": "{password of new supervisor}", 
"employeeNo": "{employee number}"
}
ResponseBody: Empty
Example:
{
"name": "Michael",
"password": "myPass",
"employeeNo": "e277"
}







Name: Delete User

Function:
Admin is authorized to delete an existing user from the system by entering his employee number
Method: DELETE
URL: {host}:{port}/admin/deleteUser
Authorization: Basic Authorization 
Content Type: application/json
RequestBody: 
{ 
"userId": "{employee number}"
}
ResponseBody: Empty
Example:
{
"employeeNo": "e277"
}












Name: View All Users

Function:
Admin is authorized to view all registered supervisors and employees
Method: GET
URL: {host}:{port}/admin/viewAllEmployees
Authorization: Basic Authorization 
Content Type: application/json
RequestParam: 
{ 
"position": "{supervisor or employee}"
}
ResponseBody: 
{
“password”: “Not available”,
“username”: “{name of employee or supervisor}”,
“authorities”:
	{
		“authority”: “{supervisor or employee}”
}
“accountNonExpired”: true,
“accountNonLocked”: true,
“credentialsNonExpired”: true,
“userId”: “{employee number of supervisor or employee}”
}
Example:
Key: position
Value: supervisor



Name: Add Employee

Function:
Supervisor can enrol an employee with unique employee number
Method: POST
URL: {host}:{port}/supervisor/addEmployee
Authorization: Basic Authorization 
Content Type: application/json
RequestBody: 
{
"name": “{name of new employee}”,
"password": "{password of new employee}", 
"employeeNo": "{employee number}"
}
ResponseBody: Empty
Example:
{
"name": "Michael Employee",
"password": "asd123",
"employeeNo": "e2777"
}










Name: View All Employees 

Function:
Supervisor is authorized to view all registered employees by entering his own Name and Password
Method: GET
URL: {host}:{port}/supervisor/viewAllEmployees
Authorization: Basic Authorization 
Content Type: application/json
RequestParam: Empty
ResponseBody: 
{
“password”: “Not available”,
“username”: “{name of employee}”,
“authorities”:
	{
		“authority”: “employee”
}
“accountNonExpired”: true,
“accountNonLocked”: true,
“credentialsNonExpired”: true,
“userId”: “{employee number}”
}








Name: Delete Employee

Function:
Supervisor can delete an employee from the system
Method: DELETE
URL: {host}:{port}/supervisor/deleteEmployee
Authorization: Basic Authorization 
Content Type: application/json
RequestBody: 
{ 
"userId": "{employee number}"
}
ResponseBody: Empty
Example:
{
"userId": "e2777"
}














Name: Add Task

Function:
Supervisor can assign task to an employee
Method: POST
URL: {host}:{port}/supervisor/assignTask
Authorization: Basic Authorization 
Content Type: application/json
RequestBody: 
{
"title": “{task title}”,
“description": "{description of task}", 
"priority": "{priority number}",
“date”: “{date in YEAR-MONTH-DAY format}”,
“userId”: “{id of employee}”
}
ResponseBody: Empty
Example:
{
“title”: “clean shelve”,
“description”: “Deep clean of shelve 2”,
“priority”: 3,
“date”: “2018-12-29”,
“userId”: “e2777”
}






Name: Supervisor - View All Tasks  

Function:
Supervisor is authorized to view all assigned tasks to a specified employee
Method: GET
URL: {host}:{port}/supervisor/tasksForEmployee
Authorization: Basic Authorization 
Content Type: application/json
RequestParam: 
{ 
"userId": "{employee number}"
}
ResponseBody: 
{
“id”: “{autoincrementing task id number}”,
“title”: “{title of task}”,
“description”: “{description of task}”,
“priority”: “{priority number}”,
“date”: “{target date}”,
“status”: “{“To Be Completed” until marked down as “Completed” by an employee}”,
“userId”: “null”
}









Name: Delete Task

Function:
Supervisor can delete a task from the system
Method: DELETE
URL: {host}:{port}/supervisor/deleteEmployee
Authorization: Basic Authorization 
Content Type: application/json
RequestBody: 
{ 
"taskId": "{task number}"
}
ResponseBody: Empty
Example:
{
"taskId": "1"
}


























Name: Employee - View All Tasks or/and filter tasks by priority or task date

Function:
Employee is authorized to view all assigned tasks to him/her and filter by priority
Method: GET
URL: {host}:{port}/employee/tasksForEmployeeByPriority
Authorization: Basic Authorization 
Content Type: application/json
RequestParam: 
{ 
"filterBy": "{leave empty, Priority or taskDate}"
}
ResponseBody: 
{
“id”: “{autoincrementing task id number}”,
“title”: “{title of task}”,
“description”: “{description of task}”,
“priority”: “{output filtered by priority number}”,
“date”: “{target date}”,
“status”: “{“To Be Completed” until marked down as “Completed” by an employee}”,
“userId”: “null”
}











Name: Employee - View All Tasks By Date

Function:
Employee is authorized to view all assigned tasks to him/her and filter by Date
Method: GET
URL: {host}:{port}/employee/tasksForEmployeeByDate
Authorization: Basic Authorization 
Content Type: application/json
RequestParam: Empty
ResponseBody: 
{
“id”: “{autoincrementing task id number}”,
“title”: “{title of task}”,
“description”: “{description of task}”,
“priority”: “{output filtered by priority number}”,
“date”: “{target date}”,
“status”: “{“To Be Completed” until marked down as “Completed” by an employee}”,
“userId”: “null”
}












Name: Confirm task completed

Function:
Employee can mark task as completed
Method: POST
URL: {host}:{port}/employee/confirmCompletion
Authorization: Basic Authorization 
Content Type: application/json
RequestBody: 
{
"taskId”: “{id of task assigned}”
}
ResponseBody: Empty
Example:
{
“taskId”: 2
}


Thank you,
Michael Stokluska
