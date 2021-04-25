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
		
		Set<Integer[]> queensCombinations = model.computeCombinations(8);
		
		System.out.println(print(queensCombinations));
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
	
	public static boolean areTheSame(Integer[] array, List<Integer> list)
	{
		if(array.length != list.size())
			return false;
		
		for(int i=0; i<list.size(); i++)
			if(array[i] == null || !list.get(i).equals(array[i]))
				return false;
		
		return true;
	}

}
