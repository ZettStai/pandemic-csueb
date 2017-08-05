package model;

import java.awt.Color;
import java.util.Vector;

import controller.GameController;


public class Player {
	private String name;
	private Location location;
	private Vector<PlayerCard> playerCards;
	private Color color = Color.MAGENTA.darker();

	public Vector<PlayerCard> getPlayerCards() {
		return playerCards;
	}
	
	public void setPlayerCards(Vector<PlayerCard> pcs){
		playerCards = pcs;
	}

	public Player(String name, Location location) {
		this.name=name;
		this.location=location;
		playerCards = new Vector<PlayerCard>();
	}
	
	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setLocation(Location loc) {
		this.location = loc;
	}

	public boolean moveTo(Location newLocation) {
		if(location.getAdjacentLocations().contains(newLocation)) {
			location=newLocation;
			return true;
		} else {
			return false;
		}
	}
	
	public boolean flyTo(Location destination,PlayerCard card) {
		boolean check = false;
		
		if(card.getPlayerCardLocation().equals(destination)){
			check = true;
			location = destination;
			playerCards.remove(card);
		}
		return check;
	}
	
	public boolean flyfrom(Location destination,PlayerCard card) {
		boolean check = false;

			if(card.getPlayerCardLocation().equals(location)){
				check = true;
				location = destination;
				playerCards.remove(card);
			}
		return check;
	}
	
	public boolean build(){
		boolean check = false;
		for(int i=0;i<this.getPlayerCards().size();i++){
    		PlayerCard cd = this.getPlayerCards().get(i);
    		if(this.getLocation().equals(cd.getPlayerCardLocation())){
    		    check = true;
    		    this.getLocation().setResearchCenter(true);
    		    this.getPlayerCards().remove(cd);
    		}
    	}
		return check;
	}
	public boolean moveTo(Location loc, PlayerCard card) {
		//TODO : this is stub
		return false;
	}
	
  public Location flyToCardCity(Location loc,PlayerCard card){
    //TODO : this is a stub
    Location cardLoc = card.getPlayerCardLocation();
    if(loc != card.getPlayerCardLocation()){
      return cardLoc;
                            }
    else{
      return loc;
         }
    //This method takes as inputs the loc(location the player is in) and card(PlayerCard the player has) 
    //new location returned is the location of the card the player has
    // however if loc and card.location are the same then the users turn is wasted and same location is returned.
    }
    
    public Location flyFromCardCity(Location currentLoc,PlayerCard card,Location destinationLoc){
      //TODO : this is a stub
      Location cardLoc = card.getPlayerCardLocation();
      if(currentLoc == cardLoc){
           return destinationLoc;
                                }
      else{
           return currentLoc;
          }
      //This method takes as inputs the currentLoc(the current player location),card(PlayerCard the player has) and destinationLoc(destination location)
      //If the current location matches with the location of the player card then destination location is returned
      //however if the current location does not match the player card location then current location itself is returned and turn is wasted.
         
   }
      
      
	public String getName() {
		return name;
	}
	
	public Location getLocation() {
		return location;
	}
	
	public String toString() {
		return "Player "+name+" at "+location.getName();
	}


}
