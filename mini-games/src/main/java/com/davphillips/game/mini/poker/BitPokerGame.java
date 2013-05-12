package com.davphillips.game.mini.poker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.davphillips.game.mini.pieces.DiceBag;

public class BitPokerGame {

	private List< BitPokerPlayer > players;
	private List< Integer > hands;
	private List< Integer > redealCounts;
	private Map< BitPokerPlayer, Integer > playerIndex;
	
	private DiceBag deck;
	
	private static final int LOW_VAL = 0;
	private static final int HIGH_VAL = 1;
	
	public BitPokerGame(int handSize)
	{
		this.setPlayers(new ArrayList<BitPokerPlayer>());
		this.setPlayerIndex(new HashMap<BitPokerPlayer, Integer>());
		this.setHands(new ArrayList< Integer >());
		this.setRedealCounts(new ArrayList< Integer >());
		this.setDeck(new DiceBag(handSize, LOW_VAL, HIGH_VAL));
	}
	
	public BitPokerGame(int handSize, BitPokerPlayer p1, BitPokerPlayer p2)
	{
		this(handSize);
		this.addPlayer(p1);
		this.addPlayer(p2);
	}
	
	public BitPokerGame(int handSize, List<BitPokerPlayer> players)
	{
		this(handSize);
		this.addPlayers(players);
	}

	
	private Map<BitPokerPlayer, Integer> getPlayerIndex() 
	{
		return playerIndex;
	}

	private final void setPlayerIndex(Map<BitPokerPlayer, Integer> playerIndex) 
	{
		this.playerIndex = playerIndex;
	}
	
	private Integer getIndexOf(BitPokerPlayer p)
	{
		return this.getPlayerIndex().get(p);
	}

	private List<BitPokerPlayer> getPlayers() 
	{
		return players;
	}

	private final void setPlayers(List<BitPokerPlayer> players) 
	{
		this.players = players;
	}
	
	
	private List<Integer> getRedealCounts() 
	{
		return redealCounts;
	}

	private final void setRedealCounts(List<Integer> redealCounts) 
	{
		this.redealCounts = redealCounts;
	}

	private final void addPlayer(BitPokerPlayer p)
	{
		this.getPlayerIndex().put(p, this.getNumPlayers());
		this.getPlayers().add(p);
		this.getHands().add(new Integer(0));
		this.getRedealCounts().add(new Integer(0));
	}
	
	private final void addPlayers(List<BitPokerPlayer> p)
	{
		for(int i = 0; i < p.size(); i++)
		{
			this.addPlayer(p.get(i));
		}
	}

	private DiceBag getDeck() 
	{
		return deck;
	}

	private final void setDeck(DiceBag deck) 
	{
		this.deck = deck;
	}

	private List< Integer > getHands() 
	{
		return hands;
	}

	private void setHands(List< Integer > hands) 
	{
		this.hands = hands;
	}
	
	private void incrementRedealCount(int player)
	{
		this.setRedealCount(player, 1 + this.getRedealCount(player));
	}
	
	private int setRedealCount(int player, int value)
	{
		return this.getRedealCounts().set(player, value);
	}
	
	public int getRedealCount(int player)
	{
		return this.getRedealCounts().get(player);
	}
	
	public int getHand(int player)
	{
		return this.getHands().get(player);
	}
	
	private int setHand(int player, int value)
	{
		return this.getHands().set(player, value);
	}
	
	public BitPokerPlayer getPlayer(int player)
	{
		return this.getPlayers().get(player);
	}
	
	public int getNumPlayers()
	{
		return this.getPlayers().size();
	}
	
	private void resetPlayer(int player)
	{
		this.setHand(player, 0);
		this.setRedealCount(player, 0);
	}
	
	private void dealOne(int player)
	{
		int newHand = 0;
		int nonZeroCount = 0;
		
		List<Integer> vals = this.getDeck().rollAll();
		
		for(int j = 0; j < vals.size(); j++)
		{
			if(vals.get(j) != 0)
			{
				newHand |= 1 << nonZeroCount++;
			}
		}
		
		if(newHand > this.getHand(player) )
		{
			this.setHand(player, newHand);	
		}
	}
	
	private void redealAllLuck()
	{
		if(this.getNumPlayers() > 1)
		{
			List< BitPokerPlayer > sortedPlayers = new ArrayList<BitPokerPlayer>();
				sortedPlayers.addAll(this.getPlayers());
				Collections.sort(sortedPlayers, new BitPokerPlayerLuckComparator());
			
			for(int i = 1; i < sortedPlayers.size(); i++)
			{	
				BitPokerPlayer ithPlayer = sortedPlayers.get(i);
				final int ithLuck = ithPlayer.getLuckPoints();
				final int ithPlayerIndex = this.getIndexOf(ithPlayer);
				
				final int deltaLuck = ithLuck - sortedPlayers.get(i - 1).getLuckPoints();
				
				for(int j = 0; j < i; j++)
				{
					BitPokerPlayer jthPlayer = sortedPlayers.get(j);
					int jthPlayerIndex = this.getIndexOf(jthPlayer);
					
					final int jthHand = this.getHand(jthPlayerIndex);
					
					for( ; this.getRedealCount(ithPlayerIndex) < deltaLuck && 
					       this.getHand(ithPlayerIndex) < jthHand; this.incrementRedealCount(ithPlayerIndex))
					{
						this.dealOne(ithPlayerIndex);
					}		
				}
			}
		}
	}
	
	private void dealAll()
	{
		for(int i = 0; i < this.getNumPlayers(); i++)
		{
			this.resetPlayer(i);
			this.dealOne(i);
		}
	}
	
	public void play()
	{	
		this.dealAll();
		
		this.redealAllLuck();
	}
	
}
