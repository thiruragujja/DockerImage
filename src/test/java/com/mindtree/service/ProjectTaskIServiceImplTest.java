package com.mindtree.service;

import com.mindtree.dao.ProjectTaskDao;
import com.mindtree.dao.ProjectTaskDaoImpl;
import com.mindtree.pojo.Employee;
import com.mindtree.pojo.Project;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProjectTaskIServiceImplTest {

	ProjectTaskDao projectTaskDao;
	Project project;
	Employee employee;
	
	@Before
	public void setUp() throws Exception {
		projectTaskDao = new ProjectTaskDaoImpl();
		project = new Project();
		employee = new Employee();
	}

	@Test
	public void testSaveProject() {
		Assert.assertNotNull(projectTaskDao);
		projectTaskDao.save(project);
	}

	@Test
	public void testSaveEmployee() {
		Assert.assertNotNull(projectTaskDao);
		projectTaskDao.save(employee);
	}

	@Test
	public void testProjectDetails() {
		Assert.assertNotNull(projectTaskDao);
		projectTaskDao.projectList();
	}

	@Test
	public void testEmployeeDetails() {
		Assert.assertNotNull(projectTaskDao);
		projectTaskDao.employeeList("projectName");
	}

	@Test
	public void testUpdateProjectDetails() {
		Assert.assertNotNull(projectTaskDao);
		projectTaskDao.updateProjectDetails(employee);
	}

	@Test
	public void testListOnDescription() {
		Assert.assertNotNull(projectTaskDao);
		projectTaskDao.employeeDescription("description");
	}

	@Test
	public void testProjectDetailsOnProjectName() {
		Assert.assertNotNull(projectTaskDao);
		projectTaskDao.projectDetails("projectName");
	}

}
