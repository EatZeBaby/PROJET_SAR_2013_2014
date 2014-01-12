//Projet SAR 2013-2014

import java.net.*;
import java.io.*;



public class Bus extends Thread{
	
	//public Thread proc = new Thread(this); //Creation du thread
	private static boolean en_marche=false;
	private int vitesse;
	private int position;
	private int num_bus;
	private int num_ligne;
	
	private String str="";
	private Socket soc;
	private String[] data=null;
	private String delims = "[;]";
	private String role="";
	private int port;
	private Socket socket;
	private PrintWriter sortie ;
	private BufferedReader entree;
	private static int delai;
	private static	ServerSocket s;

	
	public String getPos(){
	
		return GestiBus.getLigne(this.num_ligne).getArret(position);
	}
	public int getVitesse(){
	
		return vitesse;
	}
	
	public int getNumBus(){
	
		return num_bus;
	}


	private void envoyerInfos() throws Exception{
		
		
		
		while(true){
			
			Thread.sleep(3000);
			if(GestiBus.debug)
				System.out.println("Le bus "+ this.num_bus +" envoie ses infos au Serveur :\n BUS;"+num_bus+";"+this.num_ligne+";"+vitesse+";"+position);
			
			
			socket = new Socket("localhost", 6000);
			sortie = new PrintWriter(socket.getOutputStream(),true);
			entree = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			
			
			sortie.println("BUS;"+num_bus+";"+num_ligne+";"+vitesse+";"+position); //Envoi du message
			//             data[0]+ data[1]  + data[2]   +   data[3]   + data[4]  
			
			
			String rep=entree.readLine();//Attente de la réponse
			
			//TO EDIT RECUPERER LA REPONSE DU SERVEUR CONTENANT LA DECISION DU CONTROLEUR
			data=rep.split(delims);
			
			if(data[0].equals("OKBUS")){
				if(GestiBus.debug)
					System.out.println("[OK] Infos reçues par le serveur");
			}
			if(data[0].equals("CTRL")){
				if(GestiBus.debug)
					System.out.println("Bus à reçu la réponse du controleur");
			}//*/
			
			//System.out.println("Le client a reçu : " + rep);
		
		}
	}
	
	
	private void recevoirOrdres() throws Exception{
		
		String str="";
		Socket soc=s.accept();
		BufferedReader entreeBis = new BufferedReader(new InputStreamReader( soc.getInputStream()));
		PrintWriter sortieBis = new PrintWriter(soc.getOutputStream(),true);	
		//System.out.println("Bienvenue sur le Serveur " +this.getName());	
		if(GestiBus.debug)
			System.out.println("Debug : "+str);

	
		str=entreeBis.readLine(); //Attente d'un message
		String[] data_recues=str.split(delims);	
		if(GestiBus.debug)	
			System.out.println("recevoirOrdres : Ordre reçu : " + data_recues[1]);

	}
	
	public void surLigne(int num_ligne){
		num_ligne=num_ligne;
		
		GestiBus.getLigne(num_ligne).addBusSurLigne(this);
	}
	
	public void run(){
		try{
			
		/*	BufferedReader entree = new BufferedReader(new InputStreamReader( soc.getInputStream()));
			PrintWriter sortie = new PrintWriter(soc.getOutputStream(),true);//*/
					
			while(en_marche==true){	
		
					
					if (this.role.equals("listener")){
							if(GestiBus.debug)
								System.out.println("Bus "+num_bus+" : listener démarré");
							recevoirOrdres();
					}
					if (this.role.equals("facteur")){
						try
						 {	
						 	if(GestiBus.debug)
						 		System.out.println("Bus "+num_bus+" : facteur démarré");
							Thread.sleep(delai);
							envoyerInfos();	
						 }
						catch
						 (Exception e) {
							break;
						 }
					}
					
					if (this.role.equals("main")){
						
							Thread.sleep(5000);
							if(GestiBus.debug)
								System.out.println("Le bus "+this.num_bus+" avance!");
					}
					
			
			}
		}
		catch(Exception e) {
				
					
		}
		

	
	}
	
	
	public void depart(){
	
	
	
	}
	
	public Bus (String role){
		this.role=role;
	
	}
	
	public Bus(int num_bus,String role,int num_ligne) {
		this.role=role;
		this.num_bus=num_bus;
		this.num_ligne=num_ligne;
	
	}	
	public Bus(int num_bus,int num_ligne) throws Exception{
		
		this.delai = GestiBus.getDelai();
		this.num_ligne=num_ligne;
		this.num_bus=num_bus;
		en_marche=true;
		int p=6100+num_bus;
		
		s =  new ServerSocket(p);
		
		this.role="main";
		
		Thread facteur = new Bus(num_bus,"facteur",num_ligne);
		Thread listener = new Bus(num_bus,"listener",num_ligne);
		
		listener.start();
		this.start();
		facteur.start();
		//s = new ServerSocket(6100+num_bus);//BUS SOCKET
		//soc=s.accept();
		//join();
		if(GestiBus.debug)
			System.out.println("Bus " + this.num_bus + " arrêté.");
	
	}
	
	public void fin_de_journee(){
		
		//this.en_marche=false;
			
	}
	
	
	
	
	}