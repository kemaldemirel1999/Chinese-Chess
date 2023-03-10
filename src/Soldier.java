public class Soldier extends Item{
    private boolean riverCrossed = false;
    private boolean endOfTable = false;

    public Soldier(String position, String name, float value){
        super(position,name, value);
        setValue(1);
    }

    @Override
    public boolean isItSuitableMove(String destination, int rowDiff, int colDiff) throws OutOfBoardException, PieceMovementException {
        char row = destination.substring(0,1).toLowerCase().charAt(0);
        int col = Integer.parseInt(destination.substring(1,2));
        if(row < 'a' || row > 'j' || col < 1 || col > 9){
            throw new OutOfBoardException("Soldier. Hatali Hareket.");
        }
        String rowName = getRowName().toLowerCase();
        if( getGame().red.equals(getOwner())){
            if(rowName.equals("j")){
                throw new PieceMovementException("Soldier. Hatali Hareket.");
            }
        }
        else if( getGame().black.equals(getOwner())){
            if(rowName.equals("a")){
                throw new PieceMovementException("Soldier. Hatali Hareket.");
            }
        }
        if(!riverCrossed){
            if(!(colDiff == 0 && (getGame().red.equals(getOwner()) && rowDiff == 1)  || (getGame().black.equals(getOwner()) && rowDiff == -1) )){
                throw new PieceMovementException("Soldier. Hatali Hareket.");
            }
        }
        else{
            if( getGame().red.equals(getOwner()) ){
                if(! ((rowDiff == 1 && colDiff == 0) || (rowDiff == 0 && colDiff == 1) || (rowDiff == 0 && colDiff == -1)) ){
                    throw new PieceMovementException("Soldier. Hatali Hareket.");
                }
            }
            else if( getGame().black.equals(getOwner()) ){
                if(! ((rowDiff == -1 && colDiff == 0) || (rowDiff == 0 && colDiff == 1) || (rowDiff == 0 && colDiff == -1)) ){
                    throw new PieceMovementException("Soldier. Hatali Hareket.");
                }
            }
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
                    if(!riverCrossed){
                        riverCrossed = isRiverCrossed();
                        if(riverCrossed){
                            setValue(2);
                        }
                    }
                }
            } catch (OutOfBoardException | FlyingRuleException |PieceMovementException e) {
                e.printStackTrace();
            }
        }
    }
}
