public class Elephant extends Item{
    public Elephant(String position, String name, float value){
        super(position,name,value);

    }
    @Override
    public void move(String destination) {
        int[] distance = calculateDistance(getPosition(), destination);
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
