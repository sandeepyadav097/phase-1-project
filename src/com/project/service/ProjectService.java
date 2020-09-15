package com.project.service;

import java.util.List;

import com.project.exception.ProjectException;
import com.project.model.Project;

public interface ProjectService {
	public Project createProject(Project project) throws ProjectException;
	public List<Project> getAllProjects() throws ProjectException;
	public void deSerializeData(); 
	public void serializeData();
	public List<Project> search(String name);
	public void deleteByID(int id); 
}
