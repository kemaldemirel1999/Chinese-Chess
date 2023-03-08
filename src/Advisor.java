public class Advisor extends Item{

    public Advisor(String position, String name, float value){
        super(position, name, value);
    }
    @Override
    public void move(String destination) {
        int[] distance = calculateDistance(getPosition(), destination);
        if(distance != null){
            int rowDiff = distance[0];
            int colDiff = distance[1];
            if(isDimensionSuitableToCross(rowDiff,colDiff) && isItemInOwnPalace(destination)){
                if(isItemInOwnPalace(destination)){
                    putItemToDestination(destination);
                }
            }
        }
    }
}
