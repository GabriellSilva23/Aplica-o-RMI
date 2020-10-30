
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Servidor implements Interface {
 
    @Override
    public String CadastrarUser() throws RemoteException {
        String User = "";
        
        while(User.length() == 0){
            Scanner scan = new Scanner(System.in);
            System.out.println("Cadastre um usuário: ");
            User = scan.nextLine();
            if(User.length() == 0){
                System.out.println("Erro! Digite um usuário!");
            }else{
                System.out.println("Usuário Cadastrado! ");
            }
        }
	return User;
    }

    @Override
    public String CadastrarPassWord() throws RemoteException {
        String passWord = "";
        
        while(passWord.length() == 0){
            Scanner scan = new Scanner(System.in);
            System.out.print("Cadastre uma senha: ");
            passWord = scan.nextLine();
            if(passWord.length() == 0 ){
                System.out.println("Erro! Digite uma senha!");
            }else{
                System.out.println("Senha Cadastrada");
            }
        }
        return passWord;
    }

    
    @Override
    public String Autenticar() throws RemoteException {
        String confUser = "";
        String confPassWord = "";
        String usuario = CadastrarUser();
        String senha = CadastrarPassWord();
        
        while(confUser == null ? usuario != null : !confUser.equals(usuario)){
            Scanner scan = new Scanner(System.in);
            System.out.println("Digite seu Usuário: ");
            confUser = scan.nextLine();
            Scanner scan2 = new Scanner(System.in);
            System.out.println("Digite sua Senha: ");
            confPassWord = scan2.nextLine();

            if(confUser == null ? usuario == null : confUser.equals(usuario)){
                if(confPassWord == null ? senha == null : confPassWord.equals(senha)){
                    System.out.println("Login efetuado com sucesso!");
                }else{
                    confUser = "";
                    confPassWord = "";
                    System.out.println("Senha Incorreta! Digite Novamente!");
                }
            }else{
                confUser = "";
                confPassWord = "";
                System.out.println("Usuário Incorreto! Digite Novamente!");
            } 
        } 
	return null;
    }
    
    
    
    public static void main(String[] args) {
        
        try {
            Servidor objLocal = new Servidor();
            //Exportar esse objeto criado - Deixar disponível para todos os clientes.
            //acessar esse objeto.
            Interface objRemoto = (Interface) UnicastRemoteObject.exportObject(objLocal, 0);
            //Criar um registro
            Registry registro = LocateRegistry.getRegistry();
            //Inserir o objeto nesse registro.
            registro.bind("objeto", objRemoto);
            
        } catch (RemoteException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Erro ao exporta o objeto!");
        } catch (AlreadyBoundException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Não foi possível registrar o objeto!");
        }
    }
}
