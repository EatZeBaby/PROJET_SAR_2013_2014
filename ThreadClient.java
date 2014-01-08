import java.net.*;
import java.io.*;
import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

//elhaddad@lamsade.dauphine.fr

public class ThreadClient extends Thread {
	public static final String RESET = "\u001B[0m";
	public static final String BLACK = "\u001B[30m";
	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	public static final String YELLOW = "\u001B[33m";
	public static final String BLUE = "\u001B[34m";
	public static final String PURPLE = "\u001B[35m";
	public static final String CYAN = "\u001B[36m";
	public static final String WHITE = "\u001B[37m";

	private String str="";
	private String[] data=null;
	private Socket port;
	private String delims = "[;]";
	
	
	
	public ThreadClient(Socket port){
		this.port=port;
	}
	
	private String envoiVersControleur(String str,String num_ligne)throws Exception{
			
			int ligne = Integer.parseInt(num_ligne)+1; 
			System.out.println("Debug : "+ ligne);
			//RMI//	
			try {
				    Registry registry = LocateRegistry.getRegistry(null);
				    Choix stub = (Choix) registry.lookup("Choix");
				    int response = stub.getControleur(ligne);
				    System.out.println("Il faut envoyer au controleur N°" + response);	
					//RMI FIN//*/
					
					//Contacter le controleur retournée par l'appel à getControleur
					response=6001+response;
					Socket socket = new Socket("localhost", response);
					PrintWriter sortie = new PrintWriter(socket.getOutputStream(),true);
					BufferedReader entree = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					
					
					//Envoi du message au controleur
					sortie.println(str);
					
					//Attente de la réponse du controleur
					str=entree.readLine(); 
					//System.out.println("Sur serveur" + str);
	
				} catch (Exception e) {
				    System.err.println("Client exception: " + e.toString());
				    e.printStackTrace();
				}
			return str;	
	}
	
	public void run(){	
		
		try{
	

			BufferedReader entree = new BufferedReader(new InputStreamReader( this.port.getInputStream()));
			PrintWriter sortie = new PrintWriter(this.port.getOutputStream(),true);	
			//System.out.println("Bienvenue sur le Serveur " +this.getName());	
			
		
		
		
			str=entree.readLine(); //Attente d'un message
			
			data=str.split(delims);
			//SUR RECEPTION DE :  MESSAGE DE GESTIBUS AU LANCEMENT	
			//if (str.equals("lancé?")){
			if(data[0].equals("lancé?")){
				System.out.println("["+GREEN+"OK"+RESET+"] Communication établie avec l'application Gestibus©.");
				sortie.println("OK");	
			}
			
			//SUR RECEPTION DE :
			//if (str.equals("INFOS")){
			if(data[0].equals("BUS")){
				
				System.out.println("Communication établie avec le bus N°"+ data[1]+" de la ligne "+ data[2]);
				try{
					//System.out.println("Transfert des données reçues au contrôleur");
					String reponse_controleur= envoiVersControleur(str,data[2]);
				
				
				//sortie.println("OKBUS");
					sortie.println(reponse_controleur);
					}catch(Exception e){}
			}
			
		
		//DEBUG//
		/*System.out.println("Fermeture de la connexion");//*/
		entree.close();
		sortie.close();
		
		}catch(IOException e){}
			finally{
					try{
						port.close();
					}catch(IOException e){}
				
			}

	}	
}