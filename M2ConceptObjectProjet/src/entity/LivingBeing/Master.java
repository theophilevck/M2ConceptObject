package entity.LivingBeing;

import java.util.ArrayList;

import entity.Message;

public abstract class Master extends LivingBeings{
	
	protected ArrayList<Message> ownMasterMessage;
	
	protected ArrayList<Message> knownMasterMessage;

	@Override
	void move() {}
	


}
