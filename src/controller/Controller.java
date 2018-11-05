package controller;

/*
Esta classe foi pensada para ser o ponto de partida da aplicação,
sua única função é abrir a tela de login e aguarda a aplicação ser fechada
*/

import view.Login_Tela;

public class Controller
{	
	public static void main(String[] args)
	{
		Controller controller = new Controller();

		// Abir a tela de login
		controller.flow_GUI();
	}

	public void flow_GUI()
	{
		Login_Tela login_tela = new Login_Tela();
	}	
}
