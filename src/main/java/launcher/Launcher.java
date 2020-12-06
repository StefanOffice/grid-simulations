package main.java.launcher;

import main.java.game.AbstractGridGame;
import main.java.game.ConwayGameOfLife;
import main.java.game.LangtonsAntTuringMachine;

public class Launcher {
    
    public static void main(String[] args) {
        AbstractGridGame conway = new ConwayGameOfLife(30,30,40);
        AbstractGridGame langton = new LangtonsAntTuringMachine(20,20,30);
        GameView view = new GameView(langton, 100);
    }
}
