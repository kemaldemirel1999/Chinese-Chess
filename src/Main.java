

public class Main {



    public static void main(String[] args){
        Game g = new Game("A", "B"); // A ve B adında iki oyuncu için oyunu başlatır.
        g.getBoard().print(); // tahtanın o anki anını tahtaya bastırır.
        g.play("a1","d1"); // a1’deki taşı c1’e taşır.
        g.play("a1","b1");
        g.play("j3","f7");
        //g.save_binary("a"); // oyunun o halini kaydeder.
        //g.load_binary("a"); // kayıt edilen oyunu yükler
        g.getBoard().print();

    }



}