package model;

import java.util.*;

public class infectionDeck{
	
	private Set<infectionCard> infectionCards;
	
	public infectionDeck(){}
}

/*
import controller.GameController;


public class infectionDeck {
	private Stack<infectionCard> deck;
	private GameController gc;
	private Location loc;
	
	public infectionCard drawCard (Location loc, int color) {
		//TODO: Stub will need to rewritten to draw an existing card from deck -LS
		//Draws card, adds cubes to a location
		infectionCard drawn = new infectionCard(loc, 0); 
		if(!deck.isEmpty()){
			drawn = deck.peek();
			deck.insertElementAt(drawn, 0);
			//move the card to the back of the deck
		
		}
		
		return drawn;
	}
	
	public infectionCard drawBottomCard () {
		//TODO: Stub will need to rewritten to draw an existing card from deck -LS
		//Draws card, adds cubes to a location
		infectionCard drawn = new infectionCard(loc, 0); 
		if(!deck.isEmpty()){
			drawn = deck.peek();
			deck.insertElementAt(drawn, 0);
			//move the card to the back of the deck
		
		}
		
		return drawn;
	}

	
	
	
	public void generatedeckfeature(Collection<Location> diseaseLocations){
		diseaseLocations = new ArrayList();
		deck=new Stack<infectionCard>();
		for(Iterator it = diseaseLocations.iterator(); it.hasNext();){
			infectionCard card = new infectionCard((Location) it.next(), 0);
			deck.push(card);
			infectionCard card1 = new infectionCard((Location) it.next(), 1);
			deck.push(card1);
			infectionCard card2= new infectionCard((Location) it.next(), 2);
			deck.push(card2);
			infectionCard card3 = new infectionCard((Location) it.next(), 3);
			deck.push(card3);
		}
		
		
		
		//from GameController have locations, generate deck with these.
		//System.out.println(locs.size());
		//deck.add(e)
	}
	
	
	};
	
	
	
	
	
	
	
	
	
	/**
	 * For Stub created quick constructor that calls
	 * the infectionCard class which will generate a new
	 * card as cards are generated as drawn.
	 * Once all cards have been created they would get 
	 * shuffled.
	 */
	/**
	public infectionDeck(){
		card =new  infectionCard();
>>>>>>> other
	}

}*/

