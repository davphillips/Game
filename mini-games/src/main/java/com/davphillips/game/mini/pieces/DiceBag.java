package com.davphillips.game.mini.pieces;

import java.util.ArrayList;
import java.util.List;

public class DiceBag 
{
	private List< Die > dice;
	
	public DiceBag()
	{
		this.setDice( new ArrayList< Die >() );
	}
	
	public DiceBag(Die die)
	{
		this();
		this.addDie(die);
	}
	
	public DiceBag(List< Die > dice)
	{
		this();
		this.addDie(dice);
	}
	
	public DiceBag(Integer n, Integer minValue, Integer maxValue)
	{
		this();

		for(int i = 0; i < n; i++)
		{
			this.addDie(new Die(minValue, maxValue));
		}
	}
	
	private List< Die > getDice()
	{
		return this.dice;
	}
	
	private final void setDice(List< Die > dice)
	{
		this.dice = dice;
	}
	
	public Die getDie(int index)
	{
		return this.getDice().get(index);
	}
	
	public int getNumDice()
	{
		return this.getDice().size();
	}
	
	public final void addDie(Die die)
	{
		this.getDice().add(die);
	}
	
	public final void addDie(List< Die > die)
	{
		this.getDice().addAll(die);
	}
	
	public final void addDie(DiceBag die)
	{
		this.getDice().addAll(die.getDice());
	}
	
	public List< Integer > rollAll()
	{
		List< Integer > rolls = new ArrayList< Integer >();
		
		for( int i = 0; i < this.getNumDice(); i++ )
		{
			rolls.add(this.getDie(i).rollSingle());
		}
		
		return rolls;
	}
}
