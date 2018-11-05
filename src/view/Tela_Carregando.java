package view;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

@SuppressWarnings("serial")
public class Tela_Carregando extends JFrame
{	
	// 0 Até 100
	private int progress;
	
	/**
	 * Esta classe implementa uma tela de loading que irá 
	 * continuar aberta enquanto o atributo progress for menor que 97
	 */
	public Tela_Carregando(/*int time_need_in_seconds*/)
	{
		// Esconder os butões da barra superior do JDialog
		setUndecorated(true);
		
		// Esta Tela sempre será vista por cima de todas as outras
		setAlwaysOnTop(true);
		
		// Configurações da tela
		setBounds(625, 350, 110, 135);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
		// label que irá receber gif de loaging
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Tela_Carregando.class.getResource("/view/Imagens/loading.gif")));
		label.setBounds(5, 5, 100, 100);
		contentPane.add(label);
		
		// Progess Bar que irá carregar
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(3, 112, 105, 20);
		progressBar.setStringPainted(true);
		progressBar.setValue(0);;
		contentPane.add(progressBar);
		
		// Método para a barra de progresso carregar
		@SuppressWarnings("rawtypes")
		final SwingWorker load = new SwingWorker()
		{
			protected Object doInBackground() throws Exception
			{
				while(progress < 97)
				{
					try
					{
						progressBar.setValue(progress);
						progressBar.setString(progress+" %");
						
						Thread.sleep(1); // 1 segundo
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					
				}
				
				setVisible(false);
				
				return 0;
			}
		};
		
		load.execute();	
	}
	
	public void setPorcentagem(int progress) { this.progress = progress; }
}