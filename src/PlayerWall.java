public class PlayerWall{
    // wall is an array of 5 rows and 5 columns
    Integer[][] wall;
    Integer recentTile;
    public PlayerWall() {
        wall = new Integer[5][5];
        wall[0][1] = 2;
        wall[1][1] = 2;
        wall[2][1] = 2;
        wall[3][1] = 2;
        wall[4][1] = 2;

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

    
}
