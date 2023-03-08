

public class Main {



    public static void main(String[] args){
        Game g = new Game("A", "B"); // A ve B adında iki oyuncu için oyunu başlatır.
        g.play("a2","b4"); // a1’deki taşı c1’e taşır.
        //g.save_binary("a"); // oyunun o halini kaydeder.
        //g.load_binary("a"); // kayıt edilen oyunu yükler
        g.getBoard().print();

    }



}
