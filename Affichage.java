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
	
	private void Menu(){
		System.out.println(Couleur.BLUE+"["+Couleur.PURPLE+"M"+Couleur.BLUE+"]enu Principal | ["+Couleur.BLUE+"["+Couleur.PURPLE+"C"+Couleur.BLUE+"]ontroleurs ");
		System.out.println("============================================="+Couleur.RESET);
	
	}
	private void startAffichage()throws Exception{
	etat="menu_principal";
	
	
		while(!etat.equals("fin")){
			
			Thread.sleep(1000);
		
			if (etat.equals("menu_principal")){
				MenuPrincipal();
			}
			if (etat.equals("controleurs")){
				affichControl();
			}
		
		}
	
	
	}
	
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
			
			}
			
		
		}
		
		
	
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
				
				System.out.println("    Bus n°"+num_bus+" | position : "+ pos_bus + " | vitesse : "+ vit_bus);
			
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
		}//*/	
	
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