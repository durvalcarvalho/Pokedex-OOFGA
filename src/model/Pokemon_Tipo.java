package model;

import java.io.IOException;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import view.Tela_Carregando;

// Esta classe herda os métodos de requisição e parse da classe Request
// Esta classe implementa uma Thread para gerar uma lista de nomes de pokemons de um mesmo tipo
public class Pokemon_Tipo extends RequestClass implements Runnable
{	
	private Vector<String> SameType_Pokemons = null;	
	private String tipo;
	
	public Pokemon_Tipo() {}
	
	// Método que inicia a thread com as requisições dos pokemons de determinado tipo
	public Vector<String> Pokemon_type(String tipo)
	{
		// Atributo que será usado na thread, uma vez que não é possível passar como parâmetro
		this.tipo = tipo;
		
		// Thread criada
		Thread  carregando = new Thread(this);
		
		// Inicialização da thread
		carregando.start();
		
		// Enquanto não terminar não o vector de string não é retornado
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
			// Criação de um JSONArray com os dados da url 
			String data = get("https://pokeapi.co/api/v2/type/"+tipo);
			JSONObject root = parse(data);
			JSONArray root_types = root.getJSONArray("pokemon");
			
			SameType_Pokemons = new Vector<String>();
			
			// Tela de carregamento é aberta
			Tela_Carregando tc = new Tela_Carregando();
			
			// Para cada pokemon do JSONArray
			for(int i=0; i<root_types.length(); i++)
			{
				// Atualizar o progresso do loop na tela de carregamento
				int progress = (int) (i*100.00 / root_types.length()); 
				tc.setPorcentagem(progress);
												
				// É criado um pokemon com a url obtida e é adcionado no vector de pokemons
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
