package com.project.serviceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.project.DAO.ProjectDAO;
import com.project.DAOimpl.projectDAOImpl;
import com.project.exception.ProjectException;
import com.project.model.Project;
import com.project.service.ProjectService;

public class ProjectServiceImpl implements ProjectService{
	
	private ProjectDAO dao=new projectDAOImpl();
	
	@Override
	public Project createProject(Project project) throws ProjectException {
		if(!isValidName(project.getFileName())) {
			throw new ProjectException("Entered name "+project.getFileName()+" is invalid");
		}
		Project p=dao.createProject(project);
		this.serializeData();
		return p;
	}

	@Override
	public List<Project> getAllProjects() throws ProjectException {
		
		return dao.getAllProjects();
		
	}
	
	private boolean isValidName(String name) {
		boolean b=false;
		if(name.trim().matches("[a-zA-Z. ]{3,20}")) {
			b=true;
		}
		return b;
	}

	@Override
	public void deSerializeData() {
		try(FileInputStream fis=new FileInputStream("persist.txt");
				ObjectInputStream ois=new ObjectInputStream(fis);
				){
			Map<Integer, Project> deserialzed=(Map<Integer, Project>) ois.readObject();
			dao.setData(deserialzed, deserialzed.size());
		} catch (IOException | ClassNotFoundException e) {
			// System.out.println(e);
		}
	}

	@Override
	public void serializeData() {
		try(FileOutputStream fos=new FileOutputStream("persist.txt");
				ObjectOutputStream oos=new ObjectOutputStream(fos);
				){
			
			Map<Integer, Project> ProjectMap=dao.getallData();
			
			oos.writeObject(ProjectMap);
			
		} catch (FileNotFoundException e) {
			System.out.println(e);
		} catch (IOException e) {
			System.out.println(e);
		}
		
	}

	@Override
	public List<Project> search(String name) {
		// TODO Auto-generated method stub
		try {
			List <Project> p =dao.getAllProjects().stream().filter(  a -> a.getFileName().contains(name)).collect(Collectors.toList());
			return p;
		} catch (ProjectException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
		
		return null;
		
	}

	@Override
	public void deleteByID(int id) {
		// TODO Auto-generated method stub
		Map<Integer, Project> data= dao.getallData();
		
		if(data.containsKey(id)) {
			File file = new File(data.get(id).getFileName().toString());
		
	        if(file.delete()) 
	        { 
	            System.out.println("Data removed succesfully !!"); 
	        } 
			
	        data.remove(id);
			this.serializeData();
		}
		else {
			System.out.println("File Not found !");
			
		}
		
	}

	
}
