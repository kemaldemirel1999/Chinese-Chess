public class Advisor extends Item{

    public Advisor(String position, String name, float value){
        super(position, name, value);
    }

    @Override
    public boolean isItSuitableMove(String destination, int rowDiff, int colDiff) throws OutOfBoardException, PieceMovementException {
        char row = destination.substring(0,1).toLowerCase().charAt(0);
        int col = Integer.parseInt(destination.substring(1,2));
        if(row < 'a' || row > 'j' || col < 1 || col > 9){
            throw new OutOfBoardException("Advisor. Hatali Hareket.");
        }
        if(!isItemInOwnPalace(destination)){
            throw new PieceMovementException("Advisor. Hatali Haraket.");
        }
        if(Math.abs(rowDiff) != 1 || Math.abs(colDiff) != 1){
            throw new PieceMovementException("Advisor. Hatali Haraket.");
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
                    if(isItemInOwnPalace(destination)){
                        putItemToDestination(destination);
                    }
                }
            } catch (OutOfBoardException | FlyingRuleException | PieceMovementException | CheckMateException e) {
                System.out.println(e);
            }
        }
    }
}
