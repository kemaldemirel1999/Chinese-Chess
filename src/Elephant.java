public class Elephant extends Item{
    public Elephant(String position, String name, float value){
        super(position,name,value);

    }

    @Override
    public void move(String destination) {
        if(!isItAfterRiver(destination)){
            int[] distance = calculateDistance(getPosition(), destination);
            if(distance != null){
                int rowDiff = distance[0];
                int colDiff = distance[1];
                if(isDimensionSuitableToCross(rowDiff, colDiff)){
                    if ( isCrossClear(destination, rowDiff, colDiff) ){
                        putItemToDestination(destination);
                    }
                }
            }
        }
    }

}
