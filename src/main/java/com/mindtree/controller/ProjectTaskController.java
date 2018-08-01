package com.mindtree.controller;

import java.util.Date;
import java.util.HashSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mindtree.pojo.Employee;
import com.mindtree.pojo.Project;
import com.mindtree.pojo.TaskEmployee;
import com.mindtree.pojo.Tasks;
import com.mindtree.service.ProjectTaskService;

@Controller
@RequestMapping("")
public class ProjectTaskController {

	private static final Logger logger = LogManager.getLogger(ProjectTaskController.class);

	@Autowired
	private ProjectTaskService projectTaskService;

	@RequestMapping("/assignTask")
	public ModelAndView assignTask() {
		
		logger.debug("Inside assignTask method");
		ModelAndView modelAndView = new ModelAndView("assignTask");
		List<Project> projectList = projectTaskService.projectDetails();
		modelAndView.addObject("projectDetails", projectList);
		logger.debug("modelAndView: " +modelAndView);
		return modelAndView;
	}

	@RequestMapping("/viewTaskProjects")
	public ModelAndView viewTaskProjects() {
		
		logger.debug("Inside viewTaskProjects method");
		ModelAndView modelAndView = new ModelAndView("viewTask");
		Project project = new Project();
		project.setProjectName("ALL PROJECTS");
		List<Project> projectList = projectTaskService.projectDetails();
		projectList.add(project);
		modelAndView.addObject("projectDetails", projectList);
		logger.debug("modelAndView: " +modelAndView);
		return modelAndView;
	}

	@RequestMapping(value = "/getEmployees", method = RequestMethod.GET, headers = "Accept=*/*")
	@ResponseBody
	public String getEmployees(@RequestParam("projectName") String projectName, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model) {

		logger.debug("Inside getEmployees method");
		logger.debug("projectName: " + projectName);
		ObjectMapper mapper = new ObjectMapper();
		String employees = null;
		String message = "There are no employees found for the selected project. Please select other project";
		
		List<Employee> employeeList = projectTaskService.employeeDetails(projectName);
		if (employeeList.isEmpty()) {
			try {
				employees = mapper.writeValueAsString(message);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		} else {
			try {
				mapper.setVisibilityChecker(mapper.getVisibilityChecker().with(Visibility.ANY));
				employees = mapper.writeValueAsString(employeeList);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		logger.debug("employees: ", employees);
		return employees;
	}

	@RequestMapping(value = "/updateProject", method = RequestMethod.POST, headers = "Accept=*/*", produces = "application/json")
	public @ResponseBody Employee updateProject(HttpServletRequest request, HttpServletResponse response) {

		logger.debug("Inside updateProject method");
		String start = request.getParameter("startDate");
		String end = request.getParameter("endDate");
		int empId = Integer.parseInt(request.getParameter("empId"));
		String description = request.getParameter("description");
		
		logger.debug("start: " +start+ " end: " +end+ " empId: " +empId+ " desc: " +description );
		SimpleDateFormat dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
		Date startDate = null;
		Date endDate = null;
		Employee employee = new Employee();
		
		try {
			startDate = dateFormatter.parse(start);
			endDate = dateFormatter.parse(end);
		} catch (ParseException e) {
			logger.error("Parsing exception: "+e.getMessage());
		}

		employee.setEmpId(empId);
		employee.setTaskDescription(description);
		employee.setStartDate(startDate);
		employee.setEndDate(endDate);
		projectTaskService.updateProjectDetails(employee);
		logger.debug("employee: ", employee);
		return employee;
	}

	@RequestMapping(value = "/getProjectDetails", method = RequestMethod.GET, headers = "Accept=*/*", produces = "application/json")
	@ResponseBody
	public String getProjectDetails(@RequestParam("projectName") String projectName, HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, Model model) {

		logger.debug("Inside getProjectDetails method");
		logger.debug("projectName: " + projectName);
		List<Project> projectList = new ArrayList<>();
		List<Tasks> tasksList = new ArrayList<>();
		String projectDetails = "";
		String task = "";
		Set<String> taskSet = new HashSet<>();
		
		try {
			if (projectName.equalsIgnoreCase("ALL PROJECTS")) {
				projectList = projectTaskService.projectDetails();
			} else {
				projectList = projectTaskService.projectDetailsOnProjectName(projectName);
			}
			
			for (int i = 0; i < projectList.size(); i++) {
				for (int j = 0; j < projectList.get(i).getEmployeeList().size(); j++) {
					task = projectList.get(i).getEmployeeList().get(j).getTaskDescription();
					taskSet.add(task);
					taskSet.remove(null);
				}
			}

			for (String taskDesc : taskSet) {
				Tasks tasks = new Tasks();
				List<Employee> employeeList = projectTaskService.listOnDescription(taskDesc);
				List<TaskEmployee> taskEmployeeList = new ArrayList<>();
				
				for (int i = 0; i < employeeList.size(); i++) {
					TaskEmployee taskEmployee = new TaskEmployee();
					taskEmployee.setId(employeeList.get(i).getEmpId());
					taskEmployee.setName(employeeList.get(i).getName());
					taskEmployeeList.add(taskEmployee);
				}
				for (int i = 0; i < employeeList.size(); i++) {
					tasks.setEndTask(employeeList.get(i).getEndDate());
					tasks.setStartTask(employeeList.get(i).getStartDate());
					tasks.setTaskDescription(employeeList.get(i).getTaskDescription());
					tasks.setProjectName(employeeList.get(i).getProject().getProjectName());
				}
				tasks.setListTaskEmployee(taskEmployeeList);
				tasksList.add(tasks);
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		
		try {
			ObjectMapper mapper = new ObjectMapper();
			mapper.setVisibilityChecker(mapper.getVisibilityChecker().with(Visibility.ANY));
			projectDetails = mapper.writeValueAsString(tasksList);
		} catch (JsonProcessingException e) {
			logger.error("JsonProcessingException: "+e.getMessage());
		}
		
		logger.debug("projectDetails: " +projectDetails);
		return projectDetails;
	}
}