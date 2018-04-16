import java.rmi.Remote;
import java.rmi.RemoteException;

public interface DBInterface extends Remote{
	public String conc(String str1, String str2) throws RemoteException;
}
