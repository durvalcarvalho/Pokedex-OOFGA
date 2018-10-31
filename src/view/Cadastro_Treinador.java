package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import java.awt.Choice;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Cadastro_Treinador extends JFrame {

	private JPanel Cadastro_Panel;
	private JTextField Nome_textField;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private static Cadastro_Treinador frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					frame = new Cadastro_Treinador();
					frame.setVisible(true);
				}
				
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public Cadastro_Treinador() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 150, 650, 500);
		Cadastro_Panel = new JPanel();
		Cadastro_Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Cadastro_Panel);
		Cadastro_Panel.setLayout(null);
		
		JLabel New_trainer_lbl = new JLabel("Cadastrar Novo Treinador");
		New_trainer_lbl.setFont(new Font("Ubuntu Mono", Font.BOLD, 25));
		New_trainer_lbl.setBounds(160, 45, 315, 30);
		Cadastro_Panel.add(New_trainer_lbl);
		
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Ubuntu Mono", Font.BOLD, 20));
		lblNome.setBounds(60, 125, 70, 15);
		Cadastro_Panel.add(lblNome);
		
		Nome_textField = new JTextField();
		Nome_textField.setBounds(180, 125, 300, 20);
		Cadastro_Panel.add(Nome_textField);
		Nome_textField.setColumns(10);
		
		JLabel Idade_label = new JLabel("Idade:");
		Idade_label.setFont(new Font("Ubuntu Mono", Font.BOLD, 20));
		Idade_label.setBounds(60, 185, 70, 15);
		Cadastro_Panel.add(Idade_label);
		
		Choice Idade_choice = new Choice();
		for(int i=6; i<101; i++) { Idade_choice.add(""+i); }
		Idade_choice.setBounds(180, 185, 300, 20);
		Cadastro_Panel.add(Idade_choice);
		
		JLabel Regiao_label = new JLabel("Região:");
		Regiao_label.setFont(new Font("Ubuntu Mono", Font.BOLD, 20));
		Regiao_label.setBounds(60, 245, 70, 15);
		Cadastro_Panel.add(Regiao_label);
		
		Choice Regiao_choice = new Choice();
		String[] regioes = {"Kanto","Johto","Hoenn","Sinnoh","Unova",
				"Kalos","Sevii Islands","Orre","Almia","Ilhas Laranja"};
		for(int i=0; i<regioes.length; i++) { Regiao_choice.add(regioes[i]); }
		Regiao_choice.setBounds(180, 245, 300, 20);
		Cadastro_Panel.add(Regiao_choice);
		
		JLabel Cidade_label = new JLabel("Cidade:");
		Cidade_label.setFont(new Font("Ubuntu Mono", Font.BOLD, 20));
		Cidade_label.setBounds(60, 305, 70, 15);
		Cadastro_Panel.add(Cidade_label);
		
		Choice Cidade_choice = new Choice();
		Cidade_choice.setBounds(180, 305, 300, 20);
		String[] cidades = {"Pallet Town", "Viridian City","Pewter City",
				"Cerulean City","Vermilion City","Lavender Town",
				"Celadon City", "Fuchsia City", "Saffron City"};
		for(int i=0; i<cidades.length; i++) { Cidade_choice.add(cidades[i]); }
		Cadastro_Panel.add(Cidade_choice);
		
		JLabel Genero_label = new JLabel("Gênero:");
		Genero_label.setFont(new Font("Ubuntu Mono", Font.BOLD, 20));
		Genero_label.setBounds(60, 365, 70, 15);
		Cadastro_Panel.add(Genero_label);
		
		JRadioButton Masculino_Option = new JRadioButton("Masculino ♂");
		Masculino_Option.setFont(new Font("Dialog", Font.BOLD, 14));
		buttonGroup.add(Masculino_Option);
		Masculino_Option.setBounds(180, 365, 150, 20);
		Cadastro_Panel.add(Masculino_Option);
		
		JRadioButton Feminino_Option = new JRadioButton("Feminino ♀");
		Feminino_Option.setFont(new Font("Dialog", Font.BOLD, 14));
		buttonGroup.add(Feminino_Option);
		Feminino_Option.setBounds(360, 365, 150, 20);
		Cadastro_Panel.add(Feminino_Option);
		
		JLabel Mensagem_Erro_lbl = new JLabel("");
		Mensagem_Erro_lbl.setForeground(Color.RED);
		Mensagem_Erro_lbl.setBounds(150, 90, 350, 15);
		Cadastro_Panel.add(Mensagem_Erro_lbl);
		
		JButton Cadastrar_Button = new JButton("Cadastrar");
		Cadastrar_Button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{				
				String nome = Nome_textField.getText();
				int idade = Integer.parseInt(Idade_choice.getSelectedItem());
				String regiao = Regiao_choice.getSelectedItem();
				
				String genero;
				if(Masculino_Option.isSelected()) genero = "Masculino ♂";
				else if (Feminino_Option.isSelected()) genero = "Feminino ♀";
				else genero = "";
				
				String cidade = Cidade_choice.getSelectedItem();
				
				if(nome != "" && genero != "")
				{
					Pokedex ct = new Pokedex();
					// TODO: Mudar o construtor da pokedex
					//Pokedex ct = new Pokedex(nome, idade, genero, cidade, regiao);
					ct.setVisible(true);
					frame.dispose();	
				}
				
				else
				{
					Mensagem_Erro_lbl.setText("Preencha todas as opções para se cadastrar!");					
				}
			}
		});
		
		Cadastrar_Button.setBounds(260, 430, 120, 25);
		Cadastro_Panel.add(Cadastrar_Button);
	}
}
