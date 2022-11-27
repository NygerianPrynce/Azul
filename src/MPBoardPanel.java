import javax.swing.*;
import java.awt.*;
public class MPBoardPanel extends JPanel{
    //set the width and height to a fourth of the frame
    public MPBoardPanel(int width, int height){
        setBackground(Color.yellow);
        setBounds(width/2,height/2,width/5,height/4);
        
    }
}
