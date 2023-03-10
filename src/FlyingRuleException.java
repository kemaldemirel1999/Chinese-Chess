public class FlyingRuleException extends Exception{

    private String message = "";

    public FlyingRuleException(){
        this.message = "Flying Rule Yüzünden Hamle Gerçekleştirilemedi.";
    }
    public FlyingRuleException(String message){
        this.message = message;
    }
    public String toString(){
        return this.message;
    }
    public String getMessage(){
        return this.message;
    }

}
