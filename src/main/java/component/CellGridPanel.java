package main.java.component;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class CellGridPanel extends JPanel {
    
    private final Cell[][] cells;
    public int cellSize;
    //to improve the efficiency of redrawing
    BufferedImage bufferedImage;
    
    public CellGridPanel(int numRows, int numColumns, int cellSize){
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
        bufferedImage = new BufferedImage(getNumOfColumns() * cellSize, getNumOfRows()*cellSize, BufferedImage.TYPE_3BYTE_BGR);
        drawToBuffer();
    }
    
    public void drawToBuffer(){
        Graphics graphics = bufferedImage.getGraphics();
        
        //used to check if the current cell has the same state as the previous cell
        int prevCellState = - 1;
        
        for(Cell[] row : cells){
            for(Cell cell : row){
                //if the state is same as the previous cell there is no need to call graphics.setColor,
                // just use the color used on previous cell
                if(cell.getState() != prevCellState)
                    graphics.setColor(Cell.STATE_COLORS[cell.getState()]);
                
                cell.draw(graphics);
                prevCellState = cell.getState();
            }
        }
        graphics.dispose();
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawToBuffer();
        g.drawImage(bufferedImage, 0, 0, null);
        g.dispose();
    }
    
    public void clear(){
        for(Cell[] row : cells){
            for(Cell cell : row){
                cell.clear();
            }
        }
    }
    
    public int countNeighbours(int cellRow, int cellColumn){
        int count = 0;
        for (int row = cellRow - 1 ; row <= cellRow + 1; row++) {
            for (int column = cellColumn - 1; column <= cellColumn + 1; column++) {
                //don't count yourself as the neighbour
                if(row == cellRow && column == cellColumn)
                    continue;
                
                if(isNeighbourOn(row,column))
                    count++;
            }
        }
        
        return count;
    }
    
    private boolean isNeighbourOn(int r, int c){
        if(r < 0 || r >= getNumOfRows() || c < 0 || c >= getNumOfColumns())
            return false;
        
        return cells[r][c].isOn();
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(getNumOfColumns() * cellSize + 1, getNumOfRows() * cellSize + 1);
    }
    
    public int getNumOfRows(){
        return cells.length;
    }
    
    public int getNumOfColumns(){
        return cells[0].length;
    }
    
    public Cell getCellByPosition(int row, int column){
        return cells[row][column];
    }
    
    public Cell getCellByCoordinates(int x, int y){
        return getCellByPosition(y / cellSize, x / cellSize);
    }
}
