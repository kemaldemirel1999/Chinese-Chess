import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Scanner;


public class Game extends AbstractGame{

    private int which_players_turn = -1;
    private Player winnerPlayer;
    private boolean isItDraw;

    public Game(String p1_name, String p2_name){
        red = new Player(p1_name);  // Player1(küçükler)
        black = new Player(p2_name);    //  Player2(büyükler)
        board = new Board();
        which_players_turn = 1;
        Item[] items = board.items;
        winnerPlayer = null;
        isItDraw = false;
        for (int i=0, j= items.length/2 ;i<items.length/2 && j<items.length; i++, j++){
            items[i].setOwner(black);
            items[j].setOwner(red);
            items[i].setBoard(board);
            items[j].setBoard(board);
            items[i].setGame(this);
            items[j].setGame(this);
        }
    }

    public Player getWinnerPlayer() {
        return winnerPlayer;
    }

    public void setWinnerPlayer(Player winnerPlayer) {
        this.winnerPlayer = winnerPlayer;
    }

    public void play(String from, String to){
        try{
            if(from.length() != 2 || to.length() !=2){
                throw new WrongInputException();
            }
            String fromRow;
            String toRow;
            int fromCol;
            int toCol;
            try{
                fromRow = from.substring(0,1);
                toRow = to.substring(0,1);
                fromCol = Integer.parseInt(from.substring(1));
                toCol = Integer.parseInt(to.substring(1));
            }catch (Exception e){
                throw new WrongInputException();
            }
            if(fromRow.toLowerCase().charAt(0) > 'j' || fromRow.toLowerCase().charAt(0) < 'a'){
                throw new WrongInputException();
            }
            if(toRow.toLowerCase().charAt(0) > 'j' || toRow.toLowerCase().charAt(0) < 'a'){
                throw new WrongInputException();
            }
            if(fromCol > 9 ||fromCol < 1 || toCol >9 || toCol < 1){
                throw new WrongInputException();
            }
        }catch (WrongInputException e){
            System.out.println("Hatali input girişi. Tekrar deneyiniz.");
            return;
        }
        if(winnerPlayer != null){
            System.out.println("Oyun sonlandigi icin hamle yapilamaz. Kazanan:"+winnerPlayer.getPlayer_name());
            return;
        }
        if(isItDraw){
            System.out.println("Oyunda kazanan yoktur.Hamle yapılamaz. Eşitlik.");
            return;
        }
        else{
            if(!checkCriteriaOfDraw()){
                System.out.println("Oyunda kazanan yoktur. Eşitlik.");
                isItDraw = true;
                return;
            }
        }

        try{
            Item item = board.getItem(from);
            if(item != null){
                // RED's turn
                if(item.getOwner().equals(red) && which_players_turn == 1){
                    item.move(to);
                }
                // BLACK's turn
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
        if(red.isWinner()){
            System.out.println("Oyun sonlandi. Kazanan(RED):"+red.getPlayer_name());
            winnerPlayer = red;
        }
        else if(black.isWinner()){
            System.out.println("Oyun sonlandi. Kazanan(BLACK):"+black.getPlayer_name());
            winnerPlayer = black;
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
        FileOutputStream outputStream = null;
        DataOutputStream dos = null;
        try {
            outputStream = new FileOutputStream(address);

            byte[] byte_player_and_game_infos = new byte[12];
            int redPuan = Float.floatToIntBits(red.getPuan());
            int blackPuan = Float.floatToIntBits(black.getPuan());
            int playerTurn = which_players_turn;
            for (int i = 0; i < 4; i++) {
                byte_player_and_game_infos[i] = (byte) (redPuan >> (i * 8));
                byte_player_and_game_infos[i+4] = (byte) (blackPuan >> (i * 8));
                byte_player_and_game_infos[i+8] = (byte) (playerTurn >> (i * 8) & 0xFF);
            }
            outputStream.write(byte_player_and_game_infos);
            for(Item item : board.items){
                byte[] bytePieceInfos = new byte[10];
                char itemName = item.getName().charAt(0);
                int value = Float.floatToIntBits(item.getValue());
                char row = item.getPosition().charAt(0);
                char col = item.getPosition().charAt(1);
                //int col = Integer.parseInt(item.getPosition().substring(1,2));
                for(int i=0; i<2; i++){
                    bytePieceInfos[i] = (byte) (itemName >> (i * 8));
                    bytePieceInfos[i+6] = (byte) (row >> (i * 8));
                    bytePieceInfos[i+8] = (byte) (col >> (i * 8));
                }
                for(int i=0; i<4; i++){
                    bytePieceInfos[i+2] = (byte) (value >> (i * 8));
                }
                outputStream.write(bytePieceInfos);
            }
            int winnerPlayerNo = 0;
            if(winnerPlayer != null){
                if(winnerPlayer.equals(red)){
                    winnerPlayerNo = 1;
                }
                else if(winnerPlayer.equals(black)){
                    winnerPlayerNo = 2;
                }
            }
            byte[] byteWinnerInfo = new byte[4];
            for(int i=0; i<4; i++){
                byteWinnerInfo[i] = (byte) (winnerPlayerNo >> (i * 8) & 0xFF);
            }
            outputStream.write(byteWinnerInfo);

            byte[] byteDrawInfo = new byte[4];
            int drawNo = 0;
            if(isItDraw){
                drawNo = 1;
            }
            for(int i=0; i<4; i++){
                byteDrawInfo[i] = (byte) (drawNo >> (i * 8) & 0xFF);
            }
            outputStream.write(byteDrawInfo);


            char[] redNameCharArray = red.getPlayer_name().toCharArray();
            char[] blackNameCharArray = black.getPlayer_name().toCharArray();
            byte[] byteRedNameInfo = new byte[redNameCharArray.length * 2];
            byte[] byteBlackNameInfo = new byte[blackNameCharArray.length * 2];
            int currentIndex = 0;
            for (char c: redNameCharArray){
                for(int i=0; i<2; i++){
                    byteRedNameInfo[currentIndex] = (byte) (c >> (i * 8));
                    currentIndex++;
                }
            }
            currentIndex = 0;
            for (char c: blackNameCharArray){
                for(int i=0; i<2; i++){
                    byteBlackNameInfo[currentIndex] = (byte) (c >> (i * 8));
                    currentIndex++;
                }
            }
            char delimiter = '$';
            byte[] byteDelimiterInfo = new byte[2];
            for(int i=0; i<2; i++){
                byteDelimiterInfo[i] = (byte) (delimiter >> (i * 8));
            }
            outputStream.write(byteRedNameInfo);
            outputStream.write(byteDelimiterInfo);
            outputStream.write(byteBlackNameInfo);


            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void save_text(String address){
        PrintWriter outputStream = null;
        try {
            outputStream = new PrintWriter(new FileWriter(address,false));
            outputStream.println("red player score:"+red.getPuan()+", "+red.getPlayer_name());
            outputStream.println("black player score:"+black.getPuan()+", "+black.getPlayer_name());
            outputStream.println("which_players_turn:"+which_players_turn);
            if(winnerPlayer==null){
                outputStream.println("kazanan:0");
            }
            else if(winnerPlayer.equals(red)){
                outputStream.println("kazanan:1");
            }
            else if(winnerPlayer.equals(black)){
                outputStream.println("kazanan:2");
            }
            if(isItDraw){
                outputStream.println("esitlik:true");
            }
            else{
                outputStream.println("esitlik:false");
            }
            for(Item item : board.items){
                if(item.getOwner().equals(red)){
                    outputStream.println("red,"+item.getName()+","+item.getValue()+","+item.getPosition());
                }
                else if(item.getOwner().equals(black)){
                    outputStream.println("black,"+item.getName()+","+item.getValue()+","+item.getPosition());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(outputStream != null){
            outputStream.close();
        }
    }
    public void load_text(String address){
        Scanner inputStream = null;
        try {
            inputStream = new Scanner(new FileInputStream(address));
            String redInfo = inputStream.nextLine();
            String blackInfo = inputStream.nextLine();
            red.setPuan(Float.parseFloat(redInfo.substring(redInfo.indexOf(":")+1, redInfo.indexOf(","))));
            red.setPlayer_name(redInfo.substring(redInfo.indexOf(",")+2));
            black.setPuan(Float.parseFloat(blackInfo.substring(blackInfo.indexOf(":")+1, blackInfo.indexOf(","))));
            black.setPlayer_name(blackInfo.substring(blackInfo.indexOf(",")+2));
            String turnInfo = inputStream.nextLine();
            which_players_turn = Integer.parseInt(turnInfo.substring(turnInfo.indexOf(":")+1));
            String winnerInfo = inputStream.nextLine();
            int winnerPlayer = Integer.parseInt(winnerInfo.substring(winnerInfo.indexOf(":")+1));
            if(winnerPlayer == 0){
                setWinnerPlayer(null);
            }
            else if(winnerPlayer == 1){
                setWinnerPlayer(red);
            }
            else if(winnerPlayer == 2){
                setWinnerPlayer(black);
            }
            String drawInfo = inputStream.nextLine();
            drawInfo = drawInfo.substring(winnerInfo.indexOf(":")+1);
            if(drawInfo.equals("true")){
                this.isItDraw = true;
            }else{
                this.isItDraw = false;
            }
            inputStream.useDelimiter(",");
            int index = 0;
            while(inputStream.hasNextLine()){
                String player = inputStream.next();
                String itemName = inputStream.next();
                float value = Float.parseFloat(inputStream.next());
                String position = inputStream.nextLine().substring(1);
                if(player.equals("red")){
                    switch (itemName) {
                        case "k":
                            board.items[index] = new Chariot(position, itemName, value);
                            break;
                        case "a":
                            board.items[index] = new Horse(position, itemName, value);
                            break;
                        case "f":
                            board.items[index] = new Horse(position, itemName, value);
                            break;
                        case "v":
                            board.items[index] = new Advisor(position, itemName, value);
                            break;
                        case "ş":
                            board.items[index] = new General(position, itemName, value);
                            break;
                        case "t":
                            board.items[index] = new Cannon(position, itemName, value);
                            break;
                        case "p":
                            board.items[index] = new Soldier(position, itemName, value);
                            break;
                    }
                    board.items[index].setOwner(red);
                }
                else{
                    switch (itemName) {
                        case "K":
                            board.items[index] = new Chariot(position, itemName, value);
                            break;
                        case "A":
                            board.items[index] = new Horse(position, itemName, value);
                            break;
                        case "F":
                            board.items[index] = new Horse(position, itemName, value);
                            break;
                        case "V":
                            board.items[index] = new Advisor(position, itemName, value);
                            break;
                        case "Ş":
                            board.items[index] = new General(position, itemName, value);
                            break;
                        case "T":
                            board.items[index] = new Cannon(position, itemName, value);
                            break;
                        case "P":
                            board.items[index] = new Soldier(position, itemName, value);
                            break;
                    }
                    board.items[index].setOwner(black);
                }
                board.items[index].setBoard(board);
                board.items[index].setGame(this);
                index++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void load_binary(String address){
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(address);
            board = new Board();
            byte[] buffer = new byte[4];
            inputStream.read(buffer);
            red.setPuan(ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN).getFloat());
            inputStream.read(buffer);
            black.setPuan(ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN).getFloat());
            inputStream.read(buffer);
            which_players_turn = ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN).getInt();


            for(int i=0; i<32; i++){
                buffer = new byte[2];
                inputStream.read(buffer);
                String itemName = ""+ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN).getChar();
                buffer = new byte[4];
                inputStream.read(buffer);
                float value = ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN).getFloat();
                buffer = new byte[2];
                inputStream.read(buffer);
                char row = ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN).getChar();
                buffer = new byte[2];
                inputStream.read(buffer);
                String col = ""+ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN).getChar();
                String position = row+""+col;
                switch (itemName) {
                    case "k":
                        board.items[i] = new Chariot(position, itemName, value);
                        break;
                    case "a":
                        board.items[i] = new Horse(position, itemName, value);
                        break;
                    case "f":
                        board.items[i] = new Horse(position, itemName, value);
                        break;
                    case "v":
                        board.items[i] = new Advisor(position, itemName, value);
                        break;
                    case "ş":
                        board.items[i] = new General(position, itemName, value);
                        break;
                    case "t":
                        board.items[i] = new Cannon(position, itemName, value);
                        break;
                    case "p":
                        board.items[i] = new Soldier(position, itemName, value);
                        break;
                    case "K":
                        board.items[i] = new Chariot(position, itemName, value);
                        break;
                    case "A":
                        board.items[i] = new Horse(position, itemName, value);
                        break;
                    case "F":
                        board.items[i] = new Horse(position, itemName, value);
                        break;
                    case "V":
                        board.items[i] = new Advisor(position, itemName, value);
                        break;
                    case "Ş":
                        board.items[i] = new General(position, itemName, value);
                        break;
                    case "T":
                        board.items[i] = new Cannon(position, itemName, value);
                        break;
                    case "P":
                        board.items[i] = new Soldier(position, itemName, value);
                        break;
                }
                if(itemName.equals(itemName.toLowerCase())){
                    board.items[i].setOwner(red);
                }else{
                    board.items[i].setOwner(black);
                }
                board.items[i].setBoard(board);
                board.items[i].setGame(this);
            }
            int winnerNo = 0;
            buffer = new byte[4];
            inputStream.read(buffer);
            winnerNo = ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN).getInt();
            if(winnerNo == 0){
                setWinnerPlayer(null);
            }
            else if(winnerNo == 1){
                setWinnerPlayer(red);
            }
            else if(winnerNo == 2){
                setWinnerPlayer(black);
            }

            buffer = new byte[4];
            inputStream.read(buffer);
            int drawNo = ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN).getInt();
            if(drawNo == 0){
                this.isItDraw = false;
            }
            else if(drawNo == 1){
                this.isItDraw = true;
            }

            String redName = "";
            buffer = new byte[2];
            while(true){
                int nextByte = inputStream.read(buffer);
                if(nextByte == -1){
                    break;
                }
                char character = ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN).getChar();
                if(character == '$'){
                    break;
                }
                else{
                    redName = redName + character;
                }
            }
            String blackName = "";
            buffer = new byte[2];
            while(true){
                int nextByte = inputStream.read(buffer);
                if(nextByte == -1){
                    break;
                }
                char character = ByteBuffer.wrap(buffer).order(ByteOrder.LITTLE_ENDIAN).getChar();
                if(character == '$'){
                    break;
                }
                else{
                    blackName = blackName + character;
                }
            }
            red.setPlayer_name(redName);
            black.setPlayer_name(blackName);
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkCriteriaOfDraw(){
        int attackingItemsRed = 0;
        int attackingItemsBlack = 0;
        for(Item t: board.items){
            if(t.getOwner().equals(red)){
                String itemName = t.getName().toLowerCase();
                if(itemName.equals("k") || itemName.equals("a") || itemName.equals("t") || itemName.equals("p")){
                    attackingItemsRed++;
                }
            }
            else if(t.getOwner().equals(black)){
                String itemName = t.getName().toLowerCase();
                if(itemName.equals("k") || itemName.equals("a") || itemName.equals("t") || itemName.equals("p")){
                    attackingItemsBlack++;
                }
            }
        }
        if(attackingItemsRed == 0 && attackingItemsBlack == 0){
            return false;
        }
        return true;
    }


}
