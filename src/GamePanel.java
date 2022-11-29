import java.awt.*;
import java.awt.image.*;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.*;
import java.util.Scanner;
import java.util.*;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.lang.Math;
public class GamePanel extends JPanel{
    private static int state;
    private Gamestate game;
    private BufferedImage White, Yellow, Red, Black, Blue, Starter, Board, Factory, Score, Empty, bg1, bg2, Next1, Next2;
    //array that has the images of the tiles
    private BufferedImage[] tiles;
    public GamePanel(){
        game = new Gamestate();
        try{
            White = ImageIO.read(GamePanel.class.getResource("/images/0.png"));
            Yellow = ImageIO.read(GamePanel.class.getResource("/images/1.png"));
            Red = ImageIO.read(GamePanel.class.getResource("/images/2.png"));
            Black = ImageIO.read(GamePanel.class.getResource("/images/3.png"));
            Blue = ImageIO.read(GamePanel.class.getResource("/images/4.png"));
            Starter = ImageIO.read(GamePanel.class.getResource("/images/5.png"));
            Board = ImageIO.read(GamePanel.class.getResource("/images/Board.jpg"));
            Factory = ImageIO.read(GamePanel.class.getResource("/images/Factory.png"));
            Score = ImageIO.read(GamePanel.class.getResource("/images/Score.png"));
            Empty = ImageIO.read(GamePanel.class.getResource("/images/emptypng.png"));
            bg1 = ImageIO.read(GamePanel.class.getResource("/images/bg1.jpg"));
            bg2 = ImageIO.read(GamePanel.class.getResource("/images/bg2.png"));
            Next1 = ImageIO.read(GamePanel.class.getResource("/images/Next1.png"));
            Next2 = ImageIO.read(GamePanel.class.getResource("/images/Next2.png"));


            //add tile colors to array
            tiles = new BufferedImage[]{White, Yellow, Red, Black, Blue, Starter, Empty};
            System.out.println("tiles");

        } catch(Exception E){
            System.out.println("Exception error");
            return;  
        }
        state = 0;
    }
    public void addNotify(){
        super.addNotify();
        requestFocus();
    }
    public void paint(Graphics g){
        //sets the welcome screen background
        g.drawImage(bg1, 0, 0, getWidth(), getHeight(), null);
        //sets the game background
        g.drawImage(bg2, 0, 0, getWidth(), getHeight(), null);
        //specific sizes for player icons - 0:as if the one playing & 1:left sideline & 2:right sideline
        int[][] playerBoardSizes = {{getWidth()/2 - getWidth()/6, (getHeight()/3 + getHeight()/7), getWidth()/3, getHeight()/3 + getHeight()/6},
                                   {getWidth()/15, getHeight()/4 - getHeight()/40, getWidth()/6, getHeight()/4},
                                   {getWidth()/2 - getWidth()/12, getHeight()/50, getWidth()/6, getHeight()/4},
                                   {getWidth() - getWidth()/4, getHeight()/4 - getHeight()/40, getWidth()/6, getHeight()/4}};
        int[] factorySizes = {getWidth()/4 + getWidth()/140, getHeight()/2-getHeight()/5-getHeight()/50, getWidth()/27, getHeight()/18};
        int[][] tileSizes = {{getWidth()/3 + getWidth()/13, getHeight()/2-getHeight()/7-getHeight()/200, getWidth()/72, getHeight()/47},
                            {getWidth()/2-getWidth()/28-getWidth()/4000, getHeight()-getHeight()/3 -getHeight()/67, getWidth()/40, getHeight()/25},
                            {getWidth()/3+getWidth()/6 + getWidth()/105, getHeight()-getHeight()/6 -getHeight()/255, getWidth()/40, getHeight()/25},
                            {getWidth()/3+getWidth()/78, getHeight()-getHeight()/11 -getHeight()/255, getWidth()/40, getHeight()/25},
                            {getWidth()/4 + getWidth()/26, getHeight()/2 + getHeight()/11, getWidth()/40, getHeight()/25},};

        if(state == 0){
            //empty boards - beginning picture
            g.drawImage(Board, playerBoardSizes[0][0], playerBoardSizes[0][1], playerBoardSizes[0][2], playerBoardSizes[0][3], null);
            g.drawImage(Board, playerBoardSizes[1][0], playerBoardSizes[1][1], playerBoardSizes[1][2], playerBoardSizes[1][3], null);
            g.drawImage(Board, playerBoardSizes[2][0], playerBoardSizes[2][1], playerBoardSizes[2][2], playerBoardSizes[2][3], null);
            g.drawImage(Board, playerBoardSizes[3][0], playerBoardSizes[3][1], playerBoardSizes[3][2], playerBoardSizes[3][3], null);
            //empty factories - beginning picture
            for(int i = 1; i<=9; i++){
                if(i == 1){
                    g.drawImage(Factory, factorySizes[0] + getWidth()/9, factorySizes[1] + getHeight()/9, factorySizes[2], factorySizes[3], null);
                } else if(i == 2){
                    g.drawImage(Factory, factorySizes[0] + getWidth()/9, factorySizes[1] + getHeight()/20, factorySizes[2], factorySizes[3], null);
                } else if(i == 8){
                    g.drawImage(Factory, factorySizes[0] + getWidth()/22*8 - getWidth()/45, factorySizes[1] + getHeight()/20, factorySizes[2], factorySizes[3], null);
                } else if(i == 9){
                    g.drawImage(Factory, factorySizes[0] + getWidth()/22*8 - getWidth()/45, factorySizes[1] + getHeight()/9, factorySizes[2], factorySizes[3], null);
                }
                else{
                    g.drawImage(Factory, factorySizes[0] + getWidth()/22*i, factorySizes[1], factorySizes[2], factorySizes[3], null);
                }
            }
        }
        // loop to draw discard pile
        int discardpilesize = game.getLeftOverPileSize();
        for(int i = 0; i<discardpilesize; i++){
            ArrayList<Integer> lp = game.getLeftOverPile();
            int row = i/9;
            int column = i%9;
            g.drawImage(tiles[lp.get(i)], tileSizes[0][0] + getWidth()/48*column, tileSizes[0][1] + getHeight()/30*row, tileSizes[0][2], tileSizes[0][3], null);
        }
        // loop to draw factory tiles
        for(int i = 1; i<=9; i++){
            ArrayList<Integer> factoryContents = game.getFactoryInfo(i-1);
            if(i == 1){
                g.drawImage(tiles[factoryContents.get(0)], factorySizes[0] + getWidth()/9 + getWidth()/230, factorySizes[1] + getHeight()/9 + getHeight()/220, tileSizes[0][2], tileSizes[0][3], null);
                g.drawImage(tiles[factoryContents.get(1)], factorySizes[0] + getWidth()/9 + getWidth()/230, factorySizes[1] + getHeight()/9 + getHeight()/35, tileSizes[0][2], tileSizes[0][3], null);
                g.drawImage(tiles[factoryContents.get(2)], factorySizes[0] + getWidth()/9 + getWidth()/50, factorySizes[1] + getHeight()/9 + getHeight()/220, tileSizes[0][2], tileSizes[0][3], null);
                g.drawImage(tiles[factoryContents.get(3)], factorySizes[0] + getWidth()/9 + getWidth()/50, factorySizes[1] + getHeight()/9 + getHeight()/35, tileSizes[0][2], tileSizes[0][3], null);
            } else if(i == 2){
                g.drawImage(tiles[factoryContents.get(0)], factorySizes[0] + getWidth()/9 + getWidth()/230, factorySizes[1] + getHeight()/20 + getHeight()/220, tileSizes[0][2], tileSizes[0][3], null);
                g.drawImage(tiles[factoryContents.get(1)], factorySizes[0] + getWidth()/9 + getWidth()/230, factorySizes[1] + getHeight()/20 + getHeight()/35, tileSizes[0][2], tileSizes[0][3], null);
                g.drawImage(tiles[factoryContents.get(2)], factorySizes[0] + getWidth()/9 + getWidth()/50, factorySizes[1] + getHeight()/20 + getHeight()/220, tileSizes[0][2], tileSizes[0][3], null);
                g.drawImage(tiles[factoryContents.get(3)], factorySizes[0] + getWidth()/9 + getWidth()/50, factorySizes[1] + getHeight()/20 + getHeight()/35, tileSizes[0][2], tileSizes[0][3], null);
            } else if(i == 8){
                g.drawImage(tiles[factoryContents.get(0)], factorySizes[0] + getWidth()/22*8 - getWidth()/45 + getWidth()/230, factorySizes[1] + getHeight()/20  + getHeight()/220, tileSizes[0][2], tileSizes[0][3], null);
                g.drawImage(tiles[factoryContents.get(1)], factorySizes[0] + getWidth()/22*8 - getWidth()/45 + getWidth()/230, factorySizes[1] + getHeight()/20  + getHeight()/35, tileSizes[0][2], tileSizes[0][3], null);
                g.drawImage(tiles[factoryContents.get(2)], factorySizes[0] + getWidth()/22*8 - getWidth()/45 + getWidth()/50, factorySizes[1] + getHeight()/20  + getHeight()/220, tileSizes[0][2], tileSizes[0][3], null);
                g.drawImage(tiles[factoryContents.get(3)], factorySizes[0] + getWidth()/22*8 - getWidth()/45 + getWidth()/50, factorySizes[1] + getHeight()/20  + getHeight()/35, tileSizes[0][2], tileSizes[0][3], null);
            } else if(i == 9){
                g.drawImage(tiles[factoryContents.get(0)], factorySizes[0] + getWidth()/22*8 - getWidth()/45 + getWidth()/230, factorySizes[1] + getHeight()/9  + getHeight()/220, tileSizes[0][2], tileSizes[0][3], null);    
                g.drawImage(tiles[factoryContents.get(1)], factorySizes[0] + getWidth()/22*8 - getWidth()/45 + getWidth()/230, factorySizes[1] + getHeight()/9  + getHeight()/35, tileSizes[0][2], tileSizes[0][3], null);    
                g.drawImage(tiles[factoryContents.get(2)], factorySizes[0] + getWidth()/22*8 - getWidth()/45 + getWidth()/50, factorySizes[1] + getHeight()/9  + getHeight()/220, tileSizes[0][2], tileSizes[0][3], null);    
                g.drawImage(tiles[factoryContents.get(3)], factorySizes[0] + getWidth()/22*8 - getWidth()/45 + getWidth()/50, factorySizes[1] + getHeight()/9  + getHeight()/35, tileSizes[0][2], tileSizes[0][3], null);    
            }
            else{
                g.drawImage(tiles[factoryContents.get(0)], factorySizes[0] + getWidth()/22*i + getWidth()/230, factorySizes[1]  + getHeight()/220, tileSizes[0][2], tileSizes[0][3], null);        
                g.drawImage(tiles[factoryContents.get(1)], factorySizes[0] + getWidth()/22*i + getWidth()/230, factorySizes[1]  + getHeight()/35, tileSizes[0][2], tileSizes[0][3], null);        
                g.drawImage(tiles[factoryContents.get(2)], factorySizes[0] + getWidth()/22*i + getWidth()/50, factorySizes[1]  + getHeight()/220, tileSizes[0][2], tileSizes[0][3], null);        
                g.drawImage(tiles[factoryContents.get(3)], factorySizes[0] + getWidth()/22*i + getWidth()/50, factorySizes[1]  + getHeight()/35, tileSizes[0][2], tileSizes[0][3], null);        
            }
        }
        //draws next button in game
        g.drawImage(Next2, getWidth()-getWidth()/4+getWidth()/50, getHeight()/2+getHeight()/9, getWidth()/8, getHeight()/5+getHeight()/120, null);
        //draws main player line tiling
        ArrayList<ArrayList<Integer>> mainPLinePlacements = game.getCurrentPlayer().getPlayerLine().getLinePlacements();
        for(int i = 0; i< mainPLinePlacements.size(); i++){
            ArrayList<Integer> tile = mainPLinePlacements.get(i);
            Integer type = tile.get(0);
            Integer number = tile.get(1);
            Integer row = tile.get(2);
            for(int b = 0; b< number; b++){
                g.drawImage(tiles[type], tileSizes[1][0] - getWidth()/34*b, tileSizes[1][1] + getHeight()/23*row + getHeight()/500*row, tileSizes[1][2], tileSizes[1][3], null);
            }
        }
        //draws main player wall tiling
        ArrayList<ArrayList<Integer>> mainPWallPlacements = game.getCurrentPlayer().getPlayerWall().getWallPlacements();
        for(int i = 0; i< mainPWallPlacements.size(); i++){
            ArrayList<Integer> tile = mainPWallPlacements.get(i);
            Integer type = tile.get(0);
            Integer x = tile.get(1);
            Integer y = tile.get(2);
            g.drawImage(tiles[type], tileSizes[2][0] + getWidth()/34*x + getWidth()/1455*x, tileSizes[2][1] - getHeight()/22*y, tileSizes[2][2], tileSizes[2][3], null);
        }
        //draws main player floor tiling
        for(int i = 0; i<game.getCurrentPlayer().getPlayerLine().getFloorLine().size(); i++){
            Integer tile = game.getCurrentPlayer().getPlayerLine().getFloorLine().get(i);
            g.drawImage(tiles[tile], tileSizes[3][0] + getWidth()/31*i + getWidth()/1439*i - getWidth()/1460*i, tileSizes[3][1], tileSizes[3][2], tileSizes[3][3], null);
        }
        //draws the main player score marker
        Integer score = game.getCurrentPlayer().getScore().getTotal();
        System.out.println(score);
        Integer row = score/20;
        Integer column = score%20;
        if(score == 0){
            g.drawImage(Score, playerBoardSizes[0][0] + getWidth()/61, playerBoardSizes[0][1] + getHeight()/35 + getHeight()/35*-1, getWidth()/90, getHeight()/55, null);
        } else{
            g.drawImage(Score, playerBoardSizes[0][0] + getWidth()/61 + getWidth()/64*(column-1), playerBoardSizes[0][1] + getHeight()/35 + getHeight()/35*row, getWidth()/90, getHeight()/55, null);
        }
        //draws the tiles in the main player's possesion
        ArrayList<Integer> tilesInHand = game.getCurrentPlayer().getTilesInPossession();        
        for(int i = 0; i<tilesInHand.size(); i++){
            Integer x = i/5;
            Integer y = i%5;
            g.drawImage(tiles[tilesInHand.get(i)], tileSizes[4][0] - getWidth()/28*x, tileSizes[4][1] + getHeight()/17*y, tileSizes[4][2], tileSizes[4][3], null);
        }
        //draw rectangle around each available row in the main player line
        ArrayList<Integer> availableRows = game.getCurrentPlayer().getPlayerLine().getAvailableRowsP(tilesInHand);
        System.out.println(tilesInHand);
        System.out.println(availableRows);
        g.setColor(Color.RED);
        for(int i = 0; i<availableRows.size(); i++){
            Integer row1 = availableRows.get(i);
            g.drawRect(tileSizes[1][0] + getWidth()/38, tileSizes[1][1] + getHeight()/23*row1 + getHeight()/500*row1, -getWidth()/34-getWidth()/34*row1, getHeight()/23);
        }
        //draw rectangle around the main player floor line when its available - around spaces available 
        Integer availableFloorLineSpots = 7 - game.getCurrentPlayer().getPlayerLine().getFloorLineSpace();
        if(availableFloorLineSpots >= tilesInHand.size()){
            System.out.println(availableFloorLineSpots);
            if(availableFloorLineSpots - 7 == 0){
            } else{
                g.drawRect(getWidth()/3*2 - getWidth()/10, tileSizes[1][1] + getHeight()/4, -getWidth()/5 -getWidth()/40 + getWidth()/29*availableFloorLineSpots, getHeight()/23);
            }
        }
        //SET UP FOR DISPLAYING GRAPHICS FOR OTHER PLAYERS
        //arraylist in an arraylist holding the moving factors
        Integer movingFactorX = getWidth()/7*3 - getWidth()/13;
        Integer movingFactorY = -getHeight()/5 - getHeight()/150;
        Integer movingFactorX2 = getWidth()/7*5 - getWidth()/35;
        ArrayList<ArrayList<Integer>> movingFactors = new ArrayList<ArrayList<Integer>>();
        movingFactors.add(new ArrayList<>(){{
            add(0);
            add(0);
        }});
        movingFactors.add(new ArrayList<>(){{
            add(movingFactorX);
            add(movingFactorY);
        }});
        movingFactors.add(new ArrayList<>(){{
            add(movingFactorX2);
            add(0);
        }});
        //draws player wall tiling and score marker and player and floor line for all other dormant players
        for(int a = 1; a<=3; a++){
            ArrayList<ArrayList<Integer>> sidePWallPlacements = game.getPlayer(a).getPlayerWall().getWallPlacements();
            Integer addX = movingFactors.get(a-1).get(0);
            Integer addY = movingFactors.get(a-1).get(1);
            for(int i = 0; i< sidePWallPlacements.size(); i++){
                ArrayList<Integer> tile = sidePWallPlacements.get(i);
                Integer type = tile.get(0);
                Integer x = tile.get(1);
                Integer y = tile.get(2);
                g.drawImage(tiles[type], addX + getWidth()/90 + getWidth()/7 + getWidth()/65*x, addY + getHeight()/4 + getHeight()/15 + getHeight()/43*y, getWidth()/80, getHeight()/54, null);
            }
            //draws left side player line tiling
            ArrayList<ArrayList<Integer>> sidePLinePlacements = game.getPlayer(a).getPlayerLine().getLinePlacements();
            for(int i = 0; i< sidePLinePlacements.size(); i++){
                ArrayList<Integer> tile = sidePLinePlacements.get(i);
                System.out.println("bruh" + tile);
                Integer type = tile.get(0);
                Integer number = tile.get(1);
                Integer row3 = tile.get(2);
                for(int b = 0; b< number; b++){
                    g.drawImage(tiles[type], addX + getWidth()/9 -getWidth()/65*(b) + getWidth()/46, addY + getHeight()/4 + getHeight()/16 + getHeight()/340 + getHeight()/44*row3,getWidth()/80, getHeight()/54, null);
                }
            }
            //draws left side player floor tiling
            for(int i = 0; i<game.getPlayer(a).getPlayerLine().getFloorLine().size(); i++){
                Integer tile = game.getPlayer(a).getPlayerLine().getFloorLine().get(i);
                g.drawImage(tiles[tile], addX + getWidth()/300 + getWidth()/14 +getWidth()/62*(i), addY + getHeight()/4 + getHeight()/13 + getHeight()/180 + getHeight()/44*5,getWidth()/80, getHeight()/54, null);
            }
            //draws left side player score marker
            Integer score1 = game.getPlayer(a).getScore().getTotal();
            Integer row1 = score1/20;
            Integer column1 = score1%20;
            if(score1 == 0){
                g.drawImage(Score, addX + getWidth()/14  + getWidth()/240 , addY + getHeight()/5 + getHeight()/36, getWidth()/180, getHeight()/118, null);
            } else{
                g.drawImage(Score, addX + getWidth()/14  + getWidth()/240 + getWidth()/130*(column1-1), addY + getHeight()/5 + getHeight()/36 + getHeight()/72*(row1+1), getWidth()/180, getHeight()/118, null);
            }
        }
        
    }
}
