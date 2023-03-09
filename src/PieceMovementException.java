public class PieceMovementException extends Exception{

    private String message = "";

    public PieceMovementException(){
        this.message = "Taş hareketi hatali";
    }
    public PieceMovementException(String message){
        this.message = message;
    }
    public String toString(){
        return "Taş hareketi hatalidir.";
    }
    public String getMessage(){
        return this.message;
    }
}
