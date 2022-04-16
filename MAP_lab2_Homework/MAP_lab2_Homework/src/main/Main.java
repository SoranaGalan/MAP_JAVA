package main;

import java.util.*;

import cars.Car;
import repository.CarsRepository;

public class Main {
	public static void ClearScreen() {
		for(int i = 0; i < 100; i++)
			System.out.println("\n");
	}

	public static Car readCar(CarsRepository current_repo, boolean newCar) {
		Scanner keyboard = new Scanner(System.in);

		int ID;
		String manufacturer;
		String model;
		int max_speed;
		float price;
		String manufacturing_year;
		boolean rented = false;
		String rented_date = "";

		if(newCar == true)
			while(true) {
				System.out.println("ID: ");
				ID = keyboard.nextInt();
				if(current_repo.checkID(ID) == false)
					break;
				else System.out.println("!!! ID already in list, please write another one !!!");
			}
		else {
			while(true) {
				System.out.println("ID: ");
				ID = keyboard.nextInt();
				if(current_repo.checkID(ID) == true)
					break;
				else System.out.println("!!! ID not in list, please write another one !!!");
			}
		}
		System.out.println("Manufacturer: ");
		manufacturer = keyboard.next();
		System.out.println("Model: ");
		model = keyboard.next();
		System.out.println("Max Speed: ");
		max_speed = keyboard.nextInt();
		System.out.println("Price: ");
		price = keyboard.nextFloat();
		System.out.println("Year of Production: ");
		manufacturing_year = keyboard.next();

		return new Car(ID, manufacturer, model, max_speed, price, manufacturing_year, rented, rented_date);
	}

	public static void printChoices() {
		String s = "";
		s = s + "\n ------------------------------------ \n";
		s = s + "Please choose one of the options below: \n";
		s = s + "\t \n  1. Add car";
		s = s + "\t \n  2. Remove car";
		s = s + "\t \n  3. Update car";
		s = s + "\t \n  4. Show car by id";
		s = s + "\t \n  5. Show cars";
		s = s + "\t \n  6. Reserve car";
		s = s + "\t \n  7. Cancel car reservation";
		s = s + "\n";
		s = s + "\t \n -1. Exit";
		s = s + "\n ------------------------------------ \n";
		System.out.println(s);
	}

	public static void main(String[] args) {

		CarsRepository repo = new CarsRepository();
		repo.add(new Car(0, "Audi", "TT", 210, 1500, "2005", false, ""));
		repo.add(new Car(1, "Audi", "A4", 200, 1000, "2001", true, "20.05.2022"));
		repo.add(new Car(2, "Volkswagen", "Golf", 180, 1100, "2004", true, "19.11.2021"));

		Scanner keyboard = new Scanner(System.in);

		while(true) {
			printChoices();

			int choice = 0;
			while(true) {
				System.out.println("Please enter your option (only Integers): ");
				// I should've added error handling, but it is not required for now
				choice = keyboard.nextInt();
				if(choice != 0 && choice >= -1 && choice <= 7)
					break;
				else {
					System.out.println("Please choose one of the options from above. Try again: ");
				}
			}

			ClearScreen();

			if(choice == -1) {
				System.out.println("Exiting...");
				break;
			}
			else if (choice == 1) {
				repo.add(readCar(repo, true));
				System.out.println("Car added successfully.");
			}
			else if (choice == 2) {
				System.out.println("What is the ID of the car you want to remove?: ");
				int ID = keyboard.nextInt();
				if(repo.checkID(ID) == true) {
					Car current_car = repo.findById(ID);
					System.out.println("Removed car: " + current_car);
					repo.delete(current_car);
				}
				else System.out.println("Car not in list");
			}
			else if (choice == 3) {
				Car current_car = readCar(repo, false);
				repo.update(current_car, current_car.getID());
				System.out.println("Car updated successfully.");
			}
			else if (choice == 4) {
				int ID = 0;
				System.out.println("ID: ");
				ID = keyboard.nextInt();
				if(repo.checkID(ID) == true)
					System.out.println(repo.findById(ID));
				else System.out.println("!!! ID not in list, please write another one next time !!!");
			}
			else if (choice == 5) {
				System.out.println("The cars are: ");
				for (Car first : repo.findAll())
					System.out.println(first);
			}
			else if (choice == 6) {
				int ID = 0;
				while(true) {
					System.out.println("ID: ");
					ID = keyboard.nextInt();
					if(repo.checkID(ID) == true)
						break;
					else System.out.println("!!! ID not in list, please write another one next time !!!");
				}
				String Date = "";
				System.out.println("The date is: ");
				Date = keyboard.next();
				repo.Rent(ID, Date);
			}
			else if (choice == 7) {
				int ID = 0;
				while(true) {
					System.out.println("ID: ");
					ID = keyboard.nextInt();
					if(repo.checkID(ID) == true)
						break;
					else System.out.println("!!! ID not in list, please write another one next time !!!");
				}
				repo.RemoveRent(ID);
			}
		}
	}
}