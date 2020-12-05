package main.java.launcher;

import main.java.game.ConwayGameOfLife;
import main.java.game.LangtonsAntTuringMachine;

import javax.swing.*;
import java.awt.*;

public class Launcher {
    
    public static void main(String[] args) {
//        ConwayGameOfLife game = new ConwayGameOfLife();
        LangtonsAntTuringMachine game = new LangtonsAntTuringMachine(200, 200, 4);
        String title = "Welcome to " + game.getTitle() + "!";
        
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        
        frame.setLayout(new BorderLayout());
        frame.add(game.cellGridPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);
        game.runGame();
    }
}
