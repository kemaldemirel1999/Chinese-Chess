

public class Main {



    public static void main(String[] args){
        Game g = new Game("A", "B"); // A ve B adında iki oyuncu için oyunu başlatır.
        g.play("a6","0"); // a1’deki taşı c1’e taşır.
        g.play("a1","b1"); // a1’deki taşı c1’e taşır.
        g.play("j3","g6"); // a1’deki taşı c1’e taşır.
        g.play("a5","b5"); // a1’deki taşı c1’e taşır.
        g.play("j1","i1"); // a1’deki taşı c1’e taşır.
        g.play("a3","f8"); // a1’deki taşı c1’e taşır.
        //g.save_binary("a"); // oyunun o halini kaydeder.
        //g.load_binary("a"); // kayıt edilen oyunu yükler
        g.getBoard().print();

    }



}
