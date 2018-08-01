package com.mindtree.pojo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonBackReference;

import static javax.persistence.GenerationType.IDENTITY;

@SuppressWarnings("serial")
@Entity
@Table(name = "employee")
public class Employee implements Serializable {

	@SuppressWarnings("unused")
	private static final long serialVersionUid = 1L;

	private int empId;
	private String name;
	private Project project;
	private String taskDescription;
	private Date startDate;
	private Date endDate;

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "employeeId", unique = true, nullable = false)
	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	@Column(name = "employeeName")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_Id", nullable = false)
	@JsonBackReference
	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	@Temporal(TemporalType.DATE)
	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Temporal(TemporalType.DATE)
	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
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
		return "Employee [empId: " + empId + ", name: " + name + ", taskDescription: " + taskDescription
				+ ", startDate: " + startDate + ", endDate: " + endDate + "]";
	}
}