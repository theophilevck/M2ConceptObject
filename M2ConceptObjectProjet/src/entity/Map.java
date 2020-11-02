package entity;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class Map.
 */
public class Map {
	
	/** The x. */
	int X=20;
	
	/** The y. */
	int Y=20;
	
	/** The map. */
	Case[][] map;
	
	/**
	 * Instantiates a new map.
	 *
	 * @param X the x
	 * @param Y the y
	 */
	public Map(int X, int Y ) {
		this.X=X;
		this.Y=Y;
		this.map=new Case [X][Y];
		
	}
	
	/**
	 * Inits the map
	 */
	void init() {
		
		for(int i=0;i<X;i++) {
			for(int j=0;j<Y;j++) {
				if((i==0)||(i==X-1)||(j==0)||(j==Y-1)) {
					map[i][j]=new Case(i,j,true);
				}
				else {
					map[i][j]=new Case(i,j,false);
				}
				
			}
		}
		
		this.createSZ();
		for(int i=0;i<X;i++) {
			for(int j=0;j<Y;j++) {
				if(map[i][j].isSafeZone()==false) {
					this.createObstacle(map[i][j]);
				}
			}
		}
	}
	
	
	/**
	 * Creates the 4 safe zones, size 3*3 and sets them a number in order to define an "owner".
	 */
	void createSZ() {
		for(int i=0;i<3;i++) {
			for(int j=0;j<3;j++) {
				this.map[i][j].setSafeZone(true);
				this.map[i][j].setSafeZoneOwner(0);;
			}
		}
		
		for(int i=this.getX()-3;i<this.getX();i++) {
			for(int j=0;j<3;j++) {
				this.map[i][j].setSafeZone(true);
				this.map[i][j].setSafeZoneOwner(1);;
			}
		}
		
		for(int i=0;i<3;i++) {
			for(int j=this.getY()-3;j<this.getY();j++) {
				this.map[i][j].setSafeZone(true);
				this.map[i][j].setSafeZoneOwner(2);;
			}
		}
		
		for(int i=this.getX()-3;i<this.getX();i++) {
			for(int j=this.getY()-3;j<this.getY();j++) {
				this.map[i][j].setSafeZone(true);
				this.map[i][j].setSafeZoneOwner(3);;
			}
		}
	}
	
	/**
	 * Creates the obstacle on cases which are not safe zones.
	 *
	 * @param cas the cas
	 */
	void createObstacle( Case cas) {
		
		double bool=Math.random();
		if(bool<0.15) { cas.setObstacle(true); }
	}

	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int getX() {
		return X;
	}

	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	public void setX(int x) {
		X = x;
	}

	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY() {
		return Y;
	}

	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public void setY(int y) {
		Y = y;
	}

	/**
	 * Gets the map.
	 *
	 * @return the map
	 */
	public Case[][] getMap() {
		return map;
	}

	/**
	 * Sets the map.
	 *
	 * @param map the new map
	 */
	public void setMap(Case[][] map) {
		this.map = map;
	}
	
	/**
	 * Shows map in console.
	 */
	public void aff() {
		for(int i=0;i<this.getX();i++) {
			for(int j=0;j<this.getY();j++) {
				if(this.getMap()[j][i].isObstacle()==true) {
					System.out.print("|x");}
				
			else {
				System.out.print("| ");
				}
			}
			System.out.println();
			}
		System.out.println();
		System.out.println();
		
	}
	
	/**
	 * Affe.
	 */
	public void affe() {
		for(int i=0;i<this.getX();i++) {
			for(int j=0;j<this.getY();j++) {
				if(this.getMap()[j][i].isObstacle()==true) {
					System.out.print("|x"+i+" "+j);}
				
			else {
				System.out.print("|"+i+" "+j);
				}
			}
			System.out.println();
			}
		System.out.println();
		System.out.println();
		
	}

	

}
