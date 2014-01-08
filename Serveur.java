import java.net.*;
import java.io.*;

public class Serveur{
	
public static final String RESET = "\u001B[0m";
public static final String BLACK = "\u001B[30m";
public static final String RED = "\u001B[31m";
public static final String GREEN = "\u001B[32m";
public static final String YELLOW = "\u001B[33m";
public static final String BLUE = "\u001B[34m";
public static final String PURPLE = "\u001B[35m";
public static final String CYAN = "\u001B[36m";
public static final String WHITE = "\u001B[37m";
	
	private static void accueil(){
		clearConsole();
		System.out.println(RED + " o========================================o");
		System.out.println("||"+BLUE+"   ___ ___ ___ _____ ___ ___ _   _ ___  "+RED+"||");
		System.out.println("||"+BLUE+"  / __| __/ __|_   _|_ _| _ ) | | / __| "+RED+"||");
		System.out.println("||"+BLUE+" | (_ | _|\\__ \\ | |  | || _ \\ |_| \\__ \\ "+RED+"||");
		System.out.println("||"+BLUE+"  \\___|___|___/ |_| |___|___/\\___/|___/ "+RED+"||");
		System.out.println("||"+BLUE+"                                        "  +RED+"||");
		System.out.println(" o========================================o");
		System.out.println(" o==========  SERVEUR EDITION  ===========o");
		System.out.println(" o========================================o"+RESET);	
		


	}
	

	private static void clearConsole(){
	    try{
	        
	        	System.out.print("\033[H\033[2J");
	            System.out.flush();
	        
	    }
	    catch (Exception e)
	    {
	        //   exception.
	    }
	}
	
	
	
	
	
	public static void main(String[] args) throws Exception{
		
		accueil();

	// Assignation du socket d'écoute
	ServerSocket s = new ServerSocket(6000);
	
	//Affichages écran
	
	System.out.println("["+GREEN+"OK"+RESET+"] Serveur de bus lancé. Prêt à recevoir des messages.");

	
	//Ecoute
		while(true){
			Socket soc=s.accept();
			
			ThreadClient th= new ThreadClient(soc);
			
			th.start();
		}
	}
}
