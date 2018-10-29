package model;

import org.apache.http.client.fluent.Request;
import org.json.JSONObject;

public abstract class RequestClass
{
	protected String nome;
	protected int ID;
	
	public String getNome() { return nome; }
	public int getID() { return ID; }
	
	public String toString()
	{
		String data = this.getClass().getSimpleName() + ": " +
				nome + "\nID: " + ID;
		
		return data;
	}
	
	/* Pegar dados relevantes do JSON gerado */
	protected JSONObject parse(String data)
	{
		try
		{
			JSONObject root = new JSONObject(data);
			
			return root;
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	/* Requisição Https */
	protected String get(String url)
	{
		String data = "";
		
		try
		{
			data = Request.Get(url).execute().returnContent().asString();
		}
		
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return data;
	}
	
	
	
	public void printInfo()
	{
		System.out.println(toString());
	}
}