package model;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Vector;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Pokemon extends RequestClass
{
	private String nome;
	private int id, hp, att, def, spAtt, spDef, speed;
	private double altura, peso;
	
	private Vector<String> tipo_pokemon;
	
	// String Pair -> Nome da habilidade, descrição
	private Vector<AbstractMap.SimpleImmutableEntry<String, String>> habilidades;
	
	// CONSTRUTORES
	public Pokemon() { init_pokemon(""); }
	
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
		}
		
		init_pokemon(data);
	}
	
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
	
	// INICIALIZADOR
	public void init_pokemon(String data)
	{
		if(data.equals(""))
		{
			nome = "";
			id = hp = att = def = spAtt = spDef = speed = 0;
			altura = peso = 0.0;
			habilidades = null;
		}
		
		else
		{		
			JSONObject root = parse(data);
			
			try
			{
				id = root.getInt("id");
				nome = root.getString("name");
				altura = root.getInt("height") * 10;
				peso = root.getInt("weight");
				
				/* Carrega hp, att, def, spAtt, spDef, speed */
				load_stats(root);
				
				/* Carrega o tipos do pokemon */
				load_types(root);
				
				/* Carrega as habilidades e descrições */
				load_abilities(root);
			}
			
			catch (JSONException e)
			{
				e.printStackTrace();
			}
		}
	}

	/* Carrega as habilidades e descrições */
	private void load_abilities(JSONObject root) throws JSONException
	{
		habilidades = new Vector<AbstractMap.SimpleImmutableEntry
				<String, String>>();
		
		JSONArray moves = root.getJSONArray("moves");
		for(int i=0; i<moves.length(); i++)
		{
			// Nome da habilidade
			String move = moves.getJSONObject(i).getJSONObject("move")
					.getString("name");
			
			// Descrição da habilidade
			String url = moves.getJSONObject(i)
					.getJSONObject("move").getString("url");
			
			String data = get(url);
			
			JSONObject root_move = parse(data);
			
			JSONArray description = root_move
					.getJSONArray("flavor_text_entries");
			
			String move_descrtion = description.getJSONObject(2)
					.getString("flavor_text");
			
			move_descrtion = move_descrtion.replace("\n", "");
			
			// Par: habilidade, descrição
			habilidades.add(new SimpleImmutableEntry<String, String>
			(move, move_descrtion));
		}
		
	}

	/* Carrega o tipos do pokemon */
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

	/* Carrega hp, att, def, spAtt, spDef, speed */
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

	/* GETTERS & SETTERS */
	/* ID */
	public int getId() 			{ return id; 	}
	public void setId(int id) 	{ this.id = id; }

	/* Nome */
	public String getNome() 			{ return nome; 		}
	public void setNome(String nome) 	{ this.nome = nome; }
	
	/* HP */
	public int getHp() 			{ return hp; 	}
	public void setHp(int hp) 	{ this.hp = hp; }

	/* Ataque */
	public int getAtt() 		{ return att; 		}
	public void setAtt(int att) { this.att = att; 	}

	/* Defesa */
	public int getDef() 		{ return def; 		}
	public void setDef(int def) { this.def = def; 	}
	
	/* Velocidade do Ataque */
	public int getSpAtt() 			{ return spAtt; 		}
	public void setSpAtt(int spAtt) { this.spAtt = spAtt; 	}

	/* Velocidade da Defesa */
	public int getSpDef() 			{ return spDef; 		}
	public void setSpDef(int spDef) { this.spDef = spDef; 	}

	/* Velocidade */
	public int getSpeed() 			{ return speed; 		}
	public void setSpeed(int speed) { this.speed = speed; 	}

	/* Altura */
	public double getAltura() 				{ return altura; 		}
	public void setAltura(double altura) 	{ this.altura = altura; }

	/* Peso */
	public double getPeso() 			{ return peso; 		}
	public void setPeso(double peso) 	{ this.peso = peso; }
	
	/* Tipo do Pokemon */
	public Vector<String> getTipo_pokemon() { return tipo_pokemon; }
	public void setTipo_pokemon(Vector<String> tipo_pokemon)
	{
		this.tipo_pokemon = tipo_pokemon;
	}

	/* Lista de habilidades (pares: [habiliadde, descrição] ) */
	public Vector<AbstractMap.SimpleImmutableEntry<String, String>> getHabilidades() {
		return habilidades;
	}

	/* Adicionar habilidade na lista de habilidades */
	public void addHabilidades( AbstractMap.SimpleImmutableEntry<String, String> habilidade) {
		this.habilidades.addElement(habilidade);
	}
}