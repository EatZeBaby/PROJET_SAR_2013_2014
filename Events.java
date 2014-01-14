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




public class Events extends Thread{
	private static ArrayList<Events> ListeDesEvents  = new ArrayList<Events>();
	
	private String type;
	private int num_bus;
	private int num_ligne;
	private int num_arret;
	
	public static int get_nb_events(){
		return ListeDesEvents.size();
	
	}
	public static Events get_event(int i){
		return ListeDesEvents.get(i);
	
	}
	public  String get_type(){
		return this.type;
	
	}
	public  int get_num_ligne(){
		return this.num_ligne;
	
	}
	public  int get_num_bus(){
		return this.num_bus;
	
	}
	public  int get_num_arret(){
		return this.num_arret;
	
	}
	
	public Events(String type, int num_bus){
		this.type=type;
		this.num_bus=num_bus;
		this.num_ligne=GestiBus.getBus(num_bus).getLigne();
		ListeDesEvents.add(this);
	
	}
	public Events(String type, int num_ligne,int num_arret){
		this.type=type;
		this.num_bus=num_bus;
		this.num_ligne=GestiBus.getBus(num_bus).getLigne();
		this.num_arret=num_arret;
		ListeDesEvents.add(this);
	
	}
	
	
	public void run(){
		
		if(type.equals("panne")){
			GestiBus.getLigne(num_ligne).getBus(num_bus).set_vitesse(10);
			//GestiBus.getBus(num_bus).set_vitesse(10);		
		}if(type.equals("bouchon")){
			GestiBus.getLigne(num_ligne).setBouchon(true,num_arret);
			}
			
		
	}
	
	
}