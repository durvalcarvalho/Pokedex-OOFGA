package controller;

import model.Treinador;
import view.Login_Tela;

public class Controller
{	
	public static void main(String[] args)
	{
		Controller controller = new Controller();

		controller.flow_GUI();
	}

	public void flow_GUI()
	{
		try
		{
			/* Tela de login é aberta*/
			Login_Tela login_tela = new Login_Tela(this);
		}
		
		catch(Exception e)
		{
			e.printStackTrace();			
		}
	}
	
	public void setTreinador(Treinador treinador)
	{
	}
	
	
}
