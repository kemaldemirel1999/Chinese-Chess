
public class Game extends AbstractGame{

    private int which_players_turn = -1;

    public Game(String p1_name, String p2_name){
        red = new Player(p1_name);  // Player1(küçükler)
        black = new Player(p2_name);    //  Player2(büyükler)
        board = new Board();
        which_players_turn = 1;
        Item[] items = board.items;
        for (int i=0, j= items.length/2 ;i<items.length/2 && j<items.length; i++, j++){
            items[i].setOwner(black);
            items[j].setOwner(red);
            items[i].setBoard(board);
            items[j].setBoard(board);
            items[i].setGame(this);
            items[j].setGame(this);
        }
    }
    public void play(String from, String to){
        int previousPlayerNumber = which_players_turn;
        try{
            Item item = board.getItem(from);
            if(item != null){
                if(item.getOwner().equals(red) && which_players_turn == 1){
                    item.move(to);
                }
                else if(item.getOwner().equals(black) && which_players_turn == 2){
                    item.move(to);
                }
                else{
                    System.out.println("Aynı oyuncu üst üste iki kez oynayamaz veya rakip taş hareket ettirilemez.");
                }
            }
        }catch (OutOfBoardException e){
            System.out.println(e);
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


    public void save_binary(String address){

    }
    public void save_text(String address){

    }
    public void load_text(String address){

    }
    public void load_binary(String address){

    }

}
