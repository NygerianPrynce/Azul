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
    //set the width and height to a fourth of the frame
    public GamePanel(){
        try{
            /*Baron = ImageIO.read(LoveLetterPanel.class.getResource("/images/Baron.png"));
            Chancellor = ImageIO.read(LoveLetterPanel.class.getResource("/images/Chancellor.png"));
            Countess = ImageIO.read(LoveLetterPanel.class.getResource("/images/Countess.png"));
            Guard = ImageIO.read(LoveLetterPanel.class.getResource("/images/Guard.png"));
            Handmaid = ImageIO.read(LoveLetterPanel.class.getResource("/images/Handmaid.png"));
            King = ImageIO.read(LoveLetterPanel.class.getResource("/images/King.png"));
            Priest = ImageIO.read(LoveLetterPanel.class.getResource("/images/Priest.png"));
            Prince = ImageIO.read(LoveLetterPanel.class.getResource("/images/Prince.png"));
            Princess = ImageIO.read(LoveLetterPanel.class.getResource("/images/Princess.png"));
            Spy = ImageIO.read(LoveLetterPanel.class.getResource("/images/Spy.png"));
            ReferenceCard = ImageIO.read(LoveLetterPanel.class.getResource("/images/ReferenceCard.png"));
            CardBack = ImageIO.read(LoveLetterPanel.class.getResource("/images/CardBack.png"));
            Player1Icon = ImageIO.read(LoveLetterPanel.class.getResource("/images/Player1Icon.png"));
            Player2Icon = ImageIO.read(LoveLetterPanel.class.getResource("/images/Player2Icon.png"));
            Player3Icon = ImageIO.read(LoveLetterPanel.class.getResource("/images/Player3Icon.png"));
            Token = ImageIO.read(LoveLetterPanel.class.getResource("/images/Token.png"));
            bg = ImageIO.read(LoveLetterPanel.class.getResource("/images/bg.jpeg")); */
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
        if(state == 0){
        g.fillRect(getWidth()-getWidth()/4 - getWidth()/60, getHeight()-getHeight()/3 - getHeight()/30, getWidth()/10, getHeight()/2);
        }
    }
}
