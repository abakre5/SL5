import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	static int connectionNo;
	
	public static void main(String args[]) throws Exception{
		
		ServerSocket serverSocket = new ServerSocket(2121);
		Socket clientSocket;
		System.out.println("Server is ready to accept connections ...");
		
		while(true){
			clientSocket = serverSocket.accept();
			new Thread((Runnable) new ClientThread(clientSocket, connectionNo++)).start();
		}
	}
	
}
