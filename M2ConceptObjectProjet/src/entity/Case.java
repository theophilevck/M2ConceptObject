package entity;

import entity.LivingBeing.LivingBeings;

public class Case {
	
	
	public Case(int x, int y,boolean border) {
		super();
		this.X = x;
		this.Y = y;
		this.border=border;
		
	}

	private boolean safeZone;
	
	private int safeZoneOwner;

	private boolean occupied;

	private boolean obstacle;
	
	private boolean border;
	
	private boolean corner;
	
	private LivingBeings occupant;
	
	private int X;
	
	private int Y;

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
	
	public boolean isOccupied() {
		return occupied;
	}

	public void setOcupied(boolean occupied) {
		this.occupied = occupied;
	}

	public int getSafeZoneOwner() {
		return safeZoneOwner;
	}

	public void setSafeZoneOwner(int safeZoneOwner) {
		this.safeZoneOwner = safeZoneOwner;
	}

	public LivingBeings getOccupant() {
		return occupant;
	}

	public void setOccupant(LivingBeings occupant) {
		this.occupant = occupant;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

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
 

}
