package controller;

import model.Location;
import model.Player;

public class MoveCommand implements Command {
	
	private Player player;
	private Location location;

	
	public MoveCommand(Player player, Location location) {
		super();
		this.player = player;
		this.location = location;
	}


	public boolean execute() {
		return player.moveTo(location);
	}
}
