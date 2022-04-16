package repository;
import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

import classes.WinterGame;

public class WinterGameRepositoryFile extends AbstractRepository<WinterGame, Integer>{
	private String filename;
	
	public WinterGameRepositoryFile(String filename){
		this.filename=filename;
		try {
			readFromFile();
		} catch(Exception e) {
			System.out.println(filename + " is empty");
		}
	}
	
	public WinterGameRepositoryFile() {
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream("properties"));
			String filename = properties.getProperty("WGfilenameTXT");
			if (filename == null){ //the property does not exist in the file
				filename = "wintergame.txt";
				System.err.println("Requests file not found. Using default " + filename);
			}
			this.filename = filename;
			readFromFile();
		}catch (IOException ex){
			System.err.println("Error reading the configuration file" + ex);
		}
	}
	
	private void readFromFile(){
		try(BufferedReader reader=new BufferedReader(new FileReader(filename))){
			List<String> list = new ArrayList<>();
			list = reader.lines().collect(Collectors.toList());
			for (String line : list) {
				String[] el=line.split(";");
				if(el.length!=6){
					System.err.println("Not a valid number of atributes " + line);
					continue;
				}
				try{
					int Id=Integer.parseInt(el[0]);
					int Minimum_age = Integer.parseInt(el[3]);
					int maximum_age = Integer.parseInt(el[4]);
					
					WinterGame c = new WinterGame(Id,el[1],el[2],Minimum_age,maximum_age,el[5]);
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
	public void add(WinterGame obj) {
		try{
			super.add(obj);
			writeToFile();
		}
		catch(RuntimeException e){
			throw new RepositoryException("Object wasn’t added " + e + " " + obj);
		}
	}
	private void writeToFile() {
		try(PrintWriter pw = new PrintWriter(filename)) {
			for(WinterGame el : findAll()) {
				String line = el.getID() + ";" + el.getName() + ";" + el.getType() +
				";" + el.getMinimum_age() + ";" + el.getMaximum_age() + ";" + el.getDate();
				//System.out.println(line);
				pw.println(line);
			}
		}catch(IOException ex) {
			throw new RepositoryException("Error writing" + ex);
		}
	}
	@Override
	public void delete(WinterGame obj){
		try{
			super.delete(obj);
			writeToFile();
		}
		catch(RuntimeException ex){
			throw new RepositoryException("Object was not deleted " + ex + " "  + obj);
		}
	}
	@Override
	public void update(WinterGame obj, Integer Id){
		try{
			super.update(obj, Id);
			writeToFile();
		}
		catch(RuntimeException ex){
			throw new RepositoryException("Object was not updated " + ex + " " + obj);
		}
	}
}