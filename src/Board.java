
public class Board extends AbstractBoard{

	// Board, Game, Item, Player
	private String[][] chessBoard;
	private String[] lineCode;

	public Board(){
		createChessBoard();
	}
	public String[][] getChessBoard() {
		return chessBoard;
	}
	public void setChessBoard(String[][] chessBoard) {
		this.chessBoard = chessBoard;
	}
	public String[] getLineCode() {
		return lineCode;
	}
	public void setLineCode(String[] lineCode) {
		this.lineCode = lineCode;
	}


	public void createChessBoard(){
		chessBoard = new String[10][9];
		lineCode = new String[]{"j", "i", "h", "g", "f", "e", "d", "c", "b", "a"};
	}
	public int getLineCodeIndex(String row){
		if(row.equals("a"))	return 9;
		if(row.equals("b"))	return 8;
		if(row.equals("c"))	return 7;
		if(row.equals("d"))	return 6;
		if(row.equals("e"))	return 5;
		if(row.equals("f"))	return 4;
		if(row.equals("g"))	return 3;
		if(row.equals("h"))	return 2;
		if(row.equals("i"))	return 1;
		if(row.equals("j"))	return 0;
		return -1;
	}
	public Item getItem(String position){
		try{
			for(Item t: items){
				if(position.equals(t.getPosition())){
					return t;
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
	public void print() {
		for (int row=0; row<chessBoard.length; row++){
			System.out.print(lineCode[row]+"\t");
			int ctr = 0;
			for(int col=0; col<chessBoard[row].length; col++){
				String position = getLineCode()[row] + "" + (col+1);
				Item t = getItem(position);
				if(t == null){
					System.out.print("-");
				}
				else if( !t.getPosition().equals("xx")){
					System.out.print(t);
				}
				if(ctr < 8){
					System.out.print("--");
				}
				ctr++;
			}
			System.out.println();
			if(row == 0)	System.out.println(" \t|  |  |  |\\ | /|  |  |  |");
			if(row == 1)	System.out.println(" \t|  |  |  |/ | \\|  |  |  |");
			if(row == 2 || row == 3 || row == 5 || row == 6)	System.out.println(" \t|  |  |  |  |  |  |  |  |");
			if(row == 4)	System.out.println(" \t|                       |");
			if(row == 7)	System.out.println(" \t|  |  |  |/ | \\|  |  |  |");
			if(row == 8) 	System.out.println(" \t|  |  |  |\\ | /|  |  |  |");
		}
		System.out.println(" \t1--2--3--4--5--6--7--8--9");
		
	}

}
