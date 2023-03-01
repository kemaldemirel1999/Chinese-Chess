
public class Game extends AbstractGame{

    private int which_players_turn = -1;

    public Game(String p1_name, String p2_name){
        white = new Player(p1_name);
        black = new Player(p2_name);
        board = new Board();
        which_players_turn = 1;
    }
    public void play(String from, String to){
        try{
            if(isItValidMove(from, to)){
                Item item = board.getItem(from);
                if(item != null){

                    increasePlayersScore(which_players_turn);
                    changePlayerTurn();
                }
                else{
                    System.out.println("Item is null");
                }
            }
            else {
                System.out.println("Not Valid Move");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public int getWhich_players_turn() {
        return which_players_turn;
    }
    public void setWhich_players_turn(int which_players_turn) {
        this.which_players_turn = which_players_turn;
    }

    public Board getBoard(){
        return board;
    }
    public void changePlayerTurn(){
        if(which_players_turn == 1)
            which_players_turn = 2;
        else
            which_players_turn = 1;
    }

    public void increasePlayersScore(int player_no){
        Player p;
        if(player_no == 1){
            p = white;
            p.setPuan(p.getPuan());
        }
        else if(player_no == 2){
            p = black;
            p.setPuan(p.getPuan());
        }
        else{
            System.out.println("increasePlayerScore error. There is no player ,"+player_no);
        }
    }
    public boolean isItValidMove(String from, String to){
        int fromPlayer = getWhichPlayersItem(from);
        int toPlayer = getWhichPlayersItem(to);
        if(fromPlayer == -1 || toPlayer == -1){
            System.out.println("Hata, indeks dışına çıkıldı");
            return false;
        }
        if(fromPlayer == 0){
            System.out.println("Hata, başlangıç pozisyonu boş");
            return false;
        }
        if(fromPlayer == toPlayer){
            System.out.println("Hata, Kendi taşının üstüne hareket edilemez");
            return false;
        }
        return fromPlayer == which_players_turn;
    }
    public int getWhichPlayersItem(String position){
        int row = Integer.parseInt(position.substring(0,1));
        int col = Integer.parseInt(position.substring(1,2));
        try {
            String itemName = board.getChessBoard()[row][col];
            if( !itemName.equals("-")){
                if(itemName.equals(itemName.toLowerCase())){
                    return 1;
                }
                else if(itemName.equals(itemName.toUpperCase())){
                    return 2;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
            return -1;
        }
        return 0;
    }

    public void save_binary(String address){

    }
    public void save_text(String address){

    }
    public void load_text(String address){

    }
    public void load_binary(String address){

    }

}
