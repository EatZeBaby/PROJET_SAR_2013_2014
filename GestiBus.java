/*  

╔══════════════╦════════════════════════════════════════════════════════════╗
║  ( (         ║						2013-2014							║
║    ) )	   ║				Université Dauphine Paris 9					║
║  ........	   ║					Master 1 - MIAGE						║
║  |      |]   ║			Projet Systèmes & Algorithmes Répartis			║
║  \      /    ╟────────────────────────────────────────────────────────────╢
║   `----'     ║	Axel Richier - Thibault Schleret - Guillaume Fronczak   ║
╚══════════════╩════════════════════════════════════════════════════════════╝

*/

import java.net.*;
import java.io.*;
import java.util.ArrayList;


public class GestiBus{

	private static int NB_Bus=3;
	private static int NB_Lignes=3;
	private static int NB_Controleurs=1;
	private static int delaiEnvoiInfosBus=3000;
	private static int port_serveur=6000;
	
	private static ArrayList<Bus> TousLesBus  = new ArrayList<Bus>();
	private static ArrayList<Ligne> ToutesLesLignes  = new ArrayList<Ligne>();
	private static ArrayList<Controleur> TousLesControleurs  = new ArrayList<Controleur>();
	
	public static boolean debug=false;
	
	
	////////////////////////////////////////
	//									  //
	//  Fonction permettant de recuperer  //
	//	le bus dont le numero est passé	  //
	//	en paramètre  					  //
	//									  //
	////////////////////////////////////////
	public static Bus getBus(int i){
	
		return TousLesBus.get(i);
	}



	////////////////////////////////////////
	//									  //
	//  Fonction permettant de recuperer  //
	//	le controleur dont le numero est  //
	//	passé en paramètre  			  //
	//									  //
	////////////////////////////////////////
	public static Controleur get_Controleurs(int i){
	
		return TousLesControleurs.get(i);
	}
	
	
	
	////////////////////////////////////////
	//									  //
	//  Fonction permettant de recuperer  //
	//	la ligne dont le numero est passé //
	//	en paramètre  					  //
	//									  //
	////////////////////////////////////////
	
	public static Ligne getLigne(int i){
		return ToutesLesLignes.get(i);
	}
	
	
	
	////////////////////////////////////////
	//									  //
	//  Fonction permettant de recuperer  //
	//	le numéro de la ligne dont		  //
	//	l'identifiant est passé			  //
	//	en paramètre  					  //
	//									  //
	////////////////////////////////////////
	public static int getNumLigne(int i){
		
		return ToutesLesLignes.get(i).getNumLigne();
	
	}
	
	////////////////////////////////////////
	//									  //
	//  Fonction permettant de recuperer  //
	//	le nombre total de lignes du 	  //
	//	réseau	 	 					  //
	//									  //
	////////////////////////////////////////
	public static int get_nb_Lignes(){
		return NB_Lignes;
	}
	
	
	////////////////////////////////////////
	//									  //
	//  Fonction permettant de recuperer  //
	//	le nombre total de controleurs du //
	//	réseau	 	 					  //
	//									  //
	////////////////////////////////////////
	public static int get_nb_Controleurs(){
		return NB_Controleurs;
	}
	
	
	////////////////////////////////////////
	//									  //
	//  Fonctions d'affichage de l'écran  //
	//	d'accueil					 	  //
	//									  //
	////////////////////////////////////////
	public static void ecranAccueil(){
		clearConsole();
		System.out.println(Couleur.RED + " o========================================o");
		System.out.println("||"+Couleur.BLUE+"   ___ ___ ___ _____ ___ ___ _   _ ___  "+Couleur.RED+"||");
		System.out.println("||"+Couleur.BLUE+"  / __| __/ __|_   _|_ _| _ ) | | / __| "+Couleur.RED+"||");
		System.out.println("||"+Couleur.BLUE+" | (_ | _|\\__ \\ | |  | || _ \\ |_| \\__ \\ "+Couleur.RED+"||");
		System.out.println("||"+Couleur.BLUE+"  \\___|___|___/ |_| |___|___/\\___/|___/ "+Couleur.RED+"||");
		System.out.println("||"+Couleur.BLUE+"                                        "  +Couleur.RED+"||");
		System.out.println(" o========================================o"+Couleur.RESET);
	}
	
	public static void demarrage() throws Exception{
		System.out.print("\nDémarrage Application");
		Thread.sleep(400);
		System.out.print(".");
		Thread.sleep(300);
		System.out.print(".");
		Thread.sleep(700);
		System.out.print(".");
	
	}
	
	
	////////////////////////////////////////
	//									  //
	//  Fonctions initialisant tous les   //
	//	controleurs et les ajoutant à la  //
	//	liste des controleurs			  //
	//									  //
	////////////////////////////////////////

	public static void initControleur()throws IOException{
		
		int i;
		for(i=0;i<NB_Controleurs;i++){
			TousLesControleurs.add(new Controleur(i,6001+i));
		} 
		if(TousLesControleurs.size()==NB_Controleurs){
			System.out.println("["+Couleur.GREEN+"OK"+Couleur.RESET+"] Création des "+ NB_Controleurs +" contrôleurs.");
		}else{
			System.out.println("[Erreur] Création des "+ NB_Controleurs +" contrôleurs.");
		}
		
	
	}
	
	
	////////////////////////////////////////
	//									  //
	//  Fonctions initialisant tous les   //
	//	bus et les ajoutant à la liste 	  //
	//	de tous les bus 				  //
	//									  //
	////////////////////////////////////////
	
	public static void initBus()throws Exception{
		
		int cpt=0;
		int i;
		for(i=0;i<NB_Bus;i++){
			cpt=i%NB_Lignes;
			TousLesBus.add(new Bus(i,cpt));
		} 
		if(TousLesBus.size()==NB_Bus){
			System.out.println("["+Couleur.GREEN+"OK"+Couleur.RESET+"] Création des "+NB_Bus+" bus.");
		}else{
			System.out.println("[Erreur] Création des "+NB_Bus+" bus.");
		}
		
	
	}
	
	
	////////////////////////////////////////
	//									  //
	//  Fonctions initialisant toutes les //
	//	lignes et les ajoutant à la liste //
	//	de toutes les lignes			  //
	//									  //
	////////////////////////////////////////
	public static void initLigne(){
		
		int i;
		for(i=0;i<NB_Lignes;i++){
			ToutesLesLignes.add(new Ligne(i+1));
			if(debug)
				ToutesLesLignes.get(i).afficher_ligne();*/
		} 
				if(ToutesLesLignes.size()==NB_Lignes){
			System.out.println("["+Couleur.GREEN+"OK"+Couleur.RESET+"] Création des "+NB_Lignes+" lignes.");
		}else{
			System.out.println("[Erreur] Création des "+NB_Lignes+" lignes.");
		}
		
	
	}
	
	
	
	////////////////////////////////////////
	//									  //
	//  Fonction verifiant que l'instance //
	//	du serveur est bien lancée		  //
	//									  //
	////////////////////////////////////////
	
	public static void verifServeur() throws Exception{
		System.out.println("Vérification du lancement du Serveur.");
		
		
		Socket socket = new Socket("localhost", port_serveur);
		PrintWriter sortie = new PrintWriter(socket.getOutputStream(),true);
		BufferedReader entree = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		
		sortie.println("lancé?"); //Envoi du message
		String rep=entree.readLine();//Attente d'une réponse
		
		if(rep.equals("OK")){
			System.out.println("["+Couleur.GREEN+"OK"+Couleur.RESET+"] Connexion au serveur réussie sur le port "+ port_serveur+".");
			
		}
		if(debug)
			System.out.println("Le client a reçu : " + rep);
	
	
	}
	
	
	
	////////////////////////////////////////
	//									  //
	//  Fonction repartissant les bus 	  //
	//	sur l'ensemble des lignes		  //
	//									  //
	////////////////////////////////////////
	public static void affecterBus(){
		int cpt=0;
		
		for(int i=0; i<NB_Bus;i++){
			
			cpt=i%NB_Lignes;
			
			TousLesBus.get(i).surLigne(cpt);
		}
	
	}
	
	////////////////////////////////////////
	//									  //
	//  Fonction retournant le délai	  //
	//	(inutile dans derniere version)	  //
	//	(gardée à des fins de debugs) 	  //
	//									  //
	////////////////////////////////////////
	public static int getDelai(){
	
		return delaiEnvoiInfosBus;
	}
	
	////////////////////////////////////////
	//									  //
	//  Fonction démarrant les threads    //
	//	de type Bus						  //
	//									  //
	////////////////////////////////////////
	public static void lancerBus(){
		for(int i=0;i<NB_Bus;i++){
			TousLesBus.get(i).depart();
		}
		
	
	}
	
	////////////////////////////////////////
	//									  //
	//  Fonction démarrant les threads    //
	//	de type Controleurs				  //
	//									  //
	////////////////////////////////////////
	public static void lancerControleurs(){
		for(int i=0;i<NB_Controleurs;i++){
			TousLesControleurs.get(i).proc.start();
			
		}
	
	}
	
	
	
	////////////////////////////////////////
	//									  //
	//  Fonction terminant les threads    //
	//	de type Controleurs				  //
	//									  //
	////////////////////////////////////////
	public static void terminerControleurs(){
		
		//System.out.println("Fin de journée les gars.");
		for(int i=0;i<NB_Bus;i++){
			TousLesControleurs.get(i).fin_de_journee();
			
		}
		if (debug)
			System.out.println("Fin Controleurs.");
		
	}
	
	////////////////////////////////////////
	//									  //
	//  Fonction terminant les threads    //
	//	de type Bus						  //
	//									  //
	////////////////////////////////////////
	public static void terminerBus(){
		
		//System.out.println("Fin de journée pour vous les bus.");
		for(int i=0;i<NB_Bus;i++){
			TousLesBus.get(i).fin_de_journee();
			
		}
		//System.out.println("Fin Bus.");
		
	}
	
	
	
	
	////////////////////////////////////////
	//									  //
	//  			 MAIN				  //
	//									  //
	////////////////////////////////////////
	public static void main(String[] args) throws Exception{
		
		
		if(args[0].equals("debug"))
			debug=true;
		else if(args[0].equals("normal"))
			debug=false;

		try
			 {
			 	ecranAccueil();
				verifServeur();
				Thread.sleep(500);
				initLigne();
				Thread.sleep(500);
				initBus();
				Thread.sleep(500);
				initControleur();
				Thread.sleep(500);
				demarrage();
				
				Database database=new Database(NB_Lignes,NB_Controleurs);
				//database.start();
				affecterBus();
				lancerControleurs();
				//lancerBus();
				
				
				if(!debug){
					Thread.sleep(900);
					Affichage affich= new Affichage(0);
					Affichage listen= new Affichage(1);
					affich.start();
					listen.start();
				}
				Thread.sleep(10000);
				
			 }
			 
			catch
			 (InterruptedException e) {
			
				
			 }
			 
				terminerBus();
		//terminerControleurs();
	
	
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
}