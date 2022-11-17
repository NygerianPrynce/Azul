public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        System.out.println("Hello, if");
        System.out.println("Hello, if");
        
        //PlayerWall yeet = new PlayerWall();
        Integer[][] wall = new Integer[5][5];
        wall[0][1] = 2;
        wall[1][1] = 2;
        wall[2][1] = 2;
        wall[3][1] = 2;
        wall[4][1] = 2;
        wall[0][0] = 2;
        wall[0][2] = 2;
        wall[0][3] = 2;
        wall[0][4] = 2;
        System.out.println(countColumn(wall, 1, 2));
        System.out.println(countRow(wall, 0, 1));
        
    }
    //while loop - counts how many spots in a column are connected to a spot on a column in the array wall
    public static int countColumn(Integer[][] wall, int column, int row){
        int count = 0;
        int i = row;
        while (wall[i][column] != null){
            count++;
            if (i > 0){
                i--;
            }
            else{
                break;
            }
        }
        i = row;
        while (wall[i][column] != null){
            count++;
            if (i < 4){
                i++;
            }
            else{
                break;
            }
        }
        return count;
    }
    // counts how many spaces in a row are not null and connected to a spot
    public static int countRow(Integer[][] wall, int row, int column){
        int count = 0;
        for (int i = column; i < 5; i++){
            if (wall[row][i] != null){
                count++;
            } else{
                break;
            }
        }
        for (int i = column; i > 0; i++){
            if (wall[row][i] != null){
                count--;
            } else{
                break;
            }
        }
        return count;
    }










}
