//Projet SAR 2013-2014

import java.net.*;
import java.io.*;



public class BusThread implements Runnable{
	
	private String role="";
	private int delai;
	private Socket socket;
	private PrintWriter sortie ;
	private BufferedReader entree;
	private String delims = "[;]";
	private int num_bus;
	
	
	private void envoyerInfos() throws Exception{
		
		System.out.println("Le bus "+ num_bus +" envoie ses infos au Serveur.");
		
		
		/*Socket socket = new Socket("localhost", 6000);
		PrintWriter sortie = new PrintWriter(socket.getOutputStream(),true);
		BufferedReader entree = new BufferedReader(new InputStreamReader(socket.getInputStream()));*/
		
		
		sortie.println("BUS;"+num_bus+";"+num_ligne+";"+vitesse+";"+position); //Envoi du message
		//             data[0]+ data[1]  + data[2]   +   data[3]   + data[4]  
		
		
	}
	
	private void recevoirOrdres() throws Exception{
		
		String rep=entree.readLine();//Attente de la réponse
		
		//TO EDIT RECUPERER LA REPONSE DU SERVEUR CONTENANT LA DECISION DU CONTROLEUR
		data=rep.split(delims);
		
		if(data[0].equals("OKBUS")){
			System.out.println("[OK] Infos reçues par le serveur");
		}
		if(data[0].equals("CTRL")){
			System.out.println("Bus à reçu la réponse du controleur");
		}

		
	}
	
	
	public void run(){
		
		socket = new Socket("localhost", 6000);
		sortie = new PrintWriter(socket.getOutputStream(),true);
		entree = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		
		while(true){
			if (role.equals("facteur")){
				try
				 {
					Thread.sleep(delai);
					envoyerInfos();	
				 }
				catch
				 (Exception e) {
					break;
				 }
			}
			if (role.equals("listener")){
					recevoirOrdres();
			}
		}	
	}
	
	
	public BusThread(int num_bus,String role){
		this.delai=Gestibus.getDelai();
		
		
	
	
	}


}