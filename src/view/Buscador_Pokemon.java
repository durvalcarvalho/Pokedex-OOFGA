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
public class Buscador_Pokemon extends JDialog
{
	// Elementos da tela
	private JPanel Buscador_Panel;
	private JTextField Pokemon_Nome_textField;
	private JLabel Pokemon_Img;
	private JLabel lblPokemonNoEncontrado;
	private Choice Pokemon_achados_choice;
	
	private Vector<String> poke_names = new Vector<String>();
	
	private Pokemon atual_pokemon = null;
	
	public String getPokemon()
	{
		if(atual_pokemon != null)
			return atual_pokemon.getNome();
		else
			return null;
	}
	
	// TODO: REMOVER DEPOIS
	public static void main(String[] args)
	{
		try
		{
			Buscador_Pokemon frame = new Buscador_Pokemon();
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public Buscador_Pokemon()
	{
		// O escopo onde foi chamado esse Buscador_Pokemon ficará
		// aguardando enquanto esse JDialog estiver aberto
		setModal(true);

		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(350, 150, 650, 500);
		Buscador_Panel = new JPanel();
		Buscador_Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Buscador_Panel);
		Buscador_Panel.setLayout(null);

		JLabel ProcurarPokemon_lbl = new JLabel("Procurar Pokemon");
		ProcurarPokemon_lbl.setFont(new Font("Ubuntu Mono",
				Font.BOLD, 20));
		ProcurarPokemon_lbl.setBounds(230, 28, 168, 20);
		Buscador_Panel.add(ProcurarPokemon_lbl);

		Choice Pokemon_choice_Tipo = new Choice();
		Pokemon_choice_Tipo.setBounds(100, 110, 430, 20);
		Buscador_Panel.add(Pokemon_choice_Tipo);

		String[] tipos_conhecidos = {"normal","fighting","flying",
				"poison","ground","rock","bug","ghost","steel",
				"fire","water","grass","electric","psychic","ice",
				"dragon","dark","fairy","shadow"};

		for(int i=0; i<tipos_conhecidos.length; i++)
		{
			Pokemon_choice_Tipo.add(tipos_conhecidos[i].toUpperCase());
		}

		JLabel Tipo_lbl = new JLabel("Tipo");
		Tipo_lbl.setBounds(300, 80, 30, 15);
		Buscador_Panel.add(Tipo_lbl);

		JLabel Nome_lbl = new JLabel("Nome");
		Nome_lbl.setBounds(300, 150, 40, 15);
		Buscador_Panel.add(Nome_lbl);

		Pokemon_Nome_textField = new JTextField();
		Pokemon_Nome_textField.setBounds(100, 180, 430, 19);
		Buscador_Panel.add(Pokemon_Nome_textField);
		Pokemon_Nome_textField.setColumns(10);
		
		// Esse é texto é acionado quando o pokemon não é encontrado
		lblPokemonNoEncontrado = new JLabel("Pokemon Não Encontrado! :(");
		lblPokemonNoEncontrado.setForeground(Color.BLUE);
		lblPokemonNoEncontrado.setBounds(350, 270, 205, 15);
		Buscador_Panel.add(lblPokemonNoEncontrado);
		lblPokemonNoEncontrado.setVisible(false); // default é desligado
		
		Pokemon_Img = new JLabel("Pokemon_Name");
		Pokemon_Img.setIcon(new ImageIcon(Pokedex.class.getResource("/view/Imagens/0.png")));
		Pokemon_Img.setBounds(400, 290, 100, 100);
		Buscador_Panel.add(Pokemon_Img);
		
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
				// O texto de erro é 'desligada' caso tenha sido
				// acionada em buscas anteriores
				lblPokemonNoEncontrado.setVisible(false);
				
				// Trocar a imagem de erro para a imagem padrão 
				Pokemon_Img.setIcon(new ImageIcon(Pokedex.class
						.getResource("/view/Imagens/0.png")));
				
				// Texto digitado no campo de texto
				String poke_textField = Pokemon_Nome_textField
						.getText();

				// Se o campo de texto estiver vazio é feito uma pesquisa por tipo
				if(poke_textField.isEmpty())
				{
					String tipo = Pokemon_choice_Tipo.getSelectedItem();
					
					// Remover do "log" pesquisas anteriores
					poke_names.clear();
					
					// Classe que realiza a busca por tipo
					Pokemon_Tipo pt = new Pokemon_Tipo();
					
					// Método que realiza as requisições dos pokemons de um determinado tipo
					poke_names = pt.Pokemon_type(tipo.toLowerCase());
					
					// Método para mostrar os pokemons filtrados por tipo
					choice_pokemons();
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
						
						// Mudar a imagem do pokemon
						changeImage(pokemon.getNome());			
					}
					
					// Caso o nome digitado não seja válido entra nesta exceção
					catch (HttpResponseException e1)
					{
						// Texto de aviso que pokemon não foi encontrado
						lblPokemonNoEncontrado.setVisible(true);
						
						// Imagem de apoio - Pokemon não encontrado
						Pokemon_Img.setIcon(new ImageIcon
								(Pokedex.class.getResource
										("/view/Imagens/not_found.png")));
					}
					
					// Outras exceção relacionada com a requisição
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
					dispose();
				}
			}
		});
		Capturar_Pokemon_button.setBounds(230, 440, 190, 25);
		Buscador_Panel.add(Capturar_Pokemon_button);
		
		JButton show_pokemon_btn = new JButton("Visualizar");
		show_pokemon_btn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
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
		
		// Método para tornar this.JDialog visível
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
			atual_pokemon = new Pokemon(pokemon_name);
			
			id = (atual_pokemon.getPoke_Id());

			URL url = new URL("https://raw.githubusercontent.com"
					+ "/PokeAPI/sprites/master/sprites/pokemon/"
					+id+".png");

			BufferedImage image = ImageIO.read(url);

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
}
