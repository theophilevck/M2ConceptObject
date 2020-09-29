package entity;

import java.util.ArrayList;

import entity.LivingBeing.Apprentice;
import entity.LivingBeing.Master;
import entity.LivingBeing.Peon;

public class Jedi  extends Team{

	public Jedi(Master master, ArrayList<Peon> arrayList,Alliance alliance) {
		super(master, arrayList,alliance);
	}

	
}
