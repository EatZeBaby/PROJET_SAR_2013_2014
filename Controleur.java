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


public class Controleur implements Runnable{
	
	public Thread proc = new Thread(this); //Creation du thread
	private String nom;
	private String decision;
	private String str="";
	private int num;
	private String[] data=null;
	private Socket port;
	private boolean stopThread = false;
	private ServerSocket s;
	private String delims = "[;]";
	private ArrayList<Integer> ListeDeslignesACharge  = new ArrayList<Integer>();
	private String[] ListeDesNoms  = {"Michel","Jean","Joss","Wario","Luigi","Henri","Jeanine","Paulette","Jules","Axel","Guillaume","Thibault"};


	private void initLigneACharge(){
		
		for (int i=0; i< Database.getRepartition().size(); i++){
			if (Database.getRepartition().get(i)==num){
				
				this.ListeDeslignesACharge.add(i);
			}
			//System.out.println("DEBUG");
		
		}
	}

	public Integer bus_a_charge(int i){
		return this.ListeDeslignesACharge.get(i);
	}

	public String getNom(){
		return this.nom;
	
	}
	public int nb_lignes_a_charge(){
		return this.ListeDeslignesACharge.size();
	}
	
	
	public Controleur(int num,int port)throws IOException{
		this.num=num;
		this.nom=ListeDesNoms[num];
		this.s = new ServerSocket(port);
		
		
		
				
		//Debug
		/*System.out.println(this.nom);//*/
	
	}
	public void run(){
		boolean fin = false;
		
		initLigneACharge();
		
		
		
		
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
				if(GestiBus.debug)
					System.out.println("Message reçu par contrôleur : " + str);
				data=str.split(delims);
				
				
			/*  data[0] : BUS
				data[1]	: num_bus
				data[2] : num_ligne
				data[3] : vitesse
				data[4] : position */
				
				decision="stop";
				//Traitement des infos
				if (data[3]== "30")
					decision=decision+"Nouvelle_vitesse";
				//etc...

				sortie.println("CTRL;"+decision);
				//System.out.println("Message envoyé");
				
			}
			
			
		}catch(IOException e){}
	
	
	}
	public synchronized void fin_de_journee() {
        this.stopThread = true;
	} 

}