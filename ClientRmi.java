import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ClientRmi {

    private ClientRmi() {}

    public static void main(String[] args) {
		/*String host = (args.length < 1) ? null : args[0];
			try {
				System.out.println(host);
			    Registry registry = LocateRegistry.getRegistry(host);
			    ChoixControleur stub = (ChoixControleur) registry.lookup("ChoixControleur");
			    String response = stub.getControleur();
			    System.out.println("response: " + response);
			} catch (Exception e) {
			    System.err.println("Client exception: " + e.toString());
			    e.printStackTrace();
			}
	*/}
}