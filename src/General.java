public class General extends Item{
    public General(String position, String name, float value){
        super(position,name, value);

    }
    @Override
    public void move(String destination) {
        if(getGame().red.equals(getOwner())){
            String destinationRow = destination.substring(0,1);
            int destinationCol = Integer.parseInt(destination.substring(1,2));
            if ( destinationRow.charAt(0) > 'c' ||  destinationCol < 4 || destinationCol > 6){
                System.out.println("Red General. Dışarıya çıkamaz");
                return;
            }
        }
        else{
            String destinationLine = destination.substring(0,1);
            int destinationCol = Integer.parseInt(destination.substring(1,2));
            if ( destinationLine.charAt(0) < 'f' || destinationCol < 4 ||destinationCol > 6){
                System.out.println("Black General. Palace dışına çıkamaz");
                return;
            }
        }
        int[] distance = calculateDistance(getPosition(), destination);
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
