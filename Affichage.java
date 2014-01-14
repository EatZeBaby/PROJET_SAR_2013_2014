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


public class Affichage extends Thread{

	private static String etat="";
	private  String role="";
	
	
	public Affichage(int i){
		if (i==0)
			this.role="affichage";
		if (i==1)
			this.role="listen";
	}
	
	private void modifBus(){
		
		
		String input="";
		
		System.out.print("Enrez le N° du Bus à modifier");
		Console console = System.console();
		input = console.readLine();
		
		int n= Integer.parseInt(input); 
		
		do{
			GestiBus.ecranAccueil();
			Menu();
			
			System.out.println("Vous souhaitez modifier le bus :" +n );
			
			
			input = console.readLine();
			
		}while(!input.equals("OK"));
		
	}
	
	
	private void Menu(){
		System.out.println(Couleur.BLUE+"["+Couleur.PURPLE+"M"+Couleur.BLUE+"]enu Principal | "+Couleur.BLUE+"["+Couleur.PURPLE+"C"+Couleur.BLUE+"]ontroleurs | "+Couleur.BLUE+"["+Couleur.PURPLE+"E"+Couleur.BLUE+"]venements ");
		System.out.println("============================================="+Couleur.RESET);
	
	}
	private void startAffichage()throws Exception{
	etat="menu_principal";
	
	
		while(!etat.equals("fin")){
			
			Thread.sleep(500);
		
			if (etat.equals("menu_principal")){
				MenuPrincipal();
			}
			if (etat.equals("controleurs")){
				affichControl();
			}
			if (etat.equals("events")){
				affichEvents();
			}
			
		
		}
	
	
	}
	
	private void retourMenu(){
		String input="";
		Console console = System.console();
		
		System.out.print("Retour Au Menu Principal? [O/N]")	;
		
		input = console.readLine();
	
	}
	private void affichEvents(){
		GestiBus.ecranAccueil();
		Menu();
		String input="";
		
			int nb_events=Events.get_nb_events();
			
			for(int i = 0 ; i < nb_events ; i++){
				Events e=Events.get_event(i);
				String ind="";;
				if(e.get_type().equals("panne")){
					if(i==0)
						ind="P";
					
					System.out.println("["+ind+"]"+e.get_type()+" sur bus "+e.get_num_bus()+"" );}
				if(e.get_type().equals("bouchon"))
					if(i==1)
						ind="B";
					System.out.println("["+ind+"]"+e.get_type()+" sur arret "+ GestiBus.getLigne(e.get_num_ligne()).getArret(e.get_num_arret())+" de la ligne "+e.get_num_ligne()+"." );
			}
			
		
			//GestiBus.ecranAccueil();
			
			//input = console.readLine("Enter input :");
			
							
		}//*/
		
		

	
	
	private void affichControl(){
		GestiBus.ecranAccueil();
		Menu();
		int nb_controleurs=GestiBus.get_nb_Controleurs();
		String nom_ctrl;
		int nb_lignes_a_charge;
		Controleur ctrl;
		int num_ligne;
		
		for(int i = 0 ; i < nb_controleurs ; i++){
			ctrl=GestiBus.get_Controleurs(i);
			nom_ctrl=ctrl.getNom();
			System.out.println(nom_ctrl);
			System.out.println("_______________________");
			
			nb_lignes_a_charge=ctrl.nb_lignes_a_charge();
			
			for( int j=0; j< nb_lignes_a_charge; j++){
				
				num_ligne=GestiBus.getNumLigne(ctrl.bus_a_charge(j));
								
				System.out.println("    Ligne n°"+num_ligne+"" );
				System.out.println("    ───────────────────" );
				
					for(int k=0; k< GestiBus.getLigne(num_ligne-1).nbBusSurLigne();k++){
						System.out.println("        Bus n°"+GestiBus.getLigne(num_ligne-1).getNumBus(k) );
					
					}
				System.out.println();
			
			}
			
			
		}
		
		//modifBus();
	
	}
	private void MenuPrincipal(){
		
		
		GestiBus.ecranAccueil();
		Menu();
		
		int nb_lignes=GestiBus.get_nb_Lignes();
		int num_ligne;
		int nb_bus;
		int num_bus;
		String pos_bus;
		int vit_bus;
		
		for(int i = 0 ; i < nb_lignes ; i++){
			
			num_ligne=GestiBus.getNumLigne(i);
			
			System.out.println("Ligne n°"+num_ligne+" : ");
			System.out.println("_______________________");
			
			nb_bus = GestiBus.getLigne(i).nbBusSurLigne();
			
			for( int j=0; j< nb_bus; j++){
				
				
				num_bus=GestiBus.getLigne(i).getNumBus(j);
				
				pos_bus=GestiBus.getLigne(i).getBus(j).getPos();
				
				vit_bus=GestiBus.getLigne(i).getBus(j).getVitesse();
				
				System.out.println("    Bus n°"+num_bus+"\t | position : "+ pos_bus + "\t\t| vitesse : "+ vit_bus);
				//System.out.format("%32s%10d%16s", "    Bus n°"+num_bus, " | position : "+ pos_bus , "| vitesse : "+ vit_bus);
			}//*/
		
			
		}
	
	}
	
	private void listen(){
		String input="";
		while(!input.equals("fin")){
			//GestiBus.ecranAccueil();
			Console console = System.console();
			input = console.readLine();
			//input = console.readLine("Enter input :");
			if (input.equals("C"))
				etat="controleurs";
			if (input.equals("M"))
				etat="menu_principal";
			if (input.equals("E"))
				etat="events";
			if (input.equals("P")){
				Events.get_event(0).start();
				etat="menu_principal";
			}
			if (input.equals("B")){
				Events.get_event(0).start();
				etat="menu_principal";
			}
				
			
				 //*/
			}
		}
			
			

							


	
	public void run(){
		
		
		if(role.equals("affichage")){
			try{
				startAffichage();
			}catch(Exception e){}
		}
		else if (role.equals("listen")){
			listen();
		}	
				
			
	}	
}