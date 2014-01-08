import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Choix extends Remote {
    int getControleur(int i) throws RemoteException;
}