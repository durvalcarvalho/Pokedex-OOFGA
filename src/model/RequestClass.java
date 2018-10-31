package model;

import java.io.IOException;
import org.apache.http.client.fluent.Request;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A classe RequestClass implementa os métodos de requisição https.
 *
 * Toda classe filha da RequestClass pode usar os métodos get e parse
 * para coletar dados da API pokemon.
 */
public abstract class RequestClass
{
	protected String nome; 	// nomed pokemon
	protected int ID;		// id do pokemon
	
	// TODO: Conferir se está sendo usado e remover
	public String getNome() { return nome; 	}
	public int getID() 		{ return ID; 	}
	
	
	/* Pegar String com todos os dados do pokemon e retornar em um JSONObject */
	protected JSONObject parse(String data)
	{
		JSONObject root;
		
		try
		{
			root = new JSONObject(data);
			return root;
		}
		catch (JSONException e)
		{
			// TODO: Tratar este erro!
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	/* Fazer a requisição Https e retornar os dados coletados em uma string */
	protected String get(String url)
	{
		String data = "";
		
		try
		{
			data = Request.Get(url).execute().returnContent().asString();
		}
		
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		catch(IllegalStateException e)
		{
			//TODO: Tratar error: URL INVÁLIDA
			e.printStackTrace(); 
		}
		
		return data;
	}
}
