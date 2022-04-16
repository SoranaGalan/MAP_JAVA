package repository;
import cars.Car;

import java.io.*;
import java.util.*;
import java.util.Properties;
import java.util.stream.Collectors;

public class CarsRepositoryFile extends AbstractRepository<Car, Integer>{
	private String filename;
	
	public CarsRepositoryFile(String filename){
		this.filename=filename;
		readFromFile();
	}
	
	public CarsRepositoryFile() {
		try {
			Properties properties = new Properties();
			properties.load(new FileInputStream("properties"));
			String filename = properties.getProperty("filenameTXT");
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
	
	private void readFromFile(){
		try(BufferedReader reader=new BufferedReader(new FileReader(filename))){
			List<String> list = new ArrayList<>();
			list = reader.lines().collect(Collectors.toList());
			for (String line : list) {
				String[] el=line.split(";");
				if(el.length!=8){
					System.err.println("Not a valid number of atributes " + line);
					continue;
				}
				try{
					int Id=Integer.parseInt(el[0]);
					int maxSpeed = Integer.parseInt(el[3]);
					float price = Float.parseFloat(el[4]);
					boolean rented = Boolean.parseBoolean(el[6]);
					
					Car c = new Car(Id,el[1],el[2],maxSpeed,price,el[5],rented, el[7]);
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
	public void add(Car obj) {
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
			for(Car el : findAll()) {
				String line = el.getID() + ";" + el.getManufacturer() + ";" + el.getModel() +
				";" + el.getMax_speed() + ";" + el.getPrice() + ";" + el.getManufacturing_year() + ";" + el.isRented() + ";" + el.getRented_date();
				//System.out.println(line);
				pw.println(line);
			}
		}catch(IOException ex) {
			throw new RepositoryException("Error writing" + ex);
		}
	}
	@Override
	public void delete(Car obj){
		try{
			super.delete(obj);
			writeToFile();
		}
		catch(RuntimeException ex){
			throw new RepositoryException("Object was not deleted " + ex + " "  + obj);
		}
	}
	@Override
	public void update(Car obj, Integer Id){
		try{
			super.update(obj, Id);
			writeToFile();
		}
		catch(RuntimeException ex){
			throw new RepositoryException("Object was not updated " + ex + " " + obj);
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