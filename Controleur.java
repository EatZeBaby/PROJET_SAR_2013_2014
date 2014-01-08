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
	private Socket port;
	private boolean stopThread = false;
	private ServerSocket s;
	private String delims = "[;]";
	private ArrayList<Integer> ListeDeslignesACharge  = new ArrayList<Integer>();
	private String[] ListeDesNoms  = {"Michel","Jean","Joss","Wario","Luigi"};
	
	
	public Controleur(int num,int port)throws IOException{
		this.num=num;
		this.nom=ListeDesNoms[num];
		this.s = new ServerSocket(port);
				
		//Debug
		/*System.out.println(this.nom);//*/
		
	}
	
	
	public void run(){
		boolean fin = false;
		
		try{
			
			while(!fin){
				Socket soc=s.accept();
				
				
				BufferedReader entree = new BufferedReader(new InputStreamReader( soc.getInputStream()));
				PrintWriter sortie = new PrintWriter(soc.getOutputStream(),true);	
			
				
				synchronized(this) {
                                Thread.yield();
 
                                // lecture du boolean 
                                fin = this.stopThread;
                        } 
			
				//System.out.println("Bienvenue sur le Serveur " +this.getName());	
				str=entree.readLine(); //Attente d'un message
				
				System.out.println("Message reçu par contrôleur : " + str);
				data=str.split(delims);
				
				
			}
			
			
		}catch(IOException e){}
	
	
	}
	public synchronized void fin_de_journee() {
        this.stopThread = true;
} 
		
	


}