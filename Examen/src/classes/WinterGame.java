// selectezi toate variabilele
// alt + shift + s
// apesi pe "GENERATE GETTERS AND SETTERS"

package classes;
import java.io.Serializable;

public class WinterGame implements Identifiable<Integer>, Serializable  {
	private int ID;
	private String name;
	private String type;
	private int minimum_age;
	private int maximum_age;
	private String date;

	public WinterGame() {
		this.ID = 0;
		this.name = "";
		this.type = "";
		this.minimum_age = 0;
		this.maximum_age = 100;
		this.date = "";
	}

	public WinterGame(int ID, String name, String type, int minimum_age, int maximum_age, String date) {
		this.ID = ID;
		this.name = name;
		this.type = type;
		this.minimum_age = minimum_age;
		this.maximum_age = maximum_age;
		this.date = date;
	}

	
	
	public Integer getID() {
		return ID;
	}

	public void setID(Integer Id) {
		ID = Id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getMinimum_age() {
		return minimum_age;
	}

	public void setMinimum_age(int minimum_age) {
		this.minimum_age = minimum_age;
	}

	public int getMaximum_age() {
		return maximum_age;
	}

	public void setMaximum_age(int maximum_age) {
		this.maximum_age = maximum_age;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		String s = "";
		s = s + "ID: " + this.ID + ", ";
		s = s + "Name: " + this.name + ", ";
		s = s + "Type: " + this.type + ", ";
		s = s + "With a minimum age of: " + this.minimum_age + ", ";
		s = s + "And a maximum age of: " + this.maximum_age + ", ";
		s = s + "With the date: " + this.date;
		return s;
	}

}