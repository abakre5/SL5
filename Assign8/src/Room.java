import java.io.Serializable;


public class Room implements Serializable{

	private static final long serialVersionUID = 1L;
	public static final int days = 7;
	public static final int hours = 12;
	
	int slot[][] = new int[days][hours];
	int roomNumber;
	static int rno = 1;
	
	public Room(){
		roomNumber = rno++;
		for(int i = 0;i < days;i++){
			for(int j = 0;j < hours;j++){
				slot[i][j] = 0;
			}
		}
	}
	
	public boolean isSlotAvailable(int day, int hour){
		if(slot[day][hour] == 1)	return true;
		else return false;
	}
	
	public void bookSlot(int day, int hour){
		slot[day][hour] = 1;
	}
	
}
