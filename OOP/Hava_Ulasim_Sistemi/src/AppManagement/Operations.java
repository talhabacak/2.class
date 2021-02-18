package AppManagement;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


import Business.*;
import Report.MessageCollector;
import Thread.flightThread;

public class Operations {

	private SystemDateHandler systemDateHandler = new SystemDateHandler();
	
	private ArrayList<Continent> continents = new ArrayList<Continent>();
	
	private ArrayList<Flight> flights = new ArrayList<Flight>();

	private ArrayList<flightThread> fts = new ArrayList<flightThread>();
	
	private ArrayList<Airline> airLines = new ArrayList<Airline>();
	
	private ArrayList<Route> routes = new ArrayList<Route>();
	
	private MessageCollector mc = new MessageCollector();
	
	public Operations() {
		init();
	}

	public void init() { // to have some data at the beginning
		continents.add(new Continent("Asia"));
		continents.add(new Continent("Africa"));
		continents.add(new Continent("Europe"));
		continents.add(new Continent("North America"));
		continents.add(new Continent("South America"));
		
		addCapital("Tokio", "Asia");
		addCapital("Paris","Europe");
		addCapital("CapeTown","Africa");
		addCapital("Ottava","North America");
		addCapital("Montevideo","South America");
		
		airLines.add(new Airline("Turkish Airlines"));
		airLines.add(new Airline("Fly Emirates"));
		airLines.add(new Airline("Pegasus"));
		
		airLines.get(0).addAircraft(new Aircraft("Boeing1"));
		airLines.get(0).addAircraft(new Aircraft("Boeing2"));
		
		airLines.get(1).addAircraft(new Aircraft("Boeing3")); 
		airLines.get(1).addAircraft(new Aircraft("Boeing4"));
		
		airLines.get(2).addAircraft(new Aircraft("Boeing5")); 
		airLines.get(2).addAircraft(new Aircraft("Boeing6"));
		
		Capital Tokio = continents.get(0).getCapitals().get(0);
		Capital Paris = continents.get(1).getCapitals().get(0);
		Capital CapeTown = continents.get(2).getCapitals().get(0);
		Capital Ottava = continents.get(3).getCapitals().get(0);
		Capital Montevideo = continents.get(4).getCapitals().get(0);
		
		routes.add(new Route(Tokio.getAirports().get(0), Paris.getAirports().get(0)));
		routes.add(new Route(Tokio.getAirports().get(0), CapeTown.getAirports().get(0)));
		routes.add(new Route(Tokio.getAirports().get(0), Ottava.getAirports().get(0)));
		routes.add(new Route(Tokio.getAirports().get(0), Montevideo.getAirports().get(0)));
		
		routes.add(new Route(Paris.getAirports().get(0), CapeTown.getAirports().get(0)));
		routes.add(new Route(Paris.getAirports().get(0), Ottava.getAirports().get(0)));
		routes.add(new Route(Paris.getAirports().get(0), Montevideo.getAirports().get(0)));
		
		routes.add(new Route(CapeTown.getAirports().get(0), Ottava.getAirports().get(0)));
		routes.add(new Route(CapeTown.getAirports().get(0), Montevideo.getAirports().get(0)));

		routes.add(new Route(Ottava.getAirports().get(0), Montevideo.getAirports().get(0)));
		
	
		
	}
	

	
	public boolean givePermission(String airportName) { //make the user give permission to flights of a airport which are waiting for permission to land
		if(!isAirportExist(airportName)) {
			mc.addMessage("The given airport "+airportName+" to give permission does not exist");
			return false;
		}
		for(Flight flight: flights) {
			if((flight.getRoute().getDestination().getName()).equals(airportName) && flight.isWaiting() == true) {
				for(flightThread thread : fts) {
					if(thread.getFlight().getFlightNumber() == flight.getFlightNumber()) {
						thread.notify();
					}
				}
			}
		}
		mc.addMessage("The flights which wait for permission to land to "+airportName+" are permitted");
		return true;
		
	}
	
	
	public boolean setDelay10Min(int flightNumber){//Set 10min delay to flight which its number given
		if(!isFlightNumberExist(flightNumber)) {
			mc.addMessage("The flight "+flightNumber+" which is given to delayed 10mins does not exist");
			return false;
		}
		for(int i = 0; i < flights.size(); i++) {
			Flight flight = flights.get(i);
			if(flight.getFlightNumber() == flightNumber ){
				if(flight.isStarted() == true) {//it must be before start
					mc.addMessage("The given fight "+flightNumber+" to be delayed has been started, it cannot be delayed");
					return false;
				}
				flight.setDepartureDate(new Date(flight.getDepartureDate().getTime() + 600000));
				flight.setArrivalDate(new Date(flight.getArrivalDate().getTime() + 600000));
				mc.addMessage("The Flight "+flightNumber+" has been delayed for 10mins");
				return true;
			}
		}
		return false;
	}
	

	public boolean cancelFlight(String airportName, int flightNumber) {//cancel flight which its number given of a given airport 
		if(!isAirportExist(airportName)) {
			mc.addMessage("The airport "+airportName+ " given to cancel flight does not exist");	
			return false;
		}
		if(!isFlightNumberExist(flightNumber)) {
			mc.addMessage("The flight number "+flightNumber+ " given to cancel flight does not exist");
			return false;
		}
		for(int i = 0; i < flights.size(); i++) {											
			if((flights.get(i).getRoute().getFrom().getName()).equals(airportName) ) {
				if(flights.get(i).isStarted() == true) {
					mc.addMessage("Started flights cannot be changed flight number: "+flightNumber);
					return false;
				}
				mc.addMessage("The flight: "+flightNumber+" has been cancelled succesfuly");
				flights.remove(i);
				return true;
			}
		}
		return false;
	}
	
	public void startAppropriateFlights() {//Every flights will be start when its departure date came
		Flight temp;
		for(int i = 0; i < flights.size(); i++) {
			if(systemDateHandler.getCurrent().getTime() >= flights.get(i).getDepartureDate().getTime()) {
				temp = flights.get(i);
				temp.setStarted(true);
				mc.addMessage("The flight given belov taked off"+flights.get(i).toString());
				flightThread ft = new flightThread(systemDateHandler, temp, mc);	
				fts.add(ft);
				ft.run();
			}
		}
	}
	
	
	//Airports will be acted as destinations in this implementation
	public boolean updateDestinationOfGivenCapital(String capitalName, String airportName, String newName) {//it will change name of an airport in given capital
		for(Continent cont: continents) {
			for(Capital cap: cont.getCapitals()) {
				if(cap.getName().equals(capitalName)){
					for(int i = 0; i < cap.getAirports().size() ;i++) {
						if(cap.getAirports().get(i).getName().equals(airportName)) {
							cap.getAirports().get(i).setName(newName);
							return true;
						}
					}
				}
			}
			
		}
		mc.addMessage("The given Capital or Airport to update "+airportName+" from "+capitalName +" to "+newName+ " does not exist");
		return false;
		
	}
	

	
	public boolean deleteDestinationFromTheGivenCapital(String capitalName, String airportName) {
		for(Continent cont: continents) {
			for(Capital cap: cont.getCapitals()) {
				if(cap.getName().equals(capitalName)){
					for(int i = 0; i < cap.getAirports().size() ;i++) {
						if(cap.getAirports().get(i).getName().equals(airportName)) {
							cap.getAirports().remove(i);
							return true;
						}
					}
				}
			}
		}
		mc.addMessage("The given Capital or Airport to remove "+airportName+" from "+capitalName + " does not exist");
		return false;
	}
	
	public boolean addDestinationToTheGivenCapital(String capitalName, String airportName) {
		if(isAirportExist(airportName)) {
			mc.addMessage("The given destination: "+airportName+"to add a new destination does already exist");
			return false;
		}
		for(Continent cont: continents) {
			for(Capital cap: cont.getCapitals()) {
				if(cap.getName().equals(capitalName)){
					Airport dest = new Airport( airportName);
					cap.getAirports().add(dest);
					mc.addMessage("The given destination: "+airportName+" is sucessful added to the "+capitalName);
					return true;
				}
			}
		}
		mc.addMessage("The given capitalName: "+capitalName+" to adda new destination does not exist");
		return false;
		
	}
	
	//to use when user tries to add a new flight
	private boolean isAircraftExist(String aircraftModel) {
		for(Airline airline: airLines) {
			for(Aircraft aircrft: airline.getAircrafts()) {
				if(aircrft.getModel().equals(aircraftModel))
					return true;
			}
		}
		return false;
	}
	//to use when user tries to add a new flight
	private boolean isAirlineExist(String airline) {
		for(Airline arline: airLines) {
			if(arline.getName().equals(airline))
				return true;
			}
		return false;
	}
	//to use when user tries to add a new flight
	private boolean isAirportExist(String airport) {
		for(Continent cont: continents) {
			for(Capital cap: cont.getCapitals()) {
				for(Airport airpt: cap.getAirports()) {
					if(airpt.getName().equals(airport))	return true;
				}				
			}
		}
		return false;
	}
	//to use when user tries to add a new flight	
	private boolean isRouteExist(String from, String destination) {
		for(Route route: routes) {
			if(route.getFrom().getName().equals(from) && route.getDestination().getName().equals(destination)) return true;
		}
		return false;
	}
	//to use when user tries to add a new flight
	private boolean isFlightNumberExist(int flightNumber) {
		for(Flight flight: flights) {
			if(flight.getFlightNumber() == flightNumber) return true;
		}
		return false;
	}
	
	public boolean addFlight(int flightNumber, String departureDate, String arrivalDate, String aircraftModel, String airline, String from, String destination){
		 Date departDate;
		try {
			departDate = new SimpleDateFormat("dd/MM/yyyy").parse(departureDate);
		} catch (ParseException e) {
			mc.addMessage("The given departure date to add a flight is incorrect");
			return false;
		}  
		 Date arrivlDate;
		try {
			arrivlDate = new SimpleDateFormat("dd/MM/yyyy").parse(arrivalDate);
		} catch (ParseException e) {
			mc.addMessage("The given arrival date to add a flight is incorrect");
			return false;
		} 
		 
		 if(!isFlightNumberExist(flightNumber)) { 
			 mc.addMessage("The given flight number: "+flightNumber+" to add a flight does not exist");
			 return false;
		}
		
		 if(!isAircraftExist(aircraftModel)) {
			 mc.addMessage("The given aircraft model: "+aircraftModel+" to adda flight does not exist");
			 return false;
		 }
		 Aircraft ac = new Aircraft(aircraftModel);
		
		 if(!isAirlineExist(airline)) {
			 mc.addMessage("The given airline: "+airline+" to adda flight does not exist");
			 return false;
		 }
		 Airline ai = new Airline(airline);
		 
		 if(!isAirportExist(from)) {
			 mc.addMessage("The given airport from: "+from+" to adda flight does not exist");
			 return false;
		 }
		 Airport frm = new Airport(from);
		
		 if(!isAirportExist(destination)) {
			 mc.addMessage("The given airport destination: "+destination+" to adda flight does not exist");
			 return false;
		 }
		 Airport dest = new Airport(destination);
		 
		 if(!isRouteExist(from,destination)) {
			 mc.addMessage("The given route from: "+from+" to "+"destination: "+destination+" does already exist");
			 return false;
		 }
		 Route route = new Route(frm, dest);
		 
		 
		 addFlightt(flightNumber,departDate,arrivlDate,ac,ai,route);
		return true;
	}
	
	private boolean addFlightt(int flightNumber, Date departureDate, Date arrivalDate, Aircraft aircraft, Airline airline, Route route) {
		Flight flight = new Flight(flightNumber,departureDate,arrivalDate,aircraft,airline,route);
		mc.addMessage("The new flight given below is sucessfuly added: ");
		mc.addMessage(flight.toString());
		flights.add(flight);
		return true;
	}
	

	
	public boolean deleteCapital(String capitalName, String continentName) {
		for(Continent continent: continents) 
			if(continentName.equals(continent.getName())) {
				for(Capital capital: continent.getCapitals()) {
					if(capital.getName().equals(capitalName)) {
						continent.getCapitals().remove(capital);
						mc.addMessage("The deletion of the capital: "+capitalName+" from "+continentName+" is succesfuly completed");
						return true;
					}
				}		
		}
		mc.addMessage("The given continent or capital to remove "+capitalName+" from "+continentName+" is does not exist");
		return false;
	}
	
	public boolean addCapital(String capitalName, String continentName) {
		for(Continent continent : continents) {
			if(continent.getName().equals(continentName)) {
				for(Capital cap: continent.getCapitals()) {
					if(cap.getName().equals(capitalName)) {
						mc.addMessage("The given capital "+capitalName+" to add does already exist");
						return false;
					}
				}
				mc.addMessage("The addition of capital "+capitalName+" to "+continentName+" is succesfuly completed");
				continent.addCapital(new Capital(capitalName));
				return true;
			}
		}
		mc.addMessage("The given continent " + continentName + " to add a capital, does not exist");
		return false;
	}
	
	//this method will be used to keep system date alive by using Timer class of swing
	public void toKeepSystemDateAlive() {
		systemDateHandler.keepAlive();
	}
	
	public Date getCurrentSystemDate() {
		return systemDateHandler.getCurrent();
	}

	public ArrayList<Airline> getAirLines() {
		return airLines;
	}

	public void setAirLines(ArrayList<Airline> airLines) {
		this.airLines = airLines;
	}
	
	public MessageCollector getMessageCollector() {
		return this.mc;
	}
	
	public boolean toFile() {
		try {
			mc.toFile();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	
}
