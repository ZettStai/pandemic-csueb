package model;

import static org.junit.Assert.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Vector;

import org.junit.Test;

public class PlayerTest {

	/**
	 * Test the move to location
	 * Player can move to location if it is
	 * adjacent to his current location
	 * Here new york is adjacent to san francisco
	 */
	@Test
	public void testMoveToLocation() {
		Location sanFrancisco = new Location("San Francisco");
		Location newYork = new Location("New York");
		Player p1=new Player("player1", sanFrancisco);
		sanFrancisco.addAdjacentLocation(newYork);
		assertTrue(p1.moveTo(newYork));
		assertSame(newYork, p1.getLocation());
	}
	
	/**
	 * Test the move to location
	 * Player can move to location if it is
	 * adjacent to his current location
	 * Here new york is NOT adjacent to san francisco
	 */
	@Test
	public void testMoveToLocationNotAdjacent() {
		Location sanFrancisco = new Location("San Francisco");
		Location newYork = new Location("New York");
		Player p1=new Player("player1", sanFrancisco);
		assertFalse(p1.moveTo(newYork));
		assertSame(sanFrancisco, p1.getLocation());
	}

	/**
	 * Test the fly to location
	 * Player can fly to location if he has the
	 * destination card
	 * Here the player has the new york card
	 */
	@Test
	public void testFlyTo() {
		Location sanFrancisco = new Location("San Francisco");
		Location newYork = new Location("New York");
		Player p1=new Player("player1", sanFrancisco);
		PlayerCard card = new PlayerCard(newYork, new Disease(Color.BLACK));
		p1.getPlayerCards().add(card);
		assertTrue(p1.flyTo(newYork,card));
		assertSame(newYork, p1.getLocation());
		assertFalse(p1.getPlayerCards().contains(card));
	}
	
	@Test
	public void testBuild() {
		Location paris = new Location("Paris");
		Location jakarta = new Location("Jakarta");
		Location tokyo = new Location("Tokyo");
		tokyo.hasResearchCenter();
		
		Player p1 = new Player("Player1",tokyo);
		
		Player p2 = new Player("Player2",jakarta);
		
		Vector<PlayerCard> pcs = new Vector<PlayerCard>();
		
		PlayerCard pc1 = new PlayerCard(paris, new Disease(Color.GREEN));
		PlayerCard pc2 = new PlayerCard(tokyo, new Disease(Color.RED));
		PlayerCard pc3 = new PlayerCard(jakarta, new Disease(Color.RED));
	
		pcs.add(pc1);
		pcs.add(pc2);
		pcs.add(pc3);
		
		p1.setPlayerCards(pcs);
		p2.setPlayerCards(pcs);
		
        assertTrue(p1.build());
        assertTrue(p2.build());
        
	}
	/**
	 * Test the fly to location
	 * Player can fly to location if he has the
	 * destination card
	 * Here the player does not have the new york card
	 */
	@Test
	public void testFlyToWithoutCard() {
		Location sanFrancisco = new Location("San Francisco");
		Location newYork = new Location("New York");
		Player p1=new Player("player1", sanFrancisco);
		PlayerCard card = new PlayerCard(sanFrancisco, new Disease(Color.BLACK));
		p1.getPlayerCards().add(card);
		assertFalse(p1.flyTo(newYork,card));
		assertSame(sanFrancisco, p1.getLocation());
	}

	@Test
	public void testFlyfrom() {
		fail("Not yet implemented");
	}

	@Test
	public void testMoveToLocationPlayerCard() {
		fail("Not yet implemented");
	}

	@Test
	public void testFlyToCardCity() {
		fail("Not yet implemented");
	}

	@Test
	public void testFlyFromCardCity() {
		fail("Not yet implemented");
	}

}
