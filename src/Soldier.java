public class Soldier extends Item{


    private boolean riverCrossed = false;
    private boolean endOfTable = false;

    public Soldier(String position, String name, float value){
        super(position,name, value);
        setValue(1);
    }
    @Override
    public void move(String destination) {
        if(endOfTable){
            System.out.println("Hareket edilemez. Tahta sonuna ulaşıldı");
            return;
        }
        int[] distance = calculateDistance(getPosition(), destination);
        int rowDiff = distance[0];
        int colDiff = distance[1];
        if(!riverCrossed){
            moveBeforeCrossedRiver(destination, rowDiff, colDiff);
            if(getRowName().compareTo("e") > 0){
                riverCrossed = true;
            }
        }
        else{
            moveAfterCrossedRiver(destination, rowDiff, colDiff);
        }
    }
    public void moveAfterCrossedRiver(String to, int rowDiff, int colDiff){

    }
    public void moveBeforeCrossedRiver(String to, int rowDiff, int colDiff){
        if(colDiff != 0){
            System.out.println("Soldier. Invalid move");
            return;
        }
    }
}
