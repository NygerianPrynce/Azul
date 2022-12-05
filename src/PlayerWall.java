import java.util.ArrayList;

public class PlayerWall{
    // wall is an array of 5 rows and 5 columns
    Integer[][] wall;
    Integer recentTile;
    Integer recentTileRow;
    Integer recentTileColumn;
    ArrayList<ArrayList<Integer>> wallPlacements = new ArrayList<ArrayList<Integer>>();

    public PlayerWall() {
        wall = new Integer[5][5];
        wall[0][0] = 6; wall[0][1] = 6; wall[0][2] = 6; wall[0][3] = 6; wall[0][4] = 6;
        wall[1][0] = 6; wall[1][1] = 6; wall[1][2] = 6; wall[1][3] = 6; wall[1][4] = 6;
        wall[2][0] = 6; wall[2][1] = 6; wall[2][2] = 6; wall[2][3] = 6; wall[2][4] = 6;
        wall[3][0] = 6; wall[3][1] = 6; wall[3][2] = 6; wall[3][3] = 6; wall[3][4] = 6;
        wall[4][0] = 6; wall[4][1] = 6; wall[4][2] = 6; wall[4][3] = 6; wall[4][4] = 6;        
        //fill wallPlacements with arraylists of 3 integers
        /*for(int i = 0; i<5; i++){
            for(int j = 0; j<5; j++){
                ArrayList<Integer> temp = new ArrayList<Integer>();
                temp.add(3);
                temp.add(i);
                temp.add(j);
                wallPlacements.add(temp);
            }
        }*/
    }
    //print wall
    public void printWall(){
        for(int i = 0; i<5; i++){
            for(int j = 0; j<5; j++){
                System.out.print(wall[i][j]);
            }
            System.out.println();
        }
    }
    //return wall placements
    public ArrayList<ArrayList<Integer>> getWallPlacements(){
        return wallPlacements;
    }
    // isRowAvailable checks if the row can take in the tile type in the specific row
    public boolean isRowAvailable(int row, int tileType){
        int spot = findPosition(row, tileType);
        if (wall[row][spot] == tileType){
            return false;
        }
        return true;
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
        if(isRowAvailable(row, tileType) && wall[row][spot] == 6){
            //arraylist that holds the row, spot, and tile type
            ArrayList<Integer> tile = new ArrayList<Integer>();
            tile.add(tileType);
            tile.add(row);
            tile.add(spot);
            wall[row][spot] = tileType;
            recentTile = tileType;
            recentTileRow = row;
            recentTileColumn = spot;
            wallPlacements.add(tile);
        }
        System.out.println("RECENT TILE: " + recentTile);
        System.out.println("RECENT TILE ROW: " + recentTileRow);
        System.out.println("RECENT TILE COLUMN: " + recentTileColumn);

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
            if (wall[row][i] == 6){
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
    // find postion of specific tile in row
    public int findPosition(int row, int tileType){
        //fixes
        if(tileType == 0){
            tileType = 4;
        } else if(tileType == 4){
            tileType = 0;
        }
        //actual placements
        if(row == 0 && tileType == 0){
            return 0;
        }
        if(row == 0 && tileType == 1){
            return 1;
        }
        if(row == 0 && tileType == 2){
            return 2;
        }
        if(row == 0 && tileType == 3){
            return 3;
        }
        if(row == 0 && tileType == 4){
            return 4;
        }
        if(row == 1 && tileType == 0){
            return 1;
        }
        if(row == 1 && tileType == 1){
            return 2;
        }
        if(row == 1 && tileType == 2){
            return 3;
        }
        if(row == 1 && tileType == 3){
            return 4;
        }
        if(row == 1 && tileType == 4){
            return 0;
        }
        if(row == 2 && tileType == 0){
            return 2;
        }
        if(row == 2 && tileType == 1){
            return 3;
        }
        if(row == 2 && tileType == 2){
            return 4;
        }
        if(row == 2 && tileType == 3){
            return 0;
        }
        if(row == 2 && tileType == 4){
            return 1;
        }
        if(row == 3 && tileType == 0){
            return 3;
        }
        if(row == 3 && tileType == 1){
            return 4;
        }
        if(row == 3 && tileType == 2){
            return 0;
        }
        if(row == 3 && tileType == 3){
            return 1;
        }
        if(row == 3 && tileType == 4){
            return 2;
        }
        if(row == 4 && tileType == 0){
            return 4;
        }
        if(row == 4 && tileType == 1){
            return 0;
        }
        if(row == 4 && tileType == 2){
            return 1;
        }  
        if(row == 4 && tileType == 3){
            return 2;
        }
        return 3;

    }
    
}
