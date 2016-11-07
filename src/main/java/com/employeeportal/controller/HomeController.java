package com.employeeportal.controller;
 
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.employeeportal.model.Employee;
 
@Controller
public class HomeController {
 
	public static final String EMPLOYEES_INFORMATION = "http://localhost:8080/EmployeePortal/data/employees";
	public static final String EMPLOYEE_INFORMATION = "http://localhost:8080/EmployeePortal/data/employee";

	public List<String> employeeIds;

	@RequestMapping("/welcome")
	public ModelAndView retrieveEmployeeIds() {
 
		ModelAndView mav = new ModelAndView("welcome");

		RestTemplate restTemplate = new RestTemplate();
		ParameterizedTypeReference<List<Employee>> typeRef = new ParameterizedTypeReference<List<Employee>>() {};
		ResponseEntity<List<Employee>> responseEntity = 
				restTemplate.exchange(EMPLOYEES_INFORMATION, HttpMethod.GET, new HttpEntity<>(""), typeRef);
		List<Employee> employees = responseEntity.getBody();

		employeeIds = new ArrayList<String>();
		
		for (Employee employee : employees) {
			employeeIds.add(employee.getId());
		}
		mav.addObject("employeeIds", employeeIds);

		return mav;
	
	}
	
	@RequestMapping("/retrieveEmployee/{id}")
	public ModelAndView retrieveEmployee(@PathVariable("id")String empId) {
		ModelAndView mav = new ModelAndView("welcome");

		RestTemplate restTemplate = new RestTemplate();
		ParameterizedTypeReference<Employee> typeRef = new ParameterizedTypeReference<Employee>() {};
		String restURL = EMPLOYEE_INFORMATION +"/" +empId;
		ResponseEntity<Employee> responseEntity = 
				restTemplate.exchange(restURL, HttpMethod.GET, new HttpEntity<>(""), typeRef);
		Employee employee = responseEntity.getBody();

		mav.addObject("employeeIds", employeeIds);
		mav.addObject("employee", employee);

		return mav;		
	}
}
