package controller;

import java.util.Map;
import model.Location;

import model.infectionCard;
import model.infectionDeck;

public class InfectionCommand implements Command{
	private infectionDeck infect;
	private infectionCard infected;
	private Location infectThis;
	
	public InfectionCommand(){
		super();
		infect = new infectionDeck();
		
	}
	public Object[] infectLocation(infectionCard drawn){
		
		
		
		
		
		return new Object[] {infected.getDisease(), infected.getName()};
	}
	public boolean execute() {
		// TODO Auto-generated method stub
		return false;
	}
	
}
	