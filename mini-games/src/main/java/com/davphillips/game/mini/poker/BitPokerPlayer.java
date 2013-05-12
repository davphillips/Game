package com.davphillips.game.mini.poker;

public class BitPokerPlayer {

	private int luckPoints;
	
	public BitPokerPlayer()
	{
		this.setLuckPoints(0);
	}

	public int getLuckPoints() 
	{
		return luckPoints;
	}

	public final void setLuckPoints(int luckPoints) 
	{
		this.luckPoints = luckPoints;
	}
}
