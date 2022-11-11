public class Scoring {
    int total;
    Integer recentTile;
    public Scoring(Player player, Integer recentTile) {
        total = 0;
        recentTile = player.PlayerWall.getRecentTile();
    }
    // counts how many spots in a column are not null
    public int scoreHorizontal(Player player, int column){
        int count = 0;
        for (int i = 0; i < 5; i++){
            if (player.PlayerWall.wall[i][column] != null){
                count++;
            }
        }
        return count;
    }
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

    

    
}
