package repository;

import java.io.*;
import java.util.Map;
import java.util.Properties;

import classes.WinterGame;
import repository.RepositoryException;
import repository.WinterGameRepositoryFile;

public class WinterGameRepositorySerialization extends AbstractRepository<WinterGame, Integer>{
	private String filename;

	public WinterGameRepositorySerialization(String filename) {
		this.filename=filename;
		try {
			readFromFile();
		} catch(Exception e) {
			System.out.println(filename + " is empty");
		}
	}
	
	public WinterGameRepositorySerialization() {
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream("properties"));
			String filename = properties.getProperty("WGfilenameBYTES");	// schimba
			if (filename == null){ //the property does not exist in the file
				filename = "wintergamebytes.txt";							// schimba
				System.err.println("Requests file not found. Using default " + filename);
			}
			this.filename = filename;
			readFromFile();
		}catch (IOException ex){
			System.err.println("Error reading the configuration file" + ex);
		}
	}
	
	public WinterGameRepositorySerialization(WinterGameRepositoryFile current_repo) {
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream("properties"));
			String filename = properties.getProperty("WGfilenameBYTES");			//schimba
			if (filename == null){ //the property does not exist in the file
				filename = "wintergamebytes.txt";								//schimba
				System.err.println("Requests file not found. Using default " + filename);
			}
			this.filename = filename;
		}catch (IOException ex){
			System.err.println("Error reading the configuration file" + ex);
		}
		for(WinterGame car : current_repo.findAll())
			this.add(car);
	}
	
	public WinterGameRepositorySerialization(WinterGameRepositoryFile current_repo, String filename) {
		this.filename = filename;
		for(WinterGame game : current_repo.findAll())
			this.add(game);
	}
	
	private void readFromFile ()
	{
		try(ObjectInputStream in= new ObjectInputStream (new FileInputStream(filename)))
	    {	
	    	elem = (Map<Integer, WinterGame>) in.readObject();
	  	}
	    catch(Exception err)
	    {
	    	throw new RepositoryException("Error reading from file: "+err);
	    }
	}

    @Override
    public void add(WinterGame obj) {
        try {
            super.add(obj);
            writeToFile();
        } catch (RuntimeException e) {
            throw new RepositoryException("Object wasn’t added" + e + " "+obj);
        }
    }

    @Override
    public void delete(WinterGame obj) {
        try {
            super.delete(obj);
            writeToFile();
        } catch (RuntimeException ex) {
            throw new RepositoryException("Object was not deleted" + ex +" "+obj);
        }
    }
    
    @Override
    public void update(WinterGame obj, Integer id) {
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
    	