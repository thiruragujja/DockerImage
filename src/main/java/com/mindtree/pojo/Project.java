package com.mindtree.pojo;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "project")
@JsonIgnoreProperties(ignoreUnknown=false)
public class Project implements Serializable{

	private static final long serialVersionUID = -1411766798423906474L;
	private int projectId;
	private String projectName;
	private List<Employee> employeeList = new ArrayList<>();

	@Id
	@Column(name = "projectId")
	@GeneratedValue(strategy = IDENTITY)
	public int getProjectId() {
		return projectId;
	}
	
	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}
	
	@Column(name="projectName",unique = true, nullable = false)
	public String getProjectName() {
		return projectName;
	}
	
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "project")
    @JsonManagedReference
	public List<Employee> getEmployeeList() {
		return employeeList;
	}
	
	public void setEmployeeList(List<Employee> employeeList) {
		this.employeeList = employeeList;
	}
	
	@Override
	public boolean equals(Object arg0) {
		return super.equals(arg0);
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public String toString() {
		return "Project [projectId: " + projectId + ", projectName: " + projectName + ", employeeList: " + employeeList + "]";
	}
}