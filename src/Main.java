

public class Main {



    public static void main(String[] args){
        Game g = new Game("A", "B"); // A ve B adında iki oyuncu için oyunu başlatır.
        //g.save_binary("a"); // oyunun o halini kaydeder.
        //g.load_binary("a"); // kayıt edilen oyunu yükler
        //g.getBoard().print();
        //g.red.setPuan(1);
        //g.save_binary("binary.bin");
        //g.save_text("deneme.txt");
        //g.play("a1","b1");
        //g.save_binary("binary.bin");
        g.load_binary("binary.bin");
        g.getBoard().print();

    }



}
