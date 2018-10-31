package view;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Habilidades_Pokemon extends JFrame
{

	private JPanel Habilidades_Panel;

	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					Habilidades_Pokemon frame = new Habilidades_Pokemon();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public Habilidades_Pokemon()
	{
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
		
		// TODO: Criar um método para mudar o nome do pokemon
		JLabel Pokemon_Name_lbl = new JLabel("Pikachu");
		Pokemon_Name_lbl.setForeground(Color.BLUE);
		Pokemon_Name_lbl.setFont(new Font("Ubuntu Mono", Font.BOLD, 14));
		Pokemon_Name_lbl.setBounds(75, 5, 200, 15);
		Habilidades_Panel.add(Pokemon_Name_lbl);
		
		JLabel Habilidades_lbl = new JLabel("Habilidades");
		Habilidades_lbl.setFont(new Font("Ubuntu Mono", Font.BOLD, 25));
		Habilidades_lbl.setBounds(147, 45, 160, 20);
		Habilidades_Panel.add(Habilidades_lbl);
		
		// TODO: Este painel irá receber os botões que acionam a decrição da habilidade
		JPanel Nome_Habilidade_panel = new JPanel();
		Nome_Habilidade_panel.setBounds(75, 130, 300, 100);
		Habilidades_Panel.add(Nome_Habilidade_panel);
		
		JTextPane Descricao_txtpanel = new JTextPane();
		Descricao_txtpanel.setText("The target is hit with a destructive shock wave that\\nalways inflicts 20 HP damage.");
		Descricao_txtpanel.setBounds(75, 263, 300, 100);
		Habilidades_Panel.add(Descricao_txtpanel);
		
		JButton Voltar_btn = new JButton("Voltar");
		Voltar_btn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				//TODO: Fechar a tela e voltar para a pokedex
			}
		});
		Voltar_btn.setBounds(160, 425, 120, 25);
		Habilidades_Panel.add(Voltar_btn);
	}
}
