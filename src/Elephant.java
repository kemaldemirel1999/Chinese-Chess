public class Elephant extends Item{
    public Elephant(String position, String name, float value){
        super(position,name,value);

    }
    @Override
    public boolean isItSuitableMove(String destination, int rowDiff, int colDiff) throws OutOfBoardException, PieceMovementException {
        char row = destination.substring(0,1).toLowerCase().charAt(0);
        int col = Integer.parseInt(destination.substring(1,2));
        if(row < 'a' || row > 'j' || col < 1 || col > 9){
            throw new OutOfBoardException("Elephant. Hatali Hareket.");
        }
        if(Math.abs(rowDiff) != Math.abs(colDiff) || ( rowDiff == 0 || colDiff == 0) ){
            throw new PieceMovementException("Elephant. Hatali Hareket.");
        }
        if(Math.abs(rowDiff) != 2 || Math.abs(colDiff) != 2){
            throw new PieceMovementException("Elephant. Hatali Hareket.");
        }
        return true;
    }

    @Override
    public void move(String destination) {
        if(!isItAfterRiver(destination)){
            int[] distance = calculateDistance(getPosition(), destination);
            if(distance != null){
                int rowDiff = distance[0];
                int colDiff = distance[1];
                try {
                    if(isItSuitableMove(destination, rowDiff, colDiff)){
                        if ( isCrossClear(destination, rowDiff, colDiff) ){
                            putItemToDestination(destination);
                        }
                    }
                } catch (OutOfBoardException | FlyingRuleException | PieceMovementException | CheckMateException e) {
                    System.out.println(e);
                }
            }
        }
    }

}
