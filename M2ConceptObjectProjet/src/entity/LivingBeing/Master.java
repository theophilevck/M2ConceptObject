package entity.LivingBeing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import entity.Case;
import entity.Map;
import entity.Message;
import entity.Team;

public abstract class Master extends LivingBeings{
	
	protected ArrayList<Message> ownMasterMessage;
	
	protected ArrayList<Message> knownMasterMessage;
	
	protected Team team;

	public Master(Map map) {
		super();
		this.map = map;
		this.ownMasterMessage=new ArrayList<Message>();
		this.knownMasterMessage=new ArrayList<Message>();
	}

	public ArrayList<Message> getOwnMasterMessage() {
		return ownMasterMessage;
	}

	public void setOwnMasterMessage(ArrayList<Message> ownMasterMessage) {
		this.ownMasterMessage = ownMasterMessage;
	}

	public ArrayList<Message> getKnownMasterMessage() {
		return knownMasterMessage;
	}

	public void setKnownMasterMessage(ArrayList<Message> knownMasterMessage) {
		this.knownMasterMessage = knownMasterMessage;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public void ListenToPeon(Peon peon,ArrayList<Message> peonMessages) {
		this.getKnownMasterMessage().addAll(peonMessages);
		List<Message> distinctElements = this.knownMasterMessage.stream().distinct().collect(Collectors.toList());
		this.knownMasterMessage.clear();
		this.knownMasterMessage.addAll(distinctElements);
		System.out.println(this.getName()+" possede maintenant "+ this.getKnownMasterMessage().size()+" messages ");
		SpeakToPeon(peon);
		
	}
	
	public void SpeakToPeon(Peon peon) {
		peon.ownPeonMessage.clear();
		ArrayList<Message>NewPeonMessage=new ArrayList();
		int rand= ThreadLocalRandom.current().nextInt(1, this.getOwnMasterMessage().size());
		ArrayList<Message> messagesForPeons=new ArrayList<Message>();
		messagesForPeons.addAll(this.getOwnMasterMessage());
		for (int i=0;i<rand;i++) {
			int randNumMessages= (int) (Math.random() * messagesForPeons.size());
			NewPeonMessage.add(messagesForPeons.get(randNumMessages));
			messagesForPeons.remove(randNumMessages);
		}
		peon.listenToMaster(NewPeonMessage);
	}
	
	@Override
	public ArrayList<Case> checkObstacle(Map map) {
		ArrayList <Case> movePossible=new ArrayList<Case>();
		
		if((map.getMap()[this.getX()][this.getY()].isBorder())||(map.getMap()[this.getX()][this.getY()].isCorner())) {
			movePossible.addAll(checkObstacleBorder(map, map.getMap()[this.getX()][this.getY()]));
			if(movePossible.size()==0 ) {
				System.out.println();
			}
		}
		else {
			for(int i=this.getX()-1;i<this.getX()+2;i++) {
				for(int j=this.getY()-1;j<this.getY()+2;j++) {
					if(	(	i==this.getX())	&&	(	j==this.getY()	)	||(map.getMap()[i][j].isSafeZone()==false)	) {
						//do nothing
					}
					else {
						movePossible.add(map.getMap()[i][j]);
					}
				}
			}
			if(movePossible.size()==0 ) {
				System.out.println();
			}
		}
		
		return movePossible;
	}
	
	@Override
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
				if(	(	a==cas.getX()	)	&&	(	b==cas.getY()	) ||(map.getMap()[a][b].isSafeZone()==false)	) {
					//do nothing
				}
				else {
					movePossible.add(map.getMap()[a][b]);
				}
			}
		}
		return movePossible;
	}
	
	@Override
	public	void move() {
		ArrayList<Case> movePossible = this.checkObstacle(this.map);
		
		int randomIndex = (int) (Math.random() * movePossible.size());
		if (movePossible.get(randomIndex).isOccupied() == true) {
			this.askExchange((Peon) movePossible.get(randomIndex).getOccupant());
		}
		else {
			this.getMap().getMap()[this.getX()][this.getY()].setOccupied(false);
			this.getMap().getMap()[this.getX()][this.getY()].setOccupant(null);
			this.setX(movePossible.get(randomIndex).getX());
			this.setY(movePossible.get(randomIndex).getY());
			this.getMap().getMap()[this.getX()][this.getY()].setOccupied(true);
			this.getMap().getMap()[this.getX()][this.getY()].setOccupant(this);
			this.CommunicateNewPosition();
		}
	}

	public void askExchange(Peon peon) {
		peon.speakToMaster(this);
	}
	public void CommunicateNewPosition() {
		//System.out.println(this.getName()+" a communiquer son nouvelle emplacement a tous ses peon");
		for(Peon p:this.getTeam().getPeons()) {
			p.ReceiveCommunicationMaster(this);
		}
	}

}
