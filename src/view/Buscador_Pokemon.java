package view;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.http.client.HttpResponseException;
import org.json.JSONException;

import model.Pokemon;
import model.Pokemon_Tipo;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Choice;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Color;

@SuppressWarnings("serial")
/**
 * Essa classe implementa um JDialog que põe em espera a JFrame que a chamou
 * 
 * Este JDialog serve para realizar buscas de acordo com o nome ou tipo na Pokeapi
 */
public class Buscador_Pokemon extends JDialog
{
	// Estes atributos são utilizados pelos métodos de atualização da tela
	private JLabel Pokemon_Img;
	private JLabel lblPokemonNoEncontrado;
	private Choice Pokemon_achados_choice;
	private Vector<String> poke_names = new Vector<String>();
	private Pokemon atual_pokemon = null;
	private boolean Captura_Pokemon = false;
	
	// Método para verificar se o botão capturar pokemon foi apertado
	public boolean getCaptura() { return Captura_Pokemon; }

	public Buscador_Pokemon()
	{
		// O escopo onde foi chamado esse Buscador_Pokemon ficará
		// aguardando enquanto esse JDialog estiver aberto
		setModal(true);

		// Configurações da tela principal
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(350, 150, 650, 500);
		JPanel Buscador_Panel = new JPanel();
		Buscador_Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Buscador_Panel);
		Buscador_Panel.setLayout(null);

		// label "procurar pokemon"
		JLabel ProcurarPokemon_lbl = new JLabel("Procurar Pokemon");
		ProcurarPokemon_lbl.setFont(new Font("Ubuntu Mono",
				Font.BOLD, 20));
		ProcurarPokemon_lbl.setBounds(230, 28, 168, 20);
		Buscador_Panel.add(ProcurarPokemon_lbl);

		// Choice que irá mostrar os tipos de pokemons existentes
		Choice Pokemon_choice_Tipo = new Choice();
		Pokemon_choice_Tipo.setBounds(100, 110, 430, 20);
		Buscador_Panel.add(Pokemon_choice_Tipo);

		// tipos de pokemons conhecidos
		String[] tipos_conhecidos = {"normal","fighting","flying",
				"poison","ground","rock","bug","ghost","steel",
				"fire","water","grass","electric","psychic","ice",
				"dragon","dark","fairy"};

		// Loop para adicionar os tipos conhecidos no choice
		for(int i=0; i<tipos_conhecidos.length; i++)
		{
			Pokemon_choice_Tipo.add(tipos_conhecidos[i].toUpperCase());
		}

		// label "tipo"
		JLabel Tipo_lbl = new JLabel("Tipo");
		Tipo_lbl.setBounds(300, 80, 30, 15);
		Buscador_Panel.add(Tipo_lbl);

		// label "nome"
		JLabel Nome_lbl = new JLabel("Nome");
		Nome_lbl.setBounds(300, 150, 40, 15);
		Buscador_Panel.add(Nome_lbl);

		// textfield que irá receber o nome do pokemon desejado
		JTextField Pokemon_Nome_textField = new JTextField();
		Pokemon_Nome_textField.setBounds(100, 180, 430, 19);
		Buscador_Panel.add(Pokemon_Nome_textField);
		Pokemon_Nome_textField.setColumns(10);
		
		// Esse é texto é acionado quando o pokemon não é encontrado
		lblPokemonNoEncontrado = new JLabel("Pokemon Não Encontrado! :(");
		lblPokemonNoEncontrado.setForeground(Color.BLUE);
		lblPokemonNoEncontrado.setBounds(350, 270, 205, 15);
		Buscador_Panel.add(lblPokemonNoEncontrado);
		lblPokemonNoEncontrado.setVisible(false); // default é desligado
		
		// label que irá receber a imagem do pokemon
		Pokemon_Img = new JLabel();
		Pokemon_Img.setIcon(new ImageIcon(Pokedex.class.getResource("/view/Imagens/0.png")));
		Pokemon_Img.setBounds(420, 310, 120, 120);
		Buscador_Panel.add(Pokemon_Img);
		
		// choice que irá receber os nomes dos pokemons filtrados
		Pokemon_achados_choice = new Choice();
		Pokemon_achados_choice.setBounds(100, 340, 200, 25);
		Buscador_Panel.add(Pokemon_achados_choice);
		
		/* BOTÕES */

		// Este botão irá buscar um pokemon ou um conjunto de pokemons de um determinado tipo
		JButton BuscarPokemon_btn = new JButton("Buscar Pokemon");
		BuscarPokemon_btn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// O texto de erro é 'desligada' caso tenha sido acionada em buscas anteriores
				lblPokemonNoEncontrado.setVisible(false);
				
				// Trocar a imagem de erro para a imagem padrão 
				Pokemon_Img.setIcon(new ImageIcon(Pokedex.class
						.getResource("/view/Imagens/0.png")));
				
				// recebo o que foi digitado no textField
				String poke_textField = Pokemon_Nome_textField.getText();

				// Se o campo de texto estiver vazio é feito uma pesquisa por tipo
				if(poke_textField.isEmpty())
				{
					// Pego o tipo selecionado no campo choice
					String tipo = Pokemon_choice_Tipo.getSelectedItem().toLowerCase();					
					
					// É criado um thread para realizar a busca em paralelo
					Thread t2 = new Thread()
					{
						public void run()
						{	
							// Remover do "log" pesquisas anteriores
							poke_names.clear();
							
							// Classe que realiza a busca por tipo
							Pokemon_Tipo pt = new Pokemon_Tipo();
							
							// Método que realiza as requisições dos pokemons de um determinado tipo
							poke_names = pt.Pokemon_type(tipo);
							
							// Método para mostrar os pokemons filtrados por tipo
							choice_pokemons();
							
							// trocar imagem para o primeiro pokemon do choice
							changeImage(Pokemon_achados_choice.getSelectedItem());
						}	
					};
					
					// inicializar a thread
					t2.start();
				}

				// Caso tenha alguma coisa digitada, buscar pelo nome do pokemon
				else
				{
					try
					{
						// Tentar criar um pokemon com o texto digitado
						Pokemon pokemon = new Pokemon(poke_textField);
						
						// Remover do "log" as pesquisas anteriores
						poke_names.clear();
						
						// Adicionar o nome do pokemon pesquisado no log
						poke_names.add(pokemon.getNome());
						
						// atualizar o choice de pokemons
						choice_pokemons();
						
						// Mudar a imagem do pokemon
						changeImage(pokemon.getNome());
						
						// Remover texto digitado no textField
						Pokemon_Nome_textField.setText("");						
					}
					
					// Caso o nome digitado não seja válido entra nesta exceção
					catch (HttpResponseException e1)
					{
						// Texto de aviso que pokemon não foi encontrado
						lblPokemonNoEncontrado.setVisible(true);
						
						// Imagem de apoio - Pokemon não encontrado
						Pokemon_Img.setIcon(new ImageIcon(Pokedex.class.getResource("/view/Imagens/not_found.png")));
					}
					
					catch (IllegalStateException | JSONException
							| IOException e1)
					{
						e1.printStackTrace();
					}
				}
			}
		});
		BuscarPokemon_btn.setBounds(240, 230, 160, 25);
		Buscador_Panel.add(BuscarPokemon_btn);		

		// Este botão verifica se algum pokemon foi selecionado
		// Se tiver sido, retorna para pokedex, caso contrário não faz nada
		JButton Capturar_Pokemon_button = new JButton("Capturar Pokemon");
		Capturar_Pokemon_button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Se algum pokemon estiver selecionado, retorno para pokedex
				if(!(atual_pokemon == null))
				{
					Captura_Pokemon = true;
					dispose();
				}
			}
		});
		Capturar_Pokemon_button.setBounds(230, 440, 190, 25);
		Buscador_Panel.add(Capturar_Pokemon_button);
		
		// botão para criar imagem do pokemon selecionado do choice
		JButton show_pokemon_btn = new JButton("Visualizar");
		show_pokemon_btn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Se a imagem erro estiver acionada é desligada
				lblPokemonNoEncontrado.setVisible(false);
				
				// Se alguma busca tiver sido feita, é chamado o método para mudar a imagem
				if(!(poke_names.isEmpty()))
				{
					changeImage(Pokemon_achados_choice.getSelectedItem());
				}
			}
		});
		show_pokemon_btn.setBounds(140, 310, 120, 25);
		Buscador_Panel.add(show_pokemon_btn);	
		
		// Após contruir todos os componentes da tela, esta fica visível
		setVisible(true);
	}
	
	// Método para mostrar os pokemons filtrados por tipo
	private void choice_pokemons()
	{
		// Toda vez que esse método é acionado é resetado a lista
		// de pokemons de buscas anteriores
		Pokemon_achados_choice.removeAll();
		
		for(int i=0; i<poke_names.size(); i++)
		{
			Pokemon_achados_choice.add(poke_names.elementAt(i));
		}	
	}
	
	// Método para mudar a imagem de acordo com o nome do pokemon
	private void changeImage(String pokemon_name)
	{
		int id;

		try
		{
			// O pokemon atual é atualizado
			atual_pokemon = new Pokemon(pokemon_name);
			
			// é criado o link para a imagem do pokemon desejado
			id = (atual_pokemon.getPoke_Id());
			URL url = new URL("https://raw.githubusercontent.com"
					+ "/PokeAPI/sprites/master/sprites/pokemon/"
					+id+".png");

			// É pegado a imagem
			BufferedImage image = ImageIO.read(url);

			// A imagem do JDialog é atualizado
			Pokemon_Img.setIcon(new ImageIcon(image));
		}
		
		// Se algum erro ocorrer é mostrado uma imagem de erro
		catch (IllegalStateException | JSONException | IOException e)
		{
			// Texto de aviso que pokemon não foi encontrado
			lblPokemonNoEncontrado.setVisible(true);
			
			// Imagem de apoio - Pokemon não encontrado
			Pokemon_Img.setIcon(new ImageIcon
					(Pokedex.class.getResource
							("/view/Imagens/not_found.png")));
		}
	}
	
	// Método para retornar o ultimo pokemon (atual_pokemon)
	public String getPokemon()
	{
		if(atual_pokemon != null)
			return atual_pokemon.getNome();
		else
			return null;
	}
}
