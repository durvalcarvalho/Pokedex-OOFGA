package model;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/** 
 * Essa classe foi pensada para criar os objetos pokemons de acordo
 * com o "nome" , "id" ou pelo "url" do pokemon na API
 * 
 * Os métodos get e parse foram herdados da classe RequestClass
 * onde está implementado os métodos de requisição https
 */
public class Pokemon extends RequestClass
{
	/* ATRIBUTOS */
	//TODO: Adicionar atributo imagem
	private String nome;
	private int id, hp, att, def, spAtt, spDef, speed;
	private double altura, peso;
	private Vector<String> tipo_pokemon;
	
	// Este atributo serve para carregar as habilidades do pokemon
	private JSONObject root;
	
	// Um vector com todos as habilidades do pokemon 
	// (um par de string -> nome da habilidade, descrição)
	private Vector<AbstractMap.SimpleImmutableEntry
	<String, String>> habilidades;
	
	
	/* CONSTRUTORES */
	
	// Procurar um pokemon de acordo com seu ID
	public Pokemon(int id)
	{
		String data = "";
		
		try
		{
			data = get("https://pokeapi.co/api/v2/pokemon/" + id);
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
			// TODO: Tratar Erro 
			
		}
		init_pokemon(data);
	}
	
	public Pokemon(JSONObject url)
	{
		String data = "";
		
		try
		{
			data = get(url.getString("url"));
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
			// TODO: Tratar Erro 
			
		}
		
		init_pokemon(data);
	}
	
	// Procurar um pokemon de acordo com seu nome
	public Pokemon(String nome)
	{
		String data = "";
		
		try
		{
			data = get("https://pokeapi.co/api/v2/pokemon/" + nome);
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
		
		init_pokemon(data);
	}
	
	
	// MÉTODOS PRIVADOS

	//TODO: Adicionar método para baixar imagem do pokemon
	
	// Método para criar um pokemon com os dados retornados da API
	private void init_pokemon(String data)
	{
		// Se a string data estiver vazio é criado um pokemon vazio
		if(data.equals(""))
		{
			nome = "";
			id = hp = att = def = spAtt = spDef = speed = 0;
			altura = peso = 0.0;
			habilidades = null;
		}
		
		else
		{		
			this.root = parse(data);
			
			try
			{
				id = root.getInt("id");
				nome = root.getString("name");
				altura = root.getInt("height") / 10.0; 	// metros
				peso = root.getInt("weight") / 10.0;	// Kilogramas
				
				/* Carrega atributos hp, att, def,
				 * spAtt, spDef, speed */
				load_stats(root);
				
				/* Carrega o tipos do pokemon */
				load_types(root);				
			}
			
			catch (JSONException e)
			{
				e.printStackTrace();
			}
			
			
		}
	}

	/* Carrega o nome e descrições das habilidades do pokemon */
	// Este método demora cerca de 20s
	public void load_abilities(JSONObject root) throws JSONException
	{		
		// vector de habilidades, pares (habilidade, descrição da habilidade)
		habilidades = new Vector<AbstractMap.SimpleImmutableEntry
				<String, String>>();
		
		JSONArray moves = root.getJSONArray("moves");
		
		// Este método demora cerca de 20s para completar
		for(int i=0; i<moves.length(); i++)
		{
			// Nome da habilidade
			String move = moves.getJSONObject(i).getJSONObject("move")
					.getString("name");	
			
			// url para a descrição da habilidade
			String url = moves.getJSONObject(i)
					.getJSONObject("move").getString("url");
			
			// Pego toda informação que estiver na url
			String data = get(url);
			
			// Interpreto a string 'data' com um Json Object
			JSONObject root_move = parse(data);
			
			// Descrição do habilidade
			String move_descrtion = root_move
					.getJSONArray("flavor_text_entries")
					.getJSONObject(2).getString("flavor_text")
					.replace("\n", "");
			
			// Adiciono a nova habilidade no vector de habilidades
			habilidades.add(new SimpleImmutableEntry<String, String>
			(move, move_descrtion));
		}
		
		/*
		long startTime = System.nanoTime();
		long endTime   = System.nanoTime();
		long totalTime = endTime - startTime;
		System.out.println(totalTime/1000000000 + " Segundos");
		*/
	}

	/* Este método pega os tipos do pokemon do json objecton */
	private void load_types(JSONObject root) throws JSONException
	{
		/* Filtrar e salvar o tipo do pokemon */
		tipo_pokemon = new Vector<String>();
		JSONArray aux = root.getJSONArray("types");
		for(int i = 0; i < aux.length(); i++)
		{
			String tipo = aux.getJSONObject(i).getJSONObject("type")
					.getString("name");
			tipo_pokemon.add(tipo);
		}
	}

	/* Este método pega os stats do pokemon do json objecton */
	private void load_stats(JSONObject root) throws JSONException
	{
		/* Filtrar e salvar stats do pokemon */
		JSONArray aux = root.getJSONArray("stats");
		
		for(int i=0; i<aux.length(); i++)
		{
			int laux = aux.getJSONObject(i).getInt("base_stat");
			
			switch(i)
			{
				case 0:
					speed = laux;
					break;
				case 1:
					spDef = laux;
					break;
				case 2:
					spAtt = laux;
					break;
				case 3:
					def = laux;
					break;
				case 4:
					att = laux;
					break;
				case 5:
					hp = laux;
					break;
			}
		}
	}
	
	// MÉTODOS PUBLICOS
	
	// Este método serve para utilizar o sout(pokemon)
	// System.out.println(pokemon); <-- Exemplo
	public String toString()
	{
		return ("Nome: " + this.getNome() +
				"\nID: " + this.getId());
	}
	
	/* GETTERS & SETTERS */
	
	/* ID */
	public int getId() { return id; }

	/* Nome */
	public String getNome() { return nome; }
	
	/* HP */
	public int getHp() { return hp; }

	/* Ataque */
	public int getAtt() { return att; }

	/* Defesa */
	public int getDef() { return def; }
	
	/* Velocidade do Ataque */
	public int getSpAtt() { return spAtt; }

	/* Velocidade da Defesa */
	public int getSpDef() { return spDef; }

	/* Velocidade */
	public int getSpeed() { return speed; }

	/* Altura */
	public double getAltura() { return altura; }

	/* Peso */
	public double getPeso() { return peso; }
	
	/* Tipo do Pokemon */
	public Vector<String> getTipo_pokemon() { return tipo_pokemon; }

	/* Lista de habilidades (pares: {habiliadde, descrição} ) */
	public Vector<AbstractMap.SimpleImmutableEntry<String, String>>
	getHabilidades() { return habilidades; }
}
