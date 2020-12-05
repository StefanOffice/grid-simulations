package main.java.game;

import main.java.component.CellGridPanel;

public abstract class AbstractGridGame {
    
    public static final int MIN_ROWS = 10;
    public static final int MAX_ROWS = 20;
    public static final int DEFAULT_ROWS = 15;
    
    public static final int MIN_COLUMNS = 10;
    public static final int MAX_COLUMNS = 20;
    public static final int DEFAULT_COLUMNS = 15;
    
    public static final int MIN_CELL_SIZE = 20;
    public static final int MAX_CELL_SIZE = 35;
    public static final int DEFAULT_CELL_SIZE = 20;
    
    protected String title;
    public CellGridPanel cellGridPanel;
    
    protected AbstractGridGame(int numRows, int numColumns, int cellSize) {
        
        //number of rows and columns bounds
//        numRows = Math.min(Math.max(numRows, MIN_ROWS), MAX_ROWS);
//        numColumns = Math.min(Math.max(numColumns, MIN_COLUMNS), MAX_COLUMNS);
//        //set cell size bound
//        cellSize = Math.min(Math.max(cellSize, MIN_CELL_SIZE), MAX_CELL_SIZE);
        
        cellGridPanel = new CellGridPanel(numRows, numColumns, cellSize);
    }
    
    
    protected AbstractGridGame() {
        this(DEFAULT_ROWS, DEFAULT_COLUMNS, DEFAULT_CELL_SIZE);
    }
    
    protected abstract void update();
    
    public String getTitle() {
        return title;
    }
    
    public void runGame() {
        while (true) {
            //run one round
            this.update();
            cellGridPanel.repaint();
            //wait a bit
            try {
                Thread.sleep(5);
            } catch (InterruptedException ignored){}
        }
    }
    
}
