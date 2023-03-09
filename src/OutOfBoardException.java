public class OutOfBoardException extends Exception{

    String message = "";
    public OutOfBoardException(){
        this.message = "Index disine cikildi";
    }
    public OutOfBoardException(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
    public String toString(){
        return this.message;
    }
}
