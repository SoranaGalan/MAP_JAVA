package repository;

import java.io.*;
import java.util.Map;
import java.util.Properties;

import classes.*;
import repository.*;

public class EnrollmentsRepositorySerialization extends AbstractRepository<Enrollments, Integer>{
	private String filename;

	public EnrollmentsRepositorySerialization(String filename) {
		this.filename=filename;
		try {
			readFromFile();
		} catch(Exception e) {
			System.out.println(filename + " is empty");
		}
	}
	
	public EnrollmentsRepositorySerialization() {
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream("properties"));
			String filename = properties.getProperty("EfilenameBYTES");	// schimba
			if (filename == null){ //the property does not exist in the file
				filename = "enrollmentsbytes.txt";							// schimba
				System.err.println("Requests file not found. Using default " + filename);
			}
			this.filename = filename;
			readFromFile();
		}catch (IOException ex){
			System.err.println("Error reading the configuration file" + ex);
		}
	}
	
	public EnrollmentsRepositorySerialization(EnrollmentsRepositoryFile current_repo) {
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream("properties"));
			String filename = properties.getProperty("EfilenameBYTES");			//schimba
			if (filename == null){ //the property does not exist in the file
				filename = "enrollmentsbytes.txt";							// schimba
				System.err.println("Requests file not found. Using default " + filename);
			}
			this.filename = filename;
		}catch (IOException ex){
			System.err.println("Error reading the configuration file" + ex);
		}
		for(Enrollments car : current_repo.findAll())
			this.add(car);
	}
	
	public EnrollmentsRepositorySerialization(EnrollmentsRepositoryFile current_repo, String filename) {
		this.filename = filename;
		for(Enrollments game : current_repo.findAll())
			this.add(game);
	}
	
	private void readFromFile ()
	{
		try(ObjectInputStream in= new ObjectInputStream (new FileInputStream(filename)))
	    {	
	    	elem = (Map<Integer, Enrollments>) in.readObject();
	  	}
	    catch(Exception err)
	    {
	    	throw new RepositoryException("Error reading from file: "+err);
	    }
	}

    @Override
    public void add(Enrollments obj) {
        try {
            super.add(obj);
            writeToFile();
        } catch (RuntimeException e) {
            throw new RepositoryException("Object wasn’t added" + e + " "+obj);
        }
    }

    @Override
    public void delete(Enrollments obj) {
        try {
            super.delete(obj);
            writeToFile();
        } catch (RuntimeException ex) {
            throw new RepositoryException("Object was not deleted" + ex +" "+obj);
        }
    }
    
    @Override
    public void update(Enrollments obj, Integer id) {
        try {
            super.update(obj, id);
            writeToFile();
        } catch (RuntimeException ex) {
            throw new RepositoryException("Object was not updated" + ex + " "+obj);
        }
    }
    
    public void writeToFile()
    {
		try(ObjectOutputStream e=new ObjectOutputStream(new FileOutputStream(filename))){
        		e.writeObject(elem);
    		}
    	catch(IOException r){
    		throw new RepositoryException("message " + r);
   			}
	 }
	  
}
    	