package main;

import java.util.Scanner;

import classes.WinterGame;
import controller.Controller;

public class Main {

	public static void clearScreen() {
		for(int i = 0; i < 100; i++)
			System.out.println("\n");
	}
	
	public static void printChoices() {
		String s = "";
		s = s + "\n ------------------------------------ \n";
		s = s + "Please choose one of the options below: \n";
		s = s + "\t \n  1 . Add Cooking class";
		s = s + "\t \n  2 . Add Subscription";
		s = s + "\t \n  3 . Print C";
		s = s + "\t \n  4 . Print Enrollments";
		s = s + "\t \n  5 . Print Enrollments for a certain Winter Game";
		s = s + "\t \n  6 . Print Winter Games for specific child";
		s = s + "\n";
		s = s + "\t \n -1. Exit";
		s = s + "\n ------------------------------------ \n";
		System.out.println(s);
	}
	
	public static void mainFunc() {
		int choice = 0;
		Scanner keyboard = new Scanner(System.in);
		Controller controller = new Controller();
		
		while(true) {
			printChoices();
			choice = 0;
			
			while(true) {
				System.out.println("Please enter your option (only Integers): ");
				choice = keyboard.nextInt();
				keyboard.nextLine();
				if(choice != 0 && choice >= -1 && choice <= 6)
					break;
				else {
					System.out.println("Please choose one of the options from above. Try again: ");
				}
			}

			clearScreen();

			if(choice == -1) {
				System.out.println("Exiting...");
				break;
			}
			else if (choice == 1) {
				System.out.println("Please enter the Winter Game id: ");
				int ID = keyboard.nextInt();
				System.out.println("Please enter the Winter Game name: ");
				String name = keyboard.next();
				System.out.println("Please enter the Winter Game type: ");
				String type = keyboard.next();
				System.out.println("Please enter the Winter Game minimum age: ");
				int minimum_age = keyboard.nextInt();
				System.out.println("Please enter the Winter Game maximum age: ");
				int maximum_age = keyboard.nextInt();
				System.out.println("Please enter the Winter Game date: ");
				String date = keyboard.next();
				controller.createWinterGame(ID, name, type, minimum_age, maximum_age, date);
			}
			else if (choice == 2) {
				System.out.println("Please enter the Enrollment id: ");
				int ID = keyboard.nextInt();
				System.out.println("Please enter the Child's name: ");
				String name = keyboard.next();
				System.out.println("Please enter the Child's age: ");
				int age = keyboard.nextInt();
				System.out.println("Please enter the Winter parent's name: ");
				String parent_name = keyboard.next();
				System.out.println("Please enter the Winter Game id: ");
				int WG_ID = keyboard.nextInt();
				controller.createEnrollment(ID, name, age, parent_name, WG_ID);
			}
			else if (choice == 3) {
				controller.getWinterGames();
			}
			else if (choice == 4) {
				controller.getEnrollments();
			}
			else if (choice == 5) {
				System.out.println("Please enter the Winter Game id: ");
				int wg_id = 0;
				wg_id = keyboard.nextInt();
				controller.getEnrollmentsToWinterGameID(wg_id);
			}
			else if (choice == 6) {
				System.out.println("Please enter the Enrollment (child) id: ");
				int en_id = 0;
				en_id = keyboard.nextInt();
				controller.displayAllWinterGamesToWhichChild(en_id);
			}
		}
	}
	
	public static void main(String[] args) {
//		Controller controller = new Controller();
		
//		controller.createWinterGame(2, "gigel", "gigel game", 2, 18, "19/12/2021");
		
//		controller.getWinterGames();
		
//		controller.createEnrollment(2, "GigelCopilulMinune", 19, "MamaGigel", 2);
//		controller.getEnrollments();
		
//		controller.getEnrollmentsToWinterGameID(2);
		
//		controller.displayAllWinterGamesToWhichChild(5);
		
		mainFunc();
	}
}