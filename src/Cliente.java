
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Cliente {

    public static void main(String[] args) {
        try {
            //Conectar com o registro.
            Registry registroCliente = LocateRegistry.getRegistry(null);
            //Copia o objeto Remoto do servidor.  
            Interface objRemoto = (Interface) registroCliente.lookup("objeto");
            
            objRemoto.Autenticar();
           
            
        } catch (RemoteException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao se conectar com o Registro!");
        } catch (NotBoundException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Não encontrou o objeto!");
        }
    
    }
    
}
