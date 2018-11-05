package view;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import model.Pokemon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.awt.event.ActionEvent;
import java.awt.Choice;

//TODO: Colocar uma imagem do pokemon em questão

@SuppressWarnings("serial")
public class Habilidades_Pokemon extends JFrame implements Runnable
{

	private JPanel Habilidades_Panel;
	private Pokemon pokemon;
	private JLabel img_pokemon;
	
	//public void run()
	public Habilidades_Pokemon(Pokemon pokemon)
	{
		if(pokemon!= null)
		{
			this.pokemon = pokemon;
			new Thread(this).start();			
		}
	}
	
	//public Habilidades_Pokemon(Pokemon pokemon)
	public void run()
	{
		setUndecorated(true);
		
		pokemon.load_abilities();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(550, 150, 450, 500);
		Habilidades_Panel = new JPanel();
		Habilidades_Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Habilidades_Panel);
		Habilidades_Panel.setLayout(null);
		
		JLabel Pokemon_lbl = new JLabel("Pokemon: ");
		Pokemon_lbl.setFont(new Font("Ubuntu Mono", Font.BOLD, 14));
		Pokemon_lbl.setBounds(5, 5, 70, 15);
		Habilidades_Panel.add(Pokemon_lbl);
		
		JLabel Pokemon_Name_lbl = new JLabel(pokemon.getNome());
		Pokemon_Name_lbl.setForeground(Color.BLUE);
		Pokemon_Name_lbl.setFont(new Font("Ubuntu Mono", Font.BOLD, 14));
		Pokemon_Name_lbl.setBounds(75, 5, 200, 15);
		Habilidades_Panel.add(Pokemon_Name_lbl);
		
		JLabel Habilidades_lbl = new JLabel("Habilidades");
		Habilidades_lbl.setFont(new Font("Ubuntu Mono", Font.BOLD, 25));
		Habilidades_lbl.setBounds(147, 45, 160, 20);
		Habilidades_Panel.add(Habilidades_lbl);
		
		JTextPane Descricao_txtpanel = new JTextPane();
		Descricao_txtpanel.setText("");
		Descricao_txtpanel.setBounds(75, 263, 300, 100);
		Habilidades_Panel.add(Descricao_txtpanel);
		
		JButton Voltar_btn = new JButton("Voltar");
		Voltar_btn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				setVisible(false);
			}
		});
		Voltar_btn.setBounds(160, 425, 120, 25);
		Habilidades_Panel.add(Voltar_btn);		
		
		Choice Habilidade_choice = new Choice();
		Habilidade_choice.setBounds(75, 170, 300, 25);
		Habilidades_Panel.add(Habilidade_choice);
		
		JButton Descricao_btn = new JButton("Descrição");
		Descricao_btn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int index = Habilidade_choice.getSelectedIndex();
				Descricao_txtpanel.setText(pokemon.getHabilidades().get(index).getValue());
			}
		});
		Descricao_btn.setBounds(160, 210, 117, 25);
		Habilidades_Panel.add(Descricao_btn);
		
		img_pokemon = new JLabel("");
		img_pokemon.setIcon(new ImageIcon(Habilidades_Pokemon.class.getResource("/view/Imagens/test.png")));
		img_pokemon.setBounds(177, 77, 89, 75);
		Habilidades_Panel.add(img_pokemon);
		
		int id = pokemon.getPoke_Id();
		changeImage(id);
		
		for(int i=0; i<pokemon.getHabilidades().size(); i++)
		{	
			Habilidade_choice.add(pokemon.getHabilidades().get(i).getKey());
		}
		
		setVisible(true);
	}
	
	public void changeImage(int id)
	{		
		try
		{
			URL url = new URL("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+id+".png");
			BufferedImage image = ImageIO.read(url);
			img_pokemon.setIcon(new ImageIcon(image));
		}
		catch (IllegalStateException | IOException e)
		{
			img_pokemon.setText("Este pokemon não tem imagem!");
		}
	}
}
