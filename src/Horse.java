public class Horse extends Item{


    public Horse(String position, String name, float value){
        super(position,name, value);
    }
    @Override
    public void move(String destination) {
        int[] distance = calculateDistance(getPosition(), destination);
        if(distance == null){
            return;
        }
        int rowDiff = distance[0];
        int colDiff = distance[1];

    }

}
