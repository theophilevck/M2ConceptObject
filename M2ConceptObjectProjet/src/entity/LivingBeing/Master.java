package entity.LivingBeing;

import java.util.ArrayList;

import entity.Message;

public abstract class Master extends LivingBeings{
	
	protected ArrayList<Message> ownMasterMessage;
	
	protected ArrayList<Message> knownMasterMessage;

	public ArrayList<Message> getOwnMasterMessage() {
		return ownMasterMessage;
	}

	public void setOwnMasterMessage(Message msg) {
		this.ownMasterMessage.add(msg);
	}

	public ArrayList<Message> getKnownMasterMessage() {
		return knownMasterMessage;
	}

	public void setKnownMasterMessage(Message msg) {
		this.knownMasterMessage.add(msg);
	}

	@Override
	void move() {}
	


}
