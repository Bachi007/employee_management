package com.empmng.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int empId;
	private int empSalary;
	private String empName;
	private String empDomain;
	private String empDesignation;
	private String empLocation;
	private String empPhone;

	@ManyToMany(targetEntity = employee.class)
	@JoinTable(name = "empProject", joinColumns = { @JoinColumn(name = "empId") }, inverseJoinColumns = {
			@JoinColumn(name = "projectId") })
	private List<project> empProject;
}
