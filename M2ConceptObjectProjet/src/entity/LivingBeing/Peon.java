package entity.LivingBeing;

import java.util.ArrayList;

import entity.Case;
import entity.Map;

public abstract class Peon extends LivingBeings{
	
	protected int PE;
	
	protected int PEMax;
	
	protected int MasterX;
	
	protected int MasterY;
	

	@Override
	void move() {
		// TODO Auto-generated method stub
		
	}
	
	private ArrayList<Case> checkObstacle(Map map) {
			if(map.getMap()[this.getX()][this.getY()].isBorder()) {
				if((this.getX()==0)&&(this.getY()==0)) {
					for(int i=this.getX();i<this.getX()+1;i++) {
						for(int j=this.getY();j<this.getY()+1;j++) {
							//add to arraylist of case
							
						}
					}
				}
				if((this.getY()==0)&&(this.getY()==19)) {
					for(int i=this.getX();i<this.getX()+1;i++) {
						for(int j=this.getY();j<this.getY()+1;j++) {
							//add to arraylist of case
						}
					}
				}
				if((this.getX()==19)&&(this.getY()==0)) {
					for(int i=this.getX();i<this.getX()+1;i++) {
						for(int j=this.getY();j<this.getY()+1;j++) {
							//add to arraylist of case
						}
					}
				}
				if((this.getY()==19)&&(this.getY()==19)) {
					for(int i=this.getX();i<this.getX()+1;i++) {
						for(int j=this.getY();j<this.getY()+1;j++) {
							//add to arraylist of case
						}
					}
				}
			}
			else {
				for(int i=this.getX()-1;i<this.getX()+1;i++) {
					for(int j=this.getY()-1;j<this.getY()+1;j++) {
						if((map.getMap()[i][j].isOccupied()==false)&&(map.getMap()[i][j].isObstacle()==false)) {
							if((map.getMap()[i][j].isSafeZone()==true)&&(map.getMap()[i][j].getSafeZoneOwner()!=this.getSafeZoneNumber())) {
								
							}
							else {
								//add case to possible
							}
						}
				}
			}
		}
	}
		
		
		
	
	void checkWay() {
		
	}
	void backMaster() {
		
	}
	
}


