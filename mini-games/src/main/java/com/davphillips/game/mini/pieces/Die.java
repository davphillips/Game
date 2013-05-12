package com.davphillips.game.mini.pieces;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.davphillips.game.mini.IIntegerRoller;

public class Die implements IIntegerRoller {

	private Integer minValue;
	private Integer maxValue;
	private Random generator;
	
	public Die(Integer minValue, Integer maxValue)
	{
		this.setGenerator(new Random());
		this.setMinValue(minValue);
		this.setMaxValue(maxValue);
	}
	
	private Random getGenerator()
	{
		return generator;
	}
	
	private final void setGenerator(Random generator)
	{
		this.generator = generator;
	}
	
	public Integer getMaxValue() 
	{	
		return maxValue;
	}

	public Integer getMinValue() 
	{	
		return minValue;
	}
	
	public final void setMaxValue(Integer maxValue) 
	{	
		this.maxValue = maxValue;
	}

	public final void setMinValue(Integer minValue) 
	{
		this.minValue = minValue;
	}
	
	public Integer rollSingle()
	{
		return new Integer(this.getMinValue() + this.getGenerator().nextInt(1 + this.getMaxValue()));
	}
	
	public List<Integer> rollMultiple(int numRolls) 
	{
		
		List<Integer> rolls = new ArrayList<Integer>();
		
		for(int i = 0; i < numRolls; i++)
		{
			rolls.add(this.rollSingle());
		}
		
		return rolls;
	}
}
