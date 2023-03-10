public class Horse extends Item{


    public Horse(String position, String name, float value){
        super(position,name, value);
    }

    @Override
    public boolean isItSuitableMove(String destination, int rowDiff, int colDiff) throws OutOfBoardException, PieceMovementException {
        char row = destination.substring(0,1).toLowerCase().charAt(0);
        int col = Integer.parseInt(destination.substring(1,2));
        if(row < 'a' || row > 'j' || col < 1 || col > 9){
            throw new OutOfBoardException("Horse. Hatali Hareket.");
        }
        if(!((Math.abs(rowDiff) == 1 && Math.abs(colDiff) == 2) || (Math.abs(rowDiff) == 2 && Math.abs(colDiff) == 1))){
            throw new PieceMovementException("Horse. Hatali Hareket.");
        }
        return true;
    }

    public boolean isEmptyWaypoint(String destination, int rowDiff, int colDiff){
        String waypointPosition = null;
        if( Math.abs(rowDiff) == 2){
            char destinationRow = destination.substring(0,1).charAt(0);
            if(rowDiff < 0)
                destinationRow = (char) (destinationRow + 1);
            else
                destinationRow = (char) (destinationRow - 1);
            waypointPosition =""+ destinationRow + ""+ destination.substring(1,2);
        }
        else if(Math.abs(colDiff) == 2){
            int destinationCol = Integer.parseInt(destination.substring(1,2));
            if(colDiff < 0){
                destinationCol =  destinationCol + 1;
            }
            else{
                destinationCol =  destinationCol - 1;
            }
            waypointPosition =""+ destination.substring(0,1) + ""+ destinationCol;
        }
        if(waypointPosition != null){
            try{
                if(getBoard().getItem(waypointPosition) == null){
                    return true;
                }
            }catch (OutOfBoardException e){
                return false;
            }
        }
        return false;
    }
    @Override
    public void move(String destination) {
        int[] distance = calculateDistance(getPosition(), destination);
        if(distance != null){
            int rowDiff = distance[0];
            int colDiff = distance[1];
            try {
                if(isItSuitableMove(destination, rowDiff, colDiff) ){
                    if(isEmptyWaypoint(destination, rowDiff, colDiff)){
                        putItemToDestination(destination);
                    }
                }
            } catch (OutOfBoardException| FlyingRuleException | PieceMovementException e) {
                e.printStackTrace();
            }
        }
    }
}
