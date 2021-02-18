package Business;

import java.util.ArrayList;

public class Airline {
	private ArrayList<Aircraft> aircrafts;
	private String name;
	
	public Airline(String name) {
		this.setName(name);
		this.aircrafts = new ArrayList<Aircraft>();
	}

	public void addAircraft(Aircraft newAircraft) {
		if(newAircraft != null) {
			aircrafts.add(newAircraft);
		}
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Aircraft> getAircrafts() {
		return aircrafts;
	}

	public void setAircrafts(ArrayList<Aircraft> aircrafts) {
		this.aircrafts = aircrafts;
	}
}
