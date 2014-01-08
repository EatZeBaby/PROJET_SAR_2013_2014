/*  .-----------------------------------------------------------------.
 /  .-.                                                         .-.  \
|  /   \                                                       /   \  |
| |\_.  |                                                     |    /| |
|\|  | /|                                                     |\  | |/|
| `---' |                                                     | `---' |
|       |                                                     |       | 
|       |-----------------------------------------------------|       |
\       |                                                     |       /
 \     /                                                       \     /
  `---'                                                         `---'

*/

import java.net.*;
import java.io.*;


public class Serveur {
	

	
	private static void accueil(){
		clearConsole();
		System.out.println(Couleur.RED + " o========================================o");
		System.out.println("||"+Couleur.BLUE+"   ___ ___ ___ _____ ___ ___ _   _ ___  "+Couleur.RED+"||");
		System.out.println("||"+Couleur.BLUE+"  / __| __/ __|_   _|_ _| _ ) | | / __| "+Couleur.RED+"||");
		System.out.println("||"+Couleur.BLUE+" | (_ | _|\\__ \\ | |  | || _ \\ |_| \\__ \\ "+Couleur.RED+"||");
		System.out.println("||"+Couleur.BLUE+"  \\___|___|___/ |_| |___|___/\\___/|___/ "+Couleur.RED+"||");
		System.out.println("||"+Couleur.BLUE+"                                        "  +Couleur.RED+"||");
		System.out.println(" o========================================o");
		System.out.println(" o==========  SERVEUR EDITION  ===========o");
		System.out.println(" o========================================o"+Couleur.RESET);	
		


	}


	////////////////////////////////////////
	//									  //
	//  Fonction de nettoyage de l'écran  //
	//									  //
	////////////////////////////////////////
	private static void clearConsole(){
	    try{
	        	System.out.print("\033[H\033[2J");
	            System.out.flush();
	    }	    catch (Exception e){}
	}
	
	
	
	
	////////////////////////////////////////
	//									  //
	//   Fonction Principale du Serveur   //
	//									  //
	////////////////////////////////////////	
	public static void main(String[] args) throws Exception{
	
	accueil(); 
	
	ServerSocket s = new ServerSocket(6000);// Assignation du socket d'écoute
	System.out.println("["+Couleur.GREEN+"OK"+Couleur.RESET+"] Serveur de bus lancé. Prêt à recevoir des messages."); //Affichages écran

	
	//Boucle d'écoute des requêtes.
		while(true){
			
			Socket soc=s.accept();
			ThreadClient th= new ThreadClient(soc);
			th.start();
		}
	}
}
