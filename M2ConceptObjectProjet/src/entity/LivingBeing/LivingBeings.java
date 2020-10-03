package entity.LivingBeing;

import entity.Alliance;

public abstract class LivingBeings {
	
	protected Alliance alliance;
	
	protected int X;
	
	protected int Y;
	
	protected int safeZoneNumber;
	
	public int getX() {
		return X;
	}

	public void setX(int x) {
		X = x;
	}

	public int getY() {
		return Y;
	}

	public void setY(int y) {
		Y = y;
	}

	public int getSafeZoneNumber() {
		return safeZoneNumber;
	}

	public void setSafeZoneNumber(int safeZoneNumber) {
		this.safeZoneNumber = safeZoneNumber;
	}

	public Alliance getAlliance() {
		return alliance;
	}

	public void setAlliance(Alliance alliance) {
		this.alliance = alliance;
	}

	abstract void move();
	
	
	
	

}
