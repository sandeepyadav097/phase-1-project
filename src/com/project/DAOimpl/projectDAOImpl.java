package com.project.DAOimpl;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.project.DAO.ProjectDAO;
import com.project.exception.ProjectException;
import com.project.model.Project;

public class projectDAOImpl implements ProjectDAO {
	private static Map<Integer, Project> ProjectMap = new HashMap<>();
	private static int count;

	@Override
	public Project createProject(Project project) throws ProjectException {
		project.setId(++count);
		ProjectMap.put(project.getId(), project);
		
		FileOutputStream fos=null;
		BufferedOutputStream bos=null;
		
		
		try {
			fos=new FileOutputStream(project.getFileName());
			bos=new BufferedOutputStream(fos);
			String message="FileName - "+project.getFileName() + "\nID" + project.getId();
			byte b[]=message.getBytes();
			bos.write(b);
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}finally {
			try {
				bos.flush();
				bos.close();
				fos.close();
			} catch (IOException e) {
				System.out.println(e);
			}
		}
		return project;
		
	}

	

	@Override
	public List<Project> getAllProjects() throws ProjectException {
		List<Project> files= ProjectMap.values().stream().sorted(( o1, o2) -> o1.getFileName().compareTo(o2.getFileName())).collect(Collectors.toList());
		
		return files;
	}

	@Override
	public Map<Integer, Project> getallData()  {
		// TODO Auto-generated method stub
		return ProjectMap;
	}

	@Override
	public void setData(Map<Integer, Project> data, int size) {
		// TODO Auto-generated method stub
		this.ProjectMap = data;
		this.count=size;
	}
	
}
