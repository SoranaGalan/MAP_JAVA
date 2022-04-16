package controller;

import java.util.*;

import classes.*;
import repository.*;

public class Controller{
	
	
	
	WinterGameRepositoryFile fileRepo = new WinterGameRepositoryFile();
	WinterGameRepository repo = new WinterGameRepository(fileRepo);
	WinterGameRepositorySerialization serRepo = new WinterGameRepositorySerialization(fileRepo);

	EnrollmentsRepositoryFile enrolFileRepo = new EnrollmentsRepositoryFile(repo);
	EnrollmentsRepository enrolRepo = new EnrollmentsRepository(enrolFileRepo);
	EnrollmentsRepositorySerialization enrolSerRepo = new EnrollmentsRepositorySerialization(enrolFileRepo);
	
	public void createWinterGame(int ID, String name, String type, int minimum_age, int maximum_age, String date) {
		WinterGame game = new WinterGame(ID, name, type, minimum_age, maximum_age, date);
		try {
			this.addWinterGame(game);
		}
		catch(Exception e) {
			System.out.println("Game already in list");
		}
	}
	
	public void addWinterGame(WinterGame game) {
		this.fileRepo.add(game);
		this.repo.add(game);
		this.serRepo.add(game);
	}
	
	public void createEnrollment(int ID, String name, int age, String parent_name, int wg_id) {
		try {
			WinterGame game = this.repo.findById(wg_id);
			if(age > game.getMinimum_age() && age < game.getMaximum_age()) {
				Enrollments new_enrollment = new Enrollments(ID, name, age, parent_name, game);	
				try {
					this.addEnrollement(new_enrollment);
				}
				catch(Exception e) {
					System.out.println("Enrollment already in list");
				}
			}
			else {
				System.out.println("Age is not correct for this child");
			}
		}
		catch(Exception e) {
			System.out.println("WinterGame does not exist");
		}
	}
	
	public void addEnrollement(Enrollments enrollment) {
		this.enrolFileRepo.add(enrollment);
		this.enrolRepo.add(enrollment);
		this.enrolSerRepo.add(enrollment);
	}
	
	public void getWinterGames() {
		for(WinterGame game : repo.findAll())
			System.out.println(game);
	}
	
	public void getEnrollments() {
		for(Enrollments enrollment : enrolRepo.findAll())
			System.out.println(enrollment);
	}
	
	public void getEnrollmentsToWinterGameID(int ID) {
		try {
			WinterGame cooking = repo.findById(ID);
			getEnrollmentsToWinterGame(cooking);
		}
		catch(Exception e) {
			System.out.println("Winter game not found");
		}
	}
	
	public void getEnrollmentsToWinterGame(WinterGame game) {
		String s = "";
		for(Enrollments enrollment : enrolRepo.findAll())
			if(enrollment.getGame() == game) {
				s += enrollment;
			}
		if(s.length() > 1) {
			System.out.println(s);
		}
		else {
			System.out.println("There are no enrollments to this game");
		}
	}
	
	public void displayAllWinterGamesToWhichChild(int child_ID) {
		String s = "";
		for(Enrollments enrollment : enrolRepo.findAll())
			if(enrollment.getID() == child_ID) {
				s += enrollment.getGame();
			}
		if(s.length() > 1) {
			System.out.println(s);
		}
		else {
			System.out.println("This child does not exist");
		}
	}
	
//	private List<WinterGame> getCarsList(WinterGameRepository current_repo) {
//		List<WinterGame> carsList =  new ArrayList<WinterGame>();
//		for(WinterGame car : current_repo.findAll())
//			carsList.add(car);
//		
//		return carsList;
//	}
//	
//	public static WinterGame readCar(boolean newCar, WinterGameRepository current_repo) {
//		Scanner keyboard = new Scanner(System.in);
//
//		int ID;
//		String manufacturer;
//		String model;
//		int max_speed;
//		float price;
//		String manufacturing_year;
//		boolean rented = false;
//		String rented_date = "n/a";
//
//		if(newCar == true)
//			while(true) {
//				System.out.println("ID: ");
//				ID = keyboard.nextInt();
//				if(current_repo.checkID(ID) == false)
//					break;
//				else System.out.println("!!! ID already in list, please write another one !!!");
//			}
//		else {
//			while(true) {
//				System.out.println("ID: ");
//				ID = keyboard.nextInt();
//				if(current_repo.checkID(ID) == true)
//					break;
//				else System.out.println("!!! ID not in list, please write another one !!!");
//			}
//		}
//		System.out.println("Manufacturer: ");
//		manufacturer = keyboard.next();
//		System.out.println("Model: ");
//		model = keyboard.next();
//		System.out.println("Max Speed: ");
//		max_speed = keyboard.nextInt();
//		System.out.println("Price: ");
//		price = keyboard.nextFloat();
//		System.out.println("Year of Production: ");
//		manufacturing_year = keyboard.next();
//
//		return new WinterGame(ID, manufacturer, model, max_speed, price, manufacturing_year, rented, rented_date);
//	}
//	
//	public boolean addCar() {
//		WinterGame car = readCar(true, repo);
//		try {
//			repo.add(car);
//			fileRepo.add(car);
//			serRepo.add(car);
//		}
//		catch(Exception e) {
//			System.out.println("Error " + e + " when adding Car to repository");
//			return false;
//		}
//		return true;
//	}
//	
//	public boolean updateCar() {
//		WinterGame car = readCar(false, repo);
//		try {
//			repo.add(car);
//			fileRepo.add(car);
//			serRepo.add(car);
//		}
//		catch(Exception e) {
//			System.out.println("Error " + e + " when adding Car to repository");
//			return false;
//		}
//		return true;
//	}
//	
//	public boolean removeCar(int ID) {
//		if(repo.checkID(ID) == true) {
//			WinterGame current_car = repo.findById(ID);
//			
//			repo.delete(current_car);
//			fileRepo.delete(current_car);
//			serRepo.delete(current_car);
//
//			System.out.println("Removed car: " + current_car);
//			return true;
//		}
//		else {
//			System.out.println("Car not in list");
//			return false;
//		}
//	}
//	
//	public WinterGame getCar(int ID) {
//		if(repo.checkID(ID) == false)
//			return null;
//		else return repo.findById(ID);
//	}
//	
//	public void printCars() {
//		for(WinterGame car : repo.findAll())
//			System.out.println(car);
//	}
//	
//	public void printCarsFromManufacturers(String Manufacturer) {
//		List<WinterGame> carsList = this.getCarsList(repo);
//		
//		carsList.stream()
//			.filter(c -> c.getManufacturer().startsWith(Manufacturer))
//			.forEach(System.out::println);
//	}
//	
//	public void printCarsNewerOrOlderThan(int Year, boolean newer) {
//		List<WinterGame> carsList = this.getCarsList(repo);
//		
//		if(newer)
//			carsList.stream()
//				.filter(c -> Integer.parseInt(c.getManufacturing_year()) > Year)
//				.forEach(System.out::println);
//		else
//			carsList.stream()
//			.filter(c -> Integer.parseInt(c.getManufacturing_year()) < Year)
//			.forEach(System.out::println);
//	}
//	
//	public void printPricesForSpecificCars(String CarsType) {
//		List<WinterGame> carsList = this.getCarsList(repo);
//		
//		carsList.stream()
//			.filter(c -> c.getModel().startsWith(CarsType))
//			.map(cm -> cm.getPrice())
//			.forEach(System.out::println);
//	}
//	
//	public void printYearsOfCarsFromManufacturer(String Manufacturer) {
//		List<WinterGame> carsList = this.getCarsList(repo);
//		
//		carsList.stream()
//			.filter(c -> c.getManufacturer().startsWith(Manufacturer))
//			.map(cm -> cm.getManufacturing_year())
//			.forEach(System.out::println);
//	}
//	
//	public void printSumOfPricesFromManufacturer(String Manufacturer) {
//		List<WinterGame> carsList = this.getCarsList(repo);
//		
//		int sum = carsList.stream()
//			.filter(c -> c.getManufacturer().startsWith(Manufacturer))
//			.mapToInt(cm -> (int)cm.getPrice())
//			.sum();
//		System.out.println(sum);
//	}
//	
//	public void printAllManufacturers() {
//		List<WinterGame> carsList = this.getCarsList(repo);
//		
//		carsList.stream()
//			.map(cm -> cm.getManufacturer())
//			.distinct()
//			.forEach(System.out::println);
//	}
//	
//	public void printCheapestCar() {
//
//		List<WinterGame> carsList = this.getCarsList(repo);
//		
//		WinterGame car = carsList.stream()
//			.min((c1, c2) -> (int)c1.getPrice() - (int)c2.getPrice())
//			.orElseThrow(NoSuchElementException::new);
//		
//		System.out.println(car);
//	}
}
