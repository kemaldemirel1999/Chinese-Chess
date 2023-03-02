public class Horse extends Item{


    public Horse(String position, String name, float value){
        super(position,name, value);
    }
    @Override
    public void move(String destination) {
        System.out.println(this.getClass());
    }

}
