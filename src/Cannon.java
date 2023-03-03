public class Cannon extends Item{

    public Cannon(String position, String name, float value){
        super(position,name, value);
    }
    @Override
    public void move(String destination) {
        int[] distance = calculateDistance(getPosition(), destination);
        if(distance == null){
            return;
        }
        int rowDiff = distance[0];
        int colDiff = distance[1];
        if(rowDiff == 0 && colDiff == 0){
            System.out.println("Cannon. Invalid Move");
        }
        else if(rowDiff != 0){
            if(isDestinationEmpty(destination)){
                if (!isRowClear(destination, rowDiff)){
                    System.out.println("Cannon. Row Not Clear");
                    return;
                }
            }
            else{
                if(!isRowCannonRuleSatisfy(destination, rowDiff)){
                    System.out.println("Cannon. Row Rule Not Satisfy");
                    return;
                }
            }
            putItemToDestination(destination);
        }
        else{
            if(isDestinationEmpty(destination)){
                if (!isColumnClear(destination, colDiff)){
                    System.out.println("Cannon. Column Not Clear");
                    return;
                }
            }
            else{
                if(!isColumnCannonRuleSatisfy(destination, colDiff)){
                    System.out.println("Cannon. Column Rule Not Satisfy");
                    return;
                }
            }
            putItemToDestination(destination);
        }
    }
    public boolean isRowCannonRuleSatisfy(String destination, int rowDiff){
        String[][] chessBoard = getBoard().getChessBoard();
        String currentPosition = getPosition();
        int fromLineIndex = getBoard().getLineCodeIndex(getPosition().substring(0,1));
        int toLineIndex = getBoard().getLineCodeIndex(destination.substring(0,1));
        int fromColumnIndex = Integer.parseInt(getPosition().substring(1,2));
        int counter = 0;
        if(rowDiff < 0){
            for(int row=fromLineIndex+1; row<toLineIndex; row++){
                if(getBoard().getItem(""+getBoard().getLineCode()[row]+""+fromColumnIndex) != null){
                    counter ++;
                }
            }
        }
        else if(rowDiff > 0){
            for(int row=fromLineIndex-1; row>toLineIndex; row--){
                if(getBoard().getItem(""+getBoard().getLineCode()[row]+""+fromColumnIndex) != null){
                    counter ++;
                }
            }
        }
        if(counter == 1){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isColumnCannonRuleSatisfy(String destination, int colDiff){
        String[][] chessBoard = getBoard().getChessBoard();
        String currentPosition = getPosition();
        int fromLineIndex = getBoard().getLineCodeIndex(getPosition().substring(0,1));
        int toLineIndex = getBoard().getLineCodeIndex(destination.substring(0,1));
        int fromColumnIndex = Integer.parseInt(getPosition().substring(1,2));
        int toColumnIndex = Integer.parseInt(destination.substring(1,2));
        int counter = 0;
        if(colDiff > 0){
            for(int col=fromColumnIndex+1; col<toColumnIndex-1; col++){
                if(getBoard().getItem(""+getBoard().getLineCode()[fromLineIndex]+""+col) != null){
                    counter ++;
                }
            }
        }
        else if(colDiff < 0){
            for(int col=fromColumnIndex-1; col>toColumnIndex-1; col--){
                if(getBoard().getItem(""+getBoard().getLineCode()[fromLineIndex]+""+col) != null){
                    counter ++;
                }
            }
        }
        if(counter == 1){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean isDestinationEmpty(String destination){
        if (getBoard().getItem(destination) == null ){
            return true;
        }
        return  false;
    }

}
