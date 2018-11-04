package testes;

import java.io.IOException;

import org.apache.http.client.HttpResponseException;
import org.json.JSONException;

import model.Pokemon;

public class Testa_Pokemon
{

	public static void main(String[] args)
	{
		// testes pokemon
		
		// Pelo iD
		
		//System.out.println("Prra!");
		
		Pokemon p1;
		
		try
		{
			p1 = new Pokemon("durval");
		}
		catch (HttpResponseException e)
		{
			System.out.println("Pokemon não achado!");
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		//System.out.println(p1.getPoke_Id());
		
		/*
		// Pelo nome
		Pokemon p2 = new Pokemon("bulbasaur");
		System.out.println(p2);
		*/
	}
}