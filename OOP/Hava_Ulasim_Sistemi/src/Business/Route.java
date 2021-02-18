package Business;



public class Route {
	
	private Airport from;
	private Airport destination;
	
	public Route(Airport from, Airport destination) {
		this.from = from;
		this.destination = destination;
	}
	
	public Airport getFrom() {
		return from;
	}
	
	public Airport getDestination() {
		return destination;
	}
	
	public void setFrom(Airport airport) {
		from = airport;
	}
	
	public void setDestination(Airport airport) {
		destination = airport;
	}
	
}
