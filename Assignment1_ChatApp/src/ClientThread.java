import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


public class ClientThread implements Runnable{

	Socket clientSocket;
	int connectionNo;
	
	public ClientThread(Socket clientSocket, int connectionNo) {
		this.clientSocket = clientSocket;
		this.connectionNo = connectionNo;
		System.out.println("Connection " + connectionNo + " established with " + clientSocket);
	}

	public void run() {
		try {
			DataInputStream dataInputStream = new DataInputStream(clientSocket.getInputStream());
			DataOutputStream daOutputStream = new DataOutputStream(clientSocket.getOutputStream());
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
			daOutputStream.flush();
			while(true){
				String input = dataInputStream.readUTF();
				if(input.equalsIgnoreCase("stop")) break;
				System.out.println("Client " + connectionNo + " : " + input);
				System.out.print("Enter reply : ");
				input = bufferedReader.readLine();
				daOutputStream.writeUTF(input);
			}
			dataInputStream.close();
			daOutputStream.close();
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
