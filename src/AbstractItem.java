
public abstract class AbstractItem implements ItemInterface {

	private String position;  // tahtadaki konumu gösterir. Örneğin, a1
	private String name;

	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public void setName(String name){this.name = name;}
	public String getName(){return this.name;}
	public void move(String destination){
		// Going to filled with move actions.
	}

	// Hareketin doğru olup olmadığını kontrol eder.
	abstract boolean isItSuitableMove(String destination, int rowDiff, int colDiff) throws OutOfBoardException, PieceMovementException;
}
