package model;

import java.io.IOException;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import view.Tela_Carregando;

public class Pokemon_Tipo extends RequestClass implements Runnable
{
	private Vector<String> SameType_Pokemons = null;
	
	private String tipo;

	public Pokemon_Tipo() {}
	
	public Vector<String> Pokemon_type(String tipo)
	{
		this.tipo = tipo;
		
		Thread  carregando = new Thread(this);
		
		carregando.start();
		
		// Enquanto não terminar de carregar!
		try { carregando.join(); } catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		return SameType_Pokemons;
		
	}
	
	public void run()
	{
		
		try
		{
			String data = get("https://pokeapi.co/api/v2/type/"+tipo);
			
			JSONObject root = parse(data);
			
			JSONArray root_types = root.getJSONArray("pokemon");
			
			SameType_Pokemons = new Vector<String>();
			
			Tela_Carregando tc = new Tela_Carregando();
			
			for(int i=0; i<root_types.length(); i++)
			{
				int progress = (int) (i*100.00 / root_types.length()); 
				tc.setPorcentagem(progress);
				
				int aux = (int) i*100/root_types.length();
												
				JSONObject url = root_types.getJSONObject(i).getJSONObject("pokemon");
				
				SameType_Pokemons.addElement(new Pokemon(url).getNome());
			}
		}
		
		catch (IllegalStateException | IOException | JSONException e)
		{
			e.printStackTrace();
		}
	}
	
	
}
