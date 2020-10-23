package entity.LivingBeing;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

import entity.Message;

public abstract class Master extends LivingBeings{
	
	protected ArrayList<Message> ownMasterMessage;
	
	protected ArrayList<Message> knownMasterMessage;



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

	@Override
	void move() {}
	
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


}
