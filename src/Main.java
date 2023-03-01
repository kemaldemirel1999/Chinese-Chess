

public class Main {



    public static void main(String[] args){
        Game g = new Game("A", "B"); // A ve B adında iki oyuncu için oyunu başlatır.
        g.getBoard().print(); // tahtanın o anki anını tahtaya bastırır.
        //g.play("a1","c1"); // a1’deki taşı c1’e taşır.
        //g.save_binary("a"); // oyunun o halini kaydeder.
        //g.load_binary("a"); // kayıt edilen oyunu yükler
        //g.play("c1", "d1"); // hata mesajı vermesi lazım çünkü aynı oyuncu iki kez üst üste oynayamaz.

    }



}
