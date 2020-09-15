package com.project.main;

import java.util.List;
import java.util.Scanner;

import com.project.DAO.ProjectDAO;
import com.project.DAOimpl.projectDAOImpl;
import com.project.exception.ProjectException;
import com.project.model.Project;
import com.project.service.ProjectService;
import com.project.serviceImpl.ProjectServiceImpl;

public class main {

	public static void main(String[] args) throws ProjectException {
		
		
		int ch = 0;
		Scanner scanner = new Scanner(System.in);
		ProjectService service= new ProjectServiceImpl();
		service.deSerializeData();
		System.out.println("Welcome to File Manager Application v1.0 developed by Sandeep Yadav .....");
		do {
			
			System.out.println("Choose your option");
			System.out.println("====================================");
			System.out.println("1) View All Data");
			System.out.println("2) Modify Data");
			System.out.println("3) Exit");
			
			try {
				ch = Integer.parseInt(scanner.nextLine());
			} catch (NumberFormatException e) {
				
				System.out.println("Invalid Input !");
				continue;
			}			
			
			switch (ch) {
			case 1:
				System.out.println(service.getAllProjects());
				
			 break;
			
			case 2:
System.out.println("Choose your option");
				
				
				int newCh=0;
				do {
					System.out.println("====================================");
					System.out.println("1) View All Data");
					System.out.println("2) Add data");
					System.out.println("3) Delete data");
					System.out.println("4) Search by Name");
					System.out.println("5) Go back ");
					newCh=Integer.parseInt(scanner.nextLine());
					switch(newCh) {
					case 1:
						System.out.println(service.getAllProjects());
						break;
					case 2:
						
						Project project=new Project();
						System.out.println("Enter Name");
						project.setFileName(scanner.nextLine());
						service.createProject(project);
						break;
					
					case 3:
						System.out.println("------------------------");
						System.out.println("Enter ID for record to be deleted");
						int id=Integer.parseInt(scanner.nextLine());
						
						service.deleteByID(id);
						break;
						
					case 4:
						System.out.println("------------------------");
						System.out.println("Enter name to search for");
						String name=scanner.nextLine();
						List <Project> data=service.search(name);
						if(data.size()>0) {
							System.out.println(data.size()+" match found !");
							System.out.println(data);
						}
						else {
							System.out.println("No data found");
						}
						
						
						break;
						
					case 5:
						System.out.println("Going back to main menu.");
						break;
					default:
						System.out.println("Enter a valid Choice");
						break;
					}
					
				}
				while(newCh!=5);
				
				break;
			
			case 3:
				System.out.println("Exiting Application. Persisting data !");
				break;
	
				
			default:
				
				System.out.println("Invalid Option ! Please Re-enter your choice!");
				break;
				
			
		}
		}
		while(ch!=3);
		System.out.println("Done !");
		service.serializeData();


		//System.out.println(service.getAllProjects());
		
		
		
	}

}
