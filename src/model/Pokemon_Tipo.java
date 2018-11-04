package model;

import java.io.IOException;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Pokemon_Tipo extends RequestClass
{
	private Vector<String> SameType_Pokemons = new Vector<String>();

	public Pokemon_Tipo() {}
	
	public Vector<String> Pokemon_type(String tipo)
	{
		try
		{
			String data = get("https://pokeapi.co/api/v2/type/"+tipo);
			
			JSONObject root = parse(data);
			
			JSONArray root_types = root.getJSONArray("pokemon");
			
			for(int i=0; i<root_types.length(); i++)
			{				
				JSONObject url = root_types.getJSONObject(i)
						.getJSONObject("pokemon");
				
				SameType_Pokemons.addElement(new Pokemon(url).getNome());
			}
			
			return SameType_Pokemons;
		}
		
		catch (IllegalStateException | IOException | JSONException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	
}
