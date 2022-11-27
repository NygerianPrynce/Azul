
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
    }
    //fill factory with tiles
    public void fillFactory(ArrayList<Integer> tiles){
        factory = tiles;
    }
    // get arraylist of tiles
    public ArrayList<Integer> getFactoryContents(){
        return factory;
    }
    public ArrayList getTilesFromBag(TreeMap<Integer, Integer> bag){
        ArrayList<Integer> tiles = new ArrayList<Integer>();
        for (int i = 0; i < 4; i++){
            Boolean tileFound = false;
            while (!tileFound){
                int random = (int)(Math.random() * bag.size());
                int tileTypeCount = bag.get(random);
                if (tileTypeCount > 0){
                    tiles.add(random);
                    bag.put(random, tileTypeCount - 1);
                    tileFound = true;
                }
                else{
                    if(Gamestate.getBagTotal() == 0){
                        Gamestate.refillBag();
                    }
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
    public void moveLeftOvers(){
        Gamestate.addToLeftOverPile(getFactoryContents());
        factory.clear();
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

