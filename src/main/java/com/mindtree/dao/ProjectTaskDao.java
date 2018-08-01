package com.mindtree.dao;

import java.util.List;

import com.mindtree.pojo.Employee;
import com.mindtree.pojo.Project;

public interface ProjectTaskDao {

	public void save(Project project);
	public void save(Employee employee);
	public List<Project> projectList();
	public List<Employee> employeeList(String projectName);
	public boolean updateProjectDetails(Employee employee);
	public List<Employee> employeeDescription(String description);
	public List<Project> projectDetails(String projectName);
}
