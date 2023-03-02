public class Soldier extends Item{


    private boolean riverCrossed = false;
    private boolean endOfTable = false;

    public Soldier(String position, String name, float value){
        super(position,name, value);
        setValue(1);
    }
    @Override
    public void move(String to) {
        if(endOfTable){
            System.out.println("Hareket edilemez. Tahta sonuna ulaşıldı");
            return;
        }
        if(!riverCrossed){
            moveBeforeCrossedRiver(to);
            if(getRowName().compareTo("e") > 0){
                riverCrossed = true;
            }
        }
        else{
            moveAfterCrossedRiver(to);
        }
    }
    public void moveAfterCrossedRiver(String to){

    }
    public void moveBeforeCrossedRiver(String to){

    }
}
