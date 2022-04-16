package repository;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import classes.*;
import repository.*;

public class EnrollmentsRepositoryFile extends AbstractRepository<Enrollments, Integer>{
	private String filename;
	private WinterGameRepository repo;
	
	public EnrollmentsRepositoryFile(String filename, WinterGameRepository repo){
		this.filename=filename;
		this.repo = repo;
		try {
			readFromFile(repo);
		} catch(Exception e) {
			System.out.println(filename + " is empty");
		}
	}
	
	public EnrollmentsRepositoryFile(WinterGameRepository repo) {
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream("properties"));
			String filename = properties.getProperty("EfilenameTXT");		//schimba
			if (filename == null){ //the property does not exist in the file
				filename = "enrollments.txt";								//schimba
				System.err.println("Requests file not found. Using default " + filename);
			}
			this.filename = filename;
			readFromFile(repo);
		}catch (IOException ex){
			System.err.println("Error reading the configuration file" + ex);
		}
	}
	
	private void readFromFile(WinterGameRepository repo){
		try(BufferedReader reader=new BufferedReader(new FileReader(filename))){
			List<String> list = new ArrayList<>();
			list = reader.lines().collect(Collectors.toList());
			for (String line : list) {
				String[] el=line.split(";");
				if(el.length!=5){
					System.err.println("Not a valid number of atributes " + line);
					continue;
				}
				try{
					int Id=Integer.parseInt(el[0]);
					int age = Integer.parseInt(el[2]);
					int wg_id = Integer.parseInt(el[4]);
					WinterGame game = repo.findById(wg_id);
					
					Enrollments c = new Enrollments(Id,el[1],age,el[3],game);
					super.add(c);
				}catch(NumberFormatException n){
					System.err.println("The ID is not a valid number " + el[0]);
				}
			}
		}catch(IOException ex){
			throw new RepositoryException("Error reading" + ex);
		}
	}
	@Override
	public void add(Enrollments obj) {
		try{
			super.add(obj);
			writeToFile();
		}
		catch(RuntimeException e){
			throw new RepositoryException("Object wasn’t added " + e + " " + obj);
		}
	}
	private void writeToFile() {
			for(Enrollments el : findAll()) {
				String line = el.getID() + ";" + el.getName() + ";" + el.getAge() +
				";" + el.getParent_name() + ";" + el.getGame().getID();
				//System.out.println(line);
				pw.println(line);
	}
	@Override
	public void delete(Enrollments obj){
			super.delete(obj);
			writeToFile();
	}
	@Override
	public void update(Enrollments obj, Integer Id){
			super.update(obj, Id);
			writeToFile();
	}
}