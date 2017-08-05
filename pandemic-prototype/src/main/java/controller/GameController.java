package controller;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

import model.Disease;
import model.Location;
import model.Player;
import model.PlayerCard;
import model.PlayerCard.cardType;
import model.WorldMap;
import model.infectionCard;
import view.ConsoleView;
import view.MainFrame;


public class GameController {
	private static WorldMap worldmap;
	private static List<Player> players;
	private static Stack<PlayerCard> playerCards;
	private static Stack<infectionCard> infectiondeck;
	private static int currentPlayerIndex;
	private static Player currentPlayer;
	private static int actionsLeft = 4;
	private static MainFrame frame;
	private static ConsoleView consoleView;
	private static boolean useGUI = false;
	private static int difficulty = 3;
	private static boolean scenario = false;
	private static Location firstLoc;
	private static Location resetLoc1;
	private static Location resetLoc2;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		initializeWorld();
		infectInitialLocations();
		giveInitialCardsToPlayer();
		preparePlayerDeck();
		consoleView = new ConsoleView();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame = new MainFrame(worldmap);
					frame.setVisible(true);
					worldViewListener();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		play();
		
	}
	
	private static void initializeWorld() {
		worldmap=new WorldMap();
		initializeDisease();
		Location sanFrancisco = new Location("SFO");
		sanFrancisco.setPosition(30, 100);
		Location newYork = new Location("NewYork");
		newYork.setPosition(245, 75);
		Location mexico = new Location("Mexico");
		mexico.setPosition(45, 190);
		Location dallas = new Location("Dallas");
		dallas.setPosition(135, 140);
		Location chicago = new Location("Chicago");
		chicago.setPosition(150, 60);
		
		Location paris = new Location("Paris");
		paris.setPosition(330, 60);
		Location moscow = new Location("Moscow");
		moscow.setPosition(430, 40);
		Location tokyo = new Location("Tokyo");
		tokyo.setPosition(635, 100);
		Location bamako = new Location("Bamako");
		bamako.setPosition(255, 150);
		Location newDelhi = new Location("Delhi");
		newDelhi.setPosition(540, 160);
		Location rioDeJaneiro = new Location("Rio");
		rioDeJaneiro.setPosition(210, 230);
		Location buenosAires = new Location("Buenos Aires");
		buenosAires.setPosition(205, 300);
		Location capeTown = new Location("CapeTown");
		capeTown.setPosition(360, 330);
		Location lagos = new Location("Lagos");
		lagos.setPosition(325,255);
		Location lima = new Location("Lima");
		lima.setPosition(110, 270);
		Location sydney = new Location("Sydney");
		sydney.setPosition(640, 320);
		Location rome = new Location("Rome");
		rome.setPosition(380, 110);
		Location istanbul = new Location("Istanbul");
		istanbul.setPosition(425, 200);
		Location beijing = new Location("Beijing");
		beijing.setPosition(510, 90);
		Location jakarta = new Location("Jakarta");
		jakarta.setPosition(510, 280);
		
		//Colors of city
		newYork.setColor(Color.BLUE);
		sanFrancisco.setColor(Color.BLUE);
		mexico.setColor(Color.BLUE);
		dallas.setColor(Color.BLUE);
		chicago.setColor(Color.BLUE);
		paris.setColor(Color.GREEN);
		moscow.setColor(Color.GREEN);
		bamako.setColor(Color.GREEN);
		tokyo.setColor(Color.RED);
		newDelhi.setColor(Color.RED);
		sydney.setColor(Color.RED);
		buenosAires.setColor(Color.YELLOW);
		rioDeJaneiro.setColor(Color.YELLOW);
		capeTown.setColor(Color.YELLOW);
		lima.setColor(Color.YELLOW);
		lagos.setColor(Color.YELLOW);
		rome.setColor(Color.GREEN);
		istanbul.setColor(Color.GREEN);
		beijing.setColor(Color.RED);
		jakarta.setColor(Color.RED);
		
		
		sanFrancisco.addAdjacentLocation(chicago);
		sanFrancisco.addAdjacentLocation(dallas);
		sanFrancisco.addAdjacentLocation(mexico);
		
		newYork.addAdjacentLocation(paris);
		newYork.addAdjacentLocation(bamako);
		
		chicago.addAdjacentLocation(newYork);
		chicago.addAdjacentLocation(dallas);
		
		dallas.addAdjacentLocation(mexico);
		dallas.addAdjacentLocation(rioDeJaneiro);
		
		paris.addAdjacentLocation(moscow);
		//paris.addAdjacentLocation(bamako);
		
		moscow.addAdjacentLocation(beijing);
		moscow.addAdjacentLocation(rome);
		
		tokyo.addAdjacentLocation(newDelhi);
		tokyo.addAdjacentLocation(sydney);
		
		mexico.addAdjacentLocation(lima);
		
		lima.addAdjacentLocation(rioDeJaneiro);
		lima.addAdjacentLocation(buenosAires);
		
		bamako.addAdjacentLocation(lagos);
		bamako.addAdjacentLocation(rioDeJaneiro);
		bamako.addAdjacentLocation(rome);
		
		istanbul.addAdjacentLocation(rome);
		istanbul.addAdjacentLocation(beijing);
		istanbul.addAdjacentLocation(newDelhi);
		istanbul.addAdjacentLocation(jakarta);
		
		beijing.addAdjacentLocation(tokyo);
		
		sydney.addAdjacentLocation(jakarta);
		
		lagos.addAdjacentLocation(capeTown);
		lagos.addAdjacentLocation(istanbul);
		
		rioDeJaneiro.addAdjacentLocation(buenosAires);
		buenosAires.addAdjacentLocation(lagos);
		newDelhi.addAdjacentLocation(jakarta);
		
		capeTown.addAdjacentLocation(jakarta);
		
		sanFrancisco.setResearchCenter(true);
		
		worldmap.addLocation(newYork);
		worldmap.addLocation(sanFrancisco);
		worldmap.addLocation(paris);
		worldmap.addLocation(moscow);
		worldmap.addLocation(tokyo);
		worldmap.addLocation(mexico);
		worldmap.addLocation(bamako);
		worldmap.addLocation(newDelhi);
		worldmap.addLocation(rioDeJaneiro);
		worldmap.addLocation(buenosAires);
		worldmap.addLocation(capeTown);
		worldmap.addLocation(sydney);
		worldmap.addLocation(dallas);
		worldmap.addLocation(chicago);
		worldmap.addLocation(lima);
		worldmap.addLocation(lagos);
		worldmap.addLocation(rome);
		worldmap.addLocation(istanbul);
		worldmap.addLocation(jakarta);
		worldmap.addLocation(beijing);
		
		firstLoc = sanFrancisco;
		resetLoc1 = tokyo;
		resetLoc2 = paris;
		
		initializePlayers(firstLoc);
	}
	
	public static void restart(){
		players.get(0).setLocation(resetLoc1);
		players.get(1).setLocation(resetLoc2);
	}
	
	private static void initializeDisease() {
		new Disease(Color.BLUE);
		new Disease(Color.RED);
		new Disease(Color.YELLOW);
		new Disease(Color.GREEN);
		
	}

	private static void initializePlayers(Location defaultLocation) {
		
		players=new ArrayList<Player>();
		Player p1=new Player("player1", defaultLocation);
		Player p2=new Player("player2", defaultLocation);
		initializePlayerCards(p1);
		players.add(p1);
		players.add(p2);
		
		currentPlayerIndex=0;
		currentPlayer=players.get(currentPlayerIndex);
		
	}
	
	/*
	private static void initializeDiseaseDeck(){
		Collection<Location> diseaseLocations = new ArrayList();
		diseaseLocations.addAll(worldmap.getLocations());
		System.out.println(diseaseLocations.toString());
		
		//infectiondeck.generatedeckfeature( diseaseLocations);
		
	}*/
	public static void initializeInfectionDeck(){
		infectiondeck = new Stack<infectionCard>();
		for (Location location : worldmap.getLocations()) {
			infectiondeck.add(new infectionCard(location,Disease.getDiseaseByColor(location.getColor())));
		} 
		
	}
	
	public static void initializePlayerCards(Player p){
		playerCards = new Stack<PlayerCard>();
		//Location cards
		for (Location location : worldmap.getLocations()) {
			playerCards.add(new PlayerCard(location,Disease.getDiseaseByColor(location.getColor())));
		}
		//No Epidemic card for the moment
	}
	
	private static void nextPlayer() {
		currentPlayerIndex++;
		int size = players.size();
		if (currentPlayerIndex>=size) {
			currentPlayerIndex=0;			
		}
		currentPlayer=players.get(currentPlayerIndex);
		actionsLeft=4;
		frame.getWorldView().unselect();
	}
	
	public static Player getCurrentPlayer() {
		return currentPlayer;
	}

	public static List<Player> getPlayers() {
		return players;
	}
	
	public static Collection<Location> getLocations(){
		return worldmap.getLocations();
	}

	public static int getActionsLeft() {
		return actionsLeft;
	}
	
	public static int getPlayerCardsLeft() {
		return playerCards.size();
	}

	public static void play() {
		//show player position
		boolean continuePlaying = true;
		// Create infection deck 
		InfectionCommand infect = new InfectionCommand();
		//infectiondeck.generatedeck();
		
		while(continuePlaying) {
			//each player has 4 turns
			// Draw an infection card
			//infect.infectLocation();
			//will be enclosed in for loop as epidemic level rises and more cards are to
			//be drawn.
			//consoleView.printCurrentInfection(worldmap.getInfections());
			consoleView.printPlayerPostion(currentPlayer);
			consoleView.printAdjacentLocations(currentPlayer.getLocation());
			boolean changePlayer=false;
			while(!changePlayer) {
				// one turn
				System.out.println(actionsLeft+" turns left");
				boolean invalidCommand = true;
				while(invalidCommand) {
					String ret;
					//read cmd
					String[] cmd = readCommand();
					ret=executeCommand(cmd);
					//is there an error message?
					if(ret.isEmpty()){
						invalidCommand=false;
					} else {
						System.err.println(ret);
					}		
				}
				consoleView.printPlayerPostion(currentPlayer);
				consoleView.printAdjacentLocations(currentPlayer.getLocation());
				changePlayer=actionPerformed();
			}
			nextPlayer();
		}
	}

	/** Step 3
	 * Place the infection rate marker on the left-most â€œ2â€� space of the
	 * Infection Rate Track. Shuffle the Infection cards and flip over 3 of them.
	 * Put 3 disease cubes of the matching color on each of these cities.
	 * Flip over 3 more cards: put 2 disease cubes on each of these cities.
	 * Flip over 3 more cards: put 1 disease cube on each of these cities.
	 * (You will place a total of 18 disease cubes, each matching the color of
	 * the city.) Place these 9 cards face up on the Infection Discard Pile.
	 * The other Infection cards form the Infection Deck.
	 */
	private static void infectInitialLocations() {
		initializeInfectionDeck();
		infectionCard card;
		// TODO Auto-generated method stub
		// WARNING : This is a random-based stub function
		// Should be later replaced by drawing infection cards;
		for(int numCubes=1; numCubes<=3; numCubes++) {
			for(int numLoc=1; numLoc<=2; numLoc++) { 
				card = infectiondeck.firstElement();
				//Location loc = card.getCity();
				Location loc = randomLocation();
				for(int j=0; j<numCubes; j++) {
					loc.infect(Disease.getDiseaseByColor(loc.getColor()));
				}
			}
		}
		
	}

	/** Step 4
	 * Take the Epidemic cards out the Player Deck and set them
	 * aside until Step 5. Shuffle the other Player cards (City and
	 * Event cards). Deal cards to the players to form their initial
	 * hands. Give cards according to the number of players:
	 */
	private static void giveInitialCardsToPlayer() {
		// TODO Auto-generated method stub
		int numPlayers=players.size();
		int numCardsPerPlayers=3; //TODO : depends on number of players
		List<PlayerCard> temp = playerCards.subList(0, playerCards.size());
		Collections.shuffle(playerCards);
		for (Player player : players) {
			for (int i = 0; i < numCardsPerPlayers; i++) {
				player.getPlayerCards().add(playerCards.pop());
			}
		}
	}
	
	/** Step 5
	 * Set the gameâ€™s difficulty level, by using either 4, 5, or 6 Epidemic cards, for
	 * an Introductory, Standard, or Heroic game. Remove any unused Epidemic
	 * cards from the game.
	 * Divide the remaining player cards into face down piles, as equal in size as
	 * you can, so that the number of piles matches the number of Epidemic cards
	 * you are using. Shuffle 1 Epidemic card into each pile, face down. Stack these
	 * piles to form the Player Deck, placing smaller piles on the bottom.
	 */
	private static void preparePlayerDeck() {
		Stack<PlayerCard>[] piles = new Stack[difficulty];
		for (int i=0;i<piles.length;i++) {
			piles[i]=new Stack<PlayerCard>();
		}
		int size=playerCards.size();
		int pos=0;
		for (PlayerCard card : playerCards) {
			piles[pos%difficulty].add(card);
			pos++;
		}
		for (Stack<PlayerCard> stack : piles) {
			stack.add(new PlayerCard());
			Collections.shuffle(stack);
		}
		playerCards.clear();
		for (Stack<PlayerCard> stack : piles) {
			playerCards.addAll(stack);
		}
		
	}

	/**
	 * Decrease the number of actions left
	 * and if it reached 0, change current player to next
	 * return true if the next player is called
	 */
	private static boolean actionPerformed() {
		actionsLeft--;
		if(actionsLeft==0) {
			drawCards();
			nextPlayer();
			return true;
		}
		return false;
	}
	
	private static void infectCities() {
		// TODO Auto-generated method stub
		//WARNING This is random
		//Should be replaced later by drawing infection card
		for (int i = 0; i < worldmap.getInfectionRate(); i++) {
			Location loc = randomLocation();
			frame.notifyEpidemic(loc);
			loc.infect(Disease.getDiseaseByColor(loc.getColor()));
		}
		
	}
	
	private static void infectCitiesEpidemic() {
		// TODO Auto-generated method stub
		//WARNING This is random
		//Should be replaced later by drawing infection card
			Location loc = randomLocation();
			frame.notifyEpidemic(loc);
			loc.infectEpidemic(Disease.getDiseaseByColor(loc.getColor()));
	}
	
	public static void notifyOutbreak(Location location) {
		worldmap.increaseOutbreaks();
		if(frame==null) return;
		frame.notifyOutbreak(location);
	}

	private static void drawCards() {
		// TODO Auto-generated method stub
		int numCardToDraw = 1;
		if(playerCards.empty()) {
			System.out.println("Empty player deck");
			return;
		}
		PlayerCard card = playerCards.pop();
		if(card.getType()==cardType.CITY) {
			currentPlayer.getPlayerCards().add(card);
			frame.notifyDrawnCard(currentPlayer, card);
		} else if (card.getType()==cardType.EPIDEMIC) {
			worldmap.increaseInfectionRate();
			System.out.println("Epidemic level "+worldmap.getInfectionRate());
			infectCitiesEpidemic();
			//infectionDeck.drawBottomCard();
			//TODO: Still need to add these functionalities.
			// Needs to draw bottom card of InfectionDeck
			// Add 3 cubes to the city drawn. If there are already cubes, max to 3.
			// Then call Outbreak
			// Intensify, eshuffle infection discard pile and place on top of deck.
			// -LS
			// Intensify, eshuffle infection discard pile and place on top of deck.
		}
		
	}

	public static String[] readCommand() {
		String input=null;
		//create the Scanner
		System.out.println("reading command : (help, mv)");
		Scanner terminalInput = new Scanner(System.in);
		//read input
		input = terminalInput.nextLine();
		String[] cmd=input.split("\\s");
		return cmd;
	}
	
	public static String executeCommand(String[] cmdLine) {
		int lenght = cmdLine.length; 
		if(lenght>0) {
			if(cmdLine[0].compareTo("help")==0) {
				return "commands :\n- mv : move";
			}
			else if(cmdLine[0].compareTo("mv")==0) {
				if(lenght>1) {
					String argument = new String();
					for (int i = 1; i < cmdLine.length; i++) {
						if(i!=1) {
							argument+=" ";
						}
						argument+=cmdLine[i];
					}
					MoveCommand mv = new MoveCommand(currentPlayer, worldmap.getLocationByName(argument));
					boolean ret = mv.execute();
					if(ret) {
						frame.getWorldView().updatePlayers();
						return "";
					} else {
						return "You can't move there";
					}
				} else {
					return "Please specifies the destination";
				}
			} 
			else if(cmdLine[0].compareTo("ft") == 0){
				if(lenght>1) {
					String argument = new String();
					for(int i = 1; i < cmdLine.length; i++) {
						if(i!=1) {
							argument+=" ";
						}
						argument+=cmdLine[i];
					}
					FlytoCommand ft = new FlytoCommand(currentPlayer,worldmap.getLocationByName(argument));
					boolean ret = ft.execute();
					if(ret) {
						return "";
						//don't return anything here.
					} else {
						return "You can't fly to" + argument;
					}
				}
				}
			
			else if(cmdLine[0].compareTo("ff") == 0){
				if(lenght>1) {
					String argument = new String();
					for(int i = 1; i < cmdLine.length; i++) {
						if(i!=1) {
							argument+=" ";
						}
						argument+=cmdLine[i];
					}
					FlyfromCommand ff = new FlyfromCommand(currentPlayer, worldmap.getLocationByName(argument), null);
					boolean ret = ff.execute();
					if(ret) {
						return "";
						//don't return anything here.
					} else {
						return "You can't fly to" + argument;
					}
				}
				}
			else {
				return "Invalid command";
			}
		} else {
			return "Please enter a valid command";
		}
		return null;		
	}
	
	private static void worldViewListener(){ 	
    	MouseListener clickWorldView = new MouseListener(){
    		 
			public void mouseClicked(MouseEvent arg0) {
				try {
					//System.out.println("click "+arg0.getX()+";"+arg0.getY()+" : "+arg0.getComponent().toString());
					frame.clickCanvas(arg0.getX(),arg0.getY());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
 
			public void mouseEntered(MouseEvent arg0) {
			}
 
			public void mouseExited(MouseEvent arg0) {
			}
 
			public void mousePressed(MouseEvent arg0) {
			}
 
			public void mouseReleased(MouseEvent arg0) {
			}
		};
	frame.getWorldView().addMouseListener(clickWorldView); 
}
	
	/**
	 * Pass player turn
	 */
	public static void pass() {
		int i = actionsLeft;
		while (i>0) {
			actionPerformed();
			i--;
		}
	}
	
	/**
	 * move current player to specified location
	 * @param loc location to move to
	 * @return true if the move is done
	 */
	public static boolean moveCurrentPlayerToLocation(Location loc) {
		MoveCommand mc = new MoveCommand(currentPlayer, loc);
		if(mc.execute()) {
			actionPerformed();
			return true;
		}
		return false;
	}
	
	/**
	 * Treat current player location from specified disease 
	 * @param disease
	 * @return
	 */
	public static boolean treatCurrentLocation(Disease disease) {
		
		Location currentLoc = currentPlayer.getLocation();
		if(currentLoc.treat(disease)) {
			actionPerformed();
			return true;
		}
		return false;
	}
	
	/**
	 * Cure disease 
	 */
	public static boolean cure(PlayerCard c1,PlayerCard c2) {
		
		boolean check = false;
		Color currentColor = currentPlayer.getLocation().getColor();
		Color color1 = c1.getPlayerCardLocation().getColor();
		Color color2 = c2.getPlayerCardLocation().getColor();
		if(currentColor.equals(color1) && currentColor.equals(color2)){
			check = true;
			int pcl = currentPlayer.getPlayerCards().size();
			int loc1=-1;
			int loc2=-1;
			for(int i=0;i<pcl;i++){
				Location currentLoc = currentPlayer.getPlayerCards().get(i).getPlayerCardLocation();
				if(c1.getPlayerCardLocation().equals(currentLoc) || (c2.getPlayerCardLocation().equals(currentLoc))){
					if(loc1 == -1){
						loc1 = i;
					}
					else{
						loc2 = i;
					}
				}
			}
			currentPlayer.getPlayerCards().remove(loc1);
			currentPlayer.getPlayerCards().remove(loc2-1);
			Disease.getDiseaseByColor(color1).setCured(true);
			actionPerformed();
		}
		return check;
	}
	
	/**
	 * Make the current player fly to a location using a card
	 * @param location the destination
	 * @parm card the player card to use
	 * @return true if the fly is successful
	 */
	public static boolean flyCurrentPlayerTo(Location destination, PlayerCard card) {
		
		boolean check=false;
		
		if(destination.equals(card.getPlayerCardLocation())){
			check = currentPlayer.flyTo(destination, card);
		}
		if(check) {
			actionPerformed();
		}
		return check;
	}
	
public static boolean charterPlayerTo(Location destination, PlayerCard card) {
		
		boolean check=false;
			
			if(currentPlayer.getLocation().equals(card.getPlayerCardLocation())){
				check = currentPlayer.flyfrom(destination, card);
			}
			if(check) {
				actionPerformed();
			}
			return check;
	}
	
	public static boolean buildRC(){
  	  
    	boolean check = false;
    	
    	if(currentPlayer.getLocation().hasResearchCenter()){
    		return check;
    	}
    	else{
    		check = currentPlayer.build();
    	}  
    	if(check == true){
    		actionPerformed();
    	}
		return check;
	}
	
	public static boolean shuttle(Location location){
		boolean check = false;
		
		if(currentPlayer.getLocation().hasResearchCenter() && location.hasResearchCenter()){
			check = true;
			currentPlayer.setLocation(location);
			actionPerformed();
		}
		return check;
	}
	public static int shareKnowledge(){
		
		int result = 0;
		int otherPlayerIndex;
		
		if(currentPlayerIndex==0){
			otherPlayerIndex=1;
		}
		else{
			otherPlayerIndex=0;
		}
		
		if(players.get(0).getLocation().equals(players.get(1).getLocation())){
			for(int i=0;i<currentPlayer.getPlayerCards().size();i++){
				if(currentPlayer.getPlayerCards().get(i).getPlayerCardLocation().equals(currentPlayer.getLocation())){
					result = 1;
					PlayerCard pc = players.get(currentPlayerIndex).getPlayerCards().get(i);
					players.get(otherPlayerIndex).getPlayerCards().addElement(pc);
					players.get(currentPlayerIndex).getPlayerCards().remove(i);
					actionPerformed();
				}	
			}
			
			if(result != 1){
				for(int i=0;i<players.get(otherPlayerIndex).getPlayerCards().size();i++){
					if(players.get(otherPlayerIndex).getPlayerCards().get(i).getPlayerCardLocation().equals(players.get(otherPlayerIndex).getLocation())){
						result = 2;
						PlayerCard pc = players.get(otherPlayerIndex).getPlayerCards().get(i);
						players.get(currentPlayerIndex).getPlayerCards().addElement(pc);
						players.get(otherPlayerIndex).getPlayerCards().remove(i);
						actionPerformed();
					}
				}
			}
			
			if(result != 1 && result != 2){
				result = 3;
			}
			
		}
		return result;
	}
	private static void testInfect(Location loc) {
		loc.infect(Disease.getDiseaseByColor(Color.RED));
		loc.infect(Disease.getDiseaseByColor(Color.RED));
		loc.infect(Disease.getDiseaseByColor(Color.RED));
		loc.infect(Disease.getDiseaseByColor(Color.GREEN));
		loc.infect(Disease.getDiseaseByColor(Color.BLUE));
		loc.infect(Disease.getDiseaseByColor(Color.BLUE));
		loc.infect(Disease.getDiseaseByColor(Color.YELLOW));
	}
	
	private static Location randomLocation() {
		int numLoc = worldmap.getLocations().size();
		int rand = (int) Math.floor(Math.random()*(numLoc));
		return (Location) worldmap.getLocations().toArray()[rand];
	}

}
