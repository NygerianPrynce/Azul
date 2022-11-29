
import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Random;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;

public class Factory {
    ArrayList<Integer> factory;
    public Factory() {
        factory = new ArrayList<Integer>();
        factory.add(0);
        factory.add(1);
        factory.add(2);
        factory.add(3);
    }
    //fill factory with tiles
    public void fillFactory(ArrayList<Integer> tiles){
        factory.clear();
        factory = tiles;
    }
    // get arraylist of tiles but return an arraylist with added nulls until there are 4 objects in the arraylist
    public ArrayList<Integer> getFactoryContents(){
        ArrayList<Integer> send = factory;
        while (send.size() < 4){
            send.add(6);
        }
        return send;
    }
    public static ArrayList getTilesFromBag(){
        ArrayList<Integer> tiles = new ArrayList<Integer>();
        for (int i = 0; i < 4; i++){
            Boolean tileFound = false;
            while (!tileFound){
                if(Gamestate.getBag().size() == 0){
                    Gamestate.refillBag();
                } else{
                    int random = (int)(Math.random() * Gamestate.getBag().size());
                    int tileType = Gamestate.getBag().get(random);
                    tiles.add(tileType);
                    Gamestate.getBag().remove(random);
                    tileFound = true;
                }

            }
        }
        return tiles;
    }
    //remove all integers of the same value from the factory arraylist and return aarraylist of the removed tiles
    public ArrayList<Integer> removeTiles(int tileType){
        ArrayList<Integer> removedTiles = new ArrayList<Integer>();
        for (int i = 0; i < factory.size(); i++){
            if (factory.get(i) == tileType){
                removedTiles.add(factory.get(i));
                factory.remove(i);
                i--;
            }
        }
        return removedTiles;
    }
    //adds the tiles in the factory to the discard pile in game state
    public ArrayList<Integer> moveLeftOvers(){
        ArrayList<Integer> leftOvers = getFactoryContents();
        factory.clear();
        return leftOvers;
    }
    //clear factory
    public void clearFactory(){
        factory.clear();
    }
    // to string retunrs a string of the factory contents
    public String toString(){
        String factoryString = "";
        for (int i = 0; i < factory.size(); i++){
            factoryString += factory.get(i) + " ";
        }
        return factoryString;
    }
    // returns the number of integers in the arraylist
    public int getTileCount(){
        return factory.size();
    }


    
}

