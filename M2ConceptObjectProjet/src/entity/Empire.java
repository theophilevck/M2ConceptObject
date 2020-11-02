package entity;

import java.util.ArrayList;

import entity.LivingBeing.Peon;
import entity.LivingBeing.Sergent;

// TODO: Auto-generated Javadoc
/**
 * The Class Empire.
 */
public class Empire  extends Team{

	/**
	 * Instantiates a new empire.
	 *
	 * @param master the master
	 * @param peons the peons
	 * @param alliance the alliance
	 */
	public Empire(Sergent master, ArrayList<Peon> peons,Alliance alliance) {
		super(master, peons,alliance);
		// TODO Auto-generated constructor stub
	}

}
