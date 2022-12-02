import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
public class GameFrame extends JFrame{
    private static final int WIDTH = 1600;
    private static final int HEIGHT = 960;
    public GameFrame(String framename){
        super(framename);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH,HEIGHT);
        add(new GamePanel());
        setVisible(true);
        
    }
    //get the width
    public int getWidth(){
        return WIDTH;
    }
    //get the height
    public int getHeight(){
        return HEIGHT;
    }

}
