package Business;

import java.util.Date;

public class Flight {
	
	
	private int flightNumber;
	private Date departureDate;
	private Date arrivalDate;
	private Aircraft aircraft;
	private Airline airline;
	private Route route;
	
	private boolean isStarted;
	private boolean isWaiting;
	private boolean isLanded;
	
	public Flight(int flightNumber, Date departureDate, Date arrivalDate, Aircraft aircraft, Airline airline, Route route) {
		this.flightNumber = flightNumber;
		this.departureDate = departureDate;
		this.arrivalDate = arrivalDate;
		this.aircraft = aircraft;
		this.airline = airline;
		this.route = route;
		
		//to manage threads
		this.isStarted = false;
		this.isWaiting = false;
		this.isLanded = false;
	}
	
	public String toString() {
		String str = "Flight"+flightNumber+":\n"+
		"Departure Date: "+departureDate.toString()+"\n"+
		"Arrival Date: "+arrivalDate+"\n"+
		"Aircraft: "+aircraft+"\n"+
		"Airline: "+airline+"\n"+
		"From: "+route.getFrom().getName()+"\n"+
		"Destination "+route.getFrom().getName();
		return str;
	}

	public int getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(int flightNumber) {
		this.flightNumber = flightNumber;
	}

	public Date getDepartureDate() {
		return departureDate;
	}

	public void setDepartureDate(Date departureDate) {
		this.departureDate = departureDate;
	}

	public Date getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(Date arrivalDate) {
		this.arrivalDate = arrivalDate;
	}

	public Aircraft getAircraft() {
		return aircraft;
	}

	public void setAircraft(Aircraft aircraft) {
		this.aircraft = aircraft;
	}

	public Airline getAirline() {
		return airline;
	}

	public void setAirline(Airline airline) {
		this.airline = airline;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public boolean isLanded() {
		return isLanded;
	}

	public void setLanded(boolean isLanded) {
		this.isLanded = isLanded;
	}

	public boolean isWaiting() {
		return isWaiting;
	}

	public void setWaiting(boolean isWaiting) {
		this.isWaiting = isWaiting;
	}

	public boolean isStarted() {
		return isStarted;
	}

	public void setStarted(boolean isStarted) {
		this.isStarted = isStarted;
	}
}
