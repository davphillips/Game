package com.davphillips.game.mini.poker;

import java.util.Comparator;

public class BitPokerPlayerLuckComparator implements Comparator<BitPokerPlayer> {
	
	public BitPokerPlayerLuckComparator()
	{

	}
	
	public int compare(BitPokerPlayer p1, BitPokerPlayer p2)
	{
		return new Integer(p1.getLuckPoints()).compareTo(new Integer(p2.getLuckPoints()));
	}
	
	public boolean equals(Object obj)
	{
		return obj == this;
	}
}
