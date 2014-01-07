import java.net.*;
import java.io.*;
public class Serveur{

	
	private static void accueil(){
		
		System.out.println(" o========================================o");
		System.out.println("||   ___ ___ ___ _____ ___ ___ _   _ ___  ||");
		System.out.println("||  / __| __/ __|_   _|_ _| _ ) | | / __| ||");
		System.out.println("|| | (_ | _|\\__ \\ | |  | || _ \\ |_| \\__ \\ ||");
		System.out.println("||  \\___|___|___/ |_| |___|___/\\___/|___/ ||");
		System.out.println(" o==========  SERVEUR EDITION  ===========o");	
	}
	

	
	
	
	
	
	
	public static void main(String[] args) throws Exception{
		
		accueil();

	// Assignation du socket d'écoute
	ServerSocket s = new ServerSocket(6000);
	
	//Affichages écran
	
	System.out.println("Serveur de bus lancé.");
	System.out.println("Serveur prêt à recevoir des messages.");
	
	//Ecoute
		while(true){
			Socket soc=s.accept();
			
			ThreadClient th= new ThreadClient(soc);
			
			th.start();
		}
	}
}
