package controller;

import model.Location;
import model.Player;

public class FlytoCommand implements Command{

	private Player player;
	private Location location;
	
	public FlytoCommand(Player player, Location location) {
		super();
		this.player = player;
		this.location = location;
	}
	
	public boolean execute(){
		return player.flyTo(location, null);
		
	}
	
	
	

}
