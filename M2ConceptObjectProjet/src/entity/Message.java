package entity;

public class Message {
	
	private int team;
	
	private String message;

	public Message(int team, String message) {
		this.team=team;
		this.message=message;
	}
	public int getTeam() {
		return team;
	}

	public void setTeam(int team) {
		this.team = team;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
