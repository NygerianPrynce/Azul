public class PlayerWall{
    // wall is an array of 5 rows and 5 columns
    Integer[][] wall;
    Integer recentTile;
    Integer recentTileRow;
    Integer recentTileColumn;

    public PlayerWall() {
        wall = new Integer[5][5];
        wall[0][0] = 0; wall[0][1] = 0; wall[0][2] = 0; wall[0][3] = 0; wall[0][4] = 0;
        wall[1][0] = 0; wall[1][1] = 0; wall[1][2] = 0; wall[1][3] = 0; wall[1][4] = 0;
        wall[2][0] = 0; wall[2][1] = 0; wall[2][2] = 0; wall[2][3] = 0; wall[2][4] = 0;
        wall[3][0] = 0; wall[3][1] = 0; wall[3][2] = 0; wall[3][3] = 0; wall[3][4] = 0;
        wall[4][0] = 0; wall[4][1] = null; wall[4][2] = 0; wall[4][3] = 0; wall[4][4] = 0;        

    }
    // isRowAvailable checks if the row can take in the tile type in the specific row
    public boolean isRowAvailable(int row, int tileType){
        int spot = findPosition(row, tileType);
        if (wall[row][spot] == tileType){
            return false;
        }
        return true;
    }
    // find postion of specific tile in row
    public int findPosition(int row, int tileType){
        return (row + tileType)%5;
    }

    //to string returns a string of the walls contents
    public String toString(){
        String wallString = "";
        for (int i = 0; i < 5; i++){
            for (int j = 0; j < 5; j++){
                wallString += wall[i][j] + " ";
            }
            wallString += "\n";
        }
        return wallString;
    }
    // add tile to wall
    public void addTile(int row, int tileType){
        int spot = findPosition(row, tileType);
        if(isRowAvailable(row, tileType)){
            wall[row][spot] = tileType;
            recentTile = tileType;
            recentTileRow = row;
            recentTileColumn = spot;
        }
    }
    // checks if any of the rows in the wall is full
    public boolean isGameOver(){
        for (int i = 0; i < 5; i++){
            if (isRowFull(i)){
                return true;
            }
        }
        return false;
    }
    //checks if the row is full
    public boolean isRowFull(int row){
        for (int i = 0; i < 5; i++){
            if (wall[row][i] == null){
                return false;
            }
        }
        return true;
    }
    //get recent tile
    public Integer getRecentTile(){
        return recentTile;
    }
    //get recent tile row
    public Integer getRecentTileRow(){
        return recentTileRow;
    }
    //get recent tile column
    public Integer getRecentTileColumn(){
        return recentTileColumn;
    }
    //get wall
    public Integer[][] getWall(){
        return wall;
    }
    
}
