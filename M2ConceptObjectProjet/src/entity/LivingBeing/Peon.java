package entity.LivingBeing;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import entity.Case;
import entity.Map;
import entity.Message;

public abstract class Peon extends LivingBeings{
	
	protected int PE;
	
	protected int PEMax=200;
	
	protected int MasterX;
	
	protected int MasterY;
	
	protected Map map;
	
	protected ArrayList<Message> ownPeonMessage;
	
	public Peon(Map map) {
		super();
		this.map = map;
		this.ownPeonMessage=new ArrayList<Message>();
	}
	

	@Override
	public void move() {
		if (this.getMap().getMap()[this.getX()][this.getY()].isSafeZone()==true) {
			regeneratePE();
		}
		if (this.getPE() < this.getPEMax() /2) {
			ArrayList <Case> movePossible=this.goingBack();
			this.getMap().getMap()[this.getX()][this.getY()].setOccupied(false);
			this.getMap().getMap()[this.getX()][this.getY()].setOccupant(null);
			this.setX(movePossible.get(0).getX());
			this.setY(movePossible.get(0).getY());
			this.getMap().getMap()[this.getX()][this.getY()].setOccupied(true);
			this.getMap().getMap()[this.getX()][this.getY()].setOccupant(this);
			
		} else if (this.getPE() <= 0) {
			this.getMap().getMap()[this.getX()][this.getY()].setObstacle(true);
			System.out.printf("PLUS DE PE");
			
		} else {
			ArrayList<Case> movePossible = this.checkObstacle(this.map);
			if (movePossible.size() != 0) {
				int randomIndex = (int) (Math.random() * movePossible.size());
				if (movePossible.get(randomIndex).isOccupied() == true) {
					if (movePossible.get(randomIndex).getOccupant().getAlliance()
							.equals(this.getMap().getMap()[this.getX()][this.getY()].getOccupant().getAlliance())) {
						if (movePossible.get(randomIndex).getOccupant()
								.getSafeZoneNumber() == this.getMap().getMap()[this.getX()][this.getY()].getOccupant()
										.getSafeZoneNumber()) {
							if (movePossible.get(randomIndex).getOccupant() instanceof Master) {
								// Trouver comment amener le peon et master dans le move
								giveAllMessage(
										(Master) this.map.getMap()[this.getMasterX()][this.getMasterY()].getOccupant(),
										this);
								System.out.println("giveAllMessage");
							} else {
								// fusion message of both Peon
								fusionMessage(this, (Peon) movePossible.get(randomIndex).getOccupant());
								System.out.println("fusionMessage");
							}
						} else {
							// enconter alliance each peon get random message of the other
							giveAMessageToAnAlly(this, (Peon) movePossible.get(randomIndex).getOccupant());
							System.out.println("giveAMessageToAnAlly");
						}
					} else {
						// fight for message
						this.fight(this, (Peon) movePossible.get(randomIndex).getOccupant());
						System.out.println("fight");
					}
				} else {
					System.out.println(this.getX());
					System.out.println(this.getY());
					System.out.println("");
					this.getMap().getMap()[this.getX()][this.getY()].setOccupied(false);
					this.getMap().getMap()[this.getX()][this.getY()].setOccupant(null);
					this.setX(movePossible.get(randomIndex).getX());
					this.setY(movePossible.get(randomIndex).getY());
					this.getMap().getMap()[this.getX()][this.getY()].setOccupied(true);
					this.getMap().getMap()[this.getX()][this.getY()].setOccupant(this);
				}
			}
			System.out.println(this.getX());
			System.out.println(this.getY());
			System.out.println("");
			this.consumePE();
		}
	}
	
	public ArrayList<Case> goingBack() {
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
		return bestMove;
	}
	
	public void consumePE() {
		int randomNum = ThreadLocalRandom.current().nextInt(1, 4);
		this.setPE(this.getPE()-randomNum);	
	}
	
	public void regeneratePE() {
		this.setPE(200);
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

	void backMaster(Map map, Case cas) {
		Case source=new Case(this.X,this.Y);
		Queue<Case> queue= new LinkedList<Case>();
		queue.add(source);
		while(!queue.isEmpty()) {
			Case poped = queue.poll();
			if(map.getMap()[poped.getX()][poped.getY()].isSafeZone()==true) {
				return;
			}
			else {
				map.getMap()[poped.getX()][poped.getY()].setSafeZone(false);
				
				List<Case> neighbourList = addNeighbours(poped, map);
				queue.addAll(neighbourList);
			}
		}		
	}
private static List<Case> addNeighbours(Case poped, Map map) {
		
		List<Case> list = new LinkedList<Case>();
		
		if((poped.getX() > 0 && poped.getX()-1 < map.getMap().length) && map.getMap()[poped.getX()-1][poped.getY()].isOccupied() != false) {
			list.add(new Case(poped.getX()-1, poped.getY()));
		}
		if((poped.getX()+1 > 0 && poped.getX()+1 < map.getMap().length) && map.getMap()[poped.getX()+1][poped.getY()].isOccupied() != false) {
			list.add(new Case(poped.getX()+1, poped.getY()));
		}
		if((poped.getY()-1 > 0 && poped.getY()-1 < map.getMap().length) && map.getMap()[poped.getX()][poped.getY()-1].isOccupied() != false) {
			list.add(new Case(poped.getX(), poped.getY()));
		}
		if((poped.getY()+1 > 0 && poped.getY()+1 < map.getMap().length) && map.getMap()[poped.getX()][poped.getY()+1].isOccupied() != false) {
			list.add(new Case(poped.getX(), poped.getY()));
		}		
		return list;
	}
	
	
	void giveAllMessage(Master master, Peon peon) {
		master.ownMasterMessage.addAll(peon.ownPeonMessage);
		List<Message> distinctElements = master.ownMasterMessage.stream().distinct().collect(Collectors.toList());
		master.ownMasterMessage.clear();
		master.ownMasterMessage.addAll(distinctElements);
		peon.ownPeonMessage.clear();
		System.out.println();
	}
	
	void fusionMessage (Peon peon, Peon peon2) {
		peon.ownPeonMessage.addAll(peon2.ownPeonMessage);
		List<Message> distinctMessagePeon = peon.ownPeonMessage.stream().distinct().collect(Collectors.toList());
		peon.ownPeonMessage.removeAll(ownPeonMessage);
		peon2.ownPeonMessage.removeAll(ownPeonMessage);
		peon.ownPeonMessage.addAll(distinctMessagePeon);
		peon2.ownPeonMessage.addAll(distinctMessagePeon);
		System.out.println();
	}
	
	//peut etre on pourrait voir si il a déja le message et l'enlever ? 
	void giveAMessageToAnAlly(Peon peon1, Peon peon2) {
		int random_int_peon_1 = (int)(Math.random() * (peon1.ownPeonMessage.size()));
		int random_int_peon_2 = (int)(Math.random() * (peon2.ownPeonMessage.size()));
		if(peon2.ownPeonMessage.size()!=0) {
			peon1.ownPeonMessage.add(peon2.ownPeonMessage.get(random_int_peon_2));
		}
		if(peon1.ownPeonMessage.size()!=0) {
			peon2.ownPeonMessage.add(peon1.ownPeonMessage.get(random_int_peon_1));
		}
	}
	
	void fight(Peon peon1, Peon peon2){
		int random_att_int_peon_1 = (int)(Math.random() * (10 + 1));
		int random_att_int_peon_2 = (int)(Math.random() * (10 + 1));
		int random_int_peon_1 = (int)(Math.random() * (peon1.ownPeonMessage.size()));
		int random_int_peon_2 = (int)(Math.random() * (peon2.ownPeonMessage.size()));
		if (random_att_int_peon_1 > random_att_int_peon_2) {
			if(peon2.ownPeonMessage.size()!=0) {
				peon1.ownPeonMessage.add(peon2.ownPeonMessage.get(random_int_peon_2));
			}
			List<Message> distinctMessagePeon = peon1.ownPeonMessage.stream().distinct().collect(Collectors.toList());
			peon1.ownPeonMessage.clear();
			peon1.ownPeonMessage.addAll(distinctMessagePeon);
			
			
		}
		else {
			if(peon1.ownPeonMessage.size()!=0) {
				peon2.ownPeonMessage.add(peon1.ownPeonMessage.get(random_int_peon_1));
			}
			List<Message> distinctMessagePeon = peon2.ownPeonMessage.stream().distinct().collect(Collectors.toList());
			peon2.ownPeonMessage.clear();
			peon2.ownPeonMessage.addAll(distinctMessagePeon);
		}
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
	
	public void setMessage(Message msg) {
		this.ownPeonMessage.add(msg);
	}
	
}


