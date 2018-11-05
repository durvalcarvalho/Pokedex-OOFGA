package model;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.Vector;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import view.Tela_Carregando;

/** 
 * Essa classe foi pensada para criar os objetos pokemons de acordo
 * com o "nome" , "id" ou pelo "url" do pokemon na API
 * 
 * Os métodos get e parse foram herdados da classe RequestClass
 * onde está implementado os métodos de requisição https
 */
public class Pokemon extends RequestClass
{
	private String nome;
	private int Poke_id, hp, att, def, spAtt, spDef, speed;
	private double altura, peso;
	private Vector<String> tipo_pokemon;

	// Este atributo guarda dados do pokemon para serem processados
	// quando solicitado com a finalidade de diminuir o tempo de
	// criação de uma lista de pokemons de um determinado tipo
	private JSONObject root;

	// Este atributo guarda um conjunto de pares de string, referentes
	// ao nome de uma habilidade e sua respectiva descrição
	private Vector<AbstractMap.SimpleImmutableEntry
	<String, String>> habilidades = null;

	
	// Construtor utilizado para criar um pokemon com seu id
	public Pokemon(int id) throws IllegalStateException, IOException, JSONException
	{
		String data = "";

		data = get("https://pokeapi.co/api/v2/pokemon/" + id);
		
		init_pokemon(data);
	}

	// Construtor utilizado para criar um pokemon com sua url
	public Pokemon(JSONObject url) throws JSONException, IllegalStateException, IOException
	{
		String data = "";

		data = get(url.getString("url"));

		init_pokemon(data);
	}

	// Construtor utilizado para criar um pokemon com seu nome
	public Pokemon(String nome) throws JSONException, IllegalStateException, IOException
	{
		String data = "";
		
		data = get("https://pokeapi.co/api/v2/pokemon/" + nome);

		init_pokemon(data);
	}

	// Inicializador em comum de todos os contrutores diferentes
	private void init_pokemon(String data) throws JSONException
	{
		// Se a string data estiver vazio é criado um pokemon vazio
		if(data.equals(""))
		{
			nome = "";
			Poke_id = hp = att = def = spAtt = spDef = speed = 0;
			altura = peso = 0.0;
			habilidades = null;
		}

		else
		{		
			this.root = parse(data);

			Poke_id = root.getInt("id");
			nome = root.getString("name");
			altura = root.getInt("height") / 10.0; 	// metros
			peso = root.getInt("weight") / 10.0;	// Kilogramas

			/* Carrega atributos hp, att, def,
			 * spAtt, spDef, speed */
			load_stats(root);

			/* Carrega o tipos do pokemon */
			load_types(root);
		}
	}

	// Método para carregar todos as habilidades do pokemon
	public void load_abilities()
	{		
		// vector de habilidades, pares (habilidade, descrição da habilidade)
		habilidades = new Vector< AbstractMap.SimpleImmutableEntry<String, String> >();

		JSONArray moves = null;
		
		try 
		{
			// É criado um JSONArray com todas habilidades
			moves = root.getJSONArray("moves");
		}
		
		catch (JSONException e)
		{
			e.printStackTrace();
		}
		
		// É aberto uma tela de loading
		Tela_Carregando tc = new Tela_Carregando();

		// Para cada habilidade do JSONArray
		for(int i=0; i<moves.length(); i++)
		{
			// Método para atualizar o progesso do loop na tela de loading
			int progess = (int) (i*100.00/moves.length());
			tc.setPorcentagem(progess);
			
			String move;
			try
			{
				// Nome da habilidade
				move = moves.getJSONObject(i).getJSONObject("move")
						.getString("name");

				// url para a descrição da habilidade
				String url = moves.getJSONObject(i)
						.getJSONObject("move").getString("url");

				// Pego toda informação que estiver na url em forma de string
				String data = get(url);
				
				// Interpreto a string 'data' com um JSON Object
				JSONObject root_move = parse(data);
				
				// Pego a descrição da habilidade
				String move_descrtion = root_move
						.getJSONArray("flavor_text_entries")
						.getJSONObject(2).getString("flavor_text")
						.replace("\n", "");

				// Adiciono a nova habilidade no vector de habilidades
				habilidades.add(new SimpleImmutableEntry<String, String>
				(move, move_descrtion));
			}

			catch (JSONException | IllegalStateException | IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	/* Este método busca os tipos do pokemon do JSONObject */
	private void load_types(JSONObject root) throws JSONException
	{
		tipo_pokemon = new Vector<String>();
		
		JSONArray aux = root.getJSONArray("types");
		
		// Para do pokemon ter mais de um tipo "<eletric, flying>"
		for(int i = 0; i < aux.length(); i++)
		{
			String tipo = aux.getJSONObject(i).getJSONObject("type")
					.getString("name");
			
			tipo_pokemon.add(tipo);
		}
	}

	/* Este método busca os stats do pokemon do JSONObject */
	private void load_stats(JSONObject root) throws JSONException
	{
		/* Filtrar e salvar stats do pokemon */
		JSONArray aux = root.getJSONArray("stats");

		// Os stats estão em um JSONArray e em uma ordem definida
		for(int i=0; i<aux.length(); i++)
		{
			// Pego o valor do JSONArray
			int laux = aux.getJSONObject(i).getInt("base_stat");

			// Interpreto onde deve ser salvo
			switch(i)
			{
				case 0: speed = laux; break;
				case 1: spDef = laux; break;
				case 2: spAtt = laux; break;
				case 3: def   = laux; break;
				case 4: att   = laux; break;
				case 5: hp    = laux; break;
			}
		}
	}

	/* ID */
	public int getPoke_Id() { return Poke_id; }

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

	/* Lista de habilidades (pares: {habiliadde, descrição}) */
	public Vector<AbstractMap.SimpleImmutableEntry<String, String>>
	getHabilidades() { return habilidades; }
}