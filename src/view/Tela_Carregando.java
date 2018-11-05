package view;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;

@SuppressWarnings("serial")
public class Tela_Carregando extends JFrame implements Runnable
{
	private JProgressBar progressBar;
	//private int time_need_in_seconds;
	
	// TODO: mudar o nome da variável
	private int progress;

	public static void main(String[] args)
	{
		new Tela_Carregando(/*10*/);
	}

	private JPanel contentPane;

	//public Tela_Carregando(/*int time_need_in_seconds*/)
	public void run()
	{
		//this.time_need_in_seconds = time_need_in_seconds;
		new Thread(this).start();
	}
	
	//public void run()
	public Tela_Carregando(/*int time_need_in_seconds*/)
	{
		// Tirar os butões (x) de fechar do JDialog
		setUndecorated(true);
		setVisible(true);
		setAlwaysOnTop(true);
		
		setBounds(625, 350, 110, 135);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Tela_Carregando.class.getResource("/view/Imagens/loading.gif")));
		label.setBounds(5, 5, 100, 100);
		contentPane.add(label);
		
		progressBar = new JProgressBar();
		progressBar.setBounds(3, 112, 105, 20);
		progressBar.setStringPainted(true);
		progressBar.setValue(0);;
		contentPane.add(progressBar);
		
		@SuppressWarnings("rawtypes")
		final SwingWorker load = new SwingWorker()
		{
			protected Object doInBackground() throws Exception
			{
				//for(int i=0;i<=100; i++)
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