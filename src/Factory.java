
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
        //factory.add(0);
        //factory.add(1);
        //factory.add(2);
        //factory.add(3);
    }
    //get a tile from the factory
    public int getTile(int tile){
        return factory.get(tile);
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
    //sends an arraylist of all the integers in the arraylist except for 6
    public ArrayList<Integer> getRawFactoryContents(){
        ArrayList<Integer> send = factory;
        for (int i = 0; i < send.size(); i++){
            if (send.get(i) == 6){
                send.remove(i);
            }
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
                factory.set(i,6);
                i--;
            }
        }
        return removedTiles;
    }
    //adds the tiles in the factory to the discard pile in game state
    public ArrayList<Integer> moveLeftOvers(){
        ArrayList<Integer> leftOvers = new ArrayList<Integer>();
        //add contents of getrawfactorycontents to arraylist left overs
        for (int i = 0; i < getRawFactoryContents().size(); i++){
            leftOvers.add(getRawFactoryContents().get(i));
        }
        clearFactory();
        return leftOvers;
    }
    //clear factory
    public void clearFactory(){
        //make all 4 spots in the factory hold integer 6
        factory.clear();
        factory.add(6);
        factory.add(6);
        factory.add(6);
        factory.add(6);
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
    public int getTileCountRaw(){
        //returns the number of integers in the arraylist except for 6
        int count = 0;
        for (int i = 0; i < factory.size(); i++){
            if (factory.get(i) != 6){
                count++;
            }
        }
        return count;
    }


    
}

