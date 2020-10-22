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
		if (this.getPE() <= 0) {
			this.getMap().getMap()[this.getX()][this.getY()].setObstacle(true);
			System.out.printf(this.getName()+" n'a plus de points d'endurance");
			return;
		}
		if (this.getPE() < this.getPEMax() /2) {
			Case movePossible=this.backMaster(this.getMap(),this.getMap().getMap()[this.getX()][this.getY()]);
			this.getMap().getMap()[this.getX()][this.getY()].setOccupied(false);
			this.getMap().getMap()[this.getX()][this.getY()].setOccupant(null);
			this.setX(movePossible.getX());
			this.setY(movePossible.getY());
			this.getMap().getMap()[this.getX()][this.getY()].setOccupied(true);
			this.getMap().getMap()[this.getX()][this.getY()].setOccupant(this);
			this.consumePE();
			
		}  
		
		else {
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
								giveAllMessage(
										(Master) this.map.getMap()[this.getMasterX()][this.getMasterY()].getOccupant(),
										this);
							} else {
								fusionMessage(this, (Peon) movePossible.get(randomIndex).getOccupant());
							}
						} else {
							giveAMessageToAnAlly(this, (Peon) movePossible.get(randomIndex).getOccupant());
						}
					} else {
						// fight for message
						this.fight(this, (Peon) movePossible.get(randomIndex).getOccupant());
					}
				} else {
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

	private Case backMaster(Map map, Case cas) {
		Case objectif=map.getMap()[this.getMasterX()][this.getMasterY()];
		Map createdCopyMap=createdCopyMap(map);
		Case start=createdCopyMap.getMap()[cas.getX()][cas.getY()];
		createdCopyMap=obstacleCopyMap(createdCopyMap,start,objectif);
		ArrayList<Case> movePossible=this.checkObstacle(createdCopyMap);
		if(movePossible.size()!=0) {
			int randomIndex = (int) (Math.random() * movePossible.size());
			return movePossible.get(randomIndex);
		}
		movePossible=this.checkObstacle(map);
		int randomIndex = (int) (Math.random() * movePossible.size());
		return movePossible.get(randomIndex);
	}
	
	
	
	void giveAllMessage(Master master, Peon peon) {
		master.knownMasterMessage.addAll(peon.ownPeonMessage);
		List<Message> distinctElements = master.knownMasterMessage.stream().distinct().collect(Collectors.toList());
		master.knownMasterMessage.clear();
		master.knownMasterMessage.addAll(distinctElements);
		peon.ownPeonMessage.clear();
		System.out.println("le peon "+peon.getName()+" a donné tous ses messages a "+master.getName());
		System.out.println(master.getName()+" possede maintenant "+ master.getKnownMasterMessage().size()+" messages ");
	}
	
	void fusionMessage (Peon peon, Peon peon2) {
		peon.ownPeonMessage.addAll(peon2.ownPeonMessage);
		List<Message> distinctMessagePeon = peon.ownPeonMessage.stream().distinct().collect(Collectors.toList());
		peon.ownPeonMessage.removeAll(ownPeonMessage);
		peon2.ownPeonMessage.removeAll(ownPeonMessage);
		peon.ownPeonMessage.addAll(distinctMessagePeon);
		peon2.ownPeonMessage.addAll(distinctMessagePeon);
		System.out.println("le peon "+peon.getName()+" et le "+peon2.getName() +" ont echangé l'ensemble de leur(s) message(s) entre eux");
	}
	

	void giveAMessageToAnAlly(Peon peon1, Peon peon2) {
		System.out.println("les peon "+peon1.getName()+" , "+peon2.getName()+" de l aliance "+peon1.getAlliance()); 
		int nb_mess_echanger_peon1 = (int)(Math.random() * (peon1.ownPeonMessage.size()));
		int nb_mess_echanger_peon2 = (int)(Math.random() * (peon2.ownPeonMessage.size()));
		if(peon2.ownPeonMessage.size()!=0) {
			for (int i = 0; i <= nb_mess_echanger_peon1; i++) {
				int random_int_peon_2 = (int)(Math.random() * (peon2.ownPeonMessage.size()));
				System.out.println("le peon "+peon1.getName()+" a recuperé "+random_int_peon_2);
				peon1.ownPeonMessage.add(peon2.ownPeonMessage.get(random_int_peon_2));
			}
			List<Message> distinctMessagePeon = peon1.ownPeonMessage.stream().distinct().collect(Collectors.toList());
			peon1.ownPeonMessage.clear();
			peon1.ownPeonMessage.addAll(distinctMessagePeon);
		}
		if(peon1.ownPeonMessage.size()!=0) {
			for (int i=0; i <= nb_mess_echanger_peon2; i++) {
				int random_int_peon_1 = (int)(Math.random() * (peon1.ownPeonMessage.size()));
				System.out.println("le peon "+peon2.getName()+" a recupere "+random_int_peon_1);
				peon2.ownPeonMessage.add(peon1.ownPeonMessage.get(random_int_peon_1));
			}
			List<Message> distinctMessagePeon = peon2.ownPeonMessage.stream().distinct().collect(Collectors.toList());
			peon2.ownPeonMessage.clear();
			peon2.ownPeonMessage.addAll(distinctMessagePeon);
		}
	}
	
	void fight(Peon peon1, Peon peon2){
		int random_att_int_peon_1 = (int)(Math.random() * (10 + 1));
		int random_att_int_peon_2 = (int)(Math.random() * (10 + 1));
		int nb_mess_echanger_peon1 = (int)(Math.random() * (peon1.ownPeonMessage.size()));
		int nb_mess_echanger_peon2 = (int)(Math.random() * (peon2.ownPeonMessage.size()));
		System.out.println("les peon "+peon1.getName()+" , "+peon2.getName()+" se sont battus ");
		if (random_att_int_peon_1 > random_att_int_peon_2) {
			if(peon2.ownPeonMessage.size()!=0) {
				for (int i=0; i<=nb_mess_echanger_peon1;i++ ) {
					int random_int_peon_2 = (int)(Math.random() * (peon2.ownPeonMessage.size()));
					System.out.println("le peon "+peon1.getName()+" a recuperé "+random_int_peon_2);
					peon1.ownPeonMessage.add(peon2.ownPeonMessage.get(random_int_peon_2));
					System.out.println("le peon "+peon1.getName()+" a gagné "+random_int_peon_2 +" messages");
				}
			}
			List<Message> distinctMessagePeon = peon1.ownPeonMessage.stream().distinct().collect(Collectors.toList());
			peon1.ownPeonMessage.clear();
			peon1.ownPeonMessage.addAll(distinctMessagePeon);
		}
		else {
			if(peon1.ownPeonMessage.size()!=0) {
				for (int i = 0; i<=nb_mess_echanger_peon2;i++ ) {
					int random_int_peon_1 = (int)(Math.random() * (peon1.ownPeonMessage.size()));
					peon2.ownPeonMessage.add(peon1.ownPeonMessage.get(random_int_peon_1));
					System.out.println("le peon "+peon2.getName()+" a gagné "+random_int_peon_1 +" messages");
				}
				
			}
			List<Message> distinctMessagePeon = peon2.ownPeonMessage.stream().distinct().collect(Collectors.toList());
			peon2.ownPeonMessage.clear();
			peon2.ownPeonMessage.addAll(distinctMessagePeon);

		}
	}
	

	private Map createdCopyMap(Map map) {
		Map miniMap=new Map(map.getX(), map.getY());
		
		for(int i=0;i<miniMap.getX();i++) {
			for(int j=0;j<miniMap.getY();j++) {
				miniMap.getMap()[i][j]=new Case(i,j);
					if(map.getMap()[i][j].isBorder()) {
						miniMap.getMap()[i][j].setBorder(true);
					}
					if(map.getMap()[i][j].isObstacle()==true||map.getMap()[i][j].isOccupied()==true){
						miniMap.getMap()[i][j].setObstacle(true);
					}
					else {
						miniMap.getMap()[i][j].setObstacle(false);
					}
				}
			}
		
		miniMap.getMap()[0][0].setObstacle(false);
		miniMap.getMap()[map.getX()-1][0].setObstacle(false);
		miniMap.getMap()[0][map.getY()-1].setObstacle(false);
		miniMap.getMap()[map.getX()-1][map.getY()-1].setObstacle(false);
		
		return miniMap;
	}
	
	private Map obstacleCopyMap(Map copymap,Case start,Case objectif) {
		int objX=objectif.getX();
		int startX=start.getX();
		int objY=objectif.getY();
		int startY=start.getY();
		for(int i=0;i<copymap.getX();i++) {
			for(int j=0;j<copymap.getY();j++) {
				if((objX>startX)&&(objY>startY)) {
					if((i>=startX)&&(j>=startY)) {
						//do  nothing
					}
					else {
						copymap.getMap()[i][j].setObstacle(true);
					}
				}
				if((objX>startX)&&(objY<startY)) {
					if((i>=startX)&&(j<=startY)) {
						//do  nothing
					}
					else {
						copymap.getMap()[i][j].setObstacle(true);
					}
				}
				if((objX<startX)&&(objY>startY)) {
					if((i<=startX)&&(j>=startY)) {
						
					}
					else {
						copymap.getMap()[i][j].setObstacle(true);
					}
				}
				if((objX<startX)&&(objY<startY)) {
					if((i<=startX)&&(j<=startY)) {
						//do  nothing
					}
					else {
						copymap.getMap()[i][j].setObstacle(true);
					}
				}
			}
		}
		return copymap;
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


