package main;
import java.util.Arrays;
import java.util.List;

import cars.Car;
import repository.CarsRepositoryFile;
import repository.CarsRepositorySerialization;

public class Main {

	public static void main(String[] args) {
		CarsRepositoryFile fileRepo = new CarsRepositoryFile();
		//CarsRepositorySerialization serRepo = new CarsRepositorySerialization();
	
		Car car1 = new Car(3, "Audi", "i8", 277, 17000, "2017", false, "null");
		Car car2 = new Car(4, "BMW", "Alpin", 216, 18000, "2013", true, "13.14.2018");
		Car car3 = new Car(5, "Ford", "Mustang", 300, 25000, "2016", true, "19.07.2019");
		
		fileRepo.add(car1);
		fileRepo.add(car2);
		fileRepo.add(car3);
		List<Car> carsList = Arrays.asList(car1, car2, car3);
		
		for(Car i: fileRepo.findAll()) {
			System.out.println(i);
		}
		
		/*System.out.println("\nBmw cars are:");
		carsList.stream()
			.filter(c -> c.getManufacturer().startsWith("BMW"))
			.forEach(System.out::println);
		
		System.out.println("\nCars after 2010:");
		carsList.stream()
			.filter(c -> Integer.parseInt(c.getManufacturing_year()) > 2010)
			.forEach(System.out::println);

		System.out.println("\nCars before 2015:");
		carsList.stream()
			.filter(c -> Integer.parseInt(c.getManufacturing_year()) < 2015)
			.forEach(System.out::println);*/
	}
}