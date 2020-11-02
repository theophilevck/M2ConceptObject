package entity;

import entity.LivingBeing.LivingBeings;

// TODO: Auto-generated Javadoc
/**
 * The Class Case.
 */
public class Case {
	
	
	/**
	 * Instantiates a new case.
	 *
	 * @param x the x
	 * @param y the y
	 * @param border the border
	 */
	public Case(int x, int y,boolean border) {
		super();
		this.X = x;
		this.Y = y;
		this.border=border;
		
	}
	
	/**
	 * Instantiates a new case.
	 *
	 * @param x the x
	 * @param y the y
	 */
	public Case(int x, int y) {
		super();
		this.X = x;
		this.Y = y;		
	}

	/** The safe zone. */
	private boolean safeZone;
	
	/** The safe zone owner. */
	private int safeZoneOwner;

	/** The occupied. */
	private boolean occupied;

	/** The obstacle. */
	private boolean obstacle;
	
	/** The border. */
	private boolean border;
	
	/** The corner. */
	private boolean corner;
	
	/** The occupant. */
	private LivingBeings occupant;
	
	/** The x. */
	private int X;
	
	/** The y. */
	private int Y;

	/**
	 * Checks if is safe zone.
	 *
	 * @return true, if is safe zone
	 */
	public boolean isSafeZone() {
		return safeZone;
	}

	/**
	 * Sets the safe zone.
	 *
	 * @param safeZone the new safe zone
	 */
	public void setSafeZone(boolean safeZone) {
		this.safeZone = safeZone;
	}

	/**
	 * Checks if is obstacle.
	 *
	 * @return true, if is obstacle
	 */
	public boolean isObstacle() {
		return obstacle;
	}

	/**
	 * Sets the obstacle.
	 *
	 * @param obstacle the new obstacle
	 */
	public void setObstacle(boolean obstacle) {
		this.obstacle = obstacle;
	}

	/**
	 * Checks if is border.
	 *
	 * @return true, if is border
	 */
	public boolean isBorder() {
		return border;
	}

	/**
	 * Sets the border.
	 *
	 * @param border the new border
	 */
	public void setBorder(boolean border) {
		this.border = border;
	}

	/**
	 * Checks if is corner.
	 *
	 * @return true, if is corner
	 */
	public boolean isCorner() {
		return corner;
	}

	/**
	 * Sets the corner.
	 *
	 * @param corner the new corner
	 */
	public void setCorner(boolean corner) {
		this.corner = corner;
	}
	
	/**
	 * Checks if is occupied.
	 *
	 * @return true, if is occupied
	 */
	public boolean isOccupied() {
		return occupied;
	}

	/**
	 * Sets the ocupied.
	 *
	 * @param occupied the new ocupied
	 */
	public void setOcupied(boolean occupied) {
		this.occupied = occupied;
	}

	/**
	 * Gets the safe zone owner.
	 *
	 * @return the safe zone owner
	 */
	public int getSafeZoneOwner() {
		return safeZoneOwner;
	}

	/**
	 * Sets the safe zone owner.
	 *
	 * @param safeZoneOwner the new safe zone owner
	 */
	public void setSafeZoneOwner(int safeZoneOwner) {
		this.safeZoneOwner = safeZoneOwner;
	}

	/**
	 * Gets the occupant.
	 *
	 * @return the occupant
	 */
	public LivingBeings getOccupant() {
		return occupant;
	}

	/**
	 * Sets the occupant.
	 *
	 * @param occupant the new occupant
	 */
	public void setOccupant(LivingBeings occupant) {
		this.occupant = occupant;
	}

	/**
	 * Sets the occupied.
	 *
	 * @param occupied the new occupied
	 */
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

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
 

}
