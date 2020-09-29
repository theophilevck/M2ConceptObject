package entity;

import java.util.ArrayList;

public class Map {
	int X;
	
	int Y;
	
	ArrayList<ArrayList<Case>> carte ;
	
	void init() {
		this.createSZ();
		for(int i=0;i<X;i++) {
			for(int j=0;j<Y;j++) {
				if(carte.get(i).get(j).isSafeZone()==false) {
					this.createObstacle(carte.get(i).get(j) );
				}
			}
		}
	}
	
	void createSZ() {
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				this.carte.get(i).get(j).setSafeZone(true);
				this.carte.get(i).get(j).setSafeZoneOwner(0);;
			}
		}
		
		for(int i=this.getX()-4;i<this.getX();i++) {
			for(int j=0;j<4;j++) {
				this.carte.get(i).get(j).setSafeZone(true);
				this.carte.get(i).get(j).setSafeZoneOwner(1);;
			}
		}
		
		for(int i=0;i<4;i++) {
			for(int j=this.getY()-4;j<this.getY();j++) {
				this.carte.get(i).get(j).setSafeZone(true);
				this.carte.get(i).get(j).setSafeZoneOwner(2);;
			}
		}
		
		for(int i=this.getX()-4;i<this.getX();i++) {
			for(int j=this.getY()-4;j<this.getY();j++) {
				this.carte.get(i).get(j).setSafeZone(true);
				this.carte.get(i).get(j).setSafeZoneOwner(3);;
			}
		}
	}
	
	void createObstacle( Case cas) {
		
		double bool=Math.random();
		if(bool<0.2) { cas.setObstacle(true); }
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

	public ArrayList<ArrayList<Case>> getCarte() {
		return carte;
	}

	public void setCarte(ArrayList<ArrayList<Case>> carte) {
		this.carte = carte;
	}

}
