package entity;

import java.util.ArrayList;

import entity.LivingBeing.LivingBeings;
import entity.LivingBeing.Master;
import entity.LivingBeing.Peon;

// TODO: Auto-generated Javadoc
/**
 * The Class Team.
 */
public class Team {
	
	/** The alliance. */
	private Alliance alliance;
	
	/** The maitre. */
	private Master maitre;
	
	/** The peons. */
	private ArrayList<Peon > peons;
	
	/** The all. */
	private ArrayList<LivingBeings> all;
	
	/** The safe zone number. */
	private int safeZoneNumber;

	/**
	 * Instantiates a new team.
	 *
	 * @param maitre the maitre
	 * @param peons the peons
	 * @param alliance the alliance
	 */
	public Team(Master maitre, ArrayList<Peon> peons,Alliance alliance) {
		super();
		this.maitre = maitre;
		this.peons=peons;
		this.alliance=alliance;
		this.all=new ArrayList<LivingBeings>();
	}

	/**
	 * Gets the alliance.
	 *
	 * @return the alliance
	 */
	public Alliance getAlliance() {
		return alliance;
	}

	/**
	 * Sets the alliance.
	 *
	 * @param alliance the new alliance
	 */
	public void setAlliance(Alliance alliance) {
		this.alliance = alliance;
	}

	/**
	 * Gets the maitre.
	 *
	 * @return the maitre
	 */
	public Master getMaitre() {
		return maitre;
	}

	/**
	 * Sets the maitre.
	 *
	 * @param maitre the new maitre
	 */
	public void setMaitre(Master maitre) {
		this.maitre = maitre;
	}

	/**
	 * Gets the peons.
	 *
	 * @return the peons
	 */
	public ArrayList<Peon> getPeons() {
		return peons;
	}

	/**
	 * Sets the peons.
	 *
	 * @param peons the new peons
	 */
	public void setPeons(ArrayList<Peon> peons) {
		this.peons = peons;
	}

	/**
	 * Gets the all.
	 *
	 * @return the all
	 */
	public ArrayList<LivingBeings> getAll() {
		return all;
	}

	/**
	 * Sets the all.
	 *
	 * @param all the new all
	 */
	public void setAll(ArrayList<LivingBeings> all) {
		this.all = all;
	}

	/**
	 * Gets the safe zone number.
	 *
	 * @return the safe zone number
	 */
	public int getSafeZoneNumber() {
		return safeZoneNumber;
	}

	/**
	 * Sets the safe zone number.
	 *
	 * @param safeZoneNumber the new safe zone number
	 */
	public void setSafeZoneNumber(int safeZoneNumber) {
		this.safeZoneNumber = safeZoneNumber;
	}



	
	
	

}
