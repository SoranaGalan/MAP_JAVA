// selectezi toate variabilele
// alt + shift + s
// apesi pe "GENERATE GETTERS AND SETTERS"

package classes;
import java.io.Serializable;
import classes.WinterGame;

public class Enrollments implements Identifiable<Integer>, Serializable  {
	private int ID;
	private String name;
	private int age;
	private String parent_name;
	private WinterGame game;

	public Enrollments() {
		this.ID = 0;
		this.name = "";
		this.age = 0;
		this.parent_name = "";
		this.game = new WinterGame();
	}

	public Enrollments(int ID, String name, int age, String parent_name, WinterGame game) {
		this.ID = ID;
		this.name = name;
		this.age = age;
		this.parent_name = parent_name;
		this.game = game;
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getParent_name() {
		return parent_name;
	}

	public void setParent_name(String parent_name) {
		this.parent_name = parent_name;
	}

	public WinterGame getGame() {
		return game;
	}

	public void setGame(WinterGame game) {
		this.game = game;
	}

	@Override
	public String toString() {
		String s = "";
		s = s + "ID: " + this.ID + ", ";
		s = s + "Name: " + this.name + ", ";
		s = s + "With age: " + this.age + ", ";
		s = s + "Parent Name: " + this.parent_name + ", ";
		s = s + "Winter Game: " + this.game;
		return s;
	}

}