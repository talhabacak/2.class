package Business;

public class Airport {//will act as destinations
	
	private Tower tower;
	private String name;
	
	public Airport(String name) {
		this.setTower(new Tower());
		this.setName(name);
	}

	public Tower getTower() {
		return tower;
	}

	public void setTower(Tower tower) {
		this.tower = tower;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
