package entity.LivingBeing;

import java.util.ArrayList;

import entity.Case;
import entity.Map;

public abstract class Peon extends LivingBeings{
	
	protected int PE;
	
	protected int PEMax;
	
	protected int MasterX;
	
	protected int MasterY;
	
	protected Map map;
	
	
	

	public Peon(Map map) {
		super();
		this.map = map;
	}

	@Override
	public
	void move() {
		// TODO Auto-generated method stub
		ArrayList <Case> movePosible=this.checkObstacle(this.map);
		if(movePosible.size()!=0) {
			int randomIndex = (int) (Math.random() * movePosible.size());
			this.getMap().getMap()[this.getX()][this.getY()].setOccupied(false);
			this.getMap().getMap()[this.getX()][this.getY()].setOccupant(null);
			this.setX(movePosible.get(randomIndex).getX());
			this.setY(movePosible.get(randomIndex).getY());
			this.getMap().getMap()[this.getX()][this.getY()].setOccupied(true);
			this.getMap().getMap()[this.getX()][this.getY()].setOccupant(this);
		}
		
		System.out.println("test");
	}
	
	public ArrayList<Case> checkObstacle(Map map) {
		ArrayList <Case> movePosible=new ArrayList<Case>();
		
			if(map.getMap()[this.getX()][this.getY()].isBorder()) {
				movePosible.addAll(checkObstacleBorder(map, map.getMap()[this.getX()][this.getY()]));
				
			}
			else {
				for(int i=this.getX()-1;i<this.getX()+2;i++) {
					for(int j=this.getY()-1;j<this.getY()+2;j++) {
						if(	(map.getMap()[i][j].isObstacle()==true)	||	(	(map.getMap()[i][j].isSafeZone()==true)	&&	(map.getMap()[i][j].getSafeZoneOwner()!=this.getSafeZoneNumber()	)	)	){
							//do nothing
							
						}
						else {
							movePosible.add(map.getMap()[i][j]);
						}
						/*
						//if((map.getMap()[i][j].isOccupied()==false)&&(map.getMap()[i][j].isObstacle()==false)) {
						if(map.getMap()[i][j].isObstacle()==false) {
							if((map.getMap()[i][j].isSafeZone()==true)&&(map.getMap()[i][j].getSafeZoneOwner()!=this.getSafeZoneNumber())) {
							}
							else {
								movePosible.add(map.getMap()[i][j]);
							}
						}*/
				}
			}
		}
			return movePosible;
	}
	
	
	public ArrayList<Case> checkObstacleBorder(Map map, Case cas) {
		ArrayList <Case> movePosible=new ArrayList<Case>();
		int i=cas.getX()-1;
		int j=cas.getY()-1;
		int iMax=cas.getX()+2;
		int jMax=cas.getY()+2;
		
		if(cas.getX()==0) {i=cas.getX();}
		if(cas.getX()==map.getX()-1) {iMax=cas.getX()+1;}
		if(cas.getY()==0) {j=cas.getY();}
		if(cas.getY()==map.getY()-1) {jMax=cas.getY()+1;}
		
		for( int a=i;a<iMax;a++) {
			for(int b=j;b<jMax;b++) {
				if(	(map.getMap()[a][b].isObstacle()==true)	||	(	(map.getMap()[a][b].isSafeZone()==true)	&&	(map.getMap()[a][b].getSafeZoneOwner()!=this.getSafeZoneNumber()	)	)	){
					//do nothing
					System.out.println("bug");
				}
				else {
					if(	(	a==cas.getX()	)	&&	(	b==cas.getY()	)	) {
						//do nothing
					}
					else {
						movePosible.add(map.getMap()[a][b]);
					}
					
				}
			}
		}
		return movePosible;
		
	}
		
		
	
	void checkWay() {
		
	}
	void backMaster() {
		
	}

	public int getPE() {
		return PE;
	}

	public void setPE(int pE) {
		PE = pE;
	}

	public int getPEMax() {
		return PEMax;
	}

	public void setPEMax(int pEMax) {
		PEMax = pEMax;
	}

	public int getMasterX() {
		return MasterX;
	}

	public void setMasterX(int masterX) {
		MasterX = masterX;
	}

	public int getMasterY() {
		return MasterY;
	}

	public void setMasterY(int masterY) {
		MasterY = masterY;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}
	
}


