package com.mindtree.service;

import java.util.List;

import com.mindtree.pojo.Employee;
import com.mindtree.pojo.Project;

public interface ProjectTaskService {

	public void save(Project project);
	public void save(Employee employee);
	public List<Project> projectDetails();
	public List<Employee> employeeDetails(String projectName);
	public boolean updateProjectDetails(Employee employee);
	public List<Employee> listOnDescription(String description);
	public List<Project> projectDetailsOnProjectName(String projectName);
}