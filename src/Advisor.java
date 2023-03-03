public class Advisor extends Item{

    public Advisor(String position, String name, float value){
        super(position, name, value);
    }
    @Override
    public void move(String destination) {
        int[] distance = calculateDistance(getPosition(), destination);
        if(distance == null){
            return;
        }
        int rowDiff = distance[0];
        int colDiff = distance[1];
        if( (Math.abs(rowDiff) == Math.abs(colDiff)) && (Math.abs(rowDiff) <=2 && Math.abs(colDiff) <= 2) && (Math.abs(rowDiff) >0 && Math.abs(colDiff) > 0)){
            if(!isItemInOwnPalace(destination)){
                return;
            }
            putItemToDestination(destination);
        }
    }

}
