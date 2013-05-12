package com.davphillips.game.mini.pieces;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class DiceBagTest {

	@Test
	public void testDiceBag() {

		DiceBag dice = new DiceBag();
		
		assertTrue(dice.getNumDice() == 0);
	}

	@Test
	public void testDiceBagDie() {
		
		Die die = new Die(0,0);
		
		DiceBag dice = new DiceBag(die);
		
		assertTrue(dice.getNumDice() == 1);
		assertTrue(dice.getDie(0) == die);
	}

	@Test
	public void testDiceBagListOfDie() {
		
		Die die1 = new Die(0,0);
		Die die2 = new Die(0,0);
		
		List<Die> dieList = new ArrayList< Die >();
		dieList.add(die1);
		dieList.add(die2);
		
		DiceBag dice = new DiceBag(dieList);
		
		assertTrue(dice.getNumDice() == 2);
		assertTrue(dice.getDie(0) == die1);
		assertTrue(dice.getDie(1) == die2);
		
		Die die3 = new Die(0,0);
		DiceBag extraBag = new DiceBag(die3);
		
		dice.addDie(extraBag);
		
		assertTrue(dice.getNumDice() == 3);
		assertTrue(dice.getDie(2) == die3);
	}

	@Test
	public void testDiceBagIntegerIntegerInteger() {
		
		final int numDice = 5;
		final int minValue = 0;
		final int maxValue = 2;
		
		DiceBag dice = new DiceBag(numDice, minValue, maxValue);
		
		assertTrue(dice.getNumDice() == numDice);
		
		for(int i = 0; i < dice.getNumDice(); i++)
		{
			assertTrue(dice.getDie(i).getMinValue() == minValue);
			assertTrue(dice.getDie(i).getMaxValue() == maxValue);
		}
	}
	
	@Test
	public void testDiceBagRoll() {
		
		final int numDice = 1000;
		final int minValue = 0;
		final int maxValue = 2;
		int[] buckets = new int[1 + maxValue];
		
		for( int i = 0; i < buckets.length; i++)
		{
			buckets[i] = 0;
		}
		
		DiceBag dice = new DiceBag(numDice, minValue, maxValue);
		
		List<Integer> rolls = dice.rollAll();
		
		assertNotNull(rolls);
		assertTrue(rolls.size() == numDice);
		
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
