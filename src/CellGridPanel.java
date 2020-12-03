import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
//extend JPanel to get all the functionality from it
//plus add a multidimensional array of cells
public class CellGridPanel extends JPanel {
    
    private final Cell[][] cells;
    public int cellSize;
    
    public CellGridPanel(int numRows, int numColumns, int cellSize){
        //create the cell grid based on parameters
        cells = new Cell[numRows][numColumns];
        for (int row = 0; row < numRows; row++) {
            int cellY = row * cellSize;
            for (int column = 0; column < numColumns; column++) {
                int cellX = column * cellSize;
                cells[row][column] = new Cell(cellX,cellY,cellSize);
            }
        }
        
        setSize(numColumns*cellSize, numRows*cellSize);
        this.cellSize = cellSize;
    }
    
    @Override
    public void paint(Graphics g) {
        for(Cell[] currentRow : cells){
            for(Cell currentCell : currentRow){
                currentCell.draw(g);
            }
        }
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(getNumOfColumns() * cellSize + 2, getNumOfRows() * cellSize + 2);
    }
    
    public int getNumOfRows(){
        return cells.length;
    }
    
    public int getNumOfColumns(){
        return cells[0].length;
    }
    
    
    public Cell getCell(int row, int column) {
        return cells[row][column];
    }
}
