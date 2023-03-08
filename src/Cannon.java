public class Cannon extends Item{

    public Cannon(String position, String name, float value){
        super(position,name, value);
    }

    @Override
    public void move(String destination) {
        int[] distance = calculateDistance(getPosition(), destination);
        if(distance != null){
            int rowDiff = distance[0];
            int colDiff = distance[1];
            if(isDimensionSuitableToCannon(rowDiff, colDiff)){
                if(isDestinationEmpty(destination)){
                    if(rowDiff != 0 && isRowClear(destination, rowDiff)){
                        putItemToDestination(destination);
                    }
                    else if(colDiff != 0 && isColumnClear(destination, colDiff) ){
                        putItemToDestination(destination);
                    }
                }
                else{
                    if(rowDiff != 0 && isRowCannonRuleSatisfy(destination, rowDiff)){
                        putItemToDestination(destination);
                    }
                    else if(colDiff != 0 && isColumnCannonRuleSatisfy(destination, rowDiff)){
                        putItemToDestination(destination);
                    }
                }
            }
        }

    }
    public boolean isRowCannonRuleSatisfy(String destination, int rowDiff){
        int fromLineIndex = getBoard().getLineCodeIndex(getPosition().substring(0,1));
        int toLineIndex = getBoard().getLineCodeIndex(destination.substring(0,1));
        int fromColumnIndex = Integer.parseInt(getPosition().substring(1,2));
        int counter = 0;
        try{
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
        }catch (OutOfBoardException e){
            System.out.println(e.getMessage());
            return false;
        }
        return counter == 1;
    }

    public boolean isColumnCannonRuleSatisfy(String destination, int colDiff){
        int fromLineIndex = getBoard().getLineCodeIndex(getPosition().substring(0,1));
        int fromColumnIndex = Integer.parseInt(getPosition().substring(1,2));
        int toColumnIndex = Integer.parseInt(destination.substring(1,2));
        int counter = 0;
        try{
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
        }catch (OutOfBoardException e){
            System.out.println(e.getMessage());
            return false;
        }
        return counter == 1;
    }

    public boolean isDimensionSuitableToCannon(int rowDiff, int colDiff){
        return (rowDiff == 0 && colDiff != 0) || (rowDiff != 0 && colDiff == 0);
    }

}
