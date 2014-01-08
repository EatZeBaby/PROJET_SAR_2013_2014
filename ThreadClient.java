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
	
	private void envoiVersControleur(String data)throws Exception{
		
		Socket socket = new Socket("localhost", 6001);
		
		PrintWriter sortie = new PrintWriter(socket.getOutputStream(),true);
		BufferedReader entree = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		sortie.println(data);
		
	
	
	
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
				
				System.out.println("Communication établie avec le bus N°"+ data[1]+" de la ligne "+ data[2]);
				try{
					System.out.println("Transfert des données reçues au contrôleur");
					envoiVersControleur(str);
				}catch(Exception e){}
				
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