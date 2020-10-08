package entity.LivingBeing;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

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
		ArrayList <Case> movePossible=this.checkObstacle(this.map);
		if(movePossible.size()!=0) {
			int randomIndex = (int) (Math.random() * movePossible.size());
			if(movePossible.get(randomIndex).isOccupied()==true){
				if(movePossible.get(randomIndex).getOccupant().getAlliance()==this.getMap().getMap()[this.getX()][this.getY()].getOccupant().getAlliance()) {
					if(movePossible.get(randomIndex).getOccupant().getSafeZoneNumber()==this.getMap().getMap()[this.getX()][this.getY()].getOccupant().getSafeZoneNumber()) {
						if(movePossible.get(randomIndex).getOccupant() instanceof Master) {
							//give all message to master
						}
						else {
							//fusion message of both Peon
						}
					}
					else {
						//enconter alliance each peon get random message of the other
					}
				}
				else {
					//fight for message
				}
			}
			else {
				this.getMap().getMap()[this.getX()][this.getY()].setOccupied(false);
				this.getMap().getMap()[this.getX()][this.getY()].setOccupant(null);
				this.setX(movePossible.get(randomIndex).getX());
				this.setY(movePossible.get(randomIndex).getY());
				this.getMap().getMap()[this.getX()][this.getY()].setOccupied(true);
				this.getMap().getMap()[this.getX()][this.getY()].setOccupant(this);
			}
		}
		this.consumePE();
	}
	
	public void goingBack() {
		ArrayList <Case> movePossible=this.checkObstacle(map);
		Case opti1= new Case(this.X-1,this.Y-1,false);
		Case opti2= new Case(this.X,this.Y-1,false);
		Case opti3= new Case(this.X+1,this.Y-1,false);
		Case opti4= new Case(this.X-1,this.Y,false);
		Case opti5= new Case(this.X+1,this.Y,false);
		Case opti6= new Case(this.X-1,this.Y+1,false);
		Case opti7= new Case(this.X,this.Y+1,false);
		Case opti8= new Case(this.X+1,this.Y+1,false);
		ArrayList <Case> bestMove= new ArrayList <Case>();
		
		switch (this.getSafeZoneNumber()) {
			case 0:
				bestMove.add(opti1);bestMove.add(opti2);bestMove.add(opti3);bestMove.add(opti4);
				bestMove.add(opti5);bestMove.add(opti6);bestMove.add(opti7);bestMove.add(opti8);
				for(Case c: movePossible) {
					int index=0;
					if (c==bestMove.get(index)) {
						this.setX(c.getY());
						this.setY(c.getY());
					}
					index++;
				}	
			case 1:
				bestMove.add(opti3);bestMove.add(opti2);bestMove.add(opti1);bestMove.add(opti4);
				bestMove.add(opti5);bestMove.add(opti8);bestMove.add(opti7);bestMove.add(opti6);
				for(Case c: movePossible) {
					int index=0;
					if (c==bestMove.get(index)) {
						this.X=c.getY();
						this.setY(c.getY());
					}
					index++;
				}
			case 2:
				bestMove.add(opti6);bestMove.add(opti7);bestMove.add(opti8);bestMove.add(opti4);
				bestMove.add(opti5);bestMove.add(opti1);bestMove.add(opti2);bestMove.add(opti3);
				for(Case c: movePossible) {
					int index=0;
					if (c==bestMove.get(index)) {
						this.X=c.getY();
						this.setY(c.getY());
					}
					index++;
				}
			case 3:
				bestMove.add(opti8);bestMove.add(opti7);bestMove.add(opti6);bestMove.add(opti5);
				bestMove.add(opti4);bestMove.add(opti3);bestMove.add(opti2);bestMove.add(opti1);
				for(Case c: movePossible) {
					int index=0;
					if (c==bestMove.get(index)) {
						this.X=c.getY();
						this.setY(c.getY());
					}
					index++;
				}
				
		}			
	}
	
	public void consumePE() {
		int randomNum = ThreadLocalRandom.current().nextInt(1, 4);
		this.setPE(this.getPE()-randomNum);	
	}
	
	public void regeneratePE() {
		this.setPE(75);
	}
	
	public ArrayList<Case> checkObstacle(Map map) {
		ArrayList <Case> movePossible=new ArrayList<Case>();
		
			if(map.getMap()[this.getX()][this.getY()].isBorder()) {
				movePossible.addAll(checkObstacleBorder(map, map.getMap()[this.getX()][this.getY()]));
				
			}
			else {
				for(int i=this.getX()-1;i<this.getX()+2;i++) {
					for(int j=this.getY()-1;j<this.getY()+2;j++) {
						if(	(map.getMap()[i][j].isObstacle()==true)	||	(	(map.getMap()[i][j].isSafeZone()==true)	&&	(map.getMap()[i][j].getSafeZoneOwner()!=this.getSafeZoneNumber()	)	)	){
							//do nothing
						}
						else {
							movePossible.add(map.getMap()[i][j]);
						}
				}
			}
		}
			return movePossible;
	}
	
	
	public ArrayList<Case> checkObstacleBorder(Map map, Case cas) {
		ArrayList <Case> movePossible=new ArrayList<Case>();
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
				}
				else {
					if(	(	a==cas.getX()	)	&&	(	b==cas.getY()	)	) {
						//do nothing
					}
					else {
						movePossible.add(map.getMap()[a][b]);
					}
					
				}
			}
		}
		return movePossible;
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


