package entity;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import entity.LivingBeing.Apprentice;
import entity.LivingBeing.BountyHunter;
import entity.LivingBeing.Chewbacca;
import entity.LivingBeing.Ewak;
import entity.LivingBeing.JangoFett;
import entity.LivingBeing.Master;
import entity.LivingBeing.MasterJedi;
import entity.LivingBeing.Peon;
import entity.LivingBeing.Sergent;
import entity.LivingBeing.StormTrooper;
import graphique.BoardGame;

public class Simulation {
	
	private Map map;
	
	private ArrayList<Team> teams;
	
	private int day;
	
	private int dayMax;
	
	private BoardGame boardGame;
	
	
	
	
	public Simulation(Map map) {
		super();
		this.map = map;
	}

	public void init() {
		
		Scanner input = new Scanner(System.in);
		
		System.out.println("enter an integer");
		dayMax = input.nextInt();
		
		day=0;
		
		this.setTeams(this.initTeam());
		this.map.init();
		this.setTeam(this.getTeams());
		this.setMaster();
		this.setPeon();

		
		
	}
	
	public void start() {
		for(Peon p:this.getTeams().get(0).getPeons()) {
			p.move();
		}
	}
	
public void setTeam(ArrayList<Team> teams) {
	
	ArrayList<Integer> safeZoneNumber=new ArrayList<Integer>();
	safeZoneNumber.add(0);
	safeZoneNumber.add(1);
	safeZoneNumber.add(2);
	safeZoneNumber.add(3);
	
	// random to randomize team on map
	int count=0;
	for(Team e:teams) {
		int randomIndex = (int) (Math.random() * safeZoneNumber.size());
		//teams.get(randomIndex).setSafeZoneNumber(safeZoneNumber.get(randomIndex));
		e.getAll().forEach(l -> l.setSafeZoneNumber(safeZoneNumber.get(randomIndex)));
		e.setSafeZoneNumber(safeZoneNumber.get(randomIndex));
		safeZoneNumber.remove(randomIndex);
	}
}

public void setMaster() {
	for(Team e:this.getTeams()) {
		switch(e.getMaitre().getSafeZoneNumber()) {
		  case 0:
		    e.getMaitre().setX(0);
		    e.getMaitre().setY(0);
		    this.getMap().getMap()[0][0].setOccupant(e.getMaitre());
		    this.getMap().getMap()[0][0].setOccupied(true);
		    break;
		  case 1:
			  e.getMaitre().setX(map.X-1);
			  e.getMaitre().setY(0);
			  this.getMap().getMap()[map.X-1][0].setOccupant(e.getMaitre());
			  this.getMap().getMap()[map.X-1][0].setOccupied(true);
		    break;
		  case 2:
			  e.getMaitre().setX(0);
			   e.getMaitre().setY(map.Y-1);
			   this.getMap().getMap()[0][map.Y-1].setOccupant(e.getMaitre());
			   this.getMap().getMap()[0][map.Y-1].setOccupied(true);
			    break;
		  case 3:
			  e.getMaitre().setX(map.X-1);
			  e.getMaitre().setY(map.Y-1);
			  this.getMap().getMap()[map.X-1][map.Y-1].setOccupant(e.getMaitre());
			  this.getMap().getMap()[map.X-1][map.Y-1].setOccupied(true);
			    break;
		}
	}
}


	//initialisation des equipes avec un master et 3 peons pour chaque equipe
public ArrayList<Team> initTeam() {
		
		ArrayList<Team> teams=new ArrayList<Team> ();
		
		ArrayList<Peon> pion0=new ArrayList<Peon>();
		while (pion0.size()!=3) {
			pion0.add(new Apprentice(this.getMap()));
		}
		Jedi jedi =new Jedi(new MasterJedi(), pion0,Alliance.Rebellion);
		jedi.getAll().addAll(pion0);
		jedi.getAll().add(jedi.getMaitre());
		teams.add(jedi);
		
		
		ArrayList<Peon> pion1=new ArrayList<Peon>();
		while (pion1.size()!=3) {
			pion1.add(new Ewak(this.getMap()));
		}
		Wookie wookie =new Wookie(new Chewbacca(), pion1,Alliance.Rebellion);
		wookie.getAll().addAll(pion1);
		wookie.getAll().add(wookie.getMaitre());
		teams.add(wookie);
		
		ArrayList<Peon> pion2=new ArrayList<Peon>();
		while (pion2.size()!=3) {
			pion2.add(new StormTrooper(this.getMap()));
		}
		Empire empire =new Empire(new Sergent(), pion2,Alliance.Empire);
		empire.getAll().addAll(pion2);
		empire.getAll().add(empire.getMaitre());
		teams.add(empire);
		
		ArrayList<Peon> pion3=new ArrayList<Peon>();
		while (pion3.size()!=3) {
			pion3.add(new BountyHunter(this.getMap()));
		}
		Mandalorian mandalorian =new Mandalorian(new JangoFett(), pion3,Alliance.Empire);
		mandalorian.getAll().addAll(pion3);
		mandalorian.getAll().add(mandalorian.getMaitre());
		teams.add(mandalorian);
		
		return teams;
}

public void initMessagesMaster() throws IOException {
	BufferedReader br = new BufferedReader(new FileReader("src/txt/quotes.txt"));
	ArrayList<String> quotes=new ArrayList<String>();
	String quote = br.readLine();
	while(quote != null)
	{
		quotes.add(quote);
		quote = br.readLine();		
	}
	int lenQuotes=quotes.size();
	for (Team team: this.getTeams()) {
		int index=0;
		for(int i=0;i<lenQuotes/4;i++) {
			int rand=(int)Math.random()*quotes.size();
			Message msg=new Message(index,quotes.get(rand));
			team.getMaitre().setKnownMasterMessage(msg);
			quotes.remove(rand);
		}
			index++;
	}
}


public void setPeon() {
	
	for(Team e:this.getTeams()) {
			switch(e.getPeons().get(0).getSafeZoneNumber()) {
			case(0):
				this.getMap().getMap()[1][0].setOccupant(e.getPeons().get(0));
	  			this.getMap().getMap()[1][0].setOccupied(true);
	  			e.getPeons().get(0).setX(1);
				e.getPeons().get(0).setY(0);
	  	
	  			this.getMap().getMap()[1][1].setOccupant(e.getPeons().get(1));
	  			this.getMap().getMap()[1][1].setOccupied(true);
	  			e.getPeons().get(1).setX(1);
				e.getPeons().get(1).setY(1);
	  	
	  			this.getMap().getMap()[0][1].setOccupant(e.getPeons().get(2));
	  			this.getMap().getMap()[0][1].setOccupied(true);
	  			e.getPeons().get(2).setX(0);
				e.getPeons().get(2).setY(1);
				
				
			break;
			
			case(1):
				this.getMap().getMap()[map.X-2][0].setOccupant(e.getPeons().get(0));
	  		this.getMap().getMap()[map.X-2][0].setOccupied(true);
	  		e.getPeons().get(0).setX(map.X-2);
			e.getPeons().get(0).setY(0);
	  	
	  		this.getMap().getMap()[map.X-2][1].setOccupant(e.getPeons().get(1));
	  		this.getMap().getMap()[map.X-2][1].setOccupied(true);
	  		e.getPeons().get(1).setX(map.X-2);
			e.getPeons().get(1).setY(1);
	  	
	  		this.getMap().getMap()[map.X-1][1].setOccupant(e.getPeons().get(2));
	  		this.getMap().getMap()[map.X-1][1].setOccupied(true);
	  		e.getPeons().get(2).setX(map.X-1);
			e.getPeons().get(2).setY(1);
				
			break;
				
			case(2):
				
				
				this.getMap().getMap()[0][map.Y-2].setOccupant(e.getPeons().get(0));
		  		this.getMap().getMap()[0][map.Y-2].setOccupied(true);
		  		e.getPeons().get(0).setX(0);
				e.getPeons().get(0).setY(map.Y-2);
		  	
		  		this.getMap().getMap()[1][map.Y-2].setOccupant(e.getPeons().get(1));
		  		this.getMap().getMap()[1][map.Y-2].setOccupied(true);
		  		e.getPeons().get(1).setX(1);
				e.getPeons().get(1).setY(map.Y-2);
		  	
		  		this.getMap().getMap()[1][map.Y-1].setOccupant(e.getPeons().get(2));
		  		this.getMap().getMap()[1][map.Y-1].setOccupied(true);
		  		e.getPeons().get(2).setX(1);
				e.getPeons().get(2).setY(map.Y-1);
			break;
				
			case(3):
				this.getMap().getMap()[map.X-2][map.Y-2].setOccupant(e.getPeons().get(0));
		  		this.getMap().getMap()[map.X-2][map.Y-2].setOccupied(true);
		  		e.getPeons().get(0).setX(map.X-2);
				e.getPeons().get(0).setY(map.Y-2);
		  	
		  		this.getMap().getMap()[map.X-2][map.Y-1].setOccupant(e.getPeons().get(1));
		  		this.getMap().getMap()[map.X-2][map.Y-1].setOccupied(true);
		  		e.getPeons().get(1).setX(map.X-2);
				e.getPeons().get(1).setY(map.Y-1);
		  	
		  		this.getMap().getMap()[map.X-1][map.Y-2].setOccupant(e.getPeons().get(2));
		  		this.getMap().getMap()[map.X-1][map.Y-2].setOccupied(true);
		  		e.getPeons().get(2).setX(map.X-1);
				e.getPeons().get(2).setY(map.Y-2);
			break;
			
			}
		}
	}
	
	
	
	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public ArrayList<Team> getTeams() {
		return teams;
	}

	public void setTeams(ArrayList<Team> teams) {
		this.teams = teams;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getDayMax() {
		return dayMax;
	}

	public void setDayMax(int dayMax) {
		this.dayMax = dayMax;
	}

	public BoardGame getBoardGame() {
		return boardGame;
	}

	public void setBoardGame(BoardGame boardGame) {
		this.boardGame = boardGame;
	}
	
}

	