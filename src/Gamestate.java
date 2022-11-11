
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Map;
import java.util.Comparator;
import java.util.Collections;

public class Gamestate {

    ArrayList<Player> players; // List of players
    TreeMap<Integer,Integer> bag;
    TreeMap<Integer,Integer> deadPile;
    ArrayList<Integer> leftOverPile;
    TreeMap<Integer,Factory> factories;
    GameFrame gameFrame;

    public Gamestate() {
        players = new ArrayList<Player>();
        bag = new TreeMap<Integer,Integer>();
        deadPile = new TreeMap<Integer,Integer>();
        leftOverPile = new TreeMap<Integer,Integer>();
        factories = new TreeMap<Integer,Factory>();
        gameFrame = new GameFrame();

    }
    // returns the total number of tiles in the bag
    public int getBagTotal(){
        int total = 0;
        for (int i = 0; i < bag.size(); i++){
            total += bag.get(i);
        }
        return total;
    }
    // returns the total number of tiles in the dead pile
    public int getDeadPileTotal(){
        int total = 0;
        for (int i = 0; i < deadPile.size(); i++){
            total += deadPile.get(i);
        }
        return total;
    }
    // returns the total number of tiles in the left over pile
    
    // returns the total number of tiles in the factories
    public int getFactoryTileCount(){
        int total = 0;
        for (int i = 0; i < factories.size(); i++){
            total += factories.get(i).getTileCount();
        }
        return total;
    }
    // checks if the bag is empty
    public boolean isBagEmpty(){
        if (getBagTotal() == 0){
            return true;
        }
        return false;
    }
    // returns the player order
    public ArrayList<Player> getPlayerOrder(){
        return players;
    }
    // returns the current player
    public Player getCurrentPlayer(){
        return players.get(0);
    }
    // add arraylist of tiles to the Left Over Pile
    public void addToLeftOverPile(ArrayList<Integer> tiles){
        for (int i = 0; i < tiles.size(); i++){
            leftOverPile.add(tiles.get(i));
        }
    }

}    
