package view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.Treinador;

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

@SuppressWarnings("serial")
public class Cadastro_Treinador extends JFrame
{	
	public Cadastro_Treinador()
	{
		// Configurações da tela de Cadastro
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(350, 150, 650, 500);
		JPanel Cadastro_Panel = new JPanel();
		Cadastro_Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Cadastro_Panel);
		Cadastro_Panel.setLayout(null);
		
		// Label "Cadastro novo treinador"
		JLabel New_trainer_lbl = new JLabel("Cadastrar Novo Treinador");
		New_trainer_lbl.setFont(new Font("Ubuntu Mono", Font.BOLD, 25));
		New_trainer_lbl.setBounds(160, 45, 315, 30);
		Cadastro_Panel.add(New_trainer_lbl);
		
		// Label "nome:"
		JLabel lblNome = new JLabel("Nome:");
		lblNome.setFont(new Font("Ubuntu Mono", Font.BOLD, 20));
		lblNome.setBounds(60, 125, 70, 15);
		Cadastro_Panel.add(lblNome);
		
		// Caixa de texto para receber o nome digitado
		JTextField Nome_textField = new JTextField();
		Nome_textField.setBounds(180, 125, 300, 20);
		Cadastro_Panel.add(Nome_textField);
		Nome_textField.setColumns(10);
		
		// label "idade:"
		JLabel Idade_label = new JLabel("Idade:");
		Idade_label.setFont(new Font("Ubuntu Mono", Font.BOLD, 20));
		Idade_label.setBounds(60, 185, 70, 15);
		Cadastro_Panel.add(Idade_label);
		
		// Choice para receber a idade
		Choice Idade_choice = new Choice();
		for(int i=6; i<101; i++) { Idade_choice.add(""+i); }
		Idade_choice.setBounds(180, 185, 300, 20);
		Cadastro_Panel.add(Idade_choice);
		
		// label "região:"
		JLabel Regiao_label = new JLabel("Região:");
		Regiao_label.setFont(new Font("Ubuntu Mono", Font.BOLD, 20));
		Regiao_label.setBounds(60, 245, 70, 15);
		Cadastro_Panel.add(Regiao_label);
		
		// choice para receber a região
		Choice Regiao_choice = new Choice();
		String[] regioes = {"Kanto","Johto","Hoenn","Sinnoh","Unova",
				"Kalos","Sevii Islands","Orre","Almia","Ilhas Laranja"};
		for(int i=0; i<regioes.length; i++) { Regiao_choice.add
			(regioes[i]); }
		Regiao_choice.setBounds(180, 245, 300, 20);
		Cadastro_Panel.add(Regiao_choice);
		
		// label "cidade:"
		JLabel Cidade_label = new JLabel("Cidade:");
		Cidade_label.setFont(new Font("Ubuntu Mono", Font.BOLD, 20));
		Cidade_label.setBounds(60, 305, 70, 15);
		Cadastro_Panel.add(Cidade_label);
		
		// choice para receber a cidade
		Choice Cidade_choice = new Choice();
		Cidade_choice.setBounds(180, 305, 300, 20);
		String[] cidades = {"Pallet", "Viridian","Pewter",
				"Cerulean","Vermilion","Lavender",
				"Celadon", "Fuchsia", "Saffron"};
		for(int i=0; i<cidades.length; i++) { Cidade_choice.add
			(cidades[i]); }
		Cadastro_Panel.add(Cidade_choice);
		
		// label "genero:"
		JLabel Genero_label = new JLabel("Gênero:");
		Genero_label.setFont(new Font("Ubuntu Mono", Font.BOLD, 20));
		Genero_label.setBounds(60, 365, 70, 15);
		Cadastro_Panel.add(Genero_label);
		
		// Grupo de botões excludentes, masculino ou feminino
		ButtonGroup buttonGroup = new ButtonGroup();
		
		// RadioButton Masculino
		JRadioButton Masculino_Option = new JRadioButton("Masculino ♂");
		Masculino_Option.setFont(new Font("Dialog", Font.BOLD, 14));
		Masculino_Option.setBounds(180, 365, 150, 20);
		buttonGroup.add(Masculino_Option);
		Cadastro_Panel.add(Masculino_Option);
		
		// RadioButton Feminino
		JRadioButton Feminino_Option = new JRadioButton("Feminino ♀");
		Feminino_Option.setFont(new Font("Dialog", Font.BOLD, 14));
		Feminino_Option.setBounds(360, 365, 150, 20);
		buttonGroup.add(Feminino_Option);
		Cadastro_Panel.add(Feminino_Option);		
		
		// Label que poderá receber mensagem de erro caso o usuário não preencha algo 
		JLabel Mensagem_Erro_lbl = new JLabel("");
		Mensagem_Erro_lbl.setForeground(Color.RED);
		Mensagem_Erro_lbl.setBounds(150, 90, 350, 15);
		Cadastro_Panel.add(Mensagem_Erro_lbl);
		Mensagem_Erro_lbl.setVisible(false);
		
		// Butão que irá validar o formulário
		JButton Cadastrar_Button = new JButton("Cadastrar");
		Cadastrar_Button.setBounds(260, 430, 120, 25);
		Cadastro_Panel.add(Cadastrar_Button);
		
		Cadastrar_Button.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				// Coleta dos dados digitados / marcados
				String nome = Nome_textField.getText();
				int idade = Integer.parseInt(Idade_choice.getSelectedItem());
				String regiao = Regiao_choice.getSelectedItem();
				String cidade = Cidade_choice.getSelectedItem();
				
				// Coleta de qual Option Button foi marcado
				String genero;
				if(Masculino_Option.isSelected()) genero = "Masculino ♂";
				else if (Feminino_Option.isSelected()) genero = "Feminino ♀";
				else genero = "";				
				
				// Se o nome ou o genero não estiver em branco
				if(!nome.isEmpty() && !genero.isEmpty())
				{
					// Um treinador é construido com as informações coletadas
					Treinador treinador = new Treinador(nome, genero, cidade, regiao, idade);
					
					// A tela pekedex é construida com as informações do novo treinador
					Pokedex ct = new Pokedex(treinador);
					ct.setVisible(true);
					
					// A tela de cadastro fica indisponível
					setVisible(false);
				}
				
				// Caso o nome ou o gênero estaja em branco
				else
				{
					// O label vazio recebe o texto de error
					Mensagem_Erro_lbl.setText("Preencha todas as opções para se cadastrar!");
					Mensagem_Erro_lbl.setVisible(true);
				}
			}
		});
		
		// Após construir todos componentes a tela se torna visível
		setVisible(true);
	}
}
