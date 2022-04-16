package repository;

import cars.*;

public class CarsRepository extends AbstractRepository<Car, Integer> {
	public CarsRepository() {
	};

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
};