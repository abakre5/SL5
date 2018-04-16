import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;


public class Client {

	public static void main(String args[]) throws UnknownHostException, IOException{
		
		Socket socket = new Socket("localhost", 2121);
		DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
		DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		while(true){
			System.out.print("Enter message (STOP to Close Connection) :: ");
			String input = bufferedReader.readLine();
			if(input.equalsIgnoreCase("stop"))	break;
			dataOutputStream.writeUTF(input);
			System.out.println("Server says " + dataInputStream.readUTF());
		}	
		
		dataInputStream.close();
		dataOutputStream.close();
		bufferedReader.close();
		socket.close();
	}	
}