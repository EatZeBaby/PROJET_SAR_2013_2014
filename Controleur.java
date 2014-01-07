//Projet SAR 2013-2014

import java.net.*;
import java.io.*;
import java.util.ArrayList;


public class Controleur implements Runnable{
	
	public Thread proc = new Thread(this); //Creation du thread
	private String nom;
	private String str="";
	private int num;
	private String[] data=null;
	
	private String delims = "[;]";
	private ArrayList<Integer> ListeDeslignesACharge  = new ArrayList<Integer>();
	private String[] ListeDesNoms  = {"Michel","Jean","Joss","Wario","Luigi"};
	
	
	public Controleur(int num){
		this.num=num;
		this.nom=ListeDesNoms[num];
		
		
		
		//Debug
		/*System.out.println(this.nom);//*/
		
	}
	
	
	public void run(){
		
		try{
			ServerSocket s= new ServerSocket(6002);
			Socket soc=s.accept();
			
			
			BufferedReader entree = new BufferedReader(new InputStreamReader( soc.getInputStream()));
			PrintWriter sortie = new PrintWriter(soc.getOutputStream(),true);	
			while(true){
				
		
			
			//System.out.println("Bienvenue sur le Serveur " +this.getName());	
			str=entree.readLine(); //Attente d'un message
			System.out.println("Debug");
			
			System.out.println("Message reçu par contrôleur : " + str);
			data=str.split(delims);
			}
			
			
		}catch(IOException e){}
	
	
	}
		
	


}