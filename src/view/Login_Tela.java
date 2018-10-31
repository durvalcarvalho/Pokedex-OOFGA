package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class Login_Tela extends JFrame
{
	private JPanel contentPane;
	private static Login_Tela frame;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					frame = new Login_Tela();
					frame.setVisible(true);
				}
				
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public Login_Tela()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 150, 650, 500);
		
		contentPane = new JPanel();
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblBemVindoA = new JLabel("Bem vindo a Pokedex!");
		lblBemVindoA.setFont(new Font("Ubuntu Mono", Font.BOLD, 25));
		lblBemVindoA.setBounds(200, 45, 270, 30);
		contentPane.add(lblBemVindoA);
		
		JLabel lblSelecioneUmTreinador = new JLabel("Selecione um treinador");
		lblSelecioneUmTreinador.setFont(new Font("Ubuntu Mono", Font.BOLD, 20));
		lblSelecioneUmTreinador.setBounds(215, 165, 260, 15);
		contentPane.add(lblSelecioneUmTreinador);
		
		// ASH KETCHUM
		// TODO: Caso este botão seja clicado é preciso criar
		// um treinador com os dados do Ash e mandar para a tela pokedex
		JButton btnAshButton = new JButton("Ash Ketchum");
		btnAshButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//TODO: Passar os pokemons do ash para a pokedex
				Pokedex ct = new Pokedex();
				//Pokedex ct = new Pokedex("Ash Ketchum", 10, "Masculino ♂", "Cidade de Pallet", "Kanto");
				ct.setVisible(true);
				frame.dispose();
			}
		});
		btnAshButton.setBounds(260, 205, 135, 25);
		contentPane.add(btnAshButton);
		
		// BROCK
		JButton brock_button = new JButton("Brock");
		brock_button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//TODO: Criar um treinador com os dados e pokemons do brock
				Pokedex ct = new Pokedex();
				//Pokedex ct = new Pokedex("Brock", 15, "Masculino ♂", "Pewter City", "Kanto");
				ct.setVisible(true);
				frame.dispose();
			}
		});
		brock_button.setBounds(60, 205, 135, 25);
		contentPane.add(brock_button);
		
		// MISTY
		JButton Misty_button = new JButton("Misty");
		Misty_button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//TODO: Criar um treinador com os dados e pokemons do misty
				Pokedex ct = new Pokedex();
				//Pokedex ct = new Pokedex("Misty", 10, "Feminino ♀", "Cerulean", "Kanto");
				ct.setVisible(true);
				frame.dispose();
			}
		});
		Misty_button.setBounds(460, 205, 135, 25);
		contentPane.add(Misty_button);
		
		JLabel new_trainer_label = new JLabel("Cadastre um treinador");
		new_trainer_label.setFont(new Font("Ubuntu Mono", Font.BOLD, 20));
		new_trainer_label.setBounds(220, 370, 260, 15);
		contentPane.add(new_trainer_label);
		
		JButton Cadastro_button = new JButton("Cadastrar");
		Cadastro_button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Cadastro_Treinador ct = new Cadastro_Treinador();
				ct.setVisible(true);
				frame.dispose();		
			}
		});
		Cadastro_button.setBounds(260, 420, 135, 25);
		contentPane.add(Cadastro_button);
	}
}
