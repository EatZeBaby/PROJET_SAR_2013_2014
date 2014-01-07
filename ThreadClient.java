import java.net.*;
import java.io.*;
//elhaddad@lamsade.dauphine.fr

public class ThreadClient extends Thread{
	
	private String str="";
	private String[] data=null;
	private Socket port;
	private String delims = "[;]";
	
	
	
	public ThreadClient(Socket port){
		this.port=port;
	}
	
	private void envoiVersControleur(String[] data){
	
	
	
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
				System.out.println("Communication établie avec l'application Gestibus.");
				sortie.println("OK");	
			}
			
			//SUR RECEPTION DE :
			//if (str.equals("INFOS")){
			if(data[0].equals("BUS")){
				
				System.out.println("Communication établie avec le bus N°"+ num_bus+" de la ligne "+ num_ligne);
				
				envoiVersControleur(data);
				
				sortie.println("OK");
					

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