package entity.LivingBeing;

public abstract class LivingBeings {
	
	int X;
	
	int Y;
	
	int safeZoneNumber;
	
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

	abstract void move();
	
	
	
	

}
