package main.java.game;

import main.java.component.Cell;

public class LangtonsAntTuringMachine extends AbstractGridGame {
    
    private int row;
    private int column;
    private int direction; //0-N , 1-E, 2-S, 3-W
    
    //in my version of Langton's ant
    //he gets reset to center if he is stuck for too long
    //4 of the variables below are saving data for that purpose
    private Cell previousLocation;
    private Cell gridCenter;
    private boolean stuck = false;
    private int stuckTurnCount = 0;
    
    public LangtonsAntTuringMachine(int rows, int cols, int size){
        super(rows,cols,size);
        
        row = rows / 2;
        column = cols / 2;
        direction = 0;
        gridCenter = cellGridPanel.getCellByPosition(row, column);
        gridCenter.mark();
        previousLocation = gridCenter;
        title = "Langston's Ant";
        
    }
    
    public LangtonsAntTuringMachine(){
        super();
        title = "Langston's Ant";
    }
    
    @Override
    public void update(){
        flipCell();
        moveAnt();
    }
    
    private void flipCell(){
        Cell movingFrom = cellGridPanel.getCellByPosition(row, column);
        previousLocation = movingFrom;
        
        if(movingFrom.isOff()){
            direction = (direction + 1) % 4;
            movingFrom.turnOn();
        }else{
            direction = (direction + 3) % 4;
            movingFrom.turnOff();
        }
    }
    
    private void moveAnt(){
        
        switch (direction) {
            case 0 -> row -= 1;
            case 1 -> column += 1;
            case 2 -> row += 1;
            case 3 -> column -= 1;
        }
        
        row = Math.min(Math.max(row, 0), cellGridPanel.getNumOfRows() - 1);
        column = Math.min(Math.max(column, 0), cellGridPanel.getNumOfColumns() - 1);
        
        Cell movingTo = cellGridPanel.getCellByPosition(row, column);
        
        stuck = (movingTo == previousLocation);
        
        //main logic for getting the ant unstuck
        if(isStuck())
            stuckTurnCount++;
        else
            stuckTurnCount = 0;
        
        //if the ant can't move for 8 consecutive turns he is definitely stuck
        if(stuckTurnCount > 8)
            resetToCenter();
        else
            movingTo.mark();
    }
    
    public boolean isStuck() {
        return stuck;
    }
    
    public void resetToCenter(){
        row = gridCenter.getRow();
        column = gridCenter.getColumn();
        direction = 0;
        gridCenter.mark();
        stuck = false;
    }
}
