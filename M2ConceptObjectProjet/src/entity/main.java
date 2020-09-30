package entity;

public class main {

	public static void main(String[] args) {
		Simulation simulation=new Simulation(new Map(20,20));
		simulation.init();
		simulation.getMap().getMap()[5][5].setOccupant(simulation.getTeams().get(0).getPeons().get(0));
		simulation.getMap().getMap()[5][5].setOccupied(true);
		simulation.getTeams().get(0).getPeons().get(0).setX(5);
		simulation.getTeams().get(0).getPeons().get(0).setY(5);
		simulation.getTeams().get(0).getPeons().get(0).move();
	}

}
