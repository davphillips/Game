package com.davphillips.game.mini.poker;

import static org.junit.Assert.*;

import org.junit.Test;

public class BitPokerPlayerTest {

	@Test
	public void testBitPokerPlayer() {

		final int LUCK_POINTS = 1;
		
		BitPokerPlayer p = new BitPokerPlayer();
		
		p.setLuckPoints(LUCK_POINTS);
		
		assertTrue(LUCK_POINTS == p.getLuckPoints());
		
		p.setLuckPoints(1 + LUCK_POINTS);
		
		assertTrue(1 + LUCK_POINTS == p.getLuckPoints());
	}

}
