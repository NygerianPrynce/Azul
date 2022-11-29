public class Scoring{
    int total;
    Integer recentTile;
    Integer recentTileRow;
    Integer recentTileColumn;
    Integer[][] playerWall;
    public Scoring(Player player) {
        total = 12;
        playerWall = player.getPlayerWall().getWall();
        recentTile = player.getPlayerWall().getRecentTile();
        recentTileRow = player.getPlayerWall().getRecentTileRow();
        recentTileColumn = player.getPlayerWall().getRecentTileColumn();
    }
    public int scoreVertical(){
        int count = 0;
        count++;
        //row changes column stays the same
        System.out.println("round 1");
        for(int i = recentTileRow+1; i<5; i++){
            if(playerWall[i][recentTileColumn] == null){
                i = 21;
            }else{
                count++;
            }
            System.out.println("counting downwards: " + count);
        }
        System.out.println("round 2");
        for(int i = recentTileRow-1; i>-1 && i<5; i--){
            if(playerWall[i][recentTileColumn] == null){
                i = 21;
            }else{
                count++;
            }
            System.out.println("counting upwards: " + count);
        }
        total = total + count;
        return count;
    }
    public int scoreHorizontal(){
        int count = 0;
        count++;
        //column changes row stays the same
        System.out.println("round 1");
        for(int i = recentTileColumn+1; i<5; i++){
            if(playerWall[recentTileRow][i] == null){
                i = 21;
            }else{
                count++;
            }
            System.out.println("counting right: " + count);
        }
        System.out.println("round 2");
        for(int i = recentTileColumn-1; i>-1 && i<5; i--){
            if(playerWall[recentTileRow][i] == null){
                i = 21;
            }else{
                count++;
            }
            System.out.println("counting left: " + count);
        }
        total = total + count;        
        return count;
    }
    //subtract a number from a parameter -- CHANGE ONCE PLAYER LINE IS MADE
    public int subtractFloorLine(int floorLinevalue){
        total = total - floorLinevalue;
        return total;
    }
    //get total
    public int getTotal(){
        return total;
    }
    //counts the amount of times a row is full
    public int countFullRows(){
        int count = 0;
        for(int i = 0; i<5; i++){
            if(playerWall[i][0] != null && playerWall[i][1] != null && playerWall[i][2] != null && playerWall[i][3] != null && playerWall[i][4] != null){
                count++;
            }
        }
        return count*2;
    }
    //counts the amount of times a column is full
    public int countFullColumns(){
        int count = 0;
        for(int i = 0; i<5; i++){
            if(playerWall[0][i] != null && playerWall[1][i] != null && playerWall[2][i] != null && playerWall[3][i] != null && playerWall[4][i] != null){
                count++;
            }
        }
        return count*7;
    }
    //counts how many times a tile is present in the wall
    public int countTile(Integer tile){
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
        int count = 0;
        for(int i = 0; i<5; i++){
            count = count + countTile(i);
        }
        return count*10;
    }
    // runs countAllTiles and countFullRows and countFullColumns
    public int scoreBonus(){
        int count = 0;
        count = count + countAllTiles();
        count = count + countFullRows();
        count = count + countFullColumns();
        return count;
    }





    

}

    

    

