package entity;

import java.util.ArrayList;

import entity.LivingBeing.MasterJedi;
import entity.LivingBeing.Peon;

// TODO: Auto-generated Javadoc
/**
 * The Class Jedi.
 */
public class Jedi  extends Team{

	/**
	 * Instantiates a new jedi.
	 *
	 * @param master the master
	 * @param arrayList the arraylist of peons
	 * @param alliance the alliance
	 */
	public Jedi(MasterJedi master, ArrayList<Peon> arrayList,Alliance alliance) {
		super(master, arrayList,alliance);
	}

	
}
