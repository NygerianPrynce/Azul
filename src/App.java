public class App {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        System.out.println("Hello, if");
        System.out.println("Hello, if");
        
        //PlayerWall yeet = new PlayerWall();
        int[][] twall = new int[5][5];
        Integer[][] bwall = new Integer[5][5];
        //pirnt out bwall array
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                System.out.print(bwall[i][j] + " ");
            }
            System.out.println();
        }
        //pirnt out twall array
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                System.out.print(twall[i][j] + " ");
            }
            System.out.println();
        }
    }
}
