/* 
public class Scoring{
    int total;
    Integer recentTile;
    public Scoring(Player player, Integer recentTile) {
        total = 0;
        recentTile = player.PlayerWall.getRecentTile();
    }
    // counts how many spots in a column are connected to a spot on the array


    
    // counts how many spots in a row are not null
    public int countRow(Player player, int row){
        int count = 0;
        for (int i = 0; i < 5; i++){
            if (player.PlayerWall.wall[row][i] != null){
                count++;
            }
        }
        return count;
    }

    //recrusive method - counts how many spots in a column are connected to a spot on the array
    public int countColumn(Player player, int column, int row){
        int count = 0;
        if (player.PlayerWall.wall[row][column] != null){
            count++;
            if (row > 0){
                count += countColumn(player, column, row - 1);
            }
            if (row < 4){
                count += countColumn(player, column, row + 1);
            }
        }
        return count;
    }
}
*/
    

    

