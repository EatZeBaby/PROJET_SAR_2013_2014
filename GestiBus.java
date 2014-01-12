//Projet SAR 2013-2014

import java.net.*;
import java.io.*;
import java.util.ArrayList;


public class GestiBus{

	private static int NB_Bus=2;
	private static int NB_Lignes=2;
	private static int NB_Controleurs=2;
	private static int delaiEnvoiInfosBus=3000;
	private static int port_serveur=6000;
	
	private static ArrayList<Bus> TousLesBus  = new ArrayList<Bus>();
	private static ArrayList<Ligne> ToutesLesLignes  = new ArrayList<Ligne>();
	private static ArrayList<Controleur> TousLesControleurs  = new ArrayList<Controleur>();
	
	public static boolean debug=false;
	
	
	
	public static Controleur get_Controleurs(int i){
	
		return TousLesControleurs.get(i);
	}
	
	
	public static Ligne getLigne(int i){
		return ToutesLesLignes.get(i);
	}
	
	public static int getNumLigne(int i){
		
		return ToutesLesLignes.get(i).getNumLigne();
	
	}
	
	public static int get_nb_Lignes(){
		return NB_Lignes;
	}
	
	public static int get_nb_Controleurs(){
		return NB_Controleurs;
	}
	
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
	public static void initLigne(){
		
		int i;
		for(i=0;i<NB_Lignes;i++){
			ToutesLesLignes.add(new Ligne(i+1));
			//DEBUG 
			/*ToutesLesLignes.get(i).afficher_ligne();*/
		} 
				if(ToutesLesLignes.size()==NB_Lignes){
			System.out.println("["+Couleur.GREEN+"OK"+Couleur.RESET+"] Création des "+NB_Lignes+" lignes.");
		}else{
			System.out.println("[Erreur] Création des "+NB_Lignes+" lignes.");
		}
		
	
	}
	
	
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
		//System.out.println("Le client a reçu : " + rep);
	
	
	}
	public static void affecterBus(){
		int cpt=0;
		
		for(int i=0; i<NB_Bus;i++){
			
			cpt=i%NB_Lignes;
			
			TousLesBus.get(i).surLigne(cpt);
		}
	
	}
	
	
	
	public static int getDelai(){
	
		return delaiEnvoiInfosBus;
	}
	public static void lancerBus(){
		for(int i=0;i<NB_Bus;i++){
			//TousLesBus.get(i).proc.start();
			TousLesBus.get(i).depart();
		}
		
	
	}
	
	
	public static void lancerControleurs(){
		for(int i=0;i<NB_Controleurs;i++){
			TousLesControleurs.get(i).proc.start();
			
		}
	
	}
	public static void terminerControleurs(){
		
		//System.out.println("Fin de journée les gars.");
		for(int i=0;i<NB_Bus;i++){
			TousLesControleurs.get(i).fin_de_journee();
			
		}
		//System.out.println("Fin Controleurs.");
		
	}
	
	public static void terminerBus(){
		
		//System.out.println("Fin de journée pour vous les bus.");
		for(int i=0;i<NB_Bus;i++){
			TousLesBus.get(i).fin_de_journee();
			
		}
		//System.out.println("Fin Bus.");
		
	}
	
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
				initBus();
				Thread.sleep(500);
				initLigne();
				Thread.sleep(500);
				initControleur();
				Thread.sleep(500);
				demarrage();
				
				Database database=new Database(NB_Lignes,NB_Controleurs);
				//database.start();
				affecterBus();
				lancerControleurs();
				lancerBus();
				
				
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