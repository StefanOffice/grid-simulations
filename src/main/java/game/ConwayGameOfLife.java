package main.java.game;


import main.java.component.Cell;

public class ConwayGameOfLife extends AbstractGridGame {
    
    public ConwayGameOfLife(int numRows, int numColumns, int cellSize){
        super(numRows, numColumns, cellSize);
        title = "Conway's Game of Life";
    }
    
    public ConwayGameOfLife(){
        super();
        title = "Conway's Game of Life";
    }
    
    @Override
    public void update(){
        int[][] counts = countNeighbors();
        updateGrid(counts);
    }
    
    //this needs to be in the separate method as it needs to be done before switching the state of the cells
    //take the counts as a snapshot THEN update the cells, otherwise the results are unstable
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
                Cell current = cellGridPanel.getCellByPosition(row,column);
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
}
