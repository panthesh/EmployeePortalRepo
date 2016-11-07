package com.employeeportal.test;


import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.util.Assert;

import com.employeeportal.model.Employee;
import com.employeeportal.model.Feed;
import com.employeeportal.model.Group;
import com.employeeportal.service.EmployeeService;
import com.employeeportal.service.IEmployeeService;

public class EmployeeServiceTest {
    private IEmployeeService employeeService;
    
    @Before
    public void init() {
    	employeeService= new EmployeeService();
    	((EmployeeService) employeeService).init();
    }
    
	@Test
	public void findAllEmployeesTest() {
		List<Employee> employees = employeeService.findAllEmployees();
		
		Assert.notNull(employees);
		Assert.notEmpty(employees);
		Assert.isTrue(employees.size() > 0);
	}
	
	@Test
	public void finByIdTest() {
		Employee employee = employeeService.findById("1");
		
		Assert.notNull(employee);
		Assert.isTrue(employee.getId().equals("1"));
	}
	
	@Test
	public void findAllGroupsTest() {
		List<Group> groups = employeeService.findAllGroups();
		
		Assert.notNull(groups);
		Assert.notEmpty(groups);
		Assert.isTrue(groups.size() > 0);
	}
	
	@Test
	public void findGroupByIdTest() {
		Group group = employeeService.findGroupById("GROUP-1");
		
		Assert.notNull(group);
		Assert.isTrue(group.getId().equals("GROUP-1"));
	}
	
	@Test
	public void createEmployeeTest() {
		int size = employeeService.findAllEmployees().size();
		employeeService.create(new Employee());
		int newSize = employeeService.findAllEmployees().size();
		
		Assert.isTrue(newSize == size+1);
	}
	
	@Test
	public void retrieveMediumBlogEntries() {
		Feed feed = employeeService.retrieveMediumBlogEntries(employeeService.findById("1").getMediumUserId());

		Assert.notNull(feed);
		Assert.notNull(feed.getItems());
		Assert.notEmpty(feed.getItems());
		Assert.isTrue(feed.getItems().size() > 0);

	}
}
