import java.awt.*;
import javax.swing.*;
public class GameFrame extends JFrame{
    private static final int WIDTH = 1600;
    private static final int HEIGHT = 960;
    public GameFrame(String framename){
        super(framename);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(WIDTH,HEIGHT);
        setVisible(true);
        add(new FactoriesPanel());
        add(new MPBoardPanel(WIDTH, HEIGHT));
        add(new P2BoardPanel());
        add(new P3BoardPanel());
        add(new P4BoardPanel());
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
