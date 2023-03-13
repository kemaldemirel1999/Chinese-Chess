public class RepetitiveCheckException extends Exception{
    private String message = "";

    public RepetitiveCheckException(){
        this.message = "Repetitive Check hatasi";
    }
    public RepetitiveCheckException(String message){
        this.message = message;
    }
    public String toString(){
        return this.message;
    }
    public String getMessage(){
        return this.message;
    }
}
