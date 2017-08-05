package controller;

import model.Location;
import model.Player;
import model.PlayerCard;

public class FlyfromCommand implements Command{

	private Player player;
	private Location destination;
	private PlayerCard card;
	
	public FlyfromCommand(Player player,Location destination,PlayerCard card) {
		super();
		this.player = player;
		this.destination = destination;
		this.card = card;
	}
	
	public boolean execute(){
		return player.flyfrom(destination,card);
		
	}
}
