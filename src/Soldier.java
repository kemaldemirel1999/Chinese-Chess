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
            System.out.println("Soldier hareket edemez.Tahta sonuna ulaşıldı");
            return;
        }
        int[] distance = calculateDistance(getPosition(), destination);
        if(distance != null){
            int rowDiff = distance[0];
            int colDiff = distance[1];
            // Nehir öncesi durum.
            if(!riverCrossed){
                moveBeforeCrossedRiver(destination, rowDiff, colDiff);
                riverCrossed = isRiverCrossed();
            }
            // Nehir geçildiği durum.
            else{
                moveAfterCrossedRiver(destination, rowDiff, colDiff);
            }
        }
    }
    public void moveAfterCrossedRiver(String to, int rowDiff, int colDiff){
        // Red taş ise
        if( getGame().red.equals(getOwner()) ){
            if( (rowDiff == 1 && colDiff == 0) || (rowDiff == 0 && colDiff == 1) || (rowDiff == 0 && colDiff == -1) ){
                putItemToDestination(to);
            }
        }
        // Black taş ise
        else if( getGame().black.equals(getOwner()) ){
            if( (rowDiff == -1 && colDiff == 0) || (rowDiff == 0 && colDiff == 1) || (rowDiff == 0 && colDiff == -1) ){
                putItemToDestination(to);
            }
        }
        else{
            System.out.println("Soldier. Nehir sonrasında gecersiz hareket.");
        }
    }
    public void moveBeforeCrossedRiver(String to, int rowDiff, int colDiff){
        if(colDiff == 0 && (getGame().red.equals(getOwner()) && rowDiff == 1)  || (getGame().black.equals(getOwner()) && rowDiff == -1) ){
            putItemToDestination(to);
        }
        else{
            System.out.println("Soldier. Nehir öncesi gecersiz hareket.");
        }
    }
}
