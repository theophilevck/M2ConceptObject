package entity;

public class main {

	public static void main(String[] args) {
		
		
		Simulation simulation=new Simulation(new Map(20,20));
		simulation.initTeam();
		
		
		simulation.init();
		simulation.getMap().getMap()[19][3].setOccupant(simulation.getTeams().get(0).getPeons().get(0));
		simulation.getMap().getMap()[19][3].setOccupied(true);
		simulation.getTeams().get(0).getPeons().get(0).setX(19);
		simulation.getTeams().get(0).getPeons().get(0).setY(3);
		simulation.getTeams().get(0).getPeons().get(0).move();
	}

}
