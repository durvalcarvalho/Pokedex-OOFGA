package model;

import java.util.Vector;

public class Treinador
{
	private String nome;
	private String genero;
	private String cidade;
	private String regiao;
	private int idade;
	
	Vector<String> nome_pokemons = new Vector<String>();

	public Treinador(String nome, String genero, 
		String cidade, String regiao, int idade) 
	{
		this.nome = nome;
		this.genero = genero;
		this.cidade = cidade;
		this.regiao = regiao;
		this.idade = idade;
	}
	
	// O construtor vazio cria o Ash
	public Treinador()
	{
		this.nome = "Ash Ketchum";
		this.idade = 10;
		this.genero = "Masculino ♂";
		this.cidade = "Pallet";
		this.regiao = "Kanto";
	}

	public String getNome() 			 { return nome; 		  }
	public String getGenero() 			 { return genero; 		  }
	public String getCidade() 			 { return cidade; 		  }
	public String getRegiao() 			 { return regiao; 		  }
	public int getIdade() 				 { return idade; 		  }
	
	public void set_nomes_pokemons(Vector<String> nome_pokemons)
	{
		this.nome_pokemons = nome_pokemons;
	}
	
	public Vector<String> get_nomes_pokemons() 
	{
		return this.nome_pokemons;	
	}

	public void addPokemon(String nome_pokemon) 
	{
		nome_pokemons.add(nome_pokemon);
	}
}