package entity.LivingBeing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import entity.Case;
import entity.Map;
import entity.Message;

// TODO: Auto-generated Javadoc
/**
 * The Class Peon.
 */
public abstract class Peon extends LivingBeings{
	
	/** The pe. */
	protected int PE;
	
	/** The PE max. */
	protected int PEMax=200;
	
	/** The Master X. */
	protected int MasterX;
	
	/** The Master Y. */
	protected int MasterY;
	
	
	
	/** The own peon message. */
	protected ArrayList<Message> ownPeonMessage;
	
	/**
	 * Instantiates a new peon.
	 *
	 * @param map the map
	 */
	public Peon(Map map) {
		super();
		this.map = map;
		this.ownPeonMessage=new ArrayList<Message>();
	}
	

	/**
	 * Move a peon, while checking his PE and reacting accordingly.
	 */
	@Override
	public void move() {
		if (this.getMap().getMap()[this.getX()][this.getY()].isSafeZone()==true) {
			regeneratePE();
		}
		
		if (this.getPE() < this.getPEMax() /2) {
			if (this.getPE() <= 0) {
				if(this.getMap().getMap()[this.getX()][this.getY()].isObstacle()==false) {
					this.getMap().getMap()[this.getX()][this.getY()].setObstacle(true);
					System.out.println(this.getName()+" n'a plus de points d'endurance");
				}
				return;
			}
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
								speakToMaster((Master) this.map.getMap()[this.getMasterX()][this.getMasterY()].getOccupant());
							} else {
								speakToTeammate((Peon) movePossible.get(randomIndex).getOccupant());
							}
						} else {
							speakToAlly( (Peon) movePossible.get(randomIndex).getOccupant());
						}
					} else {
						// fight for message
						this.fight( (Peon) movePossible.get(randomIndex).getOccupant());
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
	

	
	/**
	 * Consume PE.
	 */
	public void consumePE() {
		int randomNum = ThreadLocalRandom.current().nextInt(1, 4);
		this.setPE(this.getPE()-randomNum);	
	}
	
	/**
	 * Regenerate PE.
	 */
	public void regeneratePE() {
		this.setPE(200);
	}
	
	/**
	 * Check obstacle.
	 *
	 * @param map the map
	 * @return the array list
	 */
	@Override
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
							if(	(	i==this.getX())	&&	(	j==this.getY()	)	) {
								//do nothing
							}
							else {
								movePossible.add(map.getMap()[i][j]);
							}
						}
				}
			}
		}
			return movePossible;
	}
	
	/**
	 * Check obstacle border.
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

	/**
	 * Get the position where peon has to go to get back to master.
	 *
	 * @param map the map
	 * @param cas the cas
	 * @return the case
	 */
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
	
	/**
	 * Fight between foe peon.
	 *
	 * @param peonEnnemie the peon ennemie
	 */
	void fight( Peon peonEnnemie){
		//Pierre = 1 Papier = 2 ciseaux = 3
		System.out.println("les peons "+this.getName()+" , "+peonEnnemie.getName()+" se sont battus ");
		int random_att_int_peon_1 = (int)(Math.random() * (3 + 1));
		int random_att_int_peon_2 = (int)(Math.random() * (3 + 1));
		if (random_att_int_peon_1==1 && random_att_int_peon_2==3 || random_att_int_peon_1==2 && random_att_int_peon_2==1 || random_att_int_peon_1==3 && random_att_int_peon_2==2) {
			//this gagne
			System.out.println("Le peon "+this.getName() +" a gagné");
			peonEnnemie.giveMessageTowinner(this);
		}
		else if (random_att_int_peon_1==random_att_int_peon_2) {
			System.out.println("Les peons ont pris la fuite et ont conservé leur(s) messages.");
		}
		else {
			//peonEnnemie win
			System.out.println("Le peon "+peonEnnemie.getName()+" a gagné");
			this.giveMessageTowinner(peonEnnemie);
		}
	}
	

	/**
	 * Created copy map.
	 *
	 * @param map the map
	 * @return the map
	 */
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
	
	/**
	 * Obstacle copy map.
	 *
	 * @param copymap the copymap
	 * @param start the start
	 * @param objectif the objectif
	 * @return the map
	 */
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
	

	/**
	 * Gets the pe.
	 *
	 * @return the pe
	 */
	public int getPE() {
		return PE;
	}

	/**
	 * Sets the pe.
	 *
	 * @param pE the new pe
	 */
	public void setPE(int pE) {
		PE = pE;
	}

	/**
	 * Gets the PE max.
	 *
	 * @return the PE max
	 */
	public int getPEMax() {
		return PEMax;
	}

	/**
	 * Sets the PE max.
	 *
	 * @param pEMax the new PE max
	 */
	public void setPEMax(int pEMax) {
		PEMax = pEMax;
	}

	/**
	 * Gets the master X.
	 *
	 * @return the master X
	 */
	public int getMasterX() {
		return MasterX;
	}

	/**
	 * Sets the master X.
	 *
	 * @param masterX the new master X
	 */
	public void setMasterX(int masterX) {
		MasterX = masterX;
	}

	/**
	 * Gets the master Y.
	 *
	 * @return the master Y
	 */
	public int getMasterY() {
		return MasterY;
	}

	/**
	 * Sets the master Y.
	 *
	 * @param masterY the new master Y
	 */
	public void setMasterY(int masterY) {
		MasterY = masterY;
	}

	/**
	 * Gets the map.
	 *
	 * @return the map
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * Sets the map.
	 *
	 * @param map the new map
	 */
	public void setMap(Map map) {
		this.map = map;
	}
	
	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public ArrayList<Message> getMessage() {
		return ownPeonMessage;
	}
	
	/**
	 * Sets the message.
	 *
	 * @param msg the new message
	 */
	public void setMessage(Message msg) {
		this.ownPeonMessage.add(msg);
	}
	
	/**
	 * Speak to master.
	 *
	 * @param master the master
	 */
	public void speakToMaster(Master master) {
			ArrayList<Message>PeonMessage=this.getOwnPeonMessage();
			System.out.println("le peon "+this.getName()+" a donné tous ses messages a "+master.getName());
			master.ListenToPeon(this,PeonMessage);
	}
	
	/**
	 * Listen to master.
	 *
	 * @param newpeonMessages the newpeon messages
	 */
	public void listenToMaster(ArrayList<Message> newpeonMessages) {
		this.setOwnPeonMessage(newpeonMessages);
	}

	/**
	 * Speak to teammate.
	 *
	 * @param teammate the teammate
	 */
	public void speakToTeammate(Peon teammate) {
		ArrayList<Message>PeonMessage=this.getOwnPeonMessage();
		this.getOwnPeonMessage().clear();
		teammate.ListenAndRepplyToTeammate(this,PeonMessage);
		System.out.println("le peon "+this.getName()+" et le "+teammate.getName() +" ont echangé l'ensemble de leur(s) message(s) entre eux");
	}
	
	/**
	 * Listen and reply to teammate.
	 *
	 * @param teammate the teammate
	 * @param PeonMessage the peon message
	 */
	public void ListenAndRepplyToTeammate(Peon teammate,ArrayList<Message>PeonMessage) {
		this.ownPeonMessage.addAll(PeonMessage);
		List<Message> distinctMessagePeon = this.ownPeonMessage.stream().distinct().collect(Collectors.toList());
		this.ownPeonMessage.clear();
		this.ownPeonMessage.addAll(distinctMessagePeon);
		teammate.ListenBackToTeammate(distinctMessagePeon);
	}
	
	/**
	 * Listen back to teammate.
	 *
	 * @param PeonMessage the peon message
	 */
	public void ListenBackToTeammate(List<Message>PeonMessage) {
		this.ownPeonMessage.addAll(PeonMessage);
	}
	
	/**
	 * Speak to ally.
	 *
	 * @param peonally the peonally
	 */
	public void speakToAlly(Peon peonally) {
		ArrayList<Message>PeonMessage=new ArrayList<Message>();
		System.out.println("les peon "+this.getName()+" , "+peonally.getName()+" de l aliance "+this.getAlliance()+" se rencontrent"); 
		
		if(this.ownPeonMessage.size()!=0) {
			int nb_mess_echanger_peon = (int)(Math.random() * (this.ownPeonMessage.size()));
			System.out.println("le peon "+peonally.getName()+" a recupere "+nb_mess_echanger_peon+" messages");
			for (int i=0; i < nb_mess_echanger_peon; i++) {
				int random_int_peon = (int)(Math.random() * (this.ownPeonMessage.size()));
				
				PeonMessage.add(this.ownPeonMessage.get(random_int_peon));
				}
			}
		else {
			System.out.println("le peon "+peonally.getName()+" n a pas recupere de messages");
		}
		peonally.ListenAndRepplyToAlly(this,PeonMessage);
	}
	
	/**
	 * Listen and reply to ally.
	 *
	 * @param peonally the peonally
	 * @param GivenPeonMessage the given peon message
	 */
	public void ListenAndRepplyToAlly(Peon peonally,ArrayList<Message>GivenPeonMessage) {
		ArrayList<Message>PeonMessage=new ArrayList<Message>();
		int nb_mess_echanger_peon = (int)(Math.random() * (this.ownPeonMessage.size()));
		System.out.println("le peon "+peonally.getName()+" a recuperé "+nb_mess_echanger_peon+" messages");
		if(this.ownPeonMessage.size()!=0) {
			for (int i = 0; i < nb_mess_echanger_peon; i++) {
				int random_int_peon_ = (int)(Math.random() * (this.ownPeonMessage.size()));
				PeonMessage.add(this.ownPeonMessage.get(random_int_peon_));
			}
		}
		else {
			System.out.println("le peon "+peonally.getName()+" n a pas recupere de messages");
		}
		this.ownPeonMessage.addAll(GivenPeonMessage);
		List<Message> distinctMessagePeon = this.ownPeonMessage.stream().distinct().collect(Collectors.toList());
		this.ownPeonMessage.clear();
		this.ownPeonMessage.addAll(distinctMessagePeon);
		peonally.ListenBackToAlly(PeonMessage);
	}
	
	/**
	 * Listen back to ally.
	 *
	 * @param PeonMessage the peon message
	 */
	public void ListenBackToAlly(List<Message>PeonMessage) {
		this.ownPeonMessage.addAll(PeonMessage);
		List<Message> distinctMessagePeon = this.ownPeonMessage.stream().distinct().collect(Collectors.toList());
		this.ownPeonMessage.clear();
		this.ownPeonMessage.addAll(distinctMessagePeon);
	}
	
	/**
	 * Give message to winner.
	 *
	 * @param winner the winner
	 */
	public void giveMessageTowinner(Peon winner) {
		ArrayList<Message>PeonMessage=new ArrayList<Message>();
		if(this.ownPeonMessage.size()!=0) {
			int nb_mess_echanger_peon = (int)(Math.random() * (this.ownPeonMessage.size()));
			System.out.println("le peon "+this.getName()+" a perdu et donne "+nb_mess_echanger_peon+" messages qu il perd");
			for (int i=0; i<nb_mess_echanger_peon;i++ ) {
				int random_int_peon= (int)(Math.random() * (this.ownPeonMessage.size()));
				PeonMessage.add(this.ownPeonMessage.get(random_int_peon));
				this.getOwnPeonMessage().remove(random_int_peon);
			}
		}
		else {
			System.out.println("le peon "+this.getName()+" a perdu mais n a pas de message");
		}
		winner.listenToLooser(PeonMessage);
	}
	
	/**
	 * Listen to loser.
	 *
	 * @param PeonMessage the peon message
	 */
	public void listenToLooser(ArrayList<Message>PeonMessage) {
		this.ownPeonMessage.addAll(PeonMessage);
		List<Message> distinctMessagePeon = this.ownPeonMessage.stream().distinct().collect(Collectors.toList());
		this.ownPeonMessage.clear();
		this.ownPeonMessage.addAll(distinctMessagePeon);
		
	}

	/**
	 * Gets the own peon message.
	 *
	 * @return the own peon message
	 */
	public ArrayList<Message> getOwnPeonMessage() {
		return ownPeonMessage;
	}


	/**
	 * Sets the own peon message.
	 *
	 * @param ownPeonMessage the new own peon message
	 */
	public void setOwnPeonMessage(ArrayList<Message> ownPeonMessage) {
		this.ownPeonMessage = ownPeonMessage;
	}
	
	/**
	 * Receive communication master.
	 *
	 * @param master the master
	 */
	public void ReceiveCommunicationMaster(Master master) {
		this.setMasterX(master.getX());
		this.setMasterY(master.getY());
	}
	
}


