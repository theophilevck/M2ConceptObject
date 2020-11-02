package entity;

// TODO: Auto-generated Javadoc
/**
 * The Class Message.
 */
public class Message {
	
	/** The team. */
	private int team;
	
	/** The message. */
	private String message;

	/**
	 * Instantiates a new message.
	 *
	 * @param team the team
	 * @param message the message
	 */
	public Message(int team, String message) {
		this.team=team;
		this.message=message;
	}
	
	/**
	 * Gets the team.
	 *
	 * @return the team
	 */
	public int getTeam() {
		return team;
	}

	/**
	 * Sets the team.
	 *
	 * @param team the new team
	 */
	public void setTeam(int team) {
		this.team = team;
	}

	/**
	 * Gets the message.
	 *
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 *
	 * @param message the new message
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
