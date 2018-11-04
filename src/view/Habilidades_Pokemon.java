package view;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import model.Pokemon;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Choice;

//TODO: Colocar uma imagem do pokemon em questão

@SuppressWarnings("serial")
public class Habilidades_Pokemon extends JFrame
{

	private JPanel Habilidades_Panel;
	
	public Habilidades_Pokemon(Pokemon pokemon)
	{
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
		
		for(int i=0; i<pokemon.getHabilidades().size(); i++)
		{			
			Habilidade_choice.add(pokemon.getHabilidades().get(i).getKey());
		}
		
		setVisible(true);
	}
}
