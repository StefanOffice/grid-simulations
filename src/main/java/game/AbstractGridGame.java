package main.java.game;

import main.java.component.Cell;
import main.java.component.CellGridPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
    boolean playing;
    
    protected AbstractGridGame(int numRows, int numColumns, int cellSize) {
        
        //number of rows and columns bounds
        numRows = Math.min(Math.max(numRows, MIN_ROWS), MAX_ROWS);
        numColumns = Math.min(Math.max(numColumns, MIN_COLUMNS), MAX_COLUMNS);
        //set cell size bound
        cellSize = Math.min(Math.max(cellSize, MIN_CELL_SIZE), MAX_CELL_SIZE);
        
        cellGridPanel = new CellGridPanel(numRows, numColumns, cellSize);
        playing = false;
        
        addDefaultListener();
    }
    
    protected void addDefaultListener() {
        cellGridPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Cell clickedCell = cellGridPanel.getCellByCoordinates(e.getX(), e.getY());
                clickedCell.switchOnOff();
                //redraw the new status of the cell
                cellGridPanel.paintComponent(cellGridPanel.getGraphics());
            }
        });
    }
    
    protected AbstractGridGame() {
        this(DEFAULT_ROWS, DEFAULT_COLUMNS, DEFAULT_CELL_SIZE);
    }
    
    public void play() {
        playing = true;
    }
    
    public boolean isPlaying() {
        return playing;
    }
    
    public void pause() {
        playing = false;
    }
    
    public void clear() {
        pause();
        cellGridPanel.clear();
        cellGridPanel.paintComponent(cellGridPanel.getGraphics());
    }
    
    public void step() {
        update();
        cellGridPanel.paintComponent(cellGridPanel.getGraphics());
    }
    
    protected abstract void update();
    
    public String getTitle() {
        return title;
    }
}
