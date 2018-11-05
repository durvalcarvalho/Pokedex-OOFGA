package model;

import java.io.IOException;

import org.apache.http.client.HttpResponseException;
import org.apache.http.client.fluent.Request;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A classe RequestClass implementa os métodos de requisição https.
 *
 * Toda classe que herdar da RequestClass pode usar os métodos get e parse
 * para coletar dados da API pokemon.
 */
public abstract class RequestClass
{
	// Atriutos e métodos necessários para utilizar os métodos do pacote Apache
	protected String nome;
	protected int ID;
	public String getNome() { return nome; 	}
	public int getID() 		{ return ID; 	}
	
	
	// Pegar String com todos os dados do pokemon e retornar em um JSONObject
	protected JSONObject parse(String data) throws JSONException
	{
		JSONObject root = new JSONObject(data);
		
		return root;
	}
	
	
	/* Fazer a requisição Https e retornar os dados coletados em uma string */
	protected String get(String url)  throws HttpResponseException, IOException, IllegalStateException
	{	
		String data = Request.Get(url).execute().returnContent().asString();
	
		return data;
	}
}