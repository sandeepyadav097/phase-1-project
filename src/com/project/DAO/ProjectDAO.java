package com.project.DAO;

import java.util.List;
import java.util.Map;

import com.project.exception.ProjectException;
import com.project.model.Project;

public interface ProjectDAO {

	public Project createProject(Project project) throws ProjectException;
	public List<Project> getAllProjects() throws ProjectException;
	public Map<Integer, Project> getallData() ;
	public void setData(Map<Integer, Project> data, int count) ;
	
	
}
