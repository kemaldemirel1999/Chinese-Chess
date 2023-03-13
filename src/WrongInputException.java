public class WrongInputException extends Exception{

    private String message = "";

    public WrongInputException(){
        this.message = "Repetitive Check hatasi";
    }
    public WrongInputException(String message){
        this.message = message;
    }
    public String toString(){
        return this.message;
    }
    public String getMessage(){
        return this.message;
    }
}
