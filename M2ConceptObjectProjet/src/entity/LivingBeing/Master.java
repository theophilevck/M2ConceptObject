package entity.LivingBeing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import entity.Case;
import entity.Map;
import entity.Message;
import entity.Team;

// TODO: Auto-generated Javadoc
/**
 * The Class Master.
 */
public abstract class Master extends LivingBeings{
	
	/** The own master message. */
	protected ArrayList<Message> ownMasterMessage;
	
	/** The known master message. */
	protected ArrayList<Message> knownMasterMessage;
	
	/** The team. */
	protected Team team;

	/**
	 * Instantiates a new master.
	 *
	 * @param map the map
	 */
	public Master(Map map) {
		super();
		this.map = map;
		this.ownMasterMessage=new ArrayList<Message>();
		this.knownMasterMessage=new ArrayList<Message>();
	}

	/**
	 * Gets the own master message.
	 *
	 * @return the own master message
	 */
	public ArrayList<Message> getOwnMasterMessage() {
		return ownMasterMessage;
	}

	/**
	 * Sets the own master message.
	 *
	 * @param ownMasterMessage the new own master message
	 */
	public void setOwnMasterMessage(ArrayList<Message> ownMasterMessage) {
		this.ownMasterMessage = ownMasterMessage;
	}

	/**
	 * Gets the known master message.
	 *
	 * @return the known master message
	 */
	public ArrayList<Message> getKnownMasterMessage() {
		return knownMasterMessage;
	}

	/**
	 * Sets the known master message.
	 *
	 * @param knownMasterMessage the new known master message
	 */
	public void setKnownMasterMessage(ArrayList<Message> knownMasterMessage) {
		this.knownMasterMessage = knownMasterMessage;
	}

	/**
	 * Gets the team.
	 *
	 * @return the team
	 */
	public Team getTeam() {
		return team;
	}

	/**
	 * Sets the team.
	 *
	 * @param team the new team
	 */
	public void setTeam(Team team) {
		this.team = team;
	}

	/**
	 * Listen to peon as seen during class sessions.
	 *
	 * @param peon the peon
	 * @param peonMessages the peon messages
	 */
	public void ListenToPeon(Peon peon,ArrayList<Message> peonMessages) {
		this.getKnownMasterMessage().addAll(peonMessages);
		List<Message> distinctElements = this.knownMasterMessage.stream().distinct().collect(Collectors.toList());
		this.knownMasterMessage.clear();
		this.knownMasterMessage.addAll(distinctElements);
		System.out.println(this.getName()+" possede maintenant "+ this.getKnownMasterMessage().size()+" messages ");
		SpeakToPeon(peon);
		
	}
	
	/**
	 * Speaks to peon, as seen in class.
	 *
	 * @param peon the peon
	 */
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
	
	/**
	 * Check obstacles and all the possible moves for a position.
	 *
	 * @param map the map
	 * @return the array list
	 */
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
	
	/**
	 * Check obstacle border and return all possible moves.
	 *
	 * @param map the map
	 * @param cas the cas
	 * @return the array list
	 */
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
	
	/**
	 * Move the object.
	 */
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

	/**
	 * Ask exchange.
	 *
	 * @param peon the peon
	 */
	public void askExchange(Peon peon) {
		peon.speakToMaster(this);
	}
	
	/**
	 * Communicate new position.
	 */
	public void CommunicateNewPosition() {
		//System.out.println(this.getName()+" a communiquer son nouvelle emplacement a tous ses peon");
		for(Peon p:this.getTeam().getPeons()) {
			p.ReceiveCommunicationMaster(this);
		}
	}

}
