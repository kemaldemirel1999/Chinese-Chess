public class General extends Item{
    public General(String position, String name, float value){
        super(position,name, value);

    }
    @Override
    public void move(String destination) {
        if(!isItemInOwnPalace(destination)){
            return;
        }
        int[] distance = calculateDistance(getPosition(), destination);
        if(distance == null){
            return;
        }
        int rowDiff = distance[0];
        int colDiff = distance[1];
        if((rowDiff == 0 && Math.abs(colDiff) == 1 ) || (Math.abs(rowDiff) == 1 && colDiff == 0)){
            putItemToDestination(destination);
        }
        else{
            System.out.println("General. Invalid move.");
        }
    }

}
