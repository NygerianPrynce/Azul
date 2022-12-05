public class Scoring{
    int total;
    Integer recentTile;
    Integer recentTileRow;
    Integer recentTileColumn;
    Integer[][] playerWall;
    Integer floorLineValue;
    Player player;
    Boolean noConnect = false;
    public Scoring(Player player) {
        this.player = player;
        total = 0;
    }
    //score horizontal and vertical in a method called score normal
    //UPDATE THIS METHOD
    public void update(){
        playerWall = player.getPlayerWall().getWall();
        recentTile = player.getPlayerWall().getRecentTile();
        recentTileRow = player.getPlayerWall().getRecentTileRow();
        recentTileColumn = player.getPlayerWall().getRecentTileColumn();
        floorLineValue = player.getPlayerLine().getFloorLineValue();
        if(total < 0){
            total = 0;
        }
    }
    public int scoreRowAndColumn(){
        update();
        int count = 0;
        count++;
        //row changes column stays the same
        for(int i = recentTileRow+1; i<5; i++){
            if(playerWall[i][recentTileColumn] == 6){
                i = 21;
            }else{
                count++;
            }
        }
        for(int i = recentTileRow-1; i>-1 && i<5; i--){
            if(playerWall[i][recentTileColumn] == 6){
                i = 21;
            }else{
                count++;
            }
        }
        for(int i = recentTileColumn+1; i<5; i++){
            if(playerWall[recentTileRow][i] == 6){
                i = 21;
            }else{
                count++;
            }
        }
        for(int i = recentTileColumn-1; i>-1 && i<5; i--){
            if(playerWall[recentTileRow][i] == 6){
                i = 21;
            }else{
                count++;
            }
        }
        total = total + count;
        return count;
    }
    //subtract a number from a parameter -- CHANGE ONCE PLAYER LINE IS MADE
    public int subtractFloorLine(){
        update();
        total = total - floorLineValue;
        return floorLineValue;
    }
    //get total
    public int getTotal(){
        update();
        return total;
    }
    //counts the amount of times a row is full
    public int countFullRows(){
        update();
        int count = 0;
        for(int i = 0; i<5; i++){
            if(playerWall[i][0] != 6 && playerWall[i][1] != 6 && playerWall[i][2] != 6 && playerWall[i][3] != 6 && playerWall[i][4] != 6){
                count++;
            }
        }
        return count*2;
    }
    //counts the amount of times a column is full
    public int countFullColumns(){
        update();
        int count = 0;
        for(int i = 0; i<5; i++){
            if(playerWall[0][i] != 6 && playerWall[1][i] != 6 && playerWall[2][i] != 6 && playerWall[3][i] != 6 && playerWall[4][i] != 6){
                count++;
            }
        }
        return count*7;
    }
    //counts how many times a tile is present in the wall
    public int countTile(Integer tile){
        update();
        int count = 0;
        for(int i = 0; i<5; i++){
            for(int j = 0; j<5; j++){
                if(playerWall[i][j] == tile){
                    count++;
                }
            }
        }
        if(count == 5){
            return 1;
        } 
        return 0;
    }
    // repeats countTile for every type of tile
    public int countAllTiles(){
        update();
        int count = 0;
        for(int i = 0; i<5; i++){
            count = count + countTile(i);
        }
        return count*10;
    }
    // runs countAllTiles and countFullRows and countFullColumns
    public int scoreBonus(){
        update();
        int count = 0;
        count = count + countAllTiles();
        count = count + countFullRows();
        count = count + countFullColumns();
        total = total + count;
        return count;
    }
    //score normal method which does score vertical and horizontal and the floor line
    public int scoreNormal(){
        update();
        System.out.println("SCORE VERTICAL AND HORIZONTAL SCORE" + scoreRowAndColumn());
        return total;
    }





    

}

    

    

