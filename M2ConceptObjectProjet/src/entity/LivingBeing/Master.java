package entity.LivingBeing;

import java.util.ArrayList;

import entity.Message;

public abstract class Master extends LivingBeings{
	
	private ArrayList<Message> ownMessage;
	
	private ArrayList<Message> knownMessage;

	@Override
	void move() {}
	


}
