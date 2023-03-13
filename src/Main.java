import java.util.Scanner;

public class Main {

    public static void trial(){
        System.out.println("j\tK--A--F--V--Ş--V--F--A--K");
        System.out.println(" \t| | | |\\ | /| | | |");
        System.out.println("i\t-------------------------");
        System.out.println(" \t| | | |/ | \\| | | |");
        System.out.println("h\t---T-----------------T---");
        System.out.println(" \t| | | | | | | | |");
        System.out.println("g\tP-----P-----P-----P-----P");
        System.out.println(" \t| | | | | | | | |");
        System.out.println("f\t-------------------------");
        System.out.println(" \t| |");
        System.out.println("e\t-------------------------");
        System.out.println(" \t| | | | | | | | |");
        System.out.println("d\tp-----p-----p-----p-----p");
        System.out.println(" \t| | | | | | | | |");
        System.out.println("c\t---t-----------------t---");
        System.out.println(" \t| | | |/ | \\| | | |");
        System.out.println("b\t-------------------------");
        System.out.println(" \t| | | |\\ | /| | | |");
        System.out.println("a\tk--a--f--v--ş--v--f--a--k");
        System.out.println();
        System.out.println(" \t1--2--3--4--5--6--7--8--9");

        System.out.println(" *******");
    }

    public static void main(String[] args){

        trial();
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
                g.getBoard().print();
                continue;
            }
            else if(command.contains("load") && command.contains("txt")){
                command = command.substring(5);
                g.load_text(command);
                g.getBoard().print();
                continue;
            }
            else if(command.contains("save") && command.contains("bin")){
                command = command.substring(5);
                g.save_binary(command);
                continue;
            }
            else if(command.contains("save") && command.contains("txt")){
                command = command.substring(5);
                g.save_text(command);
                continue;
            }
            String from = command.substring(0,2);
            String to = command.substring(3);
            g.play(from,to);
            //System.out.println("red_puan:"+g.red.getPuan());
            //System.out.println("black_puan:"+g.black.getPuan());
            g.getBoard().print();
        }


    }



}
