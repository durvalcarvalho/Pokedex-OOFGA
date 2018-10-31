package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Choice;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Buscador_Pokemon extends JFrame
{

	private JPanel Buscador_Panel;
	private JTextField Pokemon_Nome_textField;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Buscador_Pokemon frame = new Buscador_Pokemon();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	
	public Buscador_Pokemon()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 150, 650, 500);
		Buscador_Panel = new JPanel();
		Buscador_Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Buscador_Panel);
		Buscador_Panel.setLayout(null);
		Buscador_Panel.setLayout(null);
		
		JLabel ProcurarPokemon_lbl = new JLabel("Procurar Pokemon");
		ProcurarPokemon_lbl.setFont(new Font("Ubuntu Mono", Font.BOLD, 20));
		ProcurarPokemon_lbl.setBounds(230, 28, 168, 20);
		Buscador_Panel.add(ProcurarPokemon_lbl);
		
		Choice Pokemon_choice_Tipo = new Choice();
		Pokemon_choice_Tipo.setBounds(100, 110, 430, 20);
		Buscador_Panel.add(Pokemon_choice_Tipo);
		
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
		
		JButton BuscarPokemon_btn = new JButton("Buscar Pokemon");
		BuscarPokemon_btn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// TODO: Fazer a busca de acordo com os parâmetros passados
				// TODO: Tratar os erros ( nome inválido, nome e tipo vazios)
			}
		});
		BuscarPokemon_btn.setBounds(240, 230, 160, 25);
		Buscador_Panel.add(BuscarPokemon_btn);
				
		// TODO: Implementar o método para pegar a imagem de acordo com o pokemon selecioado
		// TODO: Essa tela deve iniciar sem nenhuma imagem
		JLabel Pokemon_Img = new JLabel("Pokemon_Name");
		Pokemon_Img.setIcon(new ImageIcon(Pokedex.class.getResource("/view/Imagens/5.png")));
		Pokemon_Img.setBounds(400, 290, 100, 100);
		Buscador_Panel.add(Pokemon_Img);
		
		JButton Capturar_Pokemon_button = new JButton("Capturar Pokemon");
		Capturar_Pokemon_button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// TODO: Adicionar o pokemon encontrado a lista de pokemons capturados do treinador
			}
		});
		Capturar_Pokemon_button.setBounds(240, 440, 190, 25);
		Buscador_Panel.add(Capturar_Pokemon_button);
		
		// TODO: Descobrir como fazer uma lista rolável com os botões
		JPanel Pokemons_Filtrados_Panel = new JPanel();
		Pokemons_Filtrados_Panel.setBounds(100, 310, 215, 101);
		Buscador_Panel.add(Pokemons_Filtrados_Panel);
		Pokemons_Filtrados_Panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
				
		
		JButton buttons[] = new JButton[20];
		for(int i=0; i<20; i++)
		{
			buttons[i] = new JButton("Pokemon "+i);
			Pokemons_Filtrados_Panel.add(buttons[i]);
		}
		
		
		/*
		// TODO: Este método irá encontrar os pokemons
		  		 e criar os botões referentes a cada um
		  		 adicionar ao panel
		  		 
		encontra_pokemons(Pokemons_Filtrados_Panel);
		{
			for(int i=0; i<qnt_pokemons_encontrados(); i++)
			{
				
			}
		}
		*/
		
		
	}
}
