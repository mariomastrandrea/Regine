package it.polito.tdp.regine.model;

import java.util.HashSet;
import java.util.Set;

public class MyRegineModel
{
	/**
	 * int[] represents a queens combination: each index represents a column; 
	 * each value(int) represents the row where a queen it's placed
	 */
	public Set<Integer[]> computeCombinations(int dim)
	{
		Set<Integer[]> combinations = new HashSet<>();
		Integer[] partialSolution = new Integer[dim];
		int level = 0;
		
		recursiveComputation(dim, partialSolution, level, combinations);
		
		return combinations;
	}
	
	/**
	 * it computes recursively all queens combinations in a dim x dim chessboard
	 * @param dim				chessboard dimension
	 * @param partialSolution	partial combination of the current method call
	 * @param level				recursion level (num of elements added to the current partial solution array)
	 * @param combinations		all combinations found so far
	 */
	private void recursiveComputation(int dim, Integer[] partialSolution, int level, Set<Integer[]> combinations)
	{
		//terminal case
		if(level == dim)
		{
			combinations.add(partialSolution.clone());
			return;
		}
		//else
		for(int row=0; row<dim; row++)
		{
			if(rowAlreadyOccupied(row, partialSolution) || 
			   diagonal1AlreadyOccupied(row, level, partialSolution) ||
			   diagonal2AlreadyOccupied(row, level, partialSolution) )
			{
				continue;
			}
			
			partialSolution[level] = row;
			recursiveComputation(dim, partialSolution, level+1, combinations);
			partialSolution[level] = null; //backtracking
		}
	}
	
	
	private boolean rowAlreadyOccupied(Integer value, Integer[] array)
	{
		for(Integer i : array)
			if(i != null && value.equals(i))
				return true;
		
		return false;
	}
	
	// -1 angular coefficient
	private boolean diagonal1AlreadyOccupied(int row, int col, Integer[] array)
	{
		int dim = array.length;
		
		for(int j=0; j<dim; j++)	//columns
		{
			Integer i = array[j];
			if(i != null)
				if(col - row == j - i)	//diagonal already occupied
					return true;
		}
		return false;
	}
	
	// 1 angular coefficient
	private boolean diagonal2AlreadyOccupied(int row, int col, Integer[] array)
	{
		int dim = array.length;
		
		for(int j=0; j<dim; j++)	//columns
		{
			Integer i = array[j];
			if(i != null)
				if(col + row == j + i)	//diagonal already occupied
					return true;
		}
		return false;
	}
	

	
}
