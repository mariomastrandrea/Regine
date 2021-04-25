package it.polito.tdp.regine.model;

import java.util.List;
import java.util.Set;

public class MyTestRegine
{

	public static void main(String[] args)
	{
		/*
		for(int i=0; i<8; i++)
		{
			for(int j=0; j<8; j++)
			{
				System.out.print("("+i+","+j+") ");
			}
			System.out.println();
		}
		
		Integer[] provaArray = new Integer[8];
		
		
		for(Integer i : provaArray)
		{
			System.out.println(i);
		}
		*/
		
		MyRegineModel model = new MyRegineModel();	
		Set<Integer[]> myQueensCombinations = model.computeCombinations(8);
		
		Regine otherModel = new Regine();
		List<Integer[]> otherCombinations = otherModel.risolvi(8);
		
		System.out.println(printAndCheck(myQueensCombinations, otherCombinations));
	}
	
	public static String print(Set<Integer[]> set)
	{
		StringBuilder sb = new StringBuilder();
		for(Integer[] array : set)
		{
			if(sb.length() > 0)
				sb.append("\n");
			
			StringBuilder row = new StringBuilder();
			for(Integer i : array)
			{
				if(row.length() > 0)
					row.append(", ");
				
				row.append(i);
			}
			row.insert(0,"[").append("]");
			
			sb.append(row);
		}
		
		return sb.toString();
	}
	
	public static String printAndCheck(Set<Integer[]> set, List<Integer[]> otherSolution)
	{
		StringBuilder sb = new StringBuilder();
		for(Integer[] array : set)
		{
			if(sb.length() > 0)
				sb.append("\n");
			
			StringBuilder row = new StringBuilder();
			for(Integer i : array)
			{
				if(row.length() > 0)
					row.append(", ");
				
				row.append(i);
			}
			row.insert(0,"[").append("]").append("  -->  ");
			
			if(existCombinationLike(array, otherSolution))
				row.append("exists");
			else
				row.append("DOES NOT EXIST");
			
			sb.append(row);
		}
		
		int mySolutionSize = set.size();
		sb.append("\n\nCount: ").append(mySolutionSize).append("  -->  ");
		
		if(mySolutionSize == otherSolution.size())
			sb.append("ok");
		else
			sb.append("ERROR!");
		
		return sb.toString();
	}
	
	public static boolean areTheSame(Integer[] myArray, Integer[] other)
	{
		if(myArray.length != other.length)
			return false;
		
		for(int col=0; col<myArray.length; col++)
		{
			Integer row = myArray[col];
			if(row == null)
				return false;
			
			if(!(row >= 0 && row < other.length))
				return false;
			
			Integer otherCol = other[row];
			
			if(otherCol == null)
				return false;
			
			if(col != otherCol)
				return false;
		}
		return true;
	}
	
	public static boolean existCombinationLike(Integer[] myCombination, List<Integer[]> list)
	{
		for(Integer[] otherCombination : list)
		{
			if(areTheSame(myCombination, otherCombination))
				return true;
		}
		return false;
	}

}
