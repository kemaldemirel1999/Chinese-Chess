public class Cannon extends Item{

    public Cannon(String position, String name){
        super(position,name);
    }
    @Override
    public void move(String destination) {
        int[] distances = calculateDistance(getPosition(), destination);
        int lineDiff = distances[0];
        int rowDiff = distances[1];
    }
}
