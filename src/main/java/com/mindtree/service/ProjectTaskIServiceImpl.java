package com.mindtree.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.dao.ProjectTaskDao;
import com.mindtree.pojo.Employee;
import com.mindtree.pojo.Project;

@Service("projectTaskService")
public class ProjectTaskIServiceImpl implements ProjectTaskService {
	
	private static final Logger logger = LogManager.getLogger(ProjectTaskIServiceImpl.class);
	
	@Autowired
	ProjectTaskDao projectTaskDao;

	public void setProjectTaskDao(ProjectTaskDao projectTaskDao) {
		this.projectTaskDao = projectTaskDao;
	}

	@Override
	public void save(Project project) {
		
		logger.debug("Inside save project method");
		try {
			projectTaskDao.save(project);
		} catch (Exception e) {
			logger.error("Error in saving project: "+e.getMessage());
		}
	}

	@Override
	public void save(Employee employee) {
		
		logger.debug("Inside save employee method");
		try {
			projectTaskDao.save(employee);
		} catch (Exception e) {
			logger.error("Error in saving employee details: "+e.getMessage());
		}
	}

	@Override
	public List<Project> projectDetails() {
		
		logger.debug("Inside projectDetails method");
		List<Project> projectList = new ArrayList<>();
		
		try {
			projectList = projectTaskDao.projectList();
		} catch (Exception e) {
			logger.error("Error in fetching project details: "+e.getMessage());
		}
		
		logger.debug("projectList: " +projectList);
		return projectList;
	}

	@Override
	public List<Employee> employeeDetails(String projectName) {
		
		logger.debug("Inside employeeDetails method");
		List<Employee> employeeList = new ArrayList<>();
		
		try {
			employeeList = projectTaskDao.employeeList(projectName);
		} catch (Exception e) {
			logger.error("Error in fetching employee details: "+e.getMessage());
		}
		
		logger.debug("employeeList: " +employeeList);
		return employeeList;
	}

	@Override
	public boolean updateProjectDetails(Employee project) {
		
		logger.debug("Inside updateProjectDetails method.");
		try {
			projectTaskDao.updateProjectDetails(project);
			return true;
		} catch (Exception e) {
			logger.error("Error in updating employee details: "+e.getMessage());
		}
		return false;
	}

	@Override
	public List<Employee> listOnDescription(String description) {
		
		logger.debug("Inside listOnDescription method.");
		
		List<Employee> employeeList = new ArrayList<>();
		try {
			employeeList = projectTaskDao.employeeDescription(description);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		logger.debug("employeeList: " +employeeList);
		return employeeList;
	}

	@Override
	public List<Project> projectDetailsOnProjectName(String projectName) {
		
		logger.debug("Inside projectDetailsOnProjectName method.");
		List<Project> projectList = new ArrayList<>();
		
		try {
			projectList = projectTaskDao.projectDetails(projectName);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		logger.debug("projectList: " +projectList);
		return projectList;
	}
}