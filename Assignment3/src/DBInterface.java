import java.rmi.*;

public interface DBInterface extends Remote{
	public String conc(String str1, String str2) throws RemoteException;
}
