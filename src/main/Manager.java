package main;

import objects.ObjectManager;
import objects.Team;

public class Manager {

	public static Team teamToPlay = Team.WHITE;
	private ObjectManager manager;
	
	public Manager(ObjectManager manager) {
		
		this.manager = manager;
		
	}
	
	public void alternateTeamToPlay() {
		
		if (teamToPlay == Team.WHITE)
			teamToPlay = Team.BLACK;
		else
			teamToPlay = Team.WHITE;
		
	}
	
	public void checkCheck() {
		
		
		
	}
	
}
