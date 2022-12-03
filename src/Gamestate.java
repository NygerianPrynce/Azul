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
            System.out.println("Game has begun");
            players.add(new Player(0));
            players.add(new Player(1));
            players.add(new Player(2));
            players.add(new Player(3));
            setStarter();
            cycleToStarter();
            //fill treemap with 9 keys and 9 factory objects
            for (int i = 0; i < 9; i++){
                factories.add(new Factory());
            }
            System.out.println("Factories created");
            //add a single 3 tile to the left over pile
            //fill the bag with 20 of each tile 0-4
            for (int i = 0; i < 20; i++){
                bag.add(0);
                bag.add(1);
                bag.add(2);
                bag.add(3);
                bag.add(4);
            }
            //fill factories with tiles
            refillFactories();
            //shuffle the bag
            Collections.shuffle(bag);
        }
        //add arraylist of integers to the dead pile
        public void addToDeadPile(ArrayList<Integer> tiles){
            deadPile.addAll(tiles);
        }
        //method to cycle the players
        public void cyclePlayers(){
            Player temp = players.get(0);
            players.remove(0);
            players.add(temp);
        }
        //check if player is starter for all 5
        public Player findStarter(){
            Player starter = null;
            for (int i = 0; i < players.size(); i++){
                if (players.get(i).isStarter()){
                    starter = players.get(i);
                }
            }
            return starter;
        }
        //cycle the players until the player with the starter tile is first in the arraylist
        public void cycleToStarter(){
            Player starter = findStarter();
            while (starter != players.get(0)){
                cyclePlayers();
            }
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
        //get a factory from the treemap of factories
        public Factory getFactory(int factoryNumber){
            return factories.get(factoryNumber);
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
        public  int getFactoryTileCountRaw(){
            int total = 0;
            for (int i = 0; i < factories.size(); i++){
                total += factories.get(i).getTileCountRaw();
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
            System.out.println("Factories have been refilled");
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
        public int getLeftOverPileSize(){
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
        //pull every instance of a tile in the leftOverpile and put it in an arraylist and remove it from the left over pile
        public  ArrayList<Integer> pullFromLeftOverPile(int tile){
            ArrayList<Integer> tiles = new ArrayList<Integer>();
            for (int i = 0; i < leftOverPile.size(); i++){
                if (leftOverPile.get(i) == tile){
                    tiles.add(leftOverPile.get(i));
                    leftOverPile.remove(i);
                    i--;
                }
            }
            return tiles;
        }
        //return specific tile from left over pile
        public  int getLeftOverPileTile(int index){
            return leftOverPile.get(index);
        }
        //returns end of phase boolean
        public  boolean endOfPhase(){
            return getLeftOverPileSize() == 0 && getFactoryTileCountRaw() == 0;
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
        //remove current player from the arraylist of players
        public  void removeWinner(){
            players.remove(0);
        }

}