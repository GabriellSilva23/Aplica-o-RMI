import java.rmi.Remote;
import java.rmi.RemoteException;


public interface Interface extends Remote {
    String CadastrarUser() throws RemoteException;
    String CadastrarPassWord() throws RemoteException;
    String Autenticar() throws RemoteException;
 }
