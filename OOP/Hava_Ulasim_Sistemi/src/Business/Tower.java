package Business;

import UserModels.TowerManager;

public class Tower {
	
	private TowerManager towerManager;
	
	public Tower() {
		this.towerManager = new TowerManager("Tower Manager");
	}

	public TowerManager getTowerManager() {
		return towerManager;
	}

	public void setTowerManager(TowerManager towerManager) {
		this.towerManager = towerManager;
	}
}
