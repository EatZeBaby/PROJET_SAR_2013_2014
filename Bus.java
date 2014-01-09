//Projet SAR 2013-2014

import java.net.*;
import java.io.*;



public class Bus implements Runnable{
	
	public Thread proc = new Thread(this); //Creation du thread
	private boolean en_marche=false;
	private int vitesse;
	private int position;
	private int num_bus;
	private int num_ligne;
	private ServerSocket s;
	private String str="";
	private Socket soc;
	private String[] data=null;
	private String delims = "[;]";



	private void envoyerInfos() throws Exception{
		
		System.out.println("Le bus "+ num_bus +" envoie ses infos au Serveur.");
		
		
		Socket socket = new Socket("localhost", 6000);
		PrintWriter sortie = new PrintWriter(socket.getOutputStream(),true);
		BufferedReader entree = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		
		sortie.println("BUS;"+num_bus+";"+num_ligne+";"+vitesse+";"+position); //Envoi du message
		//             data[0]+ data[1]  + data[2]   +   data[3]   + data[4]  
		
		
		String rep=entree.readLine();//Attente de la réponse
		
		//TO EDIT RECUPERER LA REPONSE DU SERVEUR CONTENANT LA DECISION DU CONTROLEUR
		data=rep.split(delims);
		
		if(data[0].equals("OKBUS")){
			System.out.println("[OK] Infos reçues par le serveur");
		}
		if(data[0].equals("CTRL")){
			System.out.println("Bus à reçu la réponse du controleur");
		}
		
		//System.out.println("Le client a reçu : " + rep);
	
	
	}
	public void surLigne(int num_ligne){
		this.num_ligne=num_ligne;
	}
	
	public void run(){
		try{
			
		/*	BufferedReader entree = new BufferedReader(new InputStreamReader( soc.getInputStream()));
			PrintWriter sortie = new PrintWriter(soc.getOutputStream(),true);//*/
					
		while(this.en_marche==true){	
	
				try
				 {
					Thread.sleep(3000);
					//DEBUG
					/*System.out.println("Bus " + num_bus + " en Marche");//*/
					envoyerInfos();
					//String str=entree.readLine(); //Attente d'un message
					//System.out.println("Message reçu par le bus : " + str);
				 }
				 
				catch
				 (Exception e) {
				
					break;
				 }
		
		}}
		catch(Exception e) {
				
					
		}
		System.out.println("Bus " + num_bus + " arrêté.");

	
	}
	
	
	public Bus(int num_bus) throws Exception{
	
		this.num_bus=num_bus+1;
		en_marche=true;
		//s = new ServerSocket(6100+num_bus);//BUS SOCKET
		//soc=s.accept();
	
	}
	
	public void fin_de_journee(){
		
		this.en_marche=false;
			
	}
	
	
	
	
	}