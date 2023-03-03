
public class Item extends AbstractItem{
	private Player owner;
	private float value;
	public Board board;
	private Game game;

	public Item(String position, String name, float value){
		setPosition(position);
		setName(name);
		setValue(value);
	}
	public void setGame(Game g){
		this.game = g;
	}
	public Game getGame(){
		return game;
	}
	public String toString(){
		return getName();
	}
	public float getValue() {return value;}
	public void setValue(float value) {this.value = value;}
	public Player getOwner() {return owner;}
	public void setOwner(Player owner) {this.owner = owner;}
	public void increaseScore(float score){
		owner.setPuan(owner.getPuan() + score);
	}

	public Board getBoard(){return this.board;}
	public void setBoard(Board b){this.board = b;}

	public String getRowName(){
		return getPosition().substring(1,2);
	}
	public int getColumnNumber(){
		return Integer.parseInt(getPosition().substring(0,1));
	}
	public int[] calculateDistance(String from, String to){
		try{
			String fromRow = from.substring(0,1).toLowerCase();
			String toRow = to.substring(0,1).toLowerCase();
			int fromCol = Integer.parseInt(from.substring(1,2));
			int toCol = Integer.parseInt(to.substring(1,2));
			int colDiff = toCol - fromCol;
			int rowDiff = toRow.charAt(0) - fromRow.charAt(0);
			return new int[]{rowDiff, colDiff};
		}catch (Exception e){
			return null;
		}

	}
	public void putItemToDestination(String destination){
		Item willBeBeatenItem = getBoard().getItem(destination);

		if(willBeBeatenItem!= null && willBeBeatenItem.getOwner().equals(getOwner())){
			System.out.println("Chariot. Kendi taşının üstüne hareket edemez.");
		}
		// Başarılı hareket. Rakip taş yenildi.
		else if(willBeBeatenItem != null){
			getOwner().increaseScore(willBeBeatenItem.getValue());
			willBeBeatenItem.setPosition("xx");
			setPosition(destination);
			game.changePlayerTurn();
		}
		// Başarılı hareket. Boş pozisyona hareket edildi.
		else{
			setPosition(destination);
			game.changePlayerTurn();
		}
	}

	public boolean isColumnClear(String destination, int colDiff){
		String[][] chessBoard = getBoard().getChessBoard();
		String currentPosition = getPosition();
		int fromLineIndex = getBoard().getLineCodeIndex(getPosition().substring(0,1));
		int toLineIndex = getBoard().getLineCodeIndex(destination.substring(0,1));
		int fromColumnIndex = Integer.parseInt(getPosition().substring(1,2));
		int toColumnIndex = Integer.parseInt(destination.substring(1,2));
		if(colDiff > 0){
			for(int col=fromColumnIndex+1; col<toColumnIndex-1; col++){
				if(getBoard().getItem(""+getBoard().getLineCode()[fromLineIndex]+""+col) != null){
					return false;
				}
			}
		}
		else if(colDiff < 0){
			for(int col=fromColumnIndex-1; col>toColumnIndex-1; col--){
				if(getBoard().getItem(""+getBoard().getLineCode()[fromLineIndex]+""+col) != null){
					return false;
				}
			}
		}
		return true;
	}

	public boolean isRowClear(String destination, int rowDiff){
		String[][] chessBoard = getBoard().getChessBoard();
		String currentPosition = getPosition();
		int fromLineIndex = getBoard().getLineCodeIndex(getPosition().substring(0,1));
		int toLineIndex = getBoard().getLineCodeIndex(destination.substring(0,1));
		int fromColumnIndex = Integer.parseInt(getPosition().substring(1,2));
		if(rowDiff < 0){
			for(int row=fromLineIndex+1; row<toLineIndex; row++){
				if(getBoard().getItem(""+getBoard().getLineCode()[row]+""+fromColumnIndex) != null){
					return false;
				}
			}
		}
		else if(rowDiff > 0){
			for(int row=fromLineIndex-1; row>toLineIndex; row--){
				if(getBoard().getItem(""+getBoard().getLineCode()[row]+""+fromColumnIndex) != null){
					return false;
				}
			}
		}
		return true;
	}
	public boolean isDimensionSuitableToCross(int rowDiff, int colDiff){
		if ( Math.abs(rowDiff) == Math.abs(colDiff)){
			return true;
		}
		return false;
	}
	public boolean isCrossClear(String destination, int rowDiff, int colDiff){
		int fromLineIndex = getBoard().getLineCodeIndex(getPosition().substring(0,1));
		int toLineIndex = getBoard().getLineCodeIndex(destination.substring(0,1));
		int fromColumnIndex = Integer.parseInt(getPosition().substring(1,2));
		int toColumnIndex = Integer.parseInt(destination.substring(1,2));
		if(rowDiff < 0){
			if(colDiff < 0){
				for(int row=fromLineIndex+1, col=fromColumnIndex-1; row<toLineIndex && col>toColumnIndex-1 ; row++, col--){
					if(getBoard().getItem(""+getBoard().getLineCode()[row]+""+col) != null){
						return false;
					}
				}
			}
			else{
				for(int row=fromLineIndex+1, col=fromColumnIndex+1; row<toLineIndex && col<toColumnIndex-1 ; row++, col++){
					if(getBoard().getItem(""+getBoard().getLineCode()[row]+""+col) != null){
						return false;
					}
				}
			}
		}else{
			if(colDiff < 0){
				for(int row=fromLineIndex-1, col=fromColumnIndex-1; row>toLineIndex && col>toColumnIndex-1; row--, col--){
					if(getBoard().getItem(""+getBoard().getLineCode()[row]+""+col) != null){
						return false;
					}
				}
			}
			else{
				for(int row=fromLineIndex-1, col=fromColumnIndex+1; row>toLineIndex && col<toColumnIndex-1; row--,col++){
					if(getBoard().getItem(""+getBoard().getLineCode()[row]+""+col) != null){
						return false;
					}
				}
			}
		}
		return true;
	}
	public boolean isItemInOwnPalace(String destination){
		if(getGame().red.equals(getOwner())){
			String destinationRow = destination.substring(0,1);
			int destinationCol = Integer.parseInt(destination.substring(1,2));
			if ( destinationRow.charAt(0) > 'c' ||  destinationCol < 4 || destinationCol > 6){
				System.out.println("Red General. Dışarıya çıkamaz");
				return false;
			}
		}
		else{
			String destinationLine = destination.substring(0,1);
			int destinationCol = Integer.parseInt(destination.substring(1,2));
			if ( destinationLine.charAt(0) < 'f' || destinationCol < 4 ||destinationCol > 6){
				System.out.println("Black General. Palace dışına çıkamaz");
				return false;
			}
		}
		return true;
	}
}
