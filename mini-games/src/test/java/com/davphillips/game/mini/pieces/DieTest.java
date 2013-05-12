package com.davphillips.game.mini.pieces;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;

public class DieTest {

	@Test
	public void testRoll() {

		final int minValue = 0;
		final int maxValue = 2;
		final int numRolls = 1000;
		
		int[] buckets = new int[1 + maxValue];
		
		for( int i = 0; i < buckets.length; i++)
		{
			buckets[i] = 0;
		}
		
		Die die = new Die(minValue, maxValue);
		
		List< Integer > rolls = die.rollMultiple(numRolls);
		
		assertNotNull(rolls);
		assertTrue(rolls.size() == numRolls);
		
		for( int i = 0; i < rolls.size(); i++ )
		{
			final int roll = rolls.get(i); 
			
			assertTrue(roll >= minValue);
			assertTrue(roll <= maxValue);
			
			buckets[roll]++;
		}
		
		for( int i = 0; i < buckets.length; i++)
		{
			assertTrue(buckets[i] != 0);
		}
	}
}
