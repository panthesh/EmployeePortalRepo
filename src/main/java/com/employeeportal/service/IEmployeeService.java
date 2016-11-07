package com.employeeportal.service;

import java.util.List;

import com.employeeportal.model.Employee;
import com.employeeportal.model.Feed;
import com.employeeportal.model.Group;

public interface IEmployeeService {
	
	List<Employee> findAllEmployees();
	Employee findById(String id);
	List<Group> findAllGroups();
	Group findGroupById(String id);
	boolean create(Employee employee);
	Feed retrieveMediumBlogEntries(String mediumHandler);
	
}
