package entity;

import java.util.ArrayList;
import java.util.Scanner;

import entity.LivingBeing.Apprentice;
import entity.LivingBeing.BountyHunter;
import entity.LivingBeing.Chewbacca;
import entity.LivingBeing.Ewak;
import entity.LivingBeing.JangoFett;
import entity.LivingBeing.MasterJedi;
import entity.LivingBeing.Peon;
import entity.LivingBeing.Sergent;
import entity.LivingBeing.StormTrooper;

public class Simulation {
	
	private Map map;
	
	private ArrayList<Team> teams;
	
	private int day;
	
	private int dayMax;
	
	
	
	
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
		System.out.println("test");
		
		
	}
	
public void setTeam(ArrayList<Team> teams) {
	
	ArrayList<Integer> safeZoneNumber=new ArrayList<Integer>();
	safeZoneNumber.add(0);
	safeZoneNumber.add(1);
	safeZoneNumber.add(2);
	safeZoneNumber.add(3);
	
	//do random to randomize team on map
	int count=0;
	for(Team e:teams) {
		int randomIndex = (int) (Math.random() * safeZoneNumber.size());
		//teams.get(randomIndex).setSafeZoneNumber(safeZoneNumber.get(randomIndex));
		teams.get(randomIndex).getAll().forEach(l -> l.setSafeZoneNumber(safeZoneNumber.get(randomIndex)));
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
			  e.getMaitre().setX(19);
			  e.getMaitre().setY(0);
			  this.getMap().getMap()[19][0].setOccupant(e.getMaitre());
			  this.getMap().getMap()[19][0].setOccupied(true);
		    break;
		  case 2:
			  e.getMaitre().setX(0);
			   e.getMaitre().setY(19);
			   this.getMap().getMap()[0][19].setOccupant(e.getMaitre());
			   this.getMap().getMap()[0][19].setOccupied(true);
			    break;
		  case 3:
			  e.getMaitre().setX(19);
			  e.getMaitre().setY(19);
			  this.getMap().getMap()[19][19].setOccupant(e.getMaitre());
			  this.getMap().getMap()[19][19].setOccupied(true);
			    break;
		}
	}
}


	//initialisation des equipes avec un master et 3 peons pour chaque equipe
public ArrayList<Team> initTeam() {
		
		ArrayList<Team> teams=new ArrayList<Team> ();
		
		ArrayList<Peon> pion0=new ArrayList<Peon>();
		while (pion0.size()!=3) {
			pion0.add(new Apprentice());
		}
		Jedi jedi =new Jedi(new MasterJedi(), pion0,Alliance.Rebellion);
		jedi.getAll().addAll(pion0);
		jedi.getAll().add(jedi.getMaitre());
		teams.add(jedi);
		
		
		ArrayList<Peon> pion1=new ArrayList<Peon>();
		while (pion1.size()!=3) {
			pion1.add(new Ewak());
		}
		Wookie wookie =new Wookie(new Chewbacca(), pion1,Alliance.Rebellion);
		wookie.getAll().addAll(pion1);
		wookie.getAll().add(wookie.getMaitre());
		teams.add(wookie);
		
		ArrayList<Peon> pion2=new ArrayList<Peon>();
		while (pion2.size()!=3) {
			pion2.add(new StormTrooper());
		}
		Empire empire =new Empire(new Sergent(), pion2,Alliance.Empire);
		empire.getAll().addAll(pion2);
		empire.getAll().add(empire.getMaitre());
		teams.add(empire);
		
		ArrayList<Peon> pion3=new ArrayList<Peon>();
		while (pion3.size()!=3) {
			pion3.add(new BountyHunter());
		}
		Mandalorian mandalorian =new Mandalorian(new JangoFett(), pion3,Alliance.Empire);
		mandalorian.getAll().addAll(pion3);
		mandalorian.getAll().add(mandalorian.getMaitre());
		teams.add(mandalorian);
		
		return teams;
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
	
}

	