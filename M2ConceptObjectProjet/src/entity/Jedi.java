package entity;

import java.util.ArrayList;

import entity.LivingBeing.MasterJedi;
import entity.LivingBeing.Peon;

public class Jedi  extends Team{

	public Jedi(MasterJedi master, ArrayList<Peon> arrayList,Alliance alliance) {
		super(master, arrayList,alliance);
	}

	
}
