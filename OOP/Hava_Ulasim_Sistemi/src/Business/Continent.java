package Business;

import java.util.ArrayList;

public class Continent {
	
	private ArrayList<Capital> capitals;
	private String name;
	
	public Continent(String name) {
		this.capitals = new ArrayList<Capital>();
		this.setName(name);
	}
	
	public void addCapital(Capital newCapital) {
		if(newCapital != null)
			capitals.add(newCapital);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Capital> getCapitals() {
		return capitals;
	}

	public void setCapitals(ArrayList<Capital> capitals) {
		this.capitals = capitals;
	}
	
	
}
