package com.davphillips.game.mini.poker;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class BitPokerGameTest {

	@Test
	public void testBitPokerGame() {
		
		BitPokerGame game = new BitPokerGame(5);
		
		assertTrue(0 == game.getNumPlayers());
		
		BitPokerPlayer p1 = new BitPokerPlayer();
			p1.setLuckPoints(7);
		BitPokerPlayer p2 = new BitPokerPlayer();
			p1.setLuckPoints(5);
		BitPokerPlayer p3 = new BitPokerPlayer();
			p1.setLuckPoints(3);
		
		game = new BitPokerGame(5, p1, p2);
		
		assertTrue(2 == game.getNumPlayers());
		assertTrue(p1 == game.getPlayer(0));
		assertTrue(p2 == game.getPlayer(1));
		
		List<BitPokerPlayer> playerList = new ArrayList<BitPokerPlayer>();
			playerList.add(p1);
			playerList.add(p2);
			playerList.add(p3);
		
		game = new BitPokerGame(5, playerList);

		assertTrue(3 == game.getNumPlayers());
		assertTrue(p1 == game.getPlayer(0));
		assertTrue(p2 == game.getPlayer(1));
		assertTrue(p3 == game.getPlayer(2));
		
		for(int i = 0; i < game.getNumPlayers(); i++)
		{
			assertTrue(0 == game.getHand(i));
			assertTrue(0 == game.getRedealCount(i));
		}

	}
	
	@Test
	public void testBitPokerGameScenario1() 
	{	
		BitPokerPlayer p1 = new BitPokerPlayer();
			p1.setLuckPoints(30);
		BitPokerPlayer p2 = new BitPokerPlayer();
			p2.setLuckPoints(15);
		BitPokerPlayer p3 = new BitPokerPlayer();
			p3.setLuckPoints(8);
		
		List<BitPokerPlayer> playerList = new ArrayList<BitPokerPlayer>();
			playerList.add(p1);
			playerList.add(p2);
			playerList.add(p3);
		
		BitPokerGame game = new BitPokerGame(10, playerList);
		
		for(int i = 0 ; i < 50 ; i++)
		{
			System.out.println("Game " + i);
			
			game.play();
			
			for(int j = 0; j < game.getNumPlayers(); j++)
			{
				System.out.println("Player[" + ( j + 1 ) + "] " + game.getHand(j) + " (Re-Dealt " + game.getRedealCount(j) + " times)");
			}
		}
	}
}
