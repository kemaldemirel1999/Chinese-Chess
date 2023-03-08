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
            if(isDimesionSuitableToChariotMove(rowDiff, colDiff)){
                if(rowDiff != 0){
                    if (isRowClear(destination, rowDiff)){
                        putItemToDestination(destination);
                    }
                }
                else{
                    if (isColumnClear(destination, colDiff)){
                        putItemToDestination(destination);
                    }
                }
            }

        }
    }

    public boolean isDimesionSuitableToChariotMove(int rowDiff, int colDiff){
        return (rowDiff == 0 && colDiff != 0) || (rowDiff != 0 && colDiff == 0);
    }




}
