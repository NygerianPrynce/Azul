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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseMotionListener;
import java.io.*;
import java.lang.Thread;
public class GamePanel extends JPanel implements MouseListener{
    //turn state into an arraylist
    private static ArrayList<Integer> state = new ArrayList<Integer>();
    private Gamestate game;
    private BufferedImage White, Yellow, Red, Black, Blue, Starter, Board, Factory, Score, Empty, bg1, bg2, Next1, Next2;
    //array that has the images of the tiles
    private BufferedImage[] tiles;
    boolean gameStarted = false;
    boolean tilesPicked = false;
    boolean tilesAdded = false;
    boolean nextTurn = false;
    boolean wallTilingPhase = false;
    boolean gameOver = false;
    int repainted = 0;
    Integer winner = null;
    Player playerWinner = null;
    Integer times = 0;
    public GamePanel(){
        game = new Gamestate();
        try{
            White = ImageIO.read(GamePanel.class.getResource("/images/0.png"));
            Yellow = ImageIO.read(GamePanel.class.getResource("/images/1.png"));
            Red = ImageIO.read(GamePanel.class.getResource("/images/2.png"));
            Black = ImageIO.read(GamePanel.class.getResource("/images/3.png"));
            Blue = ImageIO.read(GamePanel.class.getResource("/images/4.png"));
            Starter = ImageIO.read(GamePanel.class.getResource("/images/5.png"));
            bg1 = ImageIO.read(GamePanel.class.getResource("/images/bg1.png"));
            bg2 = ImageIO.read(GamePanel.class.getResource("/images/bg2.png"));
            Board = ImageIO.read(GamePanel.class.getResource("/images/Board.jpg"));
            Empty = ImageIO.read(GamePanel.class.getResource("/images/emptypng.png"));
            Factory = ImageIO.read(GamePanel.class.getResource("/images/Factory.png"));
            Next1 = ImageIO.read(GamePanel.class.getResource("/images/Next1.png"));
            Next2 = ImageIO.read(GamePanel.class.getResource("/images/Next2.png"));
            Score = ImageIO.read(GamePanel.class.getResource("/images/Score.png"));
            
            //add tile colors to array
            tiles = new BufferedImage[]{White, Yellow, Red, Black, Blue, Starter, Empty};
            System.out.println("tiles");

        } catch(Exception E){
            System.out.println("Exception error");
            return;  
        }
        
        addMouseListener(this);
        state.add(0);
    }
    public void mousePressed(MouseEvent e){}
    public void mouseReleased(MouseEvent e){}
    public void mouseEntered(MouseEvent e){}
    public void mouseExited(MouseEvent e){}
    public void mouseClicked(MouseEvent e){
        int[][] pBS = {{getWidth()/2 - getWidth()/6, (getHeight()/3 + getHeight()/7), getWidth()/3, getHeight()/3 + getHeight()/6},
                                   {getWidth()/15, getHeight()/4 - getHeight()/40, getWidth()/6, getHeight()/4},
                                   {getWidth()/2 - getWidth()/12, getHeight()/50, getWidth()/6, getHeight()/4},
                                   {getWidth() - getWidth()/4, getHeight()/4 - getHeight()/40, getWidth()/6, getHeight()/4}};
        int[] fS = {getWidth()/4 + getWidth()/140, getHeight()/2-getHeight()/5-getHeight()/50, getWidth()/27, getHeight()/18};
        int[][] tS = {{getWidth()/3 + getWidth()/13, getHeight()/2-getHeight()/7-getHeight()/200, getWidth()/72, getHeight()/47},
                            {getWidth()/2-getWidth()/28-getWidth()/4000, getHeight()-getHeight()/3 -getHeight()/67, getWidth()/40, getHeight()/25},
                            {getWidth()/3+getWidth()/6 + getWidth()/105, getHeight()-getHeight()/6 -getHeight()/255, getWidth()/40, getHeight()/25},
                            {getWidth()/3+getWidth()/78, getHeight()-getHeight()/11 -getHeight()/255, getWidth()/40, getHeight()/25},
                            {getWidth()/4 + getWidth()/26, getHeight()/2 + getHeight()/11, getWidth()/40, getHeight()/25},};
   
        int x = e.getX();
        int y = e.getY();
        System.out.println("loc is ("+x+","+y+")");
        if(e.getButton() == e.BUTTON1){
            //create a space for the button based on the x and y coordinates and the width and length of the button
            if(Clickables.checkNext1(x, y, getWidth(), getHeight()) && !gameStarted){
                //if the button is clicked, change the state to 1
                state.set(0, 1);
                System.out.println("play button clicked");
                state.add(0);
                repaint();
                gameStarted = true;
               
            }
            //FACTORY CLICKS - 1
            else if(gameStarted && !tilesPicked && x>= fS[0] + getWidth()/9 + getWidth()/230 && x<= fS[0] + getWidth()/9 + getWidth()/230 + tS[0][2] && y>= fS[1] + getHeight()/9 + getHeight()/220 && y<= fS[1] + getHeight()/9 + getHeight()/220 + tS[0][3]){
                System.out.println("Factory 1 tile button1 clicked");
                //if the button is clicked, remove tiles from the factory and add them to the player's possession
                factoryMovements(0,0);
            }
            else if(gameStarted && !tilesPicked && x>= fS[0] + getWidth()/9 + getWidth()/230 + tS[0][2] + getWidth()/230 && x<= fS[0] + getWidth()/9 + getWidth()/230 + tS[0][2] + getWidth()/230 + tS[0][2] && y>= fS[1] + getHeight()/9 + getHeight()/220 && y<= fS[1] + getHeight()/9 + getHeight()/220 + tS[0][3]){
                System.out.println("Factory 1 tile button2 clicked");
                factoryMovements(0,2);
            }
            else if(gameStarted && !tilesPicked && x>=fS[0] + getWidth()/9 + getWidth()/230 && x<=fS[0] + getWidth()/9 + getWidth()/230 + tS[0][2] && y>=fS[1] + getHeight()/9 + getHeight()/35 && y<=fS[1] + getHeight()/9 + getHeight()/35 + tS[0][3]){
                System.out.println("Factory 1 tile button3 clicked");
                factoryMovements(0,1);
            }
            else if(gameStarted && !tilesPicked && x>= fS[0] + getWidth()/9 + getWidth()/50 && x<= fS[0] + getWidth()/9 + getWidth()/50 + tS[0][2] && y>= fS[1] + getHeight()/9 + getHeight()/35 && y<= fS[1] + getHeight()/9 + getHeight()/35 + tS[0][3]){
                System.out.println("Factory 1 tile button4 clicked");
                factoryMovements(0,3);
            }
            //FACTORY CLICKS - 2
            else if(gameStarted && !tilesPicked && x>= fS[0] + getWidth()/9 + getWidth()/230 && x<= fS[0] + getWidth()/9 + getWidth()/230 + tS[0][2] && y>= fS[1] + getHeight()/20 + getHeight()/220 && y<= fS[1] + getHeight()/20 + getHeight()/220 + tS[0][3]){
                System.out.println("Factory 2 tile button1 clicked");
                factoryMovements(1,0);
            }
            else if(gameStarted && !tilesPicked && x>= fS[0] + getWidth()/9 + getWidth()/50 && x<= fS[0] + getWidth()/9 + getWidth()/50 + tS[0][2] && y>= fS[1] + getHeight()/20 + getHeight()/220 && y<= fS[1] + getHeight()/20 + getHeight()/220 + tS[0][3]){
                System.out.println("Factory 2 tile button2 clicked");
                factoryMovements(1,2);
            }
            else if(gameStarted && !tilesPicked && x>= fS[0] + getWidth()/9 + getWidth()/230 && x<= fS[0] + getWidth()/9 + getWidth()/230 + tS[0][2] && y>= fS[1] + getHeight()/20 + getHeight()/35 && y<= fS[1] + getHeight()/20 + getHeight()/35 + tS[0][3]){
                System.out.println("Factory 2 tile button3 clicked");
                factoryMovements(1,1);
            }
            else if(gameStarted && !tilesPicked && x>= fS[0] + getWidth()/9 + getWidth()/50 && x<= fS[0] + getWidth()/9 + getWidth()/50 + tS[0][2] && y>= fS[1] + getHeight()/20 + getHeight()/35 && y<= fS[1] + getHeight()/20 + getHeight()/35 + tS[0][3]){
                System.out.println("Factory 2 tile button4 clicked");
                factoryMovements(1,3);
            }
            //FACTORY CLICKS - 3
            else if(gameStarted && !tilesPicked && x>=fS[0] + getWidth()/22*3 + getWidth()/230 && x<=fS[0] + getWidth()/22*3 + getWidth()/230 + tS[0][2] && y>= fS[1]  + getHeight()/220 && y<=fS[1]  + getHeight()/220 + tS[0][3]){
                System.out.println("Factory 3 tile button1 clicked");
                factoryMovements(2,0);
            }
            else if(gameStarted && !tilesPicked && x>= fS[0] + getWidth()/22*3 + getWidth()/50 && x<= fS[0] + getWidth()/22*3 + getWidth()/50 + tS[0][2] && y>= fS[1]  + getHeight()/220 && y<= fS[1]  + getHeight()/220 + tS[0][3]){
                System.out.println("Factory 3 tile button2 clicked");
                factoryMovements(2,2);
            }
            else if(gameStarted && !tilesPicked && x>= fS[0] + getWidth()/22*3 + getWidth()/230 && x<= fS[0] + getWidth()/22*3 + getWidth()/230 + tS[0][2] && y>= fS[1]  + getHeight()/35 && y<= fS[1]  + getHeight()/35 + tS[0][3]){
                System.out.println("Factory 3 tile button3 clicked");
                factoryMovements(2,1);
            }
            else if(gameStarted && !tilesPicked && x>= fS[0] + getWidth()/22*3 + getWidth()/50 && x<=fS[0] + getWidth()/22*3 + getWidth()/50 + tS[0][2] && y>=fS[1]  + getHeight()/35 && y<=fS[1]  + getHeight()/35 + tS[0][3] + tS[0][3]){
                System.out.println("Factory 3 tile button4 clicked");
                factoryMovements(2,3);
            }
            //FACTORY CLICKS - 4
            else if(gameStarted && !tilesPicked && x>=fS[0] + getWidth()/22*4 + getWidth()/230 && x<=fS[0] + getWidth()/22*4 + getWidth()/230 + tS[0][2] && y>= fS[1]  + getHeight()/220 && y<=fS[1]  + getHeight()/220 + tS[0][3]){
                System.out.println("Factory 4 tile button1 clicked");
                factoryMovements(3,0);
            }
            else if(gameStarted && !tilesPicked && x>= fS[0] + getWidth()/22*4 + getWidth()/50 && x<= fS[0] + getWidth()/22*4 + getWidth()/50 + tS[0][2] && y>= fS[1]  + getHeight()/220 && y<= fS[1]  + getHeight()/220 + tS[0][3]){
                System.out.println("Factory 4 tile button2 clicked");
                factoryMovements(3,2);
            }
            else if(gameStarted && !tilesPicked && x>= fS[0] + getWidth()/22*4 + getWidth()/230 && x<= fS[0] + getWidth()/22*4 + getWidth()/230 + tS[0][2] && y>= fS[1]  + getHeight()/35 && y<= fS[1]  + getHeight()/35 + tS[0][3]){
                System.out.println("Factory 4 tile button3 clicked");
                factoryMovements(3,1);
            }
            else if(gameStarted && !tilesPicked && x>= fS[0] + getWidth()/22*4 + getWidth()/50 && x<=fS[0] + getWidth()/22*4 + getWidth()/50 + tS[0][2] && y>=fS[1]  + getHeight()/35 && y<=fS[1]  + getHeight()/35 + tS[0][3] + tS[0][3]){
                System.out.println("Factory 4 tile button4 clicked");
                factoryMovements(3,3);
            }
            //FACTORY CLICKS - 5
            else if(gameStarted && !tilesPicked && x>=fS[0] + getWidth()/22*5 + getWidth()/230 && x<=fS[0] + getWidth()/22*5 + getWidth()/230 + tS[0][2] && y>= fS[1]  + getHeight()/220 && y<=fS[1]  + getHeight()/220 + tS[0][3]){
                System.out.println("Factory 5 tile button1 clicked");
                factoryMovements(4,0);  
            }
            else if(gameStarted && !tilesPicked && x>= fS[0] + getWidth()/22*5 + getWidth()/50 && x<= fS[0] + getWidth()/22*5 + getWidth()/50 + tS[0][2] && y>= fS[1]  + getHeight()/220 && y<= fS[1]  + getHeight()/220 + tS[0][3]){
                System.out.println("Factory 5 tile button2 clicked");
                factoryMovements(4,2);
            }
            else if(gameStarted && !tilesPicked && x>= fS[0] + getWidth()/22*5 + getWidth()/230 && x<= fS[0] + getWidth()/22*5 + getWidth()/230 + tS[0][2] && y>= fS[1]  + getHeight()/35 && y<= fS[1]  + getHeight()/35 + tS[0][3]){
                System.out.println("Factory 5 tile button3 clicked");
                factoryMovements(4,1);
            }
            else if(gameStarted && !tilesPicked && x>= fS[0] + getWidth()/22*5 + getWidth()/50 && x<=fS[0] + getWidth()/22*5 + getWidth()/50 + tS[0][2] && y>=fS[1]  + getHeight()/35 && y<=fS[1]  + getHeight()/35 + tS[0][3] + tS[0][3]){
                System.out.println("Factory 5 tile button4 clicked");
                factoryMovements(4,3);
            }
            //FACTORY CLICKS - 6
            if(gameStarted && !tilesPicked && x>=fS[0] + getWidth()/22*6 + getWidth()/230 && x<=fS[0] + getWidth()/22*6 + getWidth()/230 + tS[0][2] && y>= fS[1]  + getHeight()/220 && y<=fS[1]  + getHeight()/220 + tS[0][3]){
                System.out.println("Factory 6 tile button1 clicked");
                factoryMovements(5,0);
            }
            if(gameStarted && !tilesPicked && x>= fS[0] + getWidth()/22*6 + getWidth()/50 && x<= fS[0] + getWidth()/22*6 + getWidth()/50 + tS[0][2] && y>= fS[1]  + getHeight()/220 && y<= fS[1]  + getHeight()/220 + tS[0][3]){
                System.out.println("Factory 6 tile button2 clicked");
                factoryMovements(5,2);
            }
            if(gameStarted && !tilesPicked && x>= fS[0] + getWidth()/22*6 + getWidth()/230 && x<= fS[0] + getWidth()/22*6 + getWidth()/230 + tS[0][2] && y>= fS[1]  + getHeight()/35 && y<= fS[1]  + getHeight()/35 + tS[0][3]){
                System.out.println("Factory 6 tile button3 clicked");
                factoryMovements(5,1);
            }
            if(gameStarted && !tilesPicked && x>= fS[0] + getWidth()/22*6 + getWidth()/50 && x<=fS[0] + getWidth()/22*6 + getWidth()/50 + tS[0][2] && y>=fS[1]  + getHeight()/35 && y<=fS[1]  + getHeight()/35 + tS[0][3] + tS[0][3]){
                System.out.println("Factory 6 tile button4 clicked");
                factoryMovements(5,3);
            }
            //FACTORY CLICKS - 7
            if(gameStarted && !tilesPicked && x>=fS[0] + getWidth()/22*7 + getWidth()/230 && x<=fS[0] + getWidth()/22*7 + getWidth()/230 + tS[0][2] && y>= fS[1]  + getHeight()/220 && y<=fS[1]  + getHeight()/220 + tS[0][3]){
                System.out.println("Factory 7 tile button1 clicked");
                factoryMovements(6,0);
            }
            if(gameStarted && !tilesPicked && x>= fS[0] + getWidth()/22*7 + getWidth()/50 && x<= fS[0] + getWidth()/22*7 + getWidth()/50 + tS[0][2] && y>= fS[1]  + getHeight()/220 && y<= fS[1]  + getHeight()/220 + tS[0][3]){
                System.out.println("Factory 7 tile button2 clicked");
                factoryMovements(6,2);  
            }
            if(gameStarted && !tilesPicked && x>= fS[0] + getWidth()/22*7 + getWidth()/230 && x<= fS[0] + getWidth()/22*7 + getWidth()/230 + tS[0][2] && y>= fS[1]  + getHeight()/35 && y<= fS[1]  + getHeight()/35 + tS[0][3]){
                System.out.println("Factory 7 tile button3 clicked");
                factoryMovements(6,1);  
            }
            if(gameStarted && !tilesPicked && x>= fS[0] + getWidth()/22*7 + getWidth()/50 && x<=fS[0] + getWidth()/22*7 + getWidth()/50 + tS[0][2] && y>=fS[1]  + getHeight()/35 && y<=fS[1]  + getHeight()/35 + tS[0][3] + tS[0][3]){
                System.out.println("Factory 7 tile button4 clicked");
                factoryMovements(6,3);
            }
            //FACTORY CLICKS - 8
            if(gameStarted && !tilesPicked && x>=fS[0] + getWidth()/22*8 - getWidth()/45 + getWidth()/230 && x<=fS[0] + getWidth()/22*8 - getWidth()/45 + getWidth()/230  + tS[0][2]&& y>= fS[1] + getHeight()/20  + getHeight()/220 && y<=fS[1] + getHeight()/20  + getHeight()/220 + tS[0][3]){
                System.out.println("Factory 8 tile button1 clicked");
                factoryMovements(7,0);
            }
            if(gameStarted && !tilesPicked && x>=fS[0] + getWidth()/22*8 - getWidth()/45 + getWidth()/50 && x<=fS[0] + getWidth()/22*8 - getWidth()/45 + getWidth()/50 + tS[0][2] && y>= fS[1] + getHeight()/20  + getHeight()/220 && y<=fS[1] + getHeight()/20  + getHeight()/220 + tS[0][3]){
                System.out.println("Factory 8 tile button2 clicked");
                factoryMovements(7,2);
            }
            if(gameStarted && !tilesPicked && x>=fS[0] + getWidth()/22*8 - getWidth()/45 + getWidth()/230 && x<=fS[0] + getWidth()/22*8 - getWidth()/45 + getWidth()/230 + tS[0][2] && y>= fS[1] + getHeight()/20  + getHeight()/35 && y<=fS[1] + getHeight()/20  + getHeight()/35 + tS[0][3]){
                System.out.println("Factory 8 tile button3 clicked");
                factoryMovements(7,1);
            }
            if(gameStarted && !tilesPicked && x>= fS[0] + getWidth()/22*8 - getWidth()/45 + getWidth()/50 && x<= fS[0] + getWidth()/22*8 - getWidth()/45 + getWidth()/50 + tS[0][2] && y>= fS[1] + getHeight()/20  + getHeight()/35 && y<=fS[1] + getHeight()/20  + getHeight()/35 + tS[0][3]){
                System.out.println("Factory 8 tile button4 clicked");
                factoryMovements(7,3);
            }
            //FACTORY CLICKS - 9
            if(gameStarted && !tilesPicked && x>=fS[0] + getWidth()/22*8 - getWidth()/45 + getWidth()/230 && x<=fS[0] + getWidth()/22*8 - getWidth()/45 + getWidth()/230 + tS[0][2] && y>= fS[1] + getHeight()/9  + getHeight()/220 && y<= fS[1] + getHeight()/9  + getHeight()/220 + tS[0][3]){
                System.out.println("Factory 9 tile button1 clicked");
                factoryMovements(8,0);
            }
            if(gameStarted && !tilesPicked && x>=fS[0] + getWidth()/22*8 - getWidth()/45 + getWidth()/50 && x<= fS[0] + getWidth()/22*8 - getWidth()/45 + getWidth()/50 + tS[0][2] && y>= fS[1] + getHeight()/9  + getHeight()/220 && y<= fS[1] + getHeight()/9  + getHeight()/220 + tS[0][3]){
                System.out.println("Factory 9 tile button2 clicked");
                factoryMovements(8,2);
            } 
            if(gameStarted && !tilesPicked && x>=fS[0] + getWidth()/22*8 - getWidth()/45 + getWidth()/230 && x<=fS[0] + getWidth()/22*8 - getWidth()/45 + getWidth()/230 + tS[0][2] && y>=fS[1] + getHeight()/9  + getHeight()/35 && y<=fS[1] + getHeight()/9  + getHeight()/35 + tS[0][3]){
                System.out.println("Factory 9 tile button3 clicked");
                factoryMovements(8,1);
            }     
            if(gameStarted && !tilesPicked && x>=fS[0] + getWidth()/22*8 - getWidth()/45 + getWidth()/50 && x<= fS[0] + getWidth()/22*8 - getWidth()/45 + getWidth()/50 + tS[0][2] && y>= fS[1] + getHeight()/9  + getHeight()/35 && y<= fS[1] + getHeight()/9  + getHeight()/35 + tS[0][3]){
                System.out.println("Factory 9 tile button4 clicked");
                factoryMovements(8,3);
            }
            //left Over PILE CLICKS
            if(gameStarted && !tilesPicked && x>= tS[0][0] + getWidth()/48*(0%9) && x<= tS[0][0] + getWidth()/48*(0%9) + tS[0][2] && y>= tS[0][1] + getHeight()/30*(0/9) && y<= tS[0][1] + getHeight()/30*(0/9) + tS[0][3]){
                System.out.println("DP1 clicked");
                leftOverPileMovements(0);
            }
            if(gameStarted && !tilesPicked && x>= tS[0][0] + getWidth()/48*(1%9) && x<= tS[0][0] + getWidth()/48*(1%9) + tS[0][2] && y>= tS[0][1] + getHeight()/30*(1/9) && y<= tS[0][1] + getHeight()/30*(1/9) + tS[0][3]){
                System.out.println("DP2 clicked");
                leftOverPileMovements(1);
            }
            if(gameStarted && !tilesPicked && x>= tS[0][0] + getWidth()/48*(2%9) && x<= tS[0][0] + getWidth()/48*(2%9) + tS[0][2] && y>= tS[0][1] + getHeight()/30*(2/9) && y<= tS[0][1] + getHeight()/30*(2/9) + tS[0][3]){
                System.out.println("DP3 clicked");
                leftOverPileMovements(2);
            }
            if(gameStarted && !tilesPicked && x>= tS[0][0] + getWidth()/48*(3%9) && x<= tS[0][0] + getWidth()/48*(3%9) + tS[0][2] && y>= tS[0][1] + getHeight()/30*(3/9) && y<= tS[0][1] + getHeight()/30*(3/9) + tS[0][3]){
                System.out.println("DP4 clicked");
                leftOverPileMovements(3);
            }
            if(gameStarted && !tilesPicked && x>= tS[0][0] + getWidth()/48*(4%9) && x<= tS[0][0] + getWidth()/48*(4%9) + tS[0][2] && y>= tS[0][1] + getHeight()/30*(4/9) && y<= tS[0][1] + getHeight()/30*(4/9) + tS[0][3]){
                System.out.println("DP5 clicked");
                leftOverPileMovements(4);
            }
            if(gameStarted && !tilesPicked && x>= tS[0][0] + getWidth()/48*(5%9) && x<= tS[0][0] + getWidth()/48*(5%9) + tS[0][2] && y>= tS[0][1] + getHeight()/30*(5/9) && y<= tS[0][1] + getHeight()/30*(5/9) + tS[0][3]){
                System.out.println("DP6 clicked");
                leftOverPileMovements(5);
            }
            if(gameStarted && !tilesPicked && x>= tS[0][0] + getWidth()/48*(6%9) && x<= tS[0][0] + getWidth()/48*(6%9) + tS[0][2] && y>= tS[0][1] + getHeight()/30*(6/9) && y<= tS[0][1] + getHeight()/30*(6/9) + tS[0][3]){
                System.out.println("DP7 clicked");
                leftOverPileMovements(6);
            }
            if(gameStarted && !tilesPicked && x>= tS[0][0] + getWidth()/48*(7%9) && x<= tS[0][0] + getWidth()/48*(7%9) + tS[0][2] && y>= tS[0][1] + getHeight()/30*(7/9) && y<= tS[0][1] + getHeight()/30*(7/9) + tS[0][3]){
                System.out.println("DP8 clicked");
                leftOverPileMovements(7);
            }
            if(gameStarted && !tilesPicked && x>= tS[0][0] + getWidth()/48*(8%9) && x<= tS[0][0] + getWidth()/48*(8%9) + tS[0][2] && y>= tS[0][1] + getHeight()/30*(8/9) && y<= tS[0][1] + getHeight()/30*(8/9) + tS[0][3]){
                System.out.println("DP9 clicked");
                leftOverPileMovements(8);
            }
            if(gameStarted && !tilesPicked && x>= tS[0][0] + getWidth()/48*(9%9) && x<= tS[0][0] + getWidth()/48*(9%9) + tS[0][2] && y>= tS[0][1] + getHeight()/30*(9/9) && y<= tS[0][1] + getHeight()/30*(9/9) + tS[0][3]){
                System.out.println("DP10 clicked");
                leftOverPileMovements(9);
            }
            if(gameStarted && !tilesPicked && x>= tS[0][0] + getWidth()/48*(10%9) && x<= tS[0][0] + getWidth()/48*(10%9) + tS[0][2] && y>= tS[0][1] + getHeight()/30*(10/9) && y<= tS[0][1] + getHeight()/30*(10/9) + tS[0][3]){
                System.out.println("DP11 clicked");
                leftOverPileMovements(10);
            }
            if(gameStarted && !tilesPicked && x>= tS[0][0] + getWidth()/48*(11%9) && x<= tS[0][0] + getWidth()/48*(11%9) + tS[0][2] && y>= tS[0][1] + getHeight()/30*(11/9) && y<= tS[0][1] + getHeight()/30*(11/9) + tS[0][3]){
                System.out.println("DP12 clicked");
                leftOverPileMovements(11);
            }
            if(gameStarted && !tilesPicked && x>= tS[0][0] + getWidth()/48*(12%9) && x<= tS[0][0] + getWidth()/48*(12%9) + tS[0][2] && y>= tS[0][1] + getHeight()/30*(12/9) && y<= tS[0][1] + getHeight()/30*(12/9) + tS[0][3]){
                System.out.println("DP13 clicked");
                leftOverPileMovements(12);
            }
            if(gameStarted && !tilesPicked && x>= tS[0][0] + getWidth()/48*(13%9) && x<= tS[0][0] + getWidth()/48*(13%9) + tS[0][2] && y>= tS[0][1] + getHeight()/30*(13/9) && y<= tS[0][1] + getHeight()/30*(13/9) + tS[0][3]){
                System.out.println("DP14 clicked");
                leftOverPileMovements(13);
            }
            if(gameStarted && !tilesPicked && x>= tS[0][0] + getWidth()/48*(14%9) && x<= tS[0][0] + getWidth()/48*(14%9) + tS[0][2] && y>= tS[0][1] + getHeight()/30*(14/9) && y<= tS[0][1] + getHeight()/30*(14/9) + tS[0][3]){
                System.out.println("DP15 clicked");
                leftOverPileMovements(14);
            }
            if(gameStarted && !tilesPicked && x>= tS[0][0] + getWidth()/48*(15%9) && x<= tS[0][0] + getWidth()/48*(15%9) + tS[0][2] && y>= tS[0][1] + getHeight()/30*(15/9) && y<= tS[0][1] + getHeight()/30*(15/9) + tS[0][3]){
                System.out.println("DP16 clicked");
                leftOverPileMovements(15);
            }
            if(gameStarted && !tilesPicked && x>= tS[0][0] + getWidth()/48*(16%9) && x<= tS[0][0] + getWidth()/48*(16%9) + tS[0][2] && y>= tS[0][1] + getHeight()/30*(16/9) && y<= tS[0][1] + getHeight()/30*(16/9) + tS[0][3]){
                System.out.println("DP17 clicked");
                leftOverPileMovements(16);
            }
            if(gameStarted && !tilesPicked && x>= tS[0][0] + getWidth()/48*(17%9) && x<= tS[0][0] + getWidth()/48*(17%9) + tS[0][2] && y>= tS[0][1] + getHeight()/30*(17/9) && y<= tS[0][1] + getHeight()/30*(17/9) + tS[0][3]){
                System.out.println("DP18 clicked");
                leftOverPileMovements(17);
            }
            if(gameStarted && !tilesPicked && x>= tS[0][0] + getWidth()/48*(18%9) && x<= tS[0][0] + getWidth()/48*(18%9) + tS[0][2] && y>= tS[0][1] + getHeight()/30*(18/9) && y<= tS[0][1] + getHeight()/30*(18/9) + tS[0][3]){
                System.out.println("DP19 clicked");
                leftOverPileMovements(18);
            }
            if(gameStarted && !tilesPicked && x>= tS[0][0] + getWidth()/48*(19%9) && x<= tS[0][0] + getWidth()/48*(19%9) + tS[0][2] && y>= tS[0][1] + getHeight()/30*(19/9) && y<= tS[0][1] + getHeight()/30*(19/9) + tS[0][3]){
                System.out.println("DP20 clicked");
                leftOverPileMovements(19);
            }
            if(gameStarted && !tilesPicked && x>= tS[0][0] + getWidth()/48*(20%9) && x<= tS[0][0] + getWidth()/48*(20%9) + tS[0][2] && y>= tS[0][1] + getHeight()/30*(20/9) && y<= tS[0][1] + getHeight()/30*(20/9) + tS[0][3]){
                System.out.println("DP21 clicked");
                leftOverPileMovements(20);
            }
            if(gameStarted && !tilesPicked && x>= tS[0][0] + getWidth()/48*(21%9) && x<= tS[0][0] + getWidth()/48*(21%9) + tS[0][2] && y>= tS[0][1] + getHeight()/30*(21/9) && y<= tS[0][1] + getHeight()/30*(21/9) + tS[0][3]){
                System.out.println("DP22 clicked");
                leftOverPileMovements(21);
            }
            if(gameStarted && !tilesPicked && x>= tS[0][0] + getWidth()/48*(22%9) && x<= tS[0][0] + getWidth()/48*(22%9) + tS[0][2] && y>= tS[0][1] + getHeight()/30*(22/9) && y<= tS[0][1] + getHeight()/30*(22/9) + tS[0][3]){
                System.out.println("DP23 clicked");
                leftOverPileMovements(22);
            }
            if(gameStarted && !tilesPicked && x>= tS[0][0] + getWidth()/48*(23%9) && x<= tS[0][0] + getWidth()/48*(23%9) + tS[0][2] && y>= tS[0][1] + getHeight()/30*(23/9) && y<= tS[0][1] + getHeight()/30*(23/9) + tS[0][3]){
                System.out.println("DP24 clicked");
                leftOverPileMovements(23);
            }
            if(gameStarted && !tilesPicked && x>= tS[0][0] + getWidth()/48*(24%9) && x<= tS[0][0] + getWidth()/48*(24%9) + tS[0][2] && y>= tS[0][1] + getHeight()/30*(24/9) && y<= tS[0][1] + getHeight()/30*(24/9) + tS[0][3]){
                System.out.println("DP25 clicked");
                leftOverPileMovements(24);
            }
            if(gameStarted && !tilesPicked && x>= tS[0][0] + getWidth()/48*(25%9) && x<= tS[0][0] + getWidth()/48*(25%9) + tS[0][2] && y>= tS[0][1] + getHeight()/30*(25/9) && y<= tS[0][1] + getHeight()/30*(25/9) + tS[0][3]){
                System.out.println("DP26 clicked");
                leftOverPileMovements(25);
            }
            if(gameStarted && !tilesPicked && x>= tS[0][0] + getWidth()/48*(26%9) && x<= tS[0][0] + getWidth()/48*(26%9) + tS[0][2] && y>= tS[0][1] + getHeight()/30*(26/9) && y<= tS[0][1] + getHeight()/30*(26/9) + tS[0][3]){
                System.out.println("DP27 clicked");
                leftOverPileMovements(26);
            }
            //MAKE THE PLAYER LINE CLICKABLE AND MAKE IT SO THAT IT CAN ONLY BE CLICKED ONCE
            if(gameStarted && tilesPicked && x>= tS[1][0] + getWidth()/38 -getWidth()/34-getWidth()/34*0 && x<= tS[1][0] + getWidth()/38 && y>= tS[1][1] + getHeight()/23*0 + getHeight()/500*0 && y<= tS[1][1] + getHeight()/23*0 + getHeight()/500*0 + getHeight()/23){
                pLineMovements(0);
                nextTurn = true;
                //get tiles in current players possesion
                System.out.println("PL1 clicked");
            }
            if(gameStarted && tilesPicked && x>= tS[1][0] + getWidth()/38 -getWidth()/34-getWidth()/34*1 && x<= tS[1][0] + getWidth()/38 && y>= tS[1][1] + getHeight()/23*1 + getHeight()/500*1 && y<= tS[1][1] + getHeight()/23*1 + getHeight()/500*1 + getHeight()/23){
                pLineMovements(1);
                nextTurn = true;
                System.out.println("PL2 clicked");
            }
            if(gameStarted && tilesPicked && x>= tS[1][0] + getWidth()/38 -getWidth()/34-getWidth()/34*2 && x<= tS[1][0] + getWidth()/38 && y>= tS[1][1] + getHeight()/23*2 + getHeight()/500*2 && y<= tS[1][1] + getHeight()/23*2 + getHeight()/500*2 + getHeight()/23){
                pLineMovements(2);
                nextTurn = true;
                System.out.println("PL3 clicked");
            }
            if(gameStarted && tilesPicked && x>= tS[1][0] + getWidth()/38 -getWidth()/34-getWidth()/34*3 && x<= tS[1][0] + getWidth()/38 && y>= tS[1][1] + getHeight()/23*3 + getHeight()/500*3 && y<= tS[1][1] + getHeight()/23*3 + getHeight()/500*3 + getHeight()/23){
                pLineMovements(3);
                nextTurn = true;
                System.out.println("PL4 clicked");
            }
            if(gameStarted && tilesPicked && x>= tS[1][0] + getWidth()/38 -getWidth()/34-getWidth()/34*4 && x<= tS[1][0] + getWidth()/38 && y>= tS[1][1] + getHeight()/23*4 + getHeight()/500*4 && y<= tS[1][1] + getHeight()/23*4 + getHeight()/500*4 + getHeight()/23){
                pLineMovements(4);
                nextTurn = true;
                System.out.println("PL5 clicked");
            }
            //MAKE THE FLOOR LINE CLICKABLE AND MAKE IT SO THAT IT CAN ONLY BE CLICKED ONCE
            if(gameStarted && x>= getWidth()/3*2 - getWidth()/10 -getWidth()/5 -getWidth()/40 + getWidth()/29*0 && x<= getWidth()/3*2 - getWidth()/10 && y>=tS[1][1] + getHeight()/4 && y<=tS[1][1] + getHeight()/4 + getHeight()/23){
                System.out.println("FL1 clicked");
                nextTurn = true;
                fLineMovements();
            }
            //clickable space for next button
            if(gameStarted && tilesAdded && nextTurn && x>=getWidth()-getWidth()/4+getWidth()/50 && x<= getWidth()-getWidth()/4+getWidth()/50 + getWidth()/8 && y>=getHeight()/2+getHeight()/9 && y<=getHeight()/2+getHeight()/9 + getHeight()/5+getHeight()/120){
                System.out.println("next button clicked");
                if(wallTilingPhase){
                    for(int i = 0; i<4; i++){
                        System.out.println("wall tiling phase");
                        //wall tiling
                        ArrayList<ArrayList<Integer>> cRows = game.getCurrentPlayer().getPlayerLine().getCompleteRows();
                        System.out.println(cRows);
                        for(int a = 0; a<cRows.size(); a++){
                            game.getCurrentPlayer().getPlayerWall().addTile(cRows.get(a).get(0), cRows.get(a).get(1));
                            //scoring phase
                            System.out.println("scoring phase");
                            game.getCurrentPlayer().getScore().scoreNormal();
                        }
                        game.getCurrentPlayer().getScore().subtractFloorLine();
                        game.addToDeadPile(game.getCurrentPlayer().getPlayerLine().clearCompleteRows());
                        System.out.println(game.getCurrentPlayer().getPlayerNumber());
                        game.getCurrentPlayer().getScore().getTotal();                        
                        //clear factory
                        game.getCurrentPlayer().getPlayerLine().emptyFloorLine();
                        if(game.getCurrentPlayer().getPlayerWall().isGameOver()){
                            System.out.println("GEE GEE GAME OVER");
                            game.getCurrentPlayer().getScore().scoreBonus();
                            winner = game.getCurrentPlayer().getPlayerNumber();
                            playerWinner = game.getCurrentPlayer();
                            gameStarted = false;
                            game.removeWinner();
                            //SET THE STATE 0 TO 3
                            state.set(0, 2);
                        }
                        game.cyclePlayers();
                    }
                    wallTilingPhase = false;
                    game.refillFactories();
                    times = 0;
                    //add a starter tile back to the left over pile
                    ArrayList<Integer> starterTile = new ArrayList<Integer>(){{
                        add(5);
                    }};
                    game.addToLeftOverPile(starterTile);
                    
                    repaint();

                }
                game.cyclePlayers();

                tilesPicked = false;
                tilesAdded = false;
                nextTurn = false;
                repaint();
            }
        }
    }
    public void addNotify(){
        super.addNotify();
        requestFocus();
    }
    public void paint(Graphics g){
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
   
        //sets the welcome screen background
        //sets the game background
        //specific sizes for player icons - 0:as if the one playing & 1:left sideline & 2:right sideline
        if(state.get(0) == 0){
            g.drawImage(bg1, 0, 0, getWidth(), getHeight(), null);
            g.drawImage(Next1, getWidth()/2 - getWidth()/10, getHeight()/5, getWidth()/9 - getWidth()/150, getHeight()/6, null);
        }
        if(state.get(0) == 1){
            repainted++;
            System.out.println("Board repaint number: " + repainted);
                g.drawImage(bg2, 0, 0, getWidth(), getHeight(), null);
                //close to the top right corner of the screen write "press the next button"
            g.setColor(Color.red);
            g.setFont(new Font("Helvetica", Font.BOLD, 23));
            if(nextTurn){
                g.drawString("CLICK THE NEXT BUTTON", getWidth()/10, getHeight()/2 - getHeight()/3);
            } else if(tilesPicked){
                g.drawString("PICK THE ROW OR THE FLOOR LINE", getWidth()/10, getHeight()/2 - getHeight()/3);
            }
             else{
                g.drawString("PICK THE FACTORY OR THE FLOOR", getWidth()/10, getHeight()/2 - getHeight()/3);
            }
                //draws player numbers
                for(int i = 0; i<4; i++){
                    Integer pNumber = game.getPlayer(i).getPlayerNumber() + 1;
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("TimesRoman", Font.PLAIN, 16));
                    if(i == 0){
                        g.drawString("Player " + pNumber, playerBoardSizes[i][0] + getWidth()/7, playerBoardSizes[i][1] - getHeight()/200);
                    } else{
                        g.drawString("Player " + pNumber, playerBoardSizes[i][0] + getWidth()/15, playerBoardSizes[i][1] - getHeight()/200);
                    }
                }
                
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
            //MAIN PLAYER SETUP
            //draws main player line tiling
            ArrayList<ArrayList<Integer>> mainPLinePlacements = game.getCurrentPlayer().getPlayerLine().getLinePlacements();
            for(int i = 0; i< mainPLinePlacements.size(); i++){
                ArrayList<Integer> tile = mainPLinePlacements.get(i);
                System.out.println("Placement information" + tile);
                Integer type = tile.get(0);
                Integer number = tile.get(1);
                Integer row = tile.get(2);
                //System.out.println("Tile to be printed: " + type + ", " + number + " times in row " + row);          
                    for(int b = 0; b< number; b++){
                        g.drawImage(tiles[type], tileSizes[1][0] - getWidth()/34*b, tileSizes[1][1] + getHeight()/23*row + getHeight()/500*row, tileSizes[1][2], tileSizes[1][3], null);
                    }
                
            }
            //draws main player wall tiling
            ArrayList<ArrayList<Integer>> mainPWallPlacements = game.getCurrentPlayer().getPlayerWall().getWallPlacements();
            for(int i = 0; i< mainPWallPlacements.size(); i++){
                ArrayList<Integer> tile = mainPWallPlacements.get(i);
                Integer type = tile.get(0);
                Integer y = 4-tile.get(1);
                //y = 4-1;
                Integer x = tile.get(2);
                g.drawImage(tiles[type], tileSizes[2][0] + getWidth()/34*x + getWidth()/1455*x, tileSizes[2][1] - getHeight()/22*y, tileSizes[2][2], tileSizes[2][3], null);
                //draw rectangle around tile
                g.setColor(Color.GREEN);
                g.drawRect(tileSizes[2][0] + getWidth()/34*x + getWidth()/1455*x, tileSizes[2][1] - getHeight()/22*y, tileSizes[2][2], tileSizes[2][3]);
            }
            //draws main player floor tiling
            for(int i = 0; i<game.getCurrentPlayer().getPlayerLine().getFloorLine().size(); i++){
                
                Integer tile = game.getCurrentPlayer().getPlayerLine().getFloorLine().get(i);
                g.drawImage(tiles[tile], tileSizes[3][0] + getWidth()/31*i + getWidth()/1439*i - getWidth()/1460*i, tileSizes[3][1], tileSizes[3][2], tileSizes[3][3], null);
            }
            //draws the main player score marker
            Integer score = game.getCurrentPlayer().getScore().getTotal();
            System.out.println("Score: " + score);
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
            if(state.get(1) == 1){
                g.setColor(Color.RED);
            //draw rectangle around the main player floor line when its available - around spaces available 
            Integer availableFloorLineSpots = 7 - game.getCurrentPlayer().getPlayerLine().getFloorLineSpace();
            if(game.getCurrentPlayer().getPlayerLine().getFloorLineSpace() >= tilesInHand.size()){
                if(availableFloorLineSpots - 7 == 0){
                } else{
                    g.drawRect(getWidth()/3*2 - getWidth()/10, tileSizes[1][1] + getHeight()/4, -getWidth()/5 -getWidth()/40 + getWidth()/29*availableFloorLineSpots, getHeight()/23);
                }

            }
            //draw rectangle around each available row in the main player line
            ArrayList<Integer> availableRows = game.getCurrentPlayer().getPlayerLine().getAvailableRowsP(tilesInHand);
            g.setColor(Color.RED);
            for(int i = 0; i<availableRows.size(); i++){
                Integer row1 = availableRows.get(i);
                g.drawRect(tileSizes[1][0] + getWidth()/38, tileSizes[1][1] + getHeight()/23*row1 + getHeight()/500*row1, -getWidth()/34-getWidth()/34*row1, getHeight()/23);
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
                    Integer y = tile.get(1);
                    Integer x = tile.get(2);
                    g.drawImage(tiles[type], addX + getWidth()/90 + getWidth()/7 + getWidth()/65*x, addY + getHeight()/4 + getHeight()/15 + getHeight()/43*y, getWidth()/80, getHeight()/54, null);
                    //draw rectangle around tile
                    g.setColor(Color.GREEN);
                    g.drawRect(addX + getWidth()/90 + getWidth()/7 + getWidth()/65*x, addY + getHeight()/4 + getHeight()/15 + getHeight()/43*y, getWidth()/80, getHeight()/54);
                }
                //draws left side player line tiling
                ArrayList<ArrayList<Integer>> sidePLinePlacements = game.getPlayer(a).getPlayerLine().getLinePlacements();
                for(int i = 0; i< sidePLinePlacements.size(); i++){
                    ArrayList<Integer> tile = sidePLinePlacements.get(i);
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
        if(state.get(0) == 3){
            //draw a blue rectangle that takes up the entire space and draw text in the middle that has th enumber of the winner
            g.setColor(Color.BLUE);
            g.fillRect(0, 0, getWidth(), getHeight());
            g.setColor(Color.WHITE);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
            g.drawString("Player " + winner + " wins!", getWidth()/2 - getWidth()/8, getHeight()/2);
            //draw the player standings
            g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
            g.drawString("Player " +  game.getPlayer(0).getPlayerNumber() + ": " + game.getPlayer(0).getScore().getTotal(), getWidth()/2 - getWidth()/8, getHeight()/2 + getHeight()/10);
            g.drawString("Player " +  game.getPlayer(1).getPlayerNumber() + ": " + game.getPlayer(1).getScore().getTotal(), getWidth()/2 - getWidth()/8, getHeight()/2 + getHeight()/10 + getHeight()/20);
            g.drawString("Player " +  game.getPlayer(2).getPlayerNumber() + ": " + game.getPlayer(2).getScore().getTotal(), getWidth()/2 - getWidth()/8, getHeight()/2 + getHeight()/10 + getHeight()/10);
        }
        
    }
    public void factoryMovements(Integer factory, Integer tile){
        Integer ttype = game.getFactory(factory).getTile(tile);
        ArrayList<Integer> pPossesion = game.getFactory(factory).removeTiles(ttype);
        game.getCurrentPlayer().addTilesToPossession(pPossesion);
        ArrayList<Integer> availableRows = game.getCurrentPlayer().getPlayerLine().getAvailableRowsP(pPossesion);
        Boolean isAvailable = game.getCurrentPlayer().getPlayerLine().isFloorLineAvailable(pPossesion);
        game.addToLeftOverPile(game.getFactory(factory).moveLeftOvers());
        tilesPicked = true;
        state.set(1,1);
        if(times == 0){
            times = 1;
            System.out.println("TIMES" + times);
        } 
        repaint();
        if(!availableRows.contains(0) && !availableRows.contains(1) && !availableRows.contains(2) && !availableRows.contains(3) && !availableRows.contains(4) && !isAvailable){
            game.addToDeadPile(pPossesion);
            game.getCurrentPlayer().clearPlayerPossession();
            tilesAdded = true;
            game.getCurrentPlayer().getPlayerLine().getLineContents();
            state.set(1,0);
            if(game.endOfPhase()){
                wallTilingPhase = true; 
            }
            nextTurn = true;
            
        } 
    }
    public void leftOverPileMovements(Integer tile){
        System.out.println("TIMES" + times);

        if(times == 0 || (times == 1 && tile == 0)){
        }
        else{
            Integer ttype = game.getLeftOverPileTile(tile);
            if(times == 1){
                ttype = game.getLeftOverPileTile(0);
                ArrayList<Integer> pPossesion = game.pullFromLeftOverPile(ttype);
                game.getCurrentPlayer().addTilesToPossession(pPossesion);
                fLineMovements();
                times = 2;
                ttype = game.getLeftOverPileTile(tile -1);
            }
        ArrayList<Integer> pPossesion = game.pullFromLeftOverPile(ttype);
        game.getCurrentPlayer().addTilesToPossession(pPossesion);
        ArrayList<Integer> availableRows = game.getCurrentPlayer().getPlayerLine().getAvailableRowsP(pPossesion);
        Boolean isAvailable = game.getCurrentPlayer().getPlayerLine().isFloorLineAvailable(pPossesion);
        tilesPicked = true;
        if(ttype == 5){
            fLineMovements();
            nextTurn = true;
        } else if(!availableRows.contains(0) && !availableRows.contains(1) && !availableRows.contains(2) && !availableRows.contains(3) && !availableRows.contains(4) && !isAvailable){
            game.addToDeadPile(pPossesion);
            nextTurn = true;
            game.getCurrentPlayer().clearPlayerPossession();
            tilesAdded = true;
            game.getCurrentPlayer().getPlayerLine().getLineContents();
            state.set(1,0);
            if(game.endOfPhase()){
                wallTilingPhase = true; 
            }
        }
        else{
            state.set(1,1);
        }
        repaint();
    }
    }
    public void pLineMovements(Integer row){
        ArrayList<Integer> tilesInHand = game.getCurrentPlayer().getTilesInPossession();        
        ArrayList<Integer> availableRows = game.getCurrentPlayer().getPlayerLine().getAvailableRowsP(tilesInHand);
        ArrayList<Integer> availableRowsRaw = game.getCurrentPlayer().getPlayerLine().getAvailableRowsRaw(tilesInHand);
        if(availableRows.contains(row)){
            System.out.println("PLAYER LINE 1");
            game.getCurrentPlayer().getPlayerLine().addTilesToRow(row, tilesInHand);
            game.getCurrentPlayer().clearPlayerPossession();
            tilesAdded = true;
            game.getCurrentPlayer().getPlayerLine().getLineContents();
            state.set(1,0);
            if(game.endOfPhase()){
                wallTilingPhase = true; 
            }
            repaint();
        } else if(availableRowsRaw.contains(row)){
            System.out.println("PLAYER LINE 2");
            Integer amount = game.getCurrentPlayer().getPlayerLine().getRowSpace(row);
            ArrayList<Integer> tiles = new ArrayList<Integer>();
            for(int i = 0; i < amount; i++){
                tiles.add(tilesInHand.get(i));
            }
            System.out.println("TILES SPILLLOVER: " + tiles);
            System.out.println("TILES SPILLLOVER LEFT OVER: " + tilesInHand);
            System.out.println("TILES SPILLLOVER AMOUNT: " + amount);

            //remove the number of tiles that are in tiles from tileInHand
            for(int i = 0; i < amount; i++){
                tilesInHand.remove(0);
            }
            System.out.println("TILES SPILLLOVER LEFT OVER 2: " + tilesInHand);
            game.getCurrentPlayer().getPlayerLine().addTilesToRow(row, tiles);
            //add the rest of the tiles to the floor line        
            //print the files to go to the floor line
            System.out.println("Floor Line SPILLOVER: " + tilesInHand);
            System.out.println("TILES SPILLLOVER: " + tiles);
            game.getCurrentPlayer().getPlayerLine().addFloorLine(tilesInHand);
            game.getCurrentPlayer().clearPlayerPossession();
            tilesAdded = true;
            game.getCurrentPlayer().getPlayerLine().getLineContents();
            state.set(1,0);
            if(game.endOfPhase()){
                wallTilingPhase = true; 
            }
            repaint();
        }

    }
    public void fLineMovements(){
        ArrayList<Integer> tilesInHand = game.getCurrentPlayer().getTilesInPossession();   
        //is Available
        Boolean isAvailable = game.getCurrentPlayer().getPlayerLine().isFloorLineAvailable(tilesInHand);
        if(isAvailable){
            game.getCurrentPlayer().getPlayerLine().addFloorLine(tilesInHand);
            game.getCurrentPlayer().clearPlayerPossession();
            tilesAdded = true;
            state.set(1,0);
            if(game.endOfPhase()){
                wallTilingPhase = true;            }
            repaint();
        } 
    }
}
