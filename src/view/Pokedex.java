package view;
import model.Pokemon;
import model.Treinador;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Pokedex extends JFrame
{
	private JPanel main_Panel;
	
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
	
	private JLabel Trainer_Name_lbl;
	private JLabel Trainer_symbol_genero;
	private JLabel Trainer_Idade_lbl;
	private JLabel Trainer_City_lbl;
	private JLabel Trainer_Regiao_lbl;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try
				{
					Pokedex frame = new Pokedex();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	// TODO: Alguns textos devem começar sem nada
	public Pokedex()
	{
		// Tela Principal
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 150, 650, 500);
		main_Panel = new JPanel();
		main_Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(main_Panel);
		main_Panel.setLayout(null);
		
		// Textos que irão aparecer na tela
		JLabel Nome_lbl = new JLabel("Nome: ");
		Nome_lbl.setFont(new Font("Ubuntu Mono", Font.BOLD, 14));
		Nome_lbl.setBounds(5, 5, 50, 15);
		main_Panel.add(Nome_lbl);
		
		// Estes dados devem ser mudados de acordo com o treinador
		Trainer_Name_lbl = new JLabel("Ash Ketchum Durval");
		Trainer_Name_lbl.setForeground(Color.BLUE);
		Trainer_Name_lbl.setFont(new Font("Ubuntu Mono", Font.BOLD, 14));
		Trainer_Name_lbl.setBounds(45, 5, 100, 15);
		main_Panel.add(Trainer_Name_lbl);
		
		Trainer_symbol_genero = new JLabel("♂");
		// TODO: Fazer trocar de acordo com o simbolo escolhido
		//JLabel Trainer_symbol_genero = new JLabel("♀");
		Trainer_symbol_genero.setBounds(150, 5, 20, 15);
		Trainer_symbol_genero.setForeground(Color.BLUE);
		main_Panel.add(Trainer_symbol_genero);
		
		JLabel Idade_lbl = new JLabel("Idade: ");
		Idade_lbl.setBounds(175, 5, 50, 15);
		Idade_lbl.setFont(new Font("Ubuntu Mono", Font.BOLD, 14));
		main_Panel.add(Idade_lbl);
		
		Trainer_Idade_lbl = new JLabel("100");
		Trainer_Idade_lbl.setBounds(225, 5, 35, 15);
		Trainer_Idade_lbl.setForeground(Color.BLUE);
		main_Panel.add(Trainer_Idade_lbl);
		
		JLabel Cidade_lbl = new JLabel("Cidade: ");
		Cidade_lbl.setBounds(265, 5, 70, 15);
		Cidade_lbl.setFont(new Font("Ubuntu Mono", Font.BOLD, 14));
		main_Panel.add(Cidade_lbl);
		
		Trainer_City_lbl = new JLabel("Vermilion City");
		Trainer_City_lbl.setBounds(320, 5, 100, 15);
		Trainer_City_lbl.setForeground(Color.BLUE);
		main_Panel.add(Trainer_City_lbl);
		
		JLabel Regiao_lbl = new JLabel("Região:");
		Regiao_lbl.setBounds(435, 5, 100, 15);
		Regiao_lbl.setFont(new Font("Ubuntu Mono", Font.BOLD, 14));
		main_Panel.add(Regiao_lbl);
		
		Trainer_Regiao_lbl = new JLabel("Sevii Islands");
		Trainer_Regiao_lbl.setBounds(495, 5, 100, 15);
		Trainer_Regiao_lbl.setForeground(Color.BLUE);
		main_Panel.add(Trainer_Regiao_lbl);
		
		// TODO: Para mudar esta imagem primeiro será preciso baixar a imagem no
		// diretório view/Imagens e passar a imagem como referência
		Pokemon_Img = new JLabel("Pokemon_Name");
		Pokemon_Img.setIcon(new ImageIcon(Pokedex.class.getResource("/view/Imagens/5.png")));
		Pokemon_Img.setBounds(420, 65, 100, 100);
		main_Panel.add(Pokemon_Img);
		
		JLabel Pokemon_Dados_lbl = new JLabel("Pokemon Dados");
		Pokemon_Dados_lbl.setFont(new Font("Ubuntu Mono", Font.BOLD, 20));
		Pokemon_Dados_lbl.setBounds(400, 60, 135, 20);
		main_Panel.add(Pokemon_Dados_lbl);
		
		JLabel Tipo_lbl = new JLabel("Tipo:");
		Tipo_lbl.setBounds(385, 160, 70, 15);
		main_Panel.add(Tipo_lbl);
		
		// Estes dados devem ser alterados de acordo com o pokemon capturado
		// TODO: Devem inicializar zerados
		Pokemon_Tipo_txt = new JTextField();
		Pokemon_Tipo_txt.setEditable(false);
		Pokemon_Tipo_txt.setText("Elétrico");
		Pokemon_Tipo_txt.setBounds(440, 160, 115, 20);
		main_Panel.add(Pokemon_Tipo_txt);
		Pokemon_Tipo_txt.setColumns(10);
		
		JLabel Peso_lbl = new JLabel("Peso:");
		Peso_lbl.setBounds(385, 190, 70, 15);
		main_Panel.add(Peso_lbl);
		
		Pokemon_Peso_txt = new JTextField();
		Pokemon_Peso_txt.setEditable(false);
		Pokemon_Peso_txt.setText("100.5 KG");
		Pokemon_Peso_txt.setBounds(440, 190, 115, 20);
		main_Panel.add(Pokemon_Peso_txt);
		Pokemon_Peso_txt.setColumns(10);
		
		JLabel Altura_lbl = new JLabel("Altura:");
		Altura_lbl.setBounds(385, 220, 70, 15);
		main_Panel.add(Altura_lbl);
		
		Pokemon_Altura_txt = new JTextField();
		Pokemon_Altura_txt.setEditable(false);
		Pokemon_Altura_txt.setText("100.0 m");
		Pokemon_Altura_txt.setBounds(440, 220, 115, 20);
		main_Panel.add(Pokemon_Altura_txt);
		Pokemon_Altura_txt.setColumns(10);
		
		JLabel HP_lbl = new JLabel("HP:");
		HP_lbl.setBounds(385, 260, 130, 15);
		main_Panel.add(HP_lbl);
		
		Pokemon_HP_textField = new JTextField();
		Pokemon_HP_textField.setText("999");
		Pokemon_HP_textField.setEditable(false);
		Pokemon_HP_textField.setColumns(10);
		Pokemon_HP_textField.setBounds(525, 260, 30, 20);
		main_Panel.add(Pokemon_HP_textField);
		
		JLabel Velocidade_lbl = new JLabel("Velocidade:");
		Velocidade_lbl.setBounds(385, 285, 130, 15);
		main_Panel.add(Velocidade_lbl);
		
		Pokemon_Velociddade_textField = new JTextField();
		Pokemon_Velociddade_textField.setText("999");
		Pokemon_Velociddade_textField.setEditable(false);
		Pokemon_Velociddade_textField.setColumns(10);
		Pokemon_Velociddade_textField.setBounds(525, 285, 30, 20);
		main_Panel.add(Pokemon_Velociddade_textField);
		
		JLabel Ataque_lbl = new JLabel("Ataque:");
		Ataque_lbl.setBounds(385, 310, 130, 15);
		main_Panel.add(Ataque_lbl);
		
		Pokemon_Ataque_textField = new JTextField();
		Pokemon_Ataque_textField.setText("999");
		Pokemon_Ataque_textField.setEditable(false);
		Pokemon_Ataque_textField.setColumns(10);
		Pokemon_Ataque_textField.setBounds(525, 310, 30, 20);
		main_Panel.add(Pokemon_Ataque_textField);
		
		JLabel Veloc_Ataque_lbl = new JLabel("Veloc. do Ataque:");
		Veloc_Ataque_lbl.setBounds(385, 335, 130, 15);
		main_Panel.add(Veloc_Ataque_lbl);
		
		Pokemon_VelAtt_textField = new JTextField();
		Pokemon_VelAtt_textField.setText("999");
		Pokemon_VelAtt_textField.setEditable(false);
		Pokemon_VelAtt_textField.setColumns(10);
		Pokemon_VelAtt_textField.setBounds(525, 335, 30, 20);
		main_Panel.add(Pokemon_VelAtt_textField);
		
		JLabel Defesa_lbl = new JLabel("Defesa:");
		Defesa_lbl.setBounds(385, 360, 130, 15);
		main_Panel.add(Defesa_lbl);
		
		Pokemon_Defesa_textField = new JTextField();
		Pokemon_Defesa_textField.setText("999");
		Pokemon_Defesa_textField.setEditable(false);
		Pokemon_Defesa_textField.setColumns(10);
		Pokemon_Defesa_textField.setBounds(525, 360, 30, 20);
		main_Panel.add(Pokemon_Defesa_textField);
		
		JLabel Veloc_Defesa_lbl = new JLabel("Veloc. da Defesa:");
		Veloc_Defesa_lbl.setBounds(385, 385, 130, 15);
		main_Panel.add(Veloc_Defesa_lbl);
		
		Pokemon_VelDef_textField = new JTextField();
		Pokemon_VelDef_textField.setText("999");
		Pokemon_VelDef_textField.setEditable(false);
		Pokemon_VelDef_textField.setColumns(10);
		Pokemon_VelDef_textField.setBounds(525, 385, 30, 20);
		main_Panel.add(Pokemon_VelDef_textField);
		
		
		// Botão que irá chamar a tela das habilidades do pokemon em questão
		JButton Habilidades_btn = new JButton("Habilidades");
		Habilidades_btn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// TODO: Abrir a tela com as habilidades do pokemon
			}
		});
		Habilidades_btn.setBounds(405, 430, 130, 25);
		main_Panel.add(Habilidades_btn);
		
		// ================================================= //
		
		JLabel Poke_Capt_lbl = new JLabel("Pokemons Capturados");
		Poke_Capt_lbl.setFont(new Font("Ubuntu Mono", Font.BOLD, 20));
		Poke_Capt_lbl.setBounds(67, 60, 200, 20);
		main_Panel.add(Poke_Capt_lbl);
		
		// Painel de botões dos pokemons capturados
		JPanel Pokemon_Panel = new JPanel();
		Pokemon_Panel.setBounds(70, 125, 200, 300);
		main_Panel.add(Pokemon_Panel);
		
		// TODO: Esta implementação deve ser feita em um model?
		// TODO: Adicionar um Button nesse Panel
		// TODO: Como passar o pokemon certo para esse método?
		// TODO: pokemons.qnt_capturados() -> atributo do treinador?
		/*
		for(int i=0; i<pokemons.qnt_capturados(); i++)
		{
			// pokemons_capturados é um array de inteiros com os id dos pokemons capturados
			// este método irá retornar um botão
			Pokemon_Panel.add(pokemons_capturado(i));
		}
		*/
		
		JButton NovoPokemon_button = new JButton("Novo Pokemon");
		NovoPokemon_button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// TODO: Abrir a tela de Procura de pokemon
			}
		});
		NovoPokemon_button.setBounds(100, 430, 140, 25);
		main_Panel.add(NovoPokemon_button);
		
		// ================================================= //
		
		// 	TODO: Este método é chamado quando um botão dos
		//	pokemons capturados é apertado
		// atualiza_pokemon_dados(1);
		
	}
	
	// TODO: Quando um dos botoes forem apertados os métodos ao lado devem atualizar
	public void atualiza_pokemon_dados(int id)
	{
		Pokemon p = new Pokemon(id);
		
		//TODO: Atualizar imagem
		Pokemon_Tipo_txt.setText(p.getTipo_pokemon().get(0));
		Pokemon_Peso_txt.setText(""+p.getPeso());
		Pokemon_Altura_txt.setText(""+p.getAltura());
		Pokemon_Defesa_textField.setText(""+p.getDef());
		
		Pokemon_HP_textField.setText(""+p.getHp());
		Pokemon_Velociddade_textField.setText(""+p.getSpeed());
		Pokemon_Ataque_textField.setText(""+p.getAtt());
		Pokemon_VelAtt_textField.setText(""+p.getSpAtt());
		Pokemon_VelDef_textField.setText(""+p.getSpDef());
	}
	
	public void atualiza_treinador(Treinador treinador)
	{
		Trainer_Name_lbl.setText(treinador.getNome());
		
		if(treinador.getGenero() == "Masculino")
		{
			Trainer_symbol_genero.setText("♂");
		}
		else Trainer_symbol_genero.setText("♀");
			
		Trainer_Idade_lbl.setText(""+treinador.getIdade());
		Trainer_City_lbl.setText(treinador.getCidade());
		Trainer_Regiao_lbl.setText(treinador.getRegiao());
	}
	
	// TODO: Onde deve ficar esse método?
	public JButton pokemon_capturado(int id)
	{
		JButton button = new JButton(new Pokemon(id).getNome());
		return button;
	}
}
