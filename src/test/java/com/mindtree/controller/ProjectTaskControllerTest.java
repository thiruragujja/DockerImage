package com.mindtree.controller;

import com.mindtree.pojo.Employee;
import com.mindtree.service.ProjectTaskIServiceImpl;
import com.mindtree.service.ProjectTaskService;

import junit.framework.Assert;

import org.junit.Test;

@SuppressWarnings("deprecation")
public class ProjectTaskControllerTest {

	ProjectTaskController projectTaskController = new ProjectTaskController();
	
	@Test
	public void testAssignTask() {
		ProjectTaskService projectTaskService = new ProjectTaskIServiceImpl();
		projectTaskService.projectDetails();
		Assert.assertNotNull(projectTaskService);
	}

	@Test
	public void testViewTaskProjects() {
		ProjectTaskService projectTaskService = new ProjectTaskIServiceImpl();
		projectTaskService.projectDetails();
		Assert.assertNotNull(projectTaskService);
	}

	@Test
	public void testGetEmployees() {
		ProjectTaskService projectTaskService = new ProjectTaskIServiceImpl();
		projectTaskService.employeeDetails("projectName");
		Assert.assertNotNull(projectTaskService);
	}

	@Test
	public void testUpdateProject() {
		ProjectTaskService projectTaskService = new ProjectTaskIServiceImpl();
		Employee employee = new Employee();
		projectTaskService.updateProjectDetails(employee);
		Assert.assertNotNull(projectTaskService);
	}

	@Test
	public void testGetProjectDetails() {
		ProjectTaskService projectTaskService = new ProjectTaskIServiceImpl();
		projectTaskService.projectDetails();
		Assert.assertNotNull(projectTaskService);
	}

}
