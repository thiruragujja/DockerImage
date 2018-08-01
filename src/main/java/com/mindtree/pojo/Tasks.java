package com.mindtree.pojo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class Tasks implements Serializable{
	
	@SuppressWarnings("unused")
	private static final long serialversionUID = 1L;
	private List<TaskEmployee> listTaskEmployee; 
	private String taskDescription;
	private Date startTask;
	private Date endTask;
	private String projectName;
	
	public String getProjectName() {
		return projectName;
	}
	@Override
	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public List<TaskEmployee> getListTaskEmployee() {
		return listTaskEmployee;
	}
	public void setListTaskEmployee(List<TaskEmployee> listTaskEmployee) {
		this.listTaskEmployee = listTaskEmployee;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	public Date getStartTask() {
		return startTask;
	}
	public void setStartTask(Date startTask) {
		this.startTask = startTask;
	}
	public Date getEndTask() {
		return endTask;
	}
	public void setEndTask(Date endTask) {
		this.endTask = endTask;
	}
	@Override
	public String toString() {
		return "Tasks [listTaskEmployee=" + listTaskEmployee + ", taskDescription=" + taskDescription + ", startTask="
				+ startTask + ", endTask=" + endTask + ", projectName=" + projectName + "]";
	}
}