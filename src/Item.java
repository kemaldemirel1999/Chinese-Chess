
public abstract class Item extends AbstractItem{
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
	public Board getBoard(){return this.board;}
	public void setBoard(Board b){this.board = b;}
	public String getRowName(){
		return getPosition().substring(1,2);
	}
	public int getColumnNumber(){
		return Integer.parseInt(getPosition().substring(0,1));
	}
	public String getRowName(String position){return position.substring(1,2);}
	public int getColumnNumber(String position){return Integer.parseInt(position.substring(0,1));}


	public abstract boolean isItSuitableMove(String destination, int rowDiff, int colDiff) throws OutOfBoardException, PieceMovementException;
	public abstract boolean moveCheck(String destination);

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
	public void putItemToDestination(String destination, boolean isItCheckMateMove) throws OutOfBoardException, FlyingRuleException, CheckMateException, PieceMovementException {
		Item willBeBeatenItem = getBoard().getItem(destination);
		if(willBeBeatenItem!= null && willBeBeatenItem.getOwner().equals(getOwner())){
			throw new PieceMovementException("Kendi taşına hareket edemez");
		}
		// Başarılı hareket. Rakip taş yenildi.
		else if(willBeBeatenItem != null){
			String willBeBeatenItemPosition = willBeBeatenItem.getPosition();
			float willBeBeatenItemValue = willBeBeatenItem.getValue();
			String movedItemOldPosition = getPosition();
			getOwner().setPuan(getOwner().getPuan() + willBeBeatenItem.getValue());
			willBeBeatenItem.setPosition("xx");
			setPosition(destination);
			if(willBeBeatenItem.getName().toLowerCase().equals("ş")){
				if(isItCheckMateMove){
					getOwner().setPuan(getOwner().getPuan() - willBeBeatenItemValue);
					willBeBeatenItem.setPosition(willBeBeatenItemPosition);
					setPosition(movedItemOldPosition);
					System.out.println("revert1");
					return;
				}
				else{
					getOwner().setWinner(true);
					return;
				}
			}
			System.out.println("checkmate:"+checkCheckMateRule());
			System.out.println("checkfly:"+checkFlyingGeneralRule());
			System.out.println("isMove:"+!isItCheckMateMove);
			// Şah-Mat Rule kontrol edilir.
			if(checkCheckMateRule() && checkFlyingGeneralRule() && !isItCheckMateMove){
				game.changePlayerTurn();
			}
			else{
				getOwner().setPuan(getOwner().getPuan() - willBeBeatenItemValue);
				willBeBeatenItem.setPosition(willBeBeatenItemPosition);
				setPosition(movedItemOldPosition);
				if(checkCheckMateRule() == false){
					throw new CheckMateException("Check-Mate yüzünden hamle yapilamaz.");
				}
				if(checkFlyingGeneralRule() == false){
					throw new FlyingRuleException("Flying Rule yüzünden hamle yapilamaz.");
				}

			}
		}
		// Başarılı hareket. Boş pozisyona hareket edildi.
		else{
			String movedItemOldPosition = getPosition();
			setPosition(destination);
			if(checkFlyingGeneralRule() && !isItCheckMateMove){
				game.changePlayerTurn();
			}
			else{
				setPosition(movedItemOldPosition);
				System.out.println("revert3");
				if(!checkFlyingGeneralRule()){
					throw new FlyingRuleException("Flying Rule yüzünden hamle yapilamaz.");
				}
			}
		}
	}

	public boolean isColumnClear(String destination, int colDiff){
		int fromLineIndex = getBoard().getLineCodeIndex(getPosition().substring(0,1));
		int fromColumnIndex = Integer.parseInt(getPosition().substring(1,2));
		int toColumnIndex = Integer.parseInt(destination.substring(1,2));
		if(colDiff > 0){
			for(int col=fromColumnIndex+1; col<toColumnIndex-1; col++){
				Item tmp;
				try{
					 tmp = getBoard().getItem(""+getBoard().getLineCode()[fromLineIndex]+""+col);
					if(tmp != null){
						return false;
					}
				}catch (OutOfBoardException e){
					System.out.println(e.getMessage());
				}
			}
		}
		else if(colDiff < 0){
			for(int col=fromColumnIndex-1; col>toColumnIndex-1; col--){
				Item tmp;
				try{
					tmp = getBoard().getItem(""+getBoard().getLineCode()[fromLineIndex]+""+col);
					if( tmp != null){
						return false;
					}
				}catch (OutOfBoardException e){
					System.out.println(e.getMessage());
				}
			}
		}
		return true;
	}

	public boolean isRowClear(String destination, int rowDiff){
		int fromLineIndex = getBoard().getLineCodeIndex(getPosition().substring(0,1));
		int toLineIndex = getBoard().getLineCodeIndex(destination.substring(0,1));
		int fromColumnIndex = Integer.parseInt(getPosition().substring(1,2));
		if(rowDiff < 0){
			for(int row=fromLineIndex+1; row<toLineIndex; row++){
				Item tmp = null;
				try{
					tmp = getBoard().getItem(""+getBoard().getLineCode()[row]+""+fromColumnIndex);
					if( tmp != null){
						return false;
					}
				}catch (OutOfBoardException e){
					System.out.println(e.getMessage());
				}
			}
		}
		else if(rowDiff > 0){
			for(int row=fromLineIndex-1; row>toLineIndex; row--){
				Item tmp = null;
				try{
					tmp = getBoard().getItem(""+getBoard().getLineCode()[row]+""+fromColumnIndex);
					if(tmp != null){
						return false;
					}
				}catch (OutOfBoardException e){
					System.out.println(e.getMessage());
				}
			}
		}
		return true;
	}


	public boolean isCrossClear(String destination, int rowDiff, int colDiff){
		int fromLineIndex = getBoard().getLineCodeIndex(getPosition().substring(0,1));
		int toLineIndex = getBoard().getLineCodeIndex(destination.substring(0,1));
		int fromColumnIndex = Integer.parseInt(getPosition().substring(1,2));
		int toColumnIndex = Integer.parseInt(destination.substring(1,2));
		try{
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
		}catch (OutOfBoardException e){
			System.out.println(e.getMessage());
			return false;
		}
		return true;
	}

	public boolean isItemInOwnPalace(String destination){
		if(getGame().red.equals(getOwner())){
			String destinationRow = destination.substring(0,1);
			int destinationCol = Integer.parseInt(destination.substring(1,2));
			if ( destinationRow.charAt(0) > 'c' ||  destinationCol < 4 || destinationCol > 6){
				return false;
			}
		}
		else{
			String destinationLine = destination.substring(0,1);
			int destinationCol = Integer.parseInt(destination.substring(1,2));
			if ( destinationLine.charAt(0) < 'f' || destinationCol < 4 ||destinationCol > 6){
				return false;
			}
		}
		return true;
	}

	public boolean isRiverCrossed(){
		if(getPosition().charAt(0) > 'e' && getGame().red.equals(getOwner())){
			return true;
		}
		else if(getPosition().charAt(0) < 'f' && getGame().black.equals(getOwner())){
			return true;
		}
		return false;
	}

	public boolean isItAfterRiver(String destination){
		if(getGame().red.equals(getOwner())){
			return destination.substring(0, 1).charAt(0) <= 'e';
		}
		else if(getGame().black.equals(getOwner())){
			return destination.substring(0, 1).charAt(0) >= 'f';
		}
		return true;
	}

	public boolean isDestinationEmpty(String destination){
		try{
			if (getBoard().getItem(destination) == null ){
				return true;
			}
		}catch (OutOfBoardException e){
			System.out.println(e.getMessage());
			return false;
		}
		return  false;
	}

	public boolean checkFlyingGeneralRule(){
		Item redGeneral = getOwnGeneral(getGame().red);
		Item blackGeneral = getOwnGeneral(getGame().black);

		int[] distance = calculateDistance(redGeneral.getPosition(), blackGeneral.getPosition());
		int rowDiff = distance[0];
		int colDiff = distance[1];
		if(colDiff == 0){
			if(  redGeneral.isRowClear(blackGeneral.getPosition(), rowDiff)  ){
				return false;
			}
		}
		return true;
	}

	public Item getOwnGeneral(Player p){
		for(Item t: board.items){
			if(p.equals(t.getOwner()) && (t.getName().equals("Ş") || t.getName().equals("ş")) ){
				return t;
			}
		}
		return null;
	}

	public boolean checkCheckMateRule(){
		Item ownGeneral = getOwnGeneral(getOwner());
		for(Item t: board.items){
			if(  !t.getOwner().equals(getOwner())){
				if(	t.moveCheck(ownGeneral.getPosition()) && !t.getPosition().equals("xx")){
					System.out.println("Yenebilir."+t.getPosition());
					return false;
				}
			}
		}
		return true;
	}


}
