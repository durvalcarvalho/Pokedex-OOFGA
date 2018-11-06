package testes;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Vector;

import org.junit.jupiter.api.Test;

import model.Treinador;

class Test_Treinador
{
	@Test
	void testTreinador()
	{
		Treinador treinador = new Treinador();
		
		assertEquals(treinador.getNome(), "Ash Ketchum");
		assertEquals(treinador.getGenero(), "Masculino ♂");
		assertEquals(treinador.getIdade(), 10);
		assertEquals(treinador.getCidade(), "Pallet");
		assertEquals(treinador.getRegiao(), "Kanto");
		
	}

	@Test
	void testGetNome()
	{
		String nome = "Durval";
		Treinador treinador = new Treinador(nome, "Masculino", "Pallet", "Kanto", 22);
		
		assertEquals(treinador.getNome(), nome);
	}

	@Test
	void testGetGenero()
	{
		String genero = "Masculino";
		Treinador treinador = new Treinador("Durval", genero, "Pallet", "Kanto", 22);
		
		assertEquals(treinador.getGenero(), genero);
	}

	@Test
	void testGetCidade()
	{
		String cidade = "Pallet";
		Treinador treinador = new Treinador("Durval", "Masculino", cidade, "Kanto", 22);
		
		assertEquals(treinador.getCidade(), cidade);
	}

	@Test
	void testGetRegiao()
	{
		String regiao = "Kanto";
		Treinador treinador = new Treinador("Durval", "Masculino", "Pallet", regiao, 22);
		
		assertEquals(treinador.getRegiao(), regiao);
	}

	@Test
	void testGetIdade()
	{
		int idade = 10;
		Treinador treinador = new Treinador("Durval", "Masculino", "Pallet", "Kanto", idade);
		
		assertEquals(treinador.getIdade(), idade);
	}

	@Test
	void testSet_nomes_pokemons()
	{
		Vector<String> nome_pokemons = new Vector<String>();
		nome_pokemons.addElement("mew");
		nome_pokemons.addElement("mewtwo");
		nome_pokemons.addElement("lapras");
		nome_pokemons.addElement("pichu");
		
		Treinador treinador = new Treinador();
		treinador.set_nomes_pokemons(nome_pokemons);
		
		assertEquals(treinador.get_nomes_pokemons(), nome_pokemons);
	}

	@Test
	void testGet_nomes_pokemons()
	{
		Treinador treinador = new Treinador();
		assertTrue(treinador.get_nomes_pokemons().isEmpty());
	}

	@Test
	void testAddPokemon()
	{
		Treinador treinador = new Treinador();
		
		Vector<String> pok = new Vector<String>();
		pok.addElement("pichu");
		
		treinador.addPokemon("pichu");
		
		assertEquals(treinador.get_nomes_pokemons(), pok);
	}

}
