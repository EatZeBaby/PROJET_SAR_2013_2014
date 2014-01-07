//Projet SAR 2013-2014

import java.net.*;
import java.io.*;
import java.util.ArrayList;


public class Controleur{
	
	private String nom;
	private int num;
	private ArrayList<Integer> ListeDeslignesACharge  = new ArrayList<Integer>();
	private String[] ListeDesNoms  = {"Michel","Jean","Joss","Wario","Luigi"};
	
	public Controleur(int num){
		this.num=num;
		this.nom=ListeDesNoms[num];
		//Debug
		/*System.out.println(this.nom);//*/
		
	}
	
		
	


}