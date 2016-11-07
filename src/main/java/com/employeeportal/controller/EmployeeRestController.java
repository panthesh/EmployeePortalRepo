package com.employeeportal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.employeeportal.model.Employee;
import com.employeeportal.service.IEmployeeService;
 
@RestController
@RequestMapping("/data")
@EnableWebMvc
public class EmployeeRestController {
 
    @Autowired
    private IEmployeeService employeeService;
     
    //-------------------Retrieve All Employees' Information--------------------------------------------------------
     
    @RequestMapping(value = "/employees", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<List<Employee>> getAllEmployeesInfo() {
        List<Employee> employees = employeeService.findAllEmployees();
        if(employees.isEmpty()){
            return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
    }
 
 
    //-------------------Retrieve Single Employee's Information--------------------------------------------------------
     
    @RequestMapping(value = "/employee/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Employee> getEmployee(@PathVariable("id") String id) {
        System.out.println("Fetching User with id " + id);
        Employee employee = employeeService.findById(id);
        if (employee == null) {
            System.out.println("Employee with id " + id + " not found");
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
        
        //-------------------Social Media Integration--------------------------------------------------------
        employee.setMediumFeed(employeeService.retrieveMediumBlogEntries(employee.getMediumUserId()));

        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }
    
    //-------------------Create an Employee's Record--------------------------------------------------------

    @RequestMapping(value = "/employee/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Employee> createEmployee(@RequestBody Employee employeeObject) {
        boolean result = employeeService.create(employeeObject);
        if (!result) {
            System.out.println("Employee recoard for Employee " + employeeObject.getFirstName() +" "+ employeeObject.getLastName() + " has not been created!!");
            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
        System.out.println("Employee recoard for Employee " + employeeObject.getFirstName() +" "+ employeeObject.getLastName() + " has been created!!");
        return new ResponseEntity<Employee>(employeeObject, HttpStatus.OK);
    }
    

 
}
