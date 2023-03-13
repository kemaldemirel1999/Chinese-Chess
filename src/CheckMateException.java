public class CheckMateException extends Exception{
    String message = "";
    public CheckMateException(){
        this.message = "Sah cekildi. Hatali Hareket.";
    }
    public CheckMateException(String message){
        this.message = message;
    }
    public String getMessage(){
        return message;
    }
    public String toString(){
        return this.message;
    }
}
