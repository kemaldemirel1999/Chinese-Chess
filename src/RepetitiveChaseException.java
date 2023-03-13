public class RepetitiveChaseException extends Exception{
    private String message = "";

    public RepetitiveChaseException(){
        this.message = "Repetitive Chase hatasi";
    }
    public RepetitiveChaseException(String message){
        this.message = message;
    }
    public String toString(){
        return this.message;
    }
    public String getMessage(){
        return this.message;
    }
}
