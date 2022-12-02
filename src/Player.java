import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

 class Player {
 int playerNumber; 
 boolean isStarter;
 PlayerWall pW;
 PlayerLine pL; 
Scoring score;
//new arraylist of integers called tilesInPosession
ArrayList<Integer> tilesInPosession = new ArrayList<Integer>();
    public Player(int pNumber){
        playerNumber = pNumber;
        pW = new PlayerWall();
        pL = new PlayerLine(); 
        if(isStarter){
            pL.addStarterTile();
        }
        score = new Scoring(this);
        //add 4 integers 0-5 to the arraylist tilesInPosession

    }
    //clear player possesion
    public void clearPlayerPossession(){
        tilesInPosession.clear();
    }
    //get player wall
    public PlayerWall getPlayerWall(){
        return pW;
    }
    //get player line
    public PlayerLine getPlayerLine(){
        return pL;
    }
    //get score
    public Scoring getScore(){
        return score;
    }
    //get player number
    public int getPlayerNumber(){
        return playerNumber;
    }
    //make starter
    public void makeStarter(){
        isStarter = true;
    }
    //check if starter
    public boolean isStarter(){
        return isStarter;
    }
    //return the tiles in the player's possession
    public ArrayList<Integer> getTilesInPossession(){
        return tilesInPosession;
    }
    //input an arraylist of tiles to the player's possession
    public void addTilesToPossession(ArrayList<Integer> tiles){
        for(int i = 0; i < tiles.size(); i++){
            tilesInPosession.add(tiles.get(i));
        }
    }

}
