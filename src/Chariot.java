public class Chariot extends Item{
    public Chariot(String position, String name, float value){
        super(position,name, value);

    }
    @Override
    public void move(String destination) {
        int[] distance = calculateDistance(getPosition(), destination);
        int rowDiff = distance[0];
        int colDiff = distance[1];
        if(rowDiff == 0 && colDiff == 0){
            System.out.println("Chariot. Invalid Move");
        }
        else if(rowDiff != 0){
            if (!isRowClear(destination, rowDiff)){
                return;
            }
            putItemToDestination(destination);
        }
        else{
            if (!isColumnClear(destination, colDiff)){
                return;
            }
            putItemToDestination(destination);
        }
    }




}
