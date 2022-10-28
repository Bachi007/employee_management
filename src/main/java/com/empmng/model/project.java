package com.empmng.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class project {

	@Id
	private int projectId;
	private String projectName;
	private String projectDuration;

	@ManyToMany
	private List<employee> projectEmp;
}