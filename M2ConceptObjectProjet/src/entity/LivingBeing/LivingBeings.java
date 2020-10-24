package entity.LivingBeing;

import java.util.ArrayList;

import entity.Alliance;
import entity.Case;
import entity.Map;

public abstract class LivingBeings {
	
	protected Alliance alliance;
	
	protected int X;
	
	protected int Y;
	
	protected int safeZoneNumber;
	
	protected String image;
	
	protected String name;
	
	protected Map map;
	
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

	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public abstract void move();
	
	abstract ArrayList<Case> checkObstacleBorder(Map map, Case cas);
	
	abstract ArrayList<Case> checkObstacle(Map map);
	
	
	

}
