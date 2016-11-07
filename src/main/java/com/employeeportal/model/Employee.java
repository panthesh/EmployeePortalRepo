package com.employeeportal.model;

public class Employee {
	private String id;
	private String firstName;
	private String middleName;
	private String lastName;
	private String title;
	private String employeeType;
	private String groupId;
	private String groupName;
	private String managerId;
	private String managerName;
	private String emergencyContactName;
	private String emergencyContactNumber;
	private Address address;
	private String mediumUserId;
	private Feed mediumFeed;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getEmergencyContactName() {
		return emergencyContactName;
	}
	public void setEmergencyContactName(String emergencyContactName) {
		this.emergencyContactName = emergencyContactName;
	}
	public String getEmergencyContactNumber() {
		return emergencyContactNumber;
	}
	public void setEmergencyContactNumber(String emergencyContactNumber) {
		this.emergencyContactNumber = emergencyContactNumber;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(String type) {
		this.employeeType = type;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getMediumUserId() {
		return mediumUserId;
	}
	public void setMediumUserId(String mediumUserId) {
		this.mediumUserId = mediumUserId;
	}
	public Feed getMediumFeed() {
		return mediumFeed;
	}
	public void setMediumFeed(Feed mediumFeed) {
		this.mediumFeed = mediumFeed;
	}
}
