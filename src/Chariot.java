public class Chariot extends Item{
    public Chariot(String position, String name, float value){
        super(position,name, value);

    }
    @Override
    public void move(String destination) {
        int[] distance = calculateDistance(getPosition(), destination);
        if(distance != null){
            int rowDiff = distance[0];
            int colDiff = distance[1];
            try{
                if(isItSuitableMove(destination, rowDiff, colDiff)){
                    if(rowDiff != 0){
                        if (isRowClear(destination, rowDiff)){
                            putItemToDestination(destination,false);
                        }
                    }
                    else{
                        if (isColumnClear(destination, colDiff)){
                            putItemToDestination(destination,false);
                        }
                    }
                }
            }catch (PieceMovementException| FlyingRuleException | OutOfBoardException | CheckMateException e){
                System.out.println(e);
            }
        }
    }

    public boolean moveCheck(String destination) {
        int[] distance = calculateDistance(getPosition(), destination);
        if(distance != null){
            int rowDiff = distance[0];
            int colDiff = distance[1];
            try{

                if(isItSuitableMove(destination, rowDiff, colDiff)){
                    if(rowDiff != 0){
                        if (isRowClear(destination, rowDiff)){
                            putItemToDestination(destination,true);
                        }
                    }
                    else{
                        if (isColumnClear(destination, colDiff)){
                            putItemToDestination(destination,true);
                        }
                    }
                }
            }catch (PieceMovementException| FlyingRuleException | OutOfBoardException | CheckMateException e){
                return false;
            }
        }
        return true;
    }


    @Override
    public boolean isItSuitableMove(String destination, int rowDiff, int colDiff) throws OutOfBoardException, PieceMovementException{
        char row = destination.substring(0,1).toLowerCase().charAt(0);
        int col = Integer.parseInt(destination.substring(1,2));
        if(row < 'a' || row > 'j' || col < 1 || col > 9){
            throw new OutOfBoardException("Chariot. Hatali Hareket.");
        }
        if(! ( (rowDiff == 0 && colDiff != 0) || (rowDiff != 0 && colDiff == 0) )  ) {
            throw new PieceMovementException("Chariot. Hatali Hareket.");
        }
        return true;
    }




}
