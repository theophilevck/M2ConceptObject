package entity;

import java.util.ArrayList;

public class Map {
	int X=20;
	
	int Y=20;
	
	Case[][] carte;
	
	public Map(int X, int Y ) {
		this.X=X;
		this.Y=Y;
		this.carte=new Case [X][Y];
		
	}
	
	//initialisation de la carte
	void init() {
		
		for(int i=0;i<X;i++) {
			for(int j=0;j<Y;j++) {
				carte[i][j]=new Case();
			}
		}
		
		this.createSZ();
		for(int i=0;i<X;i++) {
			for(int j=0;j<Y;j++) {
				if(carte[i][j].isSafeZone()==false) {
					this.createObstacle(carte[i][j]);
				}
			}
		}
	}
	
	
	//creation de 4 safezone, de taille 3*3 et elle on chaqun un nombre pour definir le propretaire
	void createSZ() {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				this.carte[i][j].setSafeZone(true);
				this.carte[i][j].setSafeZoneOwner(0);;
			}
		}
		
		for(int i=this.getX()-3;i<this.getX();i++) {
			for(int j=0;j<3;j++) {
				this.carte[i][j].setSafeZone(true);
				this.carte[i][j].setSafeZoneOwner(1);;
			}
		}
		
		for(int i=0;i<4;i++) {
			for(int j=this.getY()-3;j<this.getY();j++) {
				this.carte[i][j].setSafeZone(true);
				this.carte[i][j].setSafeZoneOwner(2);;
			}
		}
		
		for(int i=this.getX()-3;i<this.getX();i++) {
			for(int j=this.getY()-3;j<this.getY();j++) {
				this.carte[i][j].setSafeZone(true);
				this.carte[i][j].setSafeZoneOwner(3);;
			}
		}
	}
	
	//creation des obstacle sur les case qui ne sont pas des safezone
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

	

}