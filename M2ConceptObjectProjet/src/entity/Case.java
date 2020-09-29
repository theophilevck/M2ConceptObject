package entity;

public class Case {
	
	
	private boolean safeZone;
	
	private int safeZoneOwner;

	private boolean ocupied;

	private boolean obstacle;
	
	private boolean border;
	
	private boolean corner;

	public boolean isSafeZone() {
		return safeZone;
	}

	public void setSafeZone(boolean safeZone) {
		this.safeZone = safeZone;
	}

	public boolean isObstacle() {
		return obstacle;
	}

	public void setObstacle(boolean obstacle) {
		this.obstacle = obstacle;
	}

	public boolean isBorder() {
		return border;
	}

	public void setBorder(boolean border) {
		this.border = border;
	}

	public boolean isCorner() {
		return corner;
	}

	public void setCorner(boolean corner) {
		this.corner = corner;
	}
	
	public boolean isOcupied() {
		return ocupied;
	}

	public void setOcupied(boolean ocupied) {
		this.ocupied = ocupied;
	}

	public int getSafeZoneOwner() {
		return safeZoneOwner;
	}

	public void setSafeZoneOwner(int safeZoneOwner) {
		this.safeZoneOwner = safeZoneOwner;
	}


}
