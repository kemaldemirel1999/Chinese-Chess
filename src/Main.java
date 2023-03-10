import java.util.Scanner;

public class Main {



    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        Game g = new Game("A", "B"); // A ve B adında iki oyuncu için oyunu başlatır.
        g.getBoard().print();
        while(true){
            String command = scanner.nextLine();
            if(command.equals("x")){
                break;
            }
            else if(command.contains("load") && command.contains("bin")){
                command = command.substring(5);
                g.load_binary(command);
                System.out.println("New Binary Board");
                System.out.println("red_puan:"+g.red.getPuan());
                System.out.println("black_puan:"+g.black.getPuan());
                g.getBoard().print();
                continue;
            }
            else if(command.contains("load") && command.contains("txt")){
                command = command.substring(5);
                g.load_text(command);
                System.out.println("New Text Board");
                System.out.println("red_puan:"+g.red.getPuan());
                System.out.println("black_puan:"+g.black.getPuan());
                g.getBoard().print();
                continue;
            }
            else if(command.contains("save") && command.contains("bin")){
                command = command.substring(5);
                g.save_binary(command);
                System.out.println("saved binary");
                continue;
            }
            else if(command.contains("save") && command.contains("txt")){
                command = command.substring(5);
                g.save_text(command);
                System.out.println("saved text");
                continue;
            }
            String from = command.substring(0,2);
            String to = command.substring(3,5);
            g.play(from,to);
            System.out.println("red_puan:"+g.red.getPuan());
            System.out.println("black_puan:"+g.black.getPuan());
            g.getBoard().print();
        }


    }



}
