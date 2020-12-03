import javax.swing.*;
import java.awt.*;

public class Launcher {
    
    public static void main(String[] args) {
        String title = "Welcome to Conway's Game Of Life!";
        ConwayGameOfLife game = new ConwayGameOfLife();
        
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setResizable(false);
        
        frame.setLayout(new BorderLayout());
        frame.add(game.cellGridPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
    }
}
