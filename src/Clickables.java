public class Clickables {
    //this class is used to store the clickables in the game
    //method that accepts x and y coordinates that returns a boolean
    public static Boolean checkNext1(int x, int y, int w, int h){
        return x >= w/2 - w/10 && y>= h/5 && x<= w/9 - w/150 + w/2 - w/10 && y<= h/6 +h/5;
    }


}
