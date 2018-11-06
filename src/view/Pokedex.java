package view;
import model.Pokemon;
import model.Treinador;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import org.json.JSONException;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Choice;

@SuppressWarnings("serial")
public class Pokedex extends JFrame
{	
	// Atributos usados no método atualiza_pokemon_dados()
	private JTextField Pokemon_Tipo_txt;
	private JTextField Pokemon_Peso_txt;
	private JTextField Pokemon_Altura_txt;
	private JTextField Pokemon_Defesa_textField;
	private JTextField Pokemon_HP_textField;
	private JTextField Pokemon_Velociddade_textField;
	private JTextField Pokemon_Ataque_textField;
	private JTextField Pokemon_VelAtt_textField;
	private JTextField Pokemon_VelDef_textField;

	// Atributos usados no método changeImage()
	private JLabel Pokemon_Img;
	private JLabel img_pokemon_not_found;
	
	// Atributo usado no método pokemons_capturados()
	private Choice choice;
	
	// Este atributo é usado para ter uma referência ao pokemon mostrado na tela
	private Pokemon Atual_Pokemon = null;
	
	public Pokedex(Treinador treinador)
	{		
		/* LABELS */
		
		// Configuração da Tela
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 150, 650, 500);
		JPanel main_Panel = new JPanel();
		main_Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(main_Panel);
		main_Panel.setLayout(null);
		
		
		// label "nome:"
		JLabel Nome_lbl = new JLabel("Nome: ");
		Nome_lbl.setBounds(5, 5, 50, 15);
		Nome_lbl.setFont(new Font("Ubuntu Mono", Font.BOLD, 14));
		main_Panel.add(Nome_lbl);
		
		// label que recebe o nome do treinador
		JLabel Trainer_Name_lbl = new JLabel(treinador.getNome());
		Trainer_Name_lbl.setBounds(45, 5, 100, 15);
		Trainer_Name_lbl.setForeground(Color.BLUE);
		Trainer_Name_lbl.setFont(new Font("Ubuntu Mono", Font.BOLD, 14));
		main_Panel.add(Trainer_Name_lbl);
		
		// label que recebe o simbolo do gênero do treinador
		JLabel Trainer_symbol_genero = new JLabel("");
		Trainer_symbol_genero.setBounds(150, 5, 20, 15);
		Trainer_symbol_genero.setForeground(Color.BLUE);
		if(treinador.getGenero() == "Masculino")
		{
			Trainer_symbol_genero.setText("♂");
		}
		else Trainer_symbol_genero.setText("♀");
		main_Panel.add(Trainer_symbol_genero);
		
		// label "idade:"
		JLabel Idade_lbl = new JLabel("Idade: ");
		Idade_lbl.setBounds(175, 5, 50, 15);
		Idade_lbl.setFont(new Font("Ubuntu Mono", Font.BOLD, 14));
		main_Panel.add(Idade_lbl);
		
		// label que recebe a idade do treinador
		JLabel Trainer_Idade_lbl = new JLabel(""+treinador.getIdade());
		Trainer_Idade_lbl.setBounds(225, 5, 35, 15);
		Trainer_Idade_lbl.setForeground(Color.BLUE);
		main_Panel.add(Trainer_Idade_lbl);
		
		// label "cidade:"
		JLabel Cidade_lbl = new JLabel("Cidade: ");
		Cidade_lbl.setBounds(265, 5, 70, 15);
		Cidade_lbl.setFont(new Font("Ubuntu Mono", Font.BOLD, 14));
		main_Panel.add(Cidade_lbl);
		
		// label que recebe cidade do treinador
		JLabel Trainer_City_lbl = new JLabel(treinador.getCidade());
		Trainer_City_lbl.setBounds(320, 5, 100, 15);
		Trainer_City_lbl.setForeground(Color.BLUE);
		main_Panel.add(Trainer_City_lbl);
		
		// label "região:"
		JLabel Regiao_lbl = new JLabel("Região:");
		Regiao_lbl.setBounds(435, 5, 100, 15);
		Regiao_lbl.setFont(new Font("Ubuntu Mono", Font.BOLD, 14));
		main_Panel.add(Regiao_lbl);
		
		// label que recebe região do treinador
		JLabel Trainer_Regiao_lbl = new JLabel(treinador.getRegiao());
		Trainer_Regiao_lbl.setBounds(495, 5, 100, 15);
		Trainer_Regiao_lbl.setForeground(Color.BLUE);
		main_Panel.add(Trainer_Regiao_lbl);
		
		// label que irá mudar de acordo com a imagem do atual pokemon
		Pokemon_Img = new JLabel("");
		Pokemon_Img.setBounds(420, 65, 100, 100);
		Pokemon_Img.setIcon(new ImageIcon(Pokedex.class.getResource("/view/Imagens/0.png")));
		main_Panel.add(Pokemon_Img);		
		
		// label "pokemon dados"
		JLabel Pokemon_Dados_lbl = new JLabel("Pokemon Dados");
		Pokemon_Dados_lbl.setBounds(400, 60, 135, 20);
		Pokemon_Dados_lbl.setFont(new Font("Ubuntu Mono",
				Font.BOLD, 20));
		main_Panel.add(Pokemon_Dados_lbl);
		
		// label "tipo:"
		JLabel Tipo_lbl = new JLabel("Tipo:");
		Tipo_lbl.setBounds(385, 160, 70, 15);
		main_Panel.add(Tipo_lbl);
		
		// label que recebe o tipo do pokemon
		Pokemon_Tipo_txt = new JTextField();
		Pokemon_Tipo_txt.setBounds(440, 160, 115, 20);
		Pokemon_Tipo_txt.setEditable(false);
		Pokemon_Tipo_txt.setText("");
		main_Panel.add(Pokemon_Tipo_txt);
		Pokemon_Tipo_txt.setColumns(10);
		
		// label "peso:"
		JLabel Peso_lbl = new JLabel("Peso:");
		Peso_lbl.setBounds(385, 190, 70, 15);
		main_Panel.add(Peso_lbl);
		
		// label que irá receber o peso do pokemon
		Pokemon_Peso_txt = new JTextField();
		Pokemon_Peso_txt.setBounds(440, 190, 115, 20);
		Pokemon_Peso_txt.setEditable(false);
		Pokemon_Peso_txt.setText("");
		main_Panel.add(Pokemon_Peso_txt);
		Pokemon_Peso_txt.setColumns(10);
		
		// label "altura:"
		JLabel Altura_lbl = new JLabel("Altura:");
		Altura_lbl.setBounds(385, 220, 70, 15);
		main_Panel.add(Altura_lbl);
		
		// label que irá receber altura do pokemon
		Pokemon_Altura_txt = new JTextField();
		Pokemon_Altura_txt.setBounds(440, 220, 115, 20);
		Pokemon_Altura_txt.setEditable(false);
		Pokemon_Altura_txt.setText("");
		main_Panel.add(Pokemon_Altura_txt);
		Pokemon_Altura_txt.setColumns(10);
		
		//label "hp:"
		JLabel HP_lbl = new JLabel("HP:");
		HP_lbl.setBounds(385, 260, 130, 15);
		main_Panel.add(HP_lbl);
		
		// label que irá receber hp do pokemon
		Pokemon_HP_textField = new JTextField();
		Pokemon_HP_textField.setBounds(525, 260, 30, 20);
		Pokemon_HP_textField.setText("");
		Pokemon_HP_textField.setEditable(false);
		Pokemon_HP_textField.setColumns(10);
		main_Panel.add(Pokemon_HP_textField);
		
		// label "velocidade:"
		JLabel Velocidade_lbl = new JLabel("Velocidade:");
		Velocidade_lbl.setBounds(385, 285, 130, 15);
		main_Panel.add(Velocidade_lbl);
		
		// label que irá receber velocidade do pokemon
		Pokemon_Velociddade_textField = new JTextField();
		Pokemon_Velociddade_textField.setBounds(525, 285, 30, 20);
		Pokemon_Velociddade_textField.setText("");
		Pokemon_Velociddade_textField.setEditable(false);
		Pokemon_Velociddade_textField.setColumns(10);
		main_Panel.add(Pokemon_Velociddade_textField);
		
		// label "ataque:"
		JLabel Ataque_lbl = new JLabel("Ataque:");
		Ataque_lbl.setBounds(385, 310, 130, 15);
		main_Panel.add(Ataque_lbl);
		
		// label que irá receber ataque do pokemon
		Pokemon_Ataque_textField = new JTextField();
		Pokemon_Ataque_textField.setBounds(525, 310, 30, 20);
		Pokemon_Ataque_textField.setText("");
		Pokemon_Ataque_textField.setEditable(false);
		Pokemon_Ataque_textField.setColumns(10);
		main_Panel.add(Pokemon_Ataque_textField);
		
		// label "veloc. do ataque:"
		JLabel Veloc_Ataque_lbl = new JLabel("Veloc. do Ataque:");
		Veloc_Ataque_lbl.setBounds(385, 335, 130, 15);
		main_Panel.add(Veloc_Ataque_lbl);
		
		// label que irá receber velocidade do ataque do pokemon
		Pokemon_VelAtt_textField = new JTextField();
		Pokemon_VelAtt_textField.setBounds(525, 335, 30, 20);
		Pokemon_VelAtt_textField.setText("");
		Pokemon_VelAtt_textField.setEditable(false);
		Pokemon_VelAtt_textField.setColumns(10);
		main_Panel.add(Pokemon_VelAtt_textField);
		
		// label "defesa:"
		JLabel Defesa_lbl = new JLabel("Defesa:");
		Defesa_lbl.setBounds(385, 360, 130, 15);
		main_Panel.add(Defesa_lbl);
		
		// label que irá receber defesa do pokemon
		Pokemon_Defesa_textField = new JTextField();
		Pokemon_Defesa_textField.setBounds(525, 360, 30, 20);
		Pokemon_Defesa_textField.setText("");
		Pokemon_Defesa_textField.setEditable(false);
		Pokemon_Defesa_textField.setColumns(10);
		main_Panel.add(Pokemon_Defesa_textField);
		
		// label "veloc. da defesa"
		JLabel Veloc_Defesa_lbl = new JLabel("Veloc. da Defesa:");
		Veloc_Defesa_lbl.setBounds(385, 385, 130, 15);
		main_Panel.add(Veloc_Defesa_lbl);
				
		// label que irá receber velocidade da defesa do pokemon
		Pokemon_VelDef_textField = new JTextField();
		Pokemon_VelDef_textField.setBounds(525, 385, 30, 20);
		Pokemon_VelDef_textField.setText("");
		Pokemon_VelDef_textField.setEditable(false);
		Pokemon_VelDef_textField.setColumns(10);
		main_Panel.add(Pokemon_VelDef_textField);
		
		// label "pokemons capturados"
		JLabel Poke_Capt_lbl = new JLabel("Pokemons Capturados");
		Poke_Capt_lbl.setBounds(67, 60, 200, 20);
		Poke_Capt_lbl.setFont(new Font("Ubuntu Mono", Font.BOLD, 20));
		main_Panel.add(Poke_Capt_lbl);
		
		// choice que irá receber o nome dos pokemons capturados
		choice = new Choice();
		choice.setBounds(65, 160, 200, 25);
		main_Panel.add(choice);
		
		/* BOTÕES */
		
		// Botão que abre a tela de habilidades do pokemon atual
		JButton Habilidades_btn = new JButton("Habilidades");
		Habilidades_btn.setBounds(405, 430, 130, 25);
		Habilidades_btn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{	
				// Se houver um pokemon atual
				if(! (Atual_Pokemon == null))
				{	
					// Abre a tela de habilidade do pokemon atual
					new Habilidades_Pokemon(Atual_Pokemon);	
				}
			}
		});
		main_Panel.add(Habilidades_btn);
		
		// Botão que abre a tela do Buscador de Pokemon
		JButton NovoPokemon_button = new JButton("Novo Pokemon");
		NovoPokemon_button.setBounds(100, 430, 140, 25);
		NovoPokemon_button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// A tela do buscador é um JDialog, ou seja, enquanto essa tela estiver aberta,
				// a tela que chamou o JDialog fica aguardando
				Buscador_Pokemon bp =  new Buscador_Pokemon();
				
				// Se na tela do buscador algum pokemon tiver sido "capturado"
				if(bp.getPokemon() != null && bp.getCaptura())
				{					
					// O pokeon capturado é adicionado na lista de pokemons do treinador
					String poke_capturado = bp.getPokemon();
					treinador.addPokemon(poke_capturado);
					
					// Atualiza os pokemons mostrados na tela
					pokemons_capturados(treinador);
				}
			}
		});
		main_Panel.add(NovoPokemon_button);
		
		// Botão para visualizar um pokemon capturado
		JButton btnVisualizar = new JButton("Visualizar");
		btnVisualizar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Se o treinador não tiver nenhum pokemon o botão visualizar não faz nada
				if(!(treinador.get_nomes_pokemons().isEmpty()))
				{
					// Método para atualizar os dados do pokemon selecionado
					atualiza_pokemon_dados(choice.getSelectedItem());					
				}
			}
		});
		btnVisualizar.setBounds(105, 215, 120, 25);
		main_Panel.add(btnVisualizar);
		
		// label com o texto de pokemon não encontrado fica indisponível
		img_pokemon_not_found = new JLabel("Este pokemon não tem imagem");
		img_pokemon_not_found.setForeground(Color.BLUE);
		img_pokemon_not_found.setBounds(360, 40, 225, 15);
		img_pokemon_not_found.setVisible(false);
		main_Panel.add(img_pokemon_not_found);
		
		// Método que adiciona os pokemons capturados no choice
		pokemons_capturados(treinador);
		
		// Após construir todos os componentes, a tela fica visível
		setVisible(true);
	}
	
	// Método para atualizar os pokemons capturados no choice
	public void pokemons_capturados(Treinador treinador)
	{
		choice.removeAll();
		
		for(int i=0; i<treinador.get_nomes_pokemons().size(); i++)
		{
			choice.add(treinador.get_nomes_pokemons().get(i));
		}
	}
	
	// Método para carregar os dados do pokemon escolhido na tela
	public void atualiza_pokemon_dados(String nome)
	{			
		try
		{
			// Se o nome do pokemon for válido
			Pokemon p = new Pokemon(nome);	
			
			// O pokemon atual recebe o pokemon selecionado
			Atual_Pokemon = p;
			
			// Atualização dos labels
			Pokemon_Tipo_txt.setText(p.getTipo_pokemon().get(0));
			Pokemon_Peso_txt.setText(""+p.getPeso()+" Kg");
			Pokemon_Altura_txt.setText(""+p.getAltura()+" M");
			Pokemon_Defesa_textField.setText(""+p.getDef());
			Pokemon_HP_textField.setText(""+p.getHp());
			Pokemon_Velociddade_textField.setText(""+p.getSpeed());
			Pokemon_Ataque_textField.setText(""+p.getAtt());
			Pokemon_VelAtt_textField.setText(""+p.getSpAtt());
			Pokemon_VelDef_textField.setText(""+p.getSpDef());
			
			// Método para atualizar a imagem do pokemon selecionado
			changeImage(p.getPoke_Id());
		}
		
		catch (IllegalStateException | JSONException | IOException e)
		{
			System.out.println("Erro durante a atualização dos dados do pokemon");
			e.printStackTrace();
		}
	}
	
	// Método para atualizar a imagem do pokemon selecionado
	public void changeImage(int id)
	{
		// Se esse label estiver visível é tornado indisponível
		img_pokemon_not_found.setVisible(false);
		
		try
		{
			// url para imagem do pokemon é criada
			URL url = new URL("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+id+".png");
			
			// A imagem é obtida
			BufferedImage image = ImageIO.read(url);
			
			// O label é atualizado com a nova imagem obtida
			Pokemon_Img.setIcon(new ImageIcon(image));
		}
		catch (IllegalStateException | IOException e)
		{
			// Caso ocorra algum erro a imaagem é atualizada para uma imagem de erro
			Pokemon_Img.setIcon(new ImageIcon (Pokedex.class.getResource("/view/Imagens/not_found.png")));
			
			// O texto sinalizando erro é tornado visível
			img_pokemon_not_found.setVisible(true);
			
		}
	}
}