package Business;

import java.util.ArrayList;

public class Capital {

	private String name;
	private ArrayList<Airport> airports;
	
	public Capital(String name) {
		this.setName(name);
		this.airports = new ArrayList<Airport>();
		this.airports.add(new Airport(getName()+"airport"));
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Airport> getAirports() {
		return airports;
	}

	public void setAirports(ArrayList<Airport> airports) {
		this.airports = airports;
	}
	
	public void addAirport(Airport airport) {
		if(airport !=null)
			this.airports.add(airport);
	}
	
	
}
