
public class Item extends AbstractItem{


	private Player owner;
	private int value;

	public Item(String position, String name){
		super(position,name);

	}
	public String toString(){
		return getName();
	}
	public int getValue() {return value;}
	public void setValue(int value) {this.value = value;}
	public Player getOwner() {return owner;}
	public void setOwner(Player owner) {this.owner = owner;}

	public int[] calculateDistance(String currentPostion, String destination){
		int currentRow = Integer.parseInt(currentPostion.substring(1,2));
		int destinationRow = Integer.parseInt(destination.substring(1,2));
		int currentColumn = Integer.parseInt(currentPostion.substring(0,1));
		int destinationColumn = Integer.parseInt(destination.substring(0,1));
		return new int[]{currentRow-destinationRow,currentColumn-destinationColumn};
	}
	public String getRowName(){
		return getPosition().substring(1,2);
	}
	public int getColumnNumber(){
		return Integer.parseInt(getPosition().substring(0,1));
	}
	

}
