public class General extends Item{
    public General(String position, String name, float value){
        super(position,name, value);

    }
    @Override
    public void move(String destination) {
        if(isItemInOwnPalace(destination)){
            int[] distance = calculateDistance(getPosition(), destination);
            if(distance != null){
                int rowDiff = distance[0];
                int colDiff = distance[1];
                if(isDimesionSuitableToGeneral(rowDiff, colDiff)){
                    putItemToDestination(destination);
                }
            }
        }
    }
    public boolean isDimesionSuitableToGeneral(int rowDiff, int colDiff){
        return ((rowDiff == 0 && Math.abs(colDiff) == 1 ) || (Math.abs(rowDiff) == 1 && colDiff == 0));
    }

}
