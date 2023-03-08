public class Horse extends Item{


    public Horse(String position, String name, float value){
        super(position,name, value);
    }

    // Diğer çapraz noktalar sıralanmalı
    // bir noktaya giderken diğer çaprazı kontrol et
    @Override
    public void move(String destination) {
        int[] distance = calculateDistance(getPosition(), destination);
        if(distance == null){
            return;
        }
        int rowDiff = distance[0];
        int colDiff = distance[1];
        try{
            if( (Math.abs(rowDiff) == 1 && Math.abs(colDiff) == 2) || (Math.abs(rowDiff) == 2 && Math.abs(colDiff) == 1)){
                if( Math.abs(rowDiff) == 2){
                    char destinationLine = destination.substring(0,1).charAt(0);
                    if(rowDiff < 0){
                        destinationLine = (char) (destinationLine + 1);
                    }
                    else{
                        destinationLine = (char) (destinationLine - 1);
                    }
                    String crossDestination =""+ destinationLine + ""+ destination.substring(1,2);
                    if(getBoard().getItem(crossDestination) == null){
                        putItemToDestination(destination);
                    }// Do not Move
                    else{
                        int destinationCol = Integer.parseInt(destination.substring(1,2));
                        if(colDiff < 0){
                            destinationCol =  destinationCol + 1;
                        }
                        else{
                            destinationCol =  destinationCol - 1;
                        }
                        crossDestination =""+ destination.substring(0,1) + ""+ destinationCol;
                        if(getBoard().getItem(crossDestination) == null){
                            putItemToDestination(destination);
                        }
                        else{
                            System.out.println("Horse. Cannot move");
                        }
                    }
                }
                else{
                    int destinationCol = Integer.parseInt(destination.substring(1,2));
                    if(colDiff < 0){
                        destinationCol =  destinationCol + 1;
                    }
                    else{
                        destinationCol =  destinationCol - 1;
                    }
                    String crossDestination =""+ destination.substring(0,1) + ""+ destinationCol;
                    if(getBoard().getItem(crossDestination) == null){
                        putItemToDestination(destination);
                    }// Do not Move
                    else{
                        char destinationLine = destination.substring(0,1).charAt(0);
                        if(rowDiff < 0){
                            destinationLine = (char) (destinationLine + 1);
                        }
                        else{
                            destinationLine = (char) (destinationLine - 1);
                        }
                        crossDestination = ""+ destinationLine + ""+ destination.substring(1,2);
                        if(getBoard().getItem(crossDestination) == null){
                            putItemToDestination(destination);
                        }
                        else{
                            System.out.println("Horse. Cannot move");
                        }
                    }
                }
            }
        }catch (OutOfBoardException e){
            System.out.println(e.getMessage());
        }

    }

}
