package main.java.launcher;

import main.java.game.AbstractGridGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameView implements ActionListener {
    
    private final AbstractGridGame game;
    private final Toolkit toolkit;
    private final JFrame frame;
    private final Timer timer;
    private static final String OK_TO_CLICK_TEXT = "Click on fields to turn them on/off before clicking play.";
    private static final String DONT_CLICK_TEXT = "Clicking cells is not recommended, pause first.";
    
    public GameView(AbstractGridGame game, int renderDelay) {
        this.game = game;
        frame = new JFrame(game.getTitle());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLayout(new BorderLayout());
        frame.add(game.cellGridPanel, BorderLayout.CENTER);
        
        JLabel statusLabel = new JLabel("Welcome to " + game.getTitle(), SwingConstants.CENTER);
        statusLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        JLabel instructionsLabel = new JLabel(OK_TO_CLICK_TEXT, SwingConstants.CENTER);
        instructionsLabel.setFont(new Font("Times New Roman", Font.PLAIN, 14));
        
        JButton step = new JButton("Step");
        step.addActionListener(e -> game.step());
        JButton pause = new JButton("Pause");
        pause.addActionListener(e -> {
            game.pause();
            updateLabel(statusLabel, "Game Status:  Paused.", Color.GRAY);
            updateLabel(instructionsLabel, OK_TO_CLICK_TEXT, Color.GREEN);
        });
        JButton play = new JButton("Play");
        play.addActionListener(e -> {
            game.play();
            updateLabel(statusLabel, "Game Status : Playing. ", Color.GREEN);
            updateLabel(instructionsLabel, DONT_CLICK_TEXT, Color.RED);
        });
        JButton clear = new JButton("Clear");
        clear.addActionListener(e -> {
            game.clear();
            updateLabel(statusLabel, "Game Status : Stopped and Reset.", Color.RED);
            updateLabel(instructionsLabel, OK_TO_CLICK_TEXT, Color.GREEN);
        });
        
        JPanel buttons = new JPanel();
        buttons.add(step);
        buttons.add(play);
        buttons.add(pause);
        buttons.add(clear);
        
        JPanel uiPanel = new JPanel();
        uiPanel.setLayout(new BorderLayout());
        uiPanel.add(statusLabel, BorderLayout.NORTH);
        uiPanel.add(instructionsLabel, BorderLayout.CENTER);
        uiPanel.add(buttons, BorderLayout.SOUTH);
        
        frame.add(uiPanel, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);
        toolkit = frame.getToolkit();
        
        //timer triggers the event which triggers actionPerformed below, making a continuous game loop
        timer = new Timer(renderDelay, this);
        timer.start();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(game.isPlaying()) {
            game.step();
            toolkit.sync();
        }
    }
    
    public static void updateLabel(JLabel label, String text, Color color){
        label.setForeground(color);
        label.setText(text);
    }
}