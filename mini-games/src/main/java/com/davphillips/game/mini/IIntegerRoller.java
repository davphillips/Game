package com.davphillips.game.mini;

import java.util.List;

public interface IIntegerRoller {
	
	public Integer rollSingle();
	
	public List<Integer> rollMultiple(int numRolls);
}
