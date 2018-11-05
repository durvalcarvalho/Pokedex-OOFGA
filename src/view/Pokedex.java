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
	// Painel Principal
	private JPanel main_Panel;
	
	// Componentes mutáveis de cada pokemon
	private JTextField Pokemon_Tipo_txt;
	private JTextField Pokemon_Peso_txt;
	private JTextField Pokemon_Altura_txt;
	private JTextField Pokemon_Defesa_textField;
	private JTextField Pokemon_HP_textField;
	private JTextField Pokemon_Velociddade_textField;
	private JTextField Pokemon_Ataque_textField;
	private JTextField Pokemon_VelAtt_textField;
	private JTextField Pokemon_VelDef_textField;
	private JLabel Pokemon_Img;
	
	private JLabel img_pokemon_not_found;
	
	private Choice choice;
	
	// Este atributo guarda o pokemon ilustrado na tela
	private Pokemon Atual_Pokemon = null;
	
	private Treinador treinador;
	
	public Pokedex(Treinador treinador)
	{
		this.treinador = treinador;
		
		/* LABELS */
		
		// Tela Principal
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 150, 650, 500);
		main_Panel = new JPanel();
		main_Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(main_Panel);
		main_Panel.setLayout(null);
		
		
		// Jlabels e JButtons da tela
		JLabel Nome_lbl = new JLabel("Nome: ");
		Nome_lbl.setBounds(5, 5, 50, 15);
		Nome_lbl.setFont(new Font("Ubuntu Mono", Font.BOLD, 14));
		main_Panel.add(Nome_lbl);
		
		JLabel Trainer_Name_lbl = new JLabel(treinador.getNome());
		Trainer_Name_lbl.setBounds(45, 5, 100, 15);
		Trainer_Name_lbl.setForeground(Color.BLUE);
		Trainer_Name_lbl.setFont(new Font("Ubuntu Mono", Font.BOLD, 14));
		main_Panel.add(Trainer_Name_lbl);
		
		JLabel Trainer_symbol_genero = new JLabel("");
		Trainer_symbol_genero.setBounds(150, 5, 20, 15);
		Trainer_symbol_genero.setForeground(Color.BLUE);
		if(treinador.getGenero() == "Masculino")
		{
			Trainer_symbol_genero.setText("♂");
		}
		else Trainer_symbol_genero.setText("♀");
		main_Panel.add(Trainer_symbol_genero);
		
		JLabel Idade_lbl = new JLabel("Idade: ");
		Idade_lbl.setBounds(175, 5, 50, 15);
		Idade_lbl.setFont(new Font("Ubuntu Mono", Font.BOLD, 14));
		main_Panel.add(Idade_lbl);
		
		JLabel Trainer_Idade_lbl = new JLabel(""+treinador.getIdade());
		Trainer_Idade_lbl.setBounds(225, 5, 35, 15);
		Trainer_Idade_lbl.setForeground(Color.BLUE);
		main_Panel.add(Trainer_Idade_lbl);
		
		JLabel Cidade_lbl = new JLabel("Cidade: ");
		Cidade_lbl.setBounds(265, 5, 70, 15);
		Cidade_lbl.setFont(new Font("Ubuntu Mono", Font.BOLD, 14));
		main_Panel.add(Cidade_lbl);
		
		JLabel Trainer_City_lbl = new JLabel(treinador.getCidade());
		Trainer_City_lbl.setBounds(320, 5, 100, 15);
		Trainer_City_lbl.setForeground(Color.BLUE);
		main_Panel.add(Trainer_City_lbl);
		
		JLabel Regiao_lbl = new JLabel("Região:");
		Regiao_lbl.setBounds(435, 5, 100, 15);
		Regiao_lbl.setFont(new Font("Ubuntu Mono", Font.BOLD, 14));
		main_Panel.add(Regiao_lbl);
		
		JLabel Trainer_Regiao_lbl = new JLabel(treinador.getRegiao());
		Trainer_Regiao_lbl.setBounds(495, 5, 100, 15);
		Trainer_Regiao_lbl.setForeground(Color.BLUE);
		main_Panel.add(Trainer_Regiao_lbl);
		
		// Este JLabel é uma imagem do pokemon
		Pokemon_Img = new JLabel("");
		Pokemon_Img.setBounds(420, 65, 100, 100);
		Pokemon_Img.setIcon(new ImageIcon(Pokedex.class.getResource("/view/Imagens/0.png")));
		main_Panel.add(Pokemon_Img);		
		
		JLabel Pokemon_Dados_lbl = new JLabel("Pokemon Dados");
		Pokemon_Dados_lbl.setBounds(400, 60, 135, 20);
		Pokemon_Dados_lbl.setFont(new Font("Ubuntu Mono",
				Font.BOLD, 20));
		main_Panel.add(Pokemon_Dados_lbl);
		
		JLabel Tipo_lbl = new JLabel("Tipo:");
		Tipo_lbl.setBounds(385, 160, 70, 15);
		main_Panel.add(Tipo_lbl);
		
		// Tipo do pokemon
		Pokemon_Tipo_txt = new JTextField();
		Pokemon_Tipo_txt.setBounds(440, 160, 115, 20);
		Pokemon_Tipo_txt.setEditable(false);
		Pokemon_Tipo_txt.setText("");
		main_Panel.add(Pokemon_Tipo_txt);
		Pokemon_Tipo_txt.setColumns(10);
		
		JLabel Peso_lbl = new JLabel("Peso:");
		Peso_lbl.setBounds(385, 190, 70, 15);
		main_Panel.add(Peso_lbl);
		
		// Peso do Pokemon
		Pokemon_Peso_txt = new JTextField();
		Pokemon_Peso_txt.setBounds(440, 190, 115, 20);
		Pokemon_Peso_txt.setEditable(false);
		Pokemon_Peso_txt.setText("");
		main_Panel.add(Pokemon_Peso_txt);
		Pokemon_Peso_txt.setColumns(10);
		
		JLabel Altura_lbl = new JLabel("Altura:");
		Altura_lbl.setBounds(385, 220, 70, 15);
		main_Panel.add(Altura_lbl);
		
		// Altura do Pokemon
		Pokemon_Altura_txt = new JTextField();
		Pokemon_Altura_txt.setBounds(440, 220, 115, 20);
		Pokemon_Altura_txt.setEditable(false);
		Pokemon_Altura_txt.setText("");
		main_Panel.add(Pokemon_Altura_txt);
		Pokemon_Altura_txt.setColumns(10);
		
		JLabel HP_lbl = new JLabel("HP:");
		HP_lbl.setBounds(385, 260, 130, 15);
		main_Panel.add(HP_lbl);
		
		// HP do Pokemon
		Pokemon_HP_textField = new JTextField();
		Pokemon_HP_textField.setBounds(525, 260, 30, 20);
		Pokemon_HP_textField.setText("");
		Pokemon_HP_textField.setEditable(false);
		Pokemon_HP_textField.setColumns(10);
		main_Panel.add(Pokemon_HP_textField);
		
		JLabel Velocidade_lbl = new JLabel("Velocidade:");
		Velocidade_lbl.setBounds(385, 285, 130, 15);
		main_Panel.add(Velocidade_lbl);
		
		// Velocidade do Pokemon
		Pokemon_Velociddade_textField = new JTextField();
		Pokemon_Velociddade_textField.setBounds(525, 285, 30, 20);
		Pokemon_Velociddade_textField.setText("");
		Pokemon_Velociddade_textField.setEditable(false);
		Pokemon_Velociddade_textField.setColumns(10);
		main_Panel.add(Pokemon_Velociddade_textField);
		
		JLabel Ataque_lbl = new JLabel("Ataque:");
		Ataque_lbl.setBounds(385, 310, 130, 15);
		main_Panel.add(Ataque_lbl);
		
		// Ataque do Pokemon
		Pokemon_Ataque_textField = new JTextField();
		Pokemon_Ataque_textField.setBounds(525, 310, 30, 20);
		Pokemon_Ataque_textField.setText("");
		Pokemon_Ataque_textField.setEditable(false);
		Pokemon_Ataque_textField.setColumns(10);
		main_Panel.add(Pokemon_Ataque_textField);
		
		JLabel Veloc_Ataque_lbl = new JLabel("Veloc. do Ataque:");
		Veloc_Ataque_lbl.setBounds(385, 335, 130, 15);
		main_Panel.add(Veloc_Ataque_lbl);
		
		// Velocidade de Ataque do Pokemon
		Pokemon_VelAtt_textField = new JTextField();
		Pokemon_VelAtt_textField.setBounds(525, 335, 30, 20);
		Pokemon_VelAtt_textField.setText("");
		Pokemon_VelAtt_textField.setEditable(false);
		Pokemon_VelAtt_textField.setColumns(10);
		main_Panel.add(Pokemon_VelAtt_textField);
		
		JLabel Defesa_lbl = new JLabel("Defesa:");
		Defesa_lbl.setBounds(385, 360, 130, 15);
		main_Panel.add(Defesa_lbl);
		
		// Defesa do Pokemon
		Pokemon_Defesa_textField = new JTextField();
		Pokemon_Defesa_textField.setBounds(525, 360, 30, 20);
		Pokemon_Defesa_textField.setText("");
		Pokemon_Defesa_textField.setEditable(false);
		Pokemon_Defesa_textField.setColumns(10);
		main_Panel.add(Pokemon_Defesa_textField);
		
		JLabel Veloc_Defesa_lbl = new JLabel("Veloc. da Defesa:");
		Veloc_Defesa_lbl.setBounds(385, 385, 130, 15);
		main_Panel.add(Veloc_Defesa_lbl);
				
		// Velocidade de Defesa do Pokemon
		Pokemon_VelDef_textField = new JTextField();
		Pokemon_VelDef_textField.setBounds(525, 385, 30, 20);
		Pokemon_VelDef_textField.setText("");
		Pokemon_VelDef_textField.setEditable(false);
		Pokemon_VelDef_textField.setColumns(10);
		main_Panel.add(Pokemon_VelDef_textField);
		
		choice = new Choice();
		choice.setBounds(65, 160, 200, 25);
		main_Panel.add(choice);
		
		JLabel Poke_Capt_lbl = new JLabel("Pokemons Capturados");
		Poke_Capt_lbl.setBounds(67, 60, 200, 20);
		Poke_Capt_lbl.setFont(new Font("Ubuntu Mono", Font.BOLD, 20));
		main_Panel.add(Poke_Capt_lbl);
		
		/* BOTÕES */
		
		// Botão que abre tela de habilidades do pokemon selecionado
		JButton Habilidades_btn = new JButton("Habilidades");
		Habilidades_btn.setBounds(405, 430, 130, 25);
		Habilidades_btn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{	
				// Se algum pokemon tiver sido escolhido
				if(! (Atual_Pokemon == null))
				{	
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
				// O programa pausa enquanto a tela do Buscador
				// estiver aberta
				Buscador_Pokemon bp =  new Buscador_Pokemon();
				
				// Se o treinador tiver escolhido um pokemon
				if(bp.getPokemon() != null)
				{					
					String poke_capturado = bp.getPokemon();
					treinador.addPokemon(poke_capturado);
					
					pokemons_capturados();
				}
			}
		});
		main_Panel.add(NovoPokemon_button);
		
		JButton btnVisualizar = new JButton("Visualizar");
		btnVisualizar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Se o treinador não tiver nenhum pokemon o botão visualizar não faz nada
				if(!(treinador.get_nomes_pokemons().isEmpty()))
				{
					atualiza_pokemon_dados(choice.getSelectedItem());					
				}
			}
		});
		btnVisualizar.setBounds(105, 215, 120, 25);
		main_Panel.add(btnVisualizar);
		
		// IMAGEM PARA OS POKEMONS QUE NÃO TEM IMAGEM
		img_pokemon_not_found = new JLabel("Este pokemon não tem imagem");
		img_pokemon_not_found.setForeground(Color.BLUE);
		img_pokemon_not_found.setBounds(360, 40, 225, 15);
		img_pokemon_not_found.setVisible(false);
		main_Panel.add(img_pokemon_not_found);
		
		// Método que adiciona os pokemons capturados no choice
		pokemons_capturados();
		
		setVisible(true);
	}
	
	public void pokemons_capturados()
	{
		choice.removeAll();
		
		for(int i=0; i<treinador.get_nomes_pokemons().size(); i++)
		{
			choice.add(treinador.get_nomes_pokemons().get(i));
		}
	}
	
	public void atualiza_pokemon_dados(String nome)
	{			
		try
		{
			// Se o nome do pokemon for válido
			Pokemon p = new Pokemon(nome);	
			
			// Esse atributo salva o ultimo pokemon usado para atualizar o stats
			// deste modo, quando o botão 'habilidades' é apertado é utiliazdo
			// esse pokemon
			Atual_Pokemon = p;
			
			Pokemon_Tipo_txt.setText(p.getTipo_pokemon().get(0));
			Pokemon_Peso_txt.setText(""+p.getPeso());
			Pokemon_Altura_txt.setText(""+p.getAltura());
			Pokemon_Defesa_textField.setText(""+p.getDef());
			
			Pokemon_HP_textField.setText(""+p.getHp());
			Pokemon_Velociddade_textField.setText(""+p.getSpeed());
			Pokemon_Ataque_textField.setText(""+p.getAtt());
			Pokemon_VelAtt_textField.setText(""+p.getSpAtt());
			Pokemon_VelDef_textField.setText(""+p.getSpDef());
			
			changeImage(p.getPoke_Id());
		}
		
		catch (IllegalStateException | JSONException | IOException e)
		{
			e.printStackTrace();
		}
	}
	
	// Método para fazer a requisição da imagem referente ao pokemon
	public void changeImage(int id)
	{
		img_pokemon_not_found.setVisible(false);
		
		try
		{
			URL url = new URL("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+id+".png");
			BufferedImage image = ImageIO.read(url);
			Pokemon_Img.setIcon(new ImageIcon(image));
		}
		catch (IllegalStateException | IOException e)
		{
			Pokemon_Img.setIcon(new ImageIcon (Pokedex.class.getResource("/view/Imagens/not_found.png")));
			img_pokemon_not_found.setVisible(true);
			
		}
	}
}