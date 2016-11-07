package com.employeeportal.service;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import com.employeeportal.model.Employee;
import com.employeeportal.model.Feed;
import com.employeeportal.model.Group;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository("employeeService")
public class EmployeeService implements IEmployeeService{

	public static final String RSS_TO_JSON_URL = "http://rss2json.com/api.json";

	private List<Employee> employees;
	private List<Group> groups;

	public void init() {
		//read json file data to String
		byte[] jsonData = null;
		byte[] jsonGroupData = null;

		try {
			jsonData = Files.readAllBytes(Paths.get(EmployeeService.class.getResource("/employee.json").toURI()));
			jsonGroupData = Files.readAllBytes(Paths.get(EmployeeService.class.getResource("/group.json").toURI()));
			
			ObjectMapper objectMapper = new ObjectMapper();

			employees = new ArrayList<Employee> ();
			groups = new ArrayList<Group> ();
			
			//convert json string to list of objects
			employees = objectMapper.readValue(jsonData, new TypeReference<List<Employee>>(){});
			groups = objectMapper.readValue(jsonGroupData, new TypeReference<List<Group>>(){});
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
	}
	
	// Return all the employees information
	public List<Employee> findAllEmployees() {
		for (Employee employee: employees) {
			for (Group group: groups) {
				if (group.getId().equals(employee.getGroupId())) {
					setOtherInformation(employee, group);
				}
			}
		}
		return employees;
	}

	// Return employee information for a particular Id
	public Employee findById(String id) {
		for (Employee employee: employees) {
			if (employee.getId().equals(id)) {
				for (Group group: groups) {
					if (group.getId().equals(employee.getGroupId())) {
						setOtherInformation(employee, group);
					}
				}
				return employee;
			} 
		}
		return null;
	}
	
	// Return all Groups information
	public List<Group> findAllGroups() {
		return groups;
	}

	// Return particular group information
	public Group findGroupById(String id) {
		for (Group group: groups) {
			if (group.getId().equals(id)) {
				return group;
			} 
		}
		return null;
	}
	
	// Create an employee record
	public boolean create(Employee employee) {
		int id = employees.size() + 1;
		employee.setId(String.valueOf(id));
		if (employees.add(employee)) {
			byte[] jsonData = null;

			try {
				ObjectMapper objectMapper = new ObjectMapper();

				//convert json string to list of objects
				jsonData = objectMapper.writeValueAsBytes(employees);

				//Write employees object to JSON File
				Files.write(Paths.get(EmployeeService.class.getResource("/employee.json").toURI()),jsonData, StandardOpenOption.WRITE);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (URISyntaxException e) {
				e.printStackTrace();
			}
			
			return true;
		}
		return false;
	}
	
	public Feed retrieveMediumBlogEntries(String mediumHandler) {
		URI uri = null;
		URL url = null;
		try {
			uri = new URI("https", "medium.com", "/feed/" + mediumHandler, null);
			url = uri.toURL();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		String restURL = RSS_TO_JSON_URL + "?rss_url=" + url.toString();
		RestTemplate restTemplate = new RestTemplate();
		Feed feed = restTemplate.getForObject(restURL, Feed.class, 200);
		return feed;
	}

	// Set Group Name and Manager Name 
	private void setOtherInformation(Employee employee, Group group) {
		employee.setGroupName(group.getGroupName());
		for (Employee manager: employees) {
			if (manager.getId().equals(employee.getManagerId())) {
				employee.setManagerName(manager.getFirstName()+ " " +manager.getLastName());
			}
		}
	}
}
