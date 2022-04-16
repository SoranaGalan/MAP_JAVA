package repository;

import java.io.*;
import java.util.Map;
import java.util.Properties;

import repository.RepositoryException;
import cars.Car;

public class CarsRepositorySerialization extends AbstractRepository<Car, Integer>{
	private String filename;

	public CarsRepositorySerialization(String filename) {
		this.filename = filename;
		readFromFile();
	}
	
	public CarsRepositorySerialization() {
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream("properties"));
			String filename = properties.getProperty("filenameBYTES");
			if (filename == null){ //the property does not exist in the file
				filename = "cars.txt";
				System.err.println("Requests file not found. Using default " + filename);
			}
			this.filename = filename;
			readFromFile();
		}catch (IOException ex){
			System.err.println("Error reading the configuration file" + ex);
		}
	}
	
	private void readFromFile ()
	{
		try(ObjectInputStream in= new ObjectInputStream (new FileInputStream(filename)))
	    {	
	    	elem = (Map<Integer, Car>) in.readObject();
	  	}
	    catch(Exception err)
	    {
	    	throw new RepositoryException("Error reading from file: "+err);
	    }
	}

    @Override
    public void add(Car obj) {
        try {
            super.add(obj);
            writeToFile();
        } catch (RuntimeException e) {
            throw new RepositoryException("Object wasn’t added" + e + " "+obj);
        }
    }

    @Override
    public void delete(Car obj) {
        try {
            super.delete(obj);
            writeToFile();
        } catch (RuntimeException ex) {
            throw new RepositoryException("Object was not deleted" + ex +" "+obj);
        }
    }
    
    @Override
    public void update(Car obj, Integer id) {
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
    
    public boolean Rent(Integer ID, String Date) {
		if(this.findById(ID).isRented() == true) {
			System.out.println("!!! Can not rent because car is already rented !!!");
			return false;
		}
		else {
			this.findById(ID).setRented(true);
			this.findById(ID).setRented_date(Date);
			return true;
		}
	}

	public boolean RemoveRent(Integer ID) {
		if(this.findById(ID).isRented() == false) {
			System.out.println("!!! Can not remove rent because car is not rented !!!");
			return false;
		}
		else {
			this.findById(ID).setRented(false);
			this.findById(ID).setRented_date("");
			return true;
		}
	}
	
	public Car CheapestCar() {
		float cheap_price = 999999999;
		Car cheapest_car = null;
		for (Car car : this.findAll())
			if(cheap_price > car.getPrice()) {
				cheap_price = car.getPrice();
				cheapest_car = car;
			}			
		return cheapest_car;
		}
	
	public void RentedCars() {
		for (Car car : this.findAll())
			if(car.isRented() == true) {
				System.out.println(car);
			}
	}
	  
}
    	