package entity.LivingBeing;

import java.util.ArrayList;

import entity.Alliance;
import entity.Case;
import entity.Map;

// TODO: Auto-generated Javadoc
/**
 * The Class LivingBeings.
 */
public abstract class LivingBeings {
	
	/** The alliance. */
	protected Alliance alliance;
	
	/** The x. */
	protected int X;
	
	/** The y. */
	protected int Y;
	
	/** The safe zone number. */
	protected int safeZoneNumber;
	
	/** The image. */
	protected String image;
	
	/** The name. */
	protected String name;
	
	/** The map. */
	protected Map map;
	
	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public int getX() {
		return X;
	}

	/**
	 * Sets the x.
	 *
	 * @param x the new x
	 */
	public void setX(int x) {
		X = x;
	}

	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public int getY() {
		return Y;
	}

	/**
	 * Sets the y.
	 *
	 * @param y the new y
	 */
	public void setY(int y) {
		Y = y;
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
	 * Gets the image.
	 *
	 * @return the image
	 */
	public String getImage() {
		return image;
	}

	/**
	 * Sets the image.
	 *
	 * @param image the new image
	 */
	public void setImage(String image) {
		this.image = image;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the map.
	 *
	 * @return the map
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * Sets the map.
	 *
	 * @param map the new map
	 */
	public void setMap(Map map) {
		this.map = map;
	}

	/**
	 * Move.
	 */
	public abstract void move();
	
	/**
	 * Check obstacle border.
	 *
	 * @param map the map
	 * @param cas the cas
	 * @return the array list
	 */
	abstract ArrayList<Case> checkObstacleBorder(Map map, Case cas);
	
	/**
	 * Check obstacle.
	 *
	 * @param map the map
	 * @return the array list
	 */
	abstract ArrayList<Case> checkObstacle(Map map);
	
	
	

}
