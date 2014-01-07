//Projet SAR 2013-2014

import java.net.*;
import java.io.*;
import java.util.ArrayList;


public class Ligne{

	private int numero;
	private ArrayList<String> ListeDesArrets  = new ArrayList<String>();
	
	//Tableau d'affectation des lignes
	private static ArrayList<Integer> Affectation=new ArrayList<Integer>();



	public Ligne(int i){
		//DEBUG
		/*System.out.println("Création de la ligne N°"+i);//*/

		
		if(i==1){
			this.numero=1;
			ListeDesArrets.add("La Défense");
			ListeDesArrets.add("Georges V");
			ListeDesArrets.add("Palais Royal");
			ListeDesArrets.add("Bastille");
			ListeDesArrets.add("Château de Vincennes");
				}
		if(i==2){
			this.numero=2;
			ListeDesArrets.add("Porte Dauphine");
			ListeDesArrets.add("Place de CLichy");
			ListeDesArrets.add("Pigalle");
			ListeDesArrets.add("Colonel Fabien");
			ListeDesArrets.add("Philippe Auguste");
				}
		if(i==3){
			this.numero=6;
			ListeDesArrets.add("Charles De Gaulle - Etoile");
			ListeDesArrets.add("Dupleix");
			ListeDesArrets.add("Montparnasse");
			ListeDesArrets.add("Place d'Italie");
			ListeDesArrets.add("Nation");
				}
	
	
	}
	
	public void afficher_ligne(){
		System.out.println("Ligne " + this.numero);
		for(int i=0; i< this.ListeDesArrets.size(); i++){
			
			System.out.println("Arrêt N°" + i + " : " + this.ListeDesArrets.get(i) +".");
		
		}
		
		
		
	}

}