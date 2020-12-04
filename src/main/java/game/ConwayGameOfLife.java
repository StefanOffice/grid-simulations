package main.java.game;

import main.java.component.Cell;
import main.java.component.CellGridPanel;

public class ConwayGameOfLife extends AbstractGridGame{
    
    public ConwayGameOfLife(){
        //hard-coding sample cells to test rendering
        cellGridPanel = new CellGridPanel(9, 7, 30);
        
        cellGridPanel.getCell(5,1).turnOn();
        cellGridPanel.getCell(6,1).turnOn();
        cellGridPanel.getCell(7,1).turnOn();
        
        cellGridPanel.getCell(1,4).turnOn();
        cellGridPanel.getCell(2,4).turnOn();
        cellGridPanel.getCell(3,4).turnOn();
        cellGridPanel.getCell(4,4).turnOn();
        cellGridPanel.getCell(5,4).turnOn();
        title = "Conway's Game of Life";
    }
    
    public ConwayGameOfLife(int numRows, int numColumns, int cellSize){
        super(numRows, numColumns, cellSize);
        title = "Conway's Game of Life";
    }
    
    private int[][] countNeighbors(){
        int[][] counts = new int[cellGridPanel.getNumOfRows()][cellGridPanel.getNumOfColumns()];
        
        for (int row = 0; row < counts.length; row++) {
            for (int column = 0; column < counts[0].length; column++) {
                counts[row][column] = cellGridPanel.countNeighbours(row, column);
            }
        }
        return counts;
    }
    
    public void updateGrid(int[][] counts){
        for (int row = 0; row < cellGridPanel.getNumOfRows(); row++) {
            for (int column = 0; column < cellGridPanel.getNumOfColumns(); column++) {
                Cell current = cellGridPanel.getCell(row,column);
                int numOfNeighbours = counts[row][column];
                if(current.isOn()) {
                    if (numOfNeighbours < 2 || numOfNeighbours > 3)
                        current.turnOff();
                }else{
                    if(numOfNeighbours == 3)
                        current.turnOn();
                }
            }
        }
    }
    
    //simulates one round of the game
    @Override
    public void update(){
        int[][] counts = countNeighbors();
        updateGrid(counts);
    }
    
    public void runGame() {
        while (true) {
            //run one round
            this.update();
            cellGridPanel.repaint();
            //wait a bit
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignored){}
        }
    }
    
    
    
}
