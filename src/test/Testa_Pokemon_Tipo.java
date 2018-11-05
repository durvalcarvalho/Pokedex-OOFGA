package test;

import java.util.Vector;

import model.Pokemon;
import model.Pokemon_Tipo;

public class Testa_Pokemon_Tipo
{
	public static void main(String[] args)
	{
		long startTime = System.nanoTime();

		Pokemon_Tipo pt = new Pokemon_Tipo();
		
		Vector<String> a = pt.Pokemon_type("fire");
		
		for(int i=0; i<a.size(); i++)
		{
			System.out.println(a.get(i));
		}
		
		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;
		
		System.out.println(totalTime/1000000000 + " Segundos");
		
	}
	
	// 24 segundos
	// 24 segundos
	
	
	
}
