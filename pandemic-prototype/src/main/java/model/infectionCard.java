package model;

import java.awt.Color;
import java.util.Random;


public class infectionCard {
	private Location infectCityName;  //name of the card to infect.
	private Disease disease;         // name of disease, enum?
	private boolean isdrawn = false; //is the card drawn from the deck
	
	/**
	 * Stub made as a constructor with single card.
	 * Will make enum or dictionary so that everytime the constructor
	 * is called a new card can be generated.
	 * OB
	 * 
	 */
	
	public infectionCard(Location loc,Disease dis) {
				
		this.infectCityName=loc;
		this.disease=dis;
		/*if(color == 0){
			this.disease=new Disease(Color.YELLOW);
		}
		else if(color == 1){
			this.disease=new Disease(Color.GREEN);
		}
		else if(color == 2){
			this.disease=new Disease(Color.BLUE);
		}
		else{
			this.disease=new Disease(Color.RED);
		}
		this.isdrawn=true;
		*/
	}
	public Location getCity(){
		return infectCityName;
	}
	public String getName() {
		return infectCityName.getName();
	}
	public Disease getDisease() {
		return disease;
	}
	public boolean cardStatus() {
		return isdrawn;
	}
	public String toString() {
		return infectCityName.getName() + disease.getName();
	}
	
}


