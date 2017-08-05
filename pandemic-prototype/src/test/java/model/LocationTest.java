package model;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.Test;

public class LocationTest {

	@Test
	public void testInfectDisease() {
		Disease d = new Disease(Color.YELLOW);
		Location lyon = new Location("Lyon");
		assertTrue(lyon.getInfections().containsKey(d));
		assertEquals(0, lyon.getInfections().get(d).longValue());
		lyon.infect(d);
		assertEquals(1, lyon.getInfections().get(d).longValue());
		lyon.infect(d);
		lyon.infect(d);
		// max Infection = 3
		lyon.infect(d);
		assertEquals(3, lyon.getInfections().get(d).longValue());
	}

}
