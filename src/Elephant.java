public class Elephant extends Item{
    public Elephant(String position, String name, float value){
        super(position,name,value);

    }
    @Override
    public void move(String destination) {
        if(getGame().red.equals(getOwner())){
            String destinationLine = destination.substring(0,1);
            if ( destinationLine.charAt(0) > 'e' ){
                System.out.println("Red Elephant. Nehrin karşısına geçemez");
                return;
            }
        }
        else{
            String destinationLine = destination.substring(0,1);
            if ( destinationLine.charAt(0) < 'f' ){
                System.out.println("Black Elephant. Nehrin karşısına geçemez");
                return;
            }
        }
        int[] distance = calculateDistance(getPosition(), destination);
        if(distance == null){
            return;
        }
        int rowDiff = distance[0];
        int colDiff = distance[1];
        if(rowDiff == 0 || colDiff == 0){
            System.out.println("Chariot. Invalid Move");
        }
        if(isDimensionSuitableToCross(rowDiff, colDiff)){
            if ( isCrossClear(destination, rowDiff, colDiff) ){
                putItemToDestination(destination);
            }
            else {
                System.out.println("Elephant. Çapraz dolu olduğundan hareket yapılamaz.");
            }
        }
        else{
            System.out.println("Elephant. Dimension cross için uygun değil.");
        }
    }
}
