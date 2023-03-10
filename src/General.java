public class General extends Item{
    public General(String position, String name, float value){
        super(position,name, value);

    }

    @Override
    public boolean isItSuitableMove(String destination, int rowDiff, int colDiff) throws OutOfBoardException, PieceMovementException {
        char row = destination.substring(0,1).toLowerCase().charAt(0);
        int col = Integer.parseInt(destination.substring(1,2));
        if(row < 'a' || row > 'j' || col < 1 || col > 9){
            throw new OutOfBoardException("General. Hatali Hareket.");
        }
        if(!isItemInOwnPalace(destination)){
            throw new PieceMovementException("General. Hatali Haraket.");
        }
        if(!((rowDiff == 0 && Math.abs(colDiff) == 1 ) || (Math.abs(rowDiff) == 1 && colDiff == 0))){
            throw new PieceMovementException("General. Hatali Haraket.");
        }
        return true;
    }

    @Override
    public void move(String destination) {
        int[] distance = calculateDistance(getPosition(), destination);
        if(distance != null){
            int rowDiff = distance[0];
            int colDiff = distance[1];
            try {
                if(isItSuitableMove(destination, rowDiff, colDiff)){
                    putItemToDestination(destination);
                }
            } catch (OutOfBoardException| FlyingRuleException | PieceMovementException e) {
                System.out.println(e);
            }
        }
    }

}
