package com.mindtree.pojo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class TaskEmployee implements Serializable{

	private static final long serialversionUID = 1L;
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
		return "TaskEmployee [id: " + id + ", name: " + name + "]";
	}
}
