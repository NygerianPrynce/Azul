import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

    public class Gamestate {

        private static ArrayList<Player> players; // List of players
        private static TreeMap<Integer,Integer> bag;
        private static ArrayList<Integer> deadPile;
        private static ArrayList<Integer> leftOverPile;
        private static TreeMap<Integer,Factory> factories;
        //private static GameFrame gameFrame;
    
        public Gamestate() {
            players = new ArrayList<Player>();
            bag = new TreeMap<Integer,Integer>();
            deadPile = new ArrayList<Integer>();
            leftOverPile = new ArrayList<Integer>();
            factories = new TreeMap<Integer,Factory>();
            //gameFrame = new GameFrame();
    
        }
        // returns the total number of tiles in the bag
        public static int getBagTotal(){
            int total = 0;
            for (int i = 0; i < bag.size(); i++){
                total += bag.get(i);
            }
            return total;
        }
        //sets a random player from the arraylist of players to start the game by giving them a starter tile - make sure the starter is checked at the beginning of the game
        public static void setStarter(){
            Random rand = new Random();
            int randomPlayer = rand.nextInt(players.size());
            players.get(randomPlayer).getPlayerLine().addStarterTile();
        }
        // returns athe total number of tiles in the dead pile
        public static int getDeadPileTotal(){
            int total = 0;
            for (int i = 0; i < deadPile.size(); i++){
                total += deadPile.get(i);
            }
            return total;
        }
        // returns the total number of tiles in the left over pile
        public static int getLeftOverPileTotal(){
            return leftOverPile.size();
        }
        // returns the total number of tiles in the factories
        public static int getFactoryTileCount(){
            int total = 0;
            for (int i = 0; i < factories.size(); i++){
                total += factories.get(i).getTileCount();
            }
            return total;
        }
        // checks if the bag is empty
        public static boolean isBagEmpty(){
            if (getBagTotal() == 0){
                return true;
            }
            return false;
        }
        // returns the player order
        public static ArrayList<Player> getPlayerOrder(){
            return players;
        }
        // returns the current player
        public static Player getCurrentPlayer(){
            return players.get(0);
        }
        // refills the bag
        public static void refillBag(){
            for (int i = 0; i < deadPile.size(); i++){
                bag.put(i, deadPile.get(i));
            }
            deadPile.clear();
        }
        // refills the factories using getTilesFromBag(TreeMap<Integer, Integer> bag)
        public static void refillFactories(){
            for (int i = 0; i < factories.size(); i++){
                Factory currentFactory = factories.get(i);
                ArrayList<Integer> tiles = currentFactory.getTilesFromBag(bag);
                currentFactory.fillFactory(tiles);
            }
        }
        // returns the number of tiles in the bag
        public static int getBagSize(){
            return bag.size();
        }
        // returns the number of tiles in the dead pile
        public static int getDeadPileSize(){
            return deadPile.size();
        }
        // add arraylist of ties to the left over pile
        public static void addToLeftOverPile(ArrayList<Integer> tiles){
            for (int i = 0; i < tiles.size(); i++){
                leftOverPile.add(tiles.get(i));
            }
        } 
        // returns the scores of the players in an arraylist
        public static ArrayList<Integer> getPlayerScores(){
            ArrayList<Integer> scores = new ArrayList<Integer>();
            for (int i = 0; i < players.size(); i++){
                scores.add(players.get(i).getScore().getTotal());
            }
            return scores;
        }  
        //checks if the left over pile is empty
        public static boolean isLeftOverPileEmpty(){
            if (leftOverPile.size() == 0){
                return true;
            }
            return false;
        }
        //checks if all the factories are empty
        public static boolean areFactoriesEmpty(){
            if (getFactoryTileCount() == 0){
                return true;
            }
            return false;
        }
        //returns an arraylist of the factories that have tiles in them - gives the number of the factory
        public static ArrayList<Integer> getAvailableFactories(){
            ArrayList<Integer> factoriesWithTiles = new ArrayList<Integer>();
            for (int i = 0; i < factories.size(); i++){
                if (factories.get(i).getTileCount() > 0){
                    factoriesWithTiles.add(i);
                }
            }
            return factoriesWithTiles;
        }
        //returns the information of a single factory
        public static ArrayList<Integer> getFactoryInfo(int factoryNumber){
            return factories.get(factoryNumber).getFactoryContents();
        }

}