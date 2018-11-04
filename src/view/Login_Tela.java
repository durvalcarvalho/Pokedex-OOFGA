package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controller.Controller;
import model.Treinador;

import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;

@SuppressWarnings("serial")
public class Login_Tela extends JFrame
{
	private JPanel contentPane;
	public Login_Tela(Controller controller)
	{
		init_Components();
	}
	

	public void init_Components()
	{
		/* Configurações sobre a tela */
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 150, 650, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		/* Label grande de boas vindas a pokedex */
		JLabel lblBemVindoA = new JLabel("Bem vindo a Pokedex!");
		lblBemVindoA.setFont(new Font("Ubuntu Mono", Font.BOLD, 25));
		lblBemVindoA.setBounds(200, 45, 270, 30);
		contentPane.add(lblBemVindoA);
		
		/* label de pedindo ação do usuário */
		JLabel lblSelecioneUmTreinador = new JLabel("Selecione "
				+ "um treinador");
		lblSelecioneUmTreinador.setFont(new Font("Ubuntu Mono", 
				Font.BOLD, 20));
		lblSelecioneUmTreinador.setBounds(220, 120, 260, 15);
		contentPane.add(lblSelecioneUmTreinador);
		

		// Botão para usar a pokedex do ash
		JButton btnAshButton = new JButton("Ash Ketchum");
		btnAshButton.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Treinador ash = new Treinador("Ash Ketchum", 
						"Masculino ♂", "Pallet", "Kanto", 10);
				
				/* Pokemons famosos do ash */
				Vector<String> nome_pokemons = new Vector<String>();
				nome_pokemons.addElement("pikachu");
				nome_pokemons.addElement("bulbasaur");
				nome_pokemons.addElement("charizard");
				nome_pokemons.addElement("lapras");
				nome_pokemons.addElement("pidgeot");
				
				/* Os nomes são salvos para os objetos pokemons
				   serem criados futuramente a partir destes nomes */
				ash.set_nomes_pokemons(nome_pokemons);
				
				Pokedex ct = new Pokedex(ash);
				ct.setVisible(true);
				
				setVisible(false);
			}
		});
		btnAshButton.setBounds(260, 169, 135, 25);
		contentPane.add(btnAshButton);
		
		// Imagem do ASH
		JLabel ash_img = new JLabel("");
		ash_img.setIcon(new ImageIcon(Login_Tela.class.getResource("/view/Imagens/ash.png")));
		ash_img.setBounds(280, 215, 100, 100);
		contentPane.add(ash_img);
		
		
		// Botão para usar a pokedex do Brock
		JButton brock_button = new JButton("Brock");
		brock_button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Treinador brock = new Treinador("Brock", "Masculino ♂"
						, "Pewter", "Kanto", 15);
				
				// Pokemons famosos do Brock
				Vector<String> nome_pokemons = new Vector<String>();
				nome_pokemons.addElement("onix");
				nome_pokemons.addElement("geodude");
				nome_pokemons.addElement("zubat");
				nome_pokemons.addElement("vulpix");
				nome_pokemons.addElement("mudkip");
				
				brock.set_nomes_pokemons(nome_pokemons);				
				
				Pokedex ct = new Pokedex(brock);
				ct.setVisible(true);
				
				setVisible(false);
			}
		});
		brock_button.setBounds(60, 169, 135, 25);
		contentPane.add(brock_button);
		
		// Imagem do Brock
		JLabel brock_img = new JLabel("");
		brock_img.setIcon(new ImageIcon(Login_Tela.class.getResource("/view/Imagens/brock.png")));
		brock_img.setBounds(80, 215, 100, 100);
		contentPane.add(brock_img);
		

		// Botão para usar a pokedex da Misty
		JButton Misty_button = new JButton("Misty");
		Misty_button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				Treinador misty =  new Treinador("Misty", "Feminino ♀",
						"Cerulean", "Kanto", 10);
				
				// Pokemons famosos da misty
				Vector<String> nome_pokemons = new Vector<String>();
				nome_pokemons.addElement("staryu");
				nome_pokemons.addElement("psyduck");
				nome_pokemons.addElement("goldeen");
				nome_pokemons.addElement("togepi");
				nome_pokemons.addElement("horsea");
				
				misty.set_nomes_pokemons(nome_pokemons);
				
				Pokedex ct = new Pokedex(misty);
				ct.setVisible(true);
				
				setVisible(false);
			}
		});
		Misty_button.setBounds(460, 169, 135, 25);
		contentPane.add(Misty_button);
		
		// Imagem da Misty
		JLabel misty_img = new JLabel("");
		misty_img.setIcon(new ImageIcon(Login_Tela.class.getResource("/view/Imagens/misty.png")));
		misty_img.setBounds(490, 215, 100, 100);
		contentPane.add(misty_img);
		
		
		JLabel new_trainer_label = new JLabel("Cadastre um treinador");
		new_trainer_label.setFont(new Font("Ubuntu Mono",
				Font.BOLD, 20));
		new_trainer_label.setBounds(220, 370, 260, 15);
		contentPane.add(new_trainer_label);
		
		
		// Botão para cadastrar um novo treinador
		JButton Cadastro_button = new JButton("Cadastrar");
		Cadastro_button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				
				Cadastro_Treinador ct = new Cadastro_Treinador();
				ct.setVisible(true);
				
				setVisible(false);
			}
		});
		Cadastro_button.setBounds(260, 420, 135, 25);
		contentPane.add(Cadastro_button);
		
		setVisible(true);
	}
}
