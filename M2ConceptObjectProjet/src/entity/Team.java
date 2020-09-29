package entity;

import java.util.ArrayList;

import entity.LivingBeing.LivingBeings;
import entity.LivingBeing.Master;
import entity.LivingBeing.Peon;

public class Team {
	
	private Alliance alliance;
	
	private Master maitre;
	
	private ArrayList<Peon > peons;
	
	private ArrayList<LivingBeings> all;
	
	private int safeZoneNumber;

	public Team(Master maitre, ArrayList<Peon> peons,Alliance alliance) {
		super();
		this.maitre = maitre;
		this.peons=peons;
		this.alliance=alliance;
	}

	public Master getMaitre() {
		return maitre;
	}

	public void setMaitre(Master maitre) {
		this.maitre = maitre;
	}

	public ArrayList<Peon > getPeons() {
		return peons;
	}

	public void setPeons(ArrayList<Peon > peons) {
		this.peons = peons;
	}
	
	public Alliance getAlliance() {
		return alliance;
	}

	public void setAlliance(Alliance alliance) {
		this.alliance = alliance;
	}

	public int getSafeZoneNumber() {
		return safeZoneNumber;
	}

	public void setSafeZoneNumber(int safeZoneNumber) {
		this.safeZoneNumber = safeZoneNumber;
	}

	public ArrayList<LivingBeings> getAll() {
		return all;
	}

	public void setAll(ArrayList<LivingBeings> all) {
		this.all = all;
	}

	
	
	

}
