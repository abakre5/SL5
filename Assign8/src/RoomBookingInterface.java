import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RoomBookingInterface extends Remote{
	public void init() throws RemoteException;
}
