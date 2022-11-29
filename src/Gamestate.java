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

        private  ArrayList<Player> players = new ArrayList<Player>(); // List of players
        private static  ArrayList<Integer> bag = new ArrayList<Integer>(); 
        private static  ArrayList<Integer> deadPile = new ArrayList<Integer>();
        private  ArrayList<Integer> leftOverPile = new ArrayList<Integer>(21);
        private  ArrayList<Factory> factories = new ArrayList<Factory>();
        //private static GameFrame gameFrame;
    
        public Gamestate() {
            System.out.println("GAME GAME GAME");
            players.add(new Player(0));
            players.add(new Player(1));
            players.add(new Player(2));
            players.add(new Player(3));
            setStarter();
            //fill treemap with 9 keys and 9 factory objects
            for (int i = 0; i < 9; i++){
                factories.add(new Factory());
                System.out.println("put" + i);
            }
            //fill left over pile with 27 integers 0-5
            for (int i = 0; i < 27; i++){
                leftOverPile.add(i%6);
            }
            //gameFrame = new GameFrame();
    
        }
        // returns the total number of tiles in the bag
        public int getBagTotal(){
            int total = 0;
            for (int i = 0; i < bag.size(); i++){
                total += bag.get(i);
            }
            return total;
        }
        //sets a random player from the arraylist of players to start the game by giving them a starter tile - make sure the starter is checked at the beginning of the game
        public void setStarter(){
            Random rand = new Random();
            int randomPlayer = rand.nextInt(players.size());
            players.get(randomPlayer).getPlayerLine().addStarterTile();
            players.get(randomPlayer).makeStarter();

        }
        // returns athe total number of tiles in the dead pile
        public  int getDeadPileTotal(){
            int total = 0;
            for (int i = 0; i < deadPile.size(); i++){
                total += deadPile.get(i);
            }
            return total;
        }
        // returns the total number of tiles in the factories
        public  int getFactoryTileCount(){
            int total = 0;
            for (int i = 0; i < factories.size(); i++){
                total += factories.get(i).getTileCount();
            }
            return total;
        }
        // checks if the bag is empty
        public  boolean isBagEmpty(){
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
        public  Player getCurrentPlayer(){
            return players.get(0);
        }
        //returns player at index
        public  Player getPlayer(int index){
            return players.get(index);
        }
        // refills the bag
        public static  void refillBag(){
            for (int i = 0; i < deadPile.size(); i++){
                bag.add(deadPile.get(i));
            }
            deadPile.clear();
        }
        // refills the factories using getTilesFromBag(TreeMap<Integer, Integer> bag)
        public  void refillFactories(){
            for (int i = 0; i < factories.size(); i++){
                Factory currentFactory = factories.get(i);
                ArrayList<Integer> tiles = currentFactory.getTilesFromBag();
                currentFactory.fillFactory(tiles);
            }
        }
        // returns the number of tiles in the bag
        public  int getBagSize(){
            return bag.size();
        }
        //gets the bag
        public static  ArrayList<Integer> getBag(){
            return bag;
        }
        // returns the number of tiles in the dead pile
        public int getDeadPileSize(){
            return deadPile.size();
        }
        // add arraylist of ties to the left over pile
        public  void addToLeftOverPile(ArrayList<Integer> tiles){
            for (int i = 0; i < tiles.size(); i++){
                leftOverPile.add(tiles.get(i));
            }
        } 
        //get discard pile size
        public  int getLeftOverPileSize(){
            if(leftOverPile.isEmpty()){
                return 0;
            } else{            
                return leftOverPile.size();
            }
        }
        //gets discard pile
        public  ArrayList<Integer> getLeftOverPile(){
            return leftOverPile;
        }
        // returns the scores of the players in an arraylist
        public  ArrayList<Integer> getPlayerScores(){
            ArrayList<Integer> scores = new ArrayList<Integer>();
            for (int i = 0; i < players.size(); i++){
                scores.add(players.get(i).getScore().getTotal());
            }
            return scores;
        }  
        //checks if the left over pile is empty
        public  boolean isLeftOverPileEmpty(){
            if (leftOverPile.size() == 0){
                return true;
            }
            return false;
        }
        //checks if all the factories are empty
        public boolean areFactoriesEmpty(){
            if (getFactoryTileCount() == 0){
                return true;
            }
            return false;
        }
        //returns an arraylist of the factories that have tiles in them - gives the number of the factory
        public ArrayList<Integer> getAvailableFactories(){
            ArrayList<Integer> factoriesWithTiles = new ArrayList<Integer>();
            for (int i = 0; i < factories.size(); i++){
                if (factories.get(i).getTileCount() > 0){
                    factoriesWithTiles.add(i);
                }
            }
            return factoriesWithTiles;
        }
        //returns the information of a single factory
        public ArrayList<Integer> getFactoryInfo(int factoryNumber){
            return factories.get(factoryNumber).getFactoryContents();
        }

}