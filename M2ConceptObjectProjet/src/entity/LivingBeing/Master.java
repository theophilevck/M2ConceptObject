package entity.LivingBeing;

import java.util.ArrayList;

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
	


}
