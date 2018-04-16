import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Client {

	public static void main(String args[]) throws Exception{
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		int ch = 1;
		String str1 = null, str2 = null;
		Registry registry = LocateRegistry.getRegistry("localhost", 2122);
		DBInterface dbInterface = (DBInterface) registry.lookup("DBServ");
		
		do{
			System.out.print("1. Enter Strings\n2. Concatenate String\nEnter Choice :: ");
			ch = Integer.parseInt(bufferedReader.readLine());
			switch (ch) {
			case 1:
				str1 = bufferedReader.readLine();
				str2 = bufferedReader.readLine();
				break;
			case 2:
				System.out.println(dbInterface.conc(str1, str2));
				break;
			}
		}while(ch == 1 || ch == 2);
		
	}
	
}
