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
		
		short blackIndex = (short) manager.indexOf(manager.blackKing);
		short whiteIndex = (short) manager.indexOf(manager.whiteKing);

		if (manager.blackKingCantMove.contains(blackIndex))
			manager.blackKing.setCheck(true);
		else
			manager.blackKing.setCheck(false);

		if (manager.whiteKingCantMove.contains(whiteIndex))
			manager.whiteKing.setCheck(true);
		else
			manager.whiteKing.setCheck(false);
		
	}
	
}
