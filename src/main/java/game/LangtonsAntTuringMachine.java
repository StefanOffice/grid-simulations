package main.java.game;

import main.java.component.Cell;

public class LangtonsAntTuringMachine extends AbstractGridGame {
    
    private int row;
    private int column;
    private int direction; //0-N , 1-E, 2-S, 3-W
    
    //modification to the classing langton's ant
    //in my version when the ant gets stuck and can't move
    //for more than 8 rounds it gets reset back to center
    private Cell previousLocation;
    private Cell gridCenterCell;
    private boolean stuck = false;
    private int stuckTurnCount = 0;
    
    public LangtonsAntTuringMachine(int rows, int cols, int size){
        super(rows,cols,size);
        
        row = cellGridPanel.getNumOfRows() / 2;
        column = cellGridPanel.getNumOfColumns() / 2;
        direction = 0;
        //keeping this cell as a instance variable for resetting the ant
        gridCenterCell = cellGridPanel.getCell(row, column);
        gridCenterCell.mark();
    
        //for keeping track if the ant is stuck
        previousLocation = gridCenterCell;
        title = "Langston's Ant";
        
    }
    
    public LangtonsAntTuringMachine(){
        this(AbstractGridGame.DEFAULT_ROWS, AbstractGridGame.DEFAULT_COLUMNS, AbstractGridGame.DEFAULT_CELL_SIZE);
    }
    
    @Override
    public void update(){
        flipCell();
        moveAnt();
    }
    
    private void flipCell(){
        Cell movingFrom = cellGridPanel.getCell(row, column);
        previousLocation = movingFrom;
        
        //fulfill the base condition
        if(movingFrom.isOff()){
            //if the cell the ant is moving from was off, turn right
            direction = (direction + 1) % 4;
            movingFrom.turnOn();
        }else{
            //if the cell the ant is moving from was on, turn left
            direction = (direction + 3) % 4;
            movingFrom.turnOff();
        }
    }
    
    private void moveAnt(){
        
        //update ant's position based on the direction he is moving towards
        //remember //0-N , 1-E, 2-S, 3-W
        switch (direction) {
            case 0 -> row -= 1;
            case 1 -> column += 1;
            case 2 -> row += 1;
            case 3 -> column -= 1;
        }
        
        //keep the ant from going off the screen
        row = Math.min(Math.max(row, 0), cellGridPanel.getNumOfRows() - 1);
        column = Math.min(Math.max(column, 0), cellGridPanel.getNumOfColumns() - 1);
        
        Cell movingTo = cellGridPanel.getCell(row, column);
    
        //my variation of the langton's ant
        // includes resetting to center when the ant is stuck for too long
        //this is the main logic behind it.
        stuck = (movingTo == previousLocation);
        
        if(isStuck())
            stuckTurnCount++;
        else
            stuckTurnCount = 0;
        
        if(stuckTurnCount > 8)
            resetToCenter();
        else
            movingTo.mark();
    }
    
    public boolean isStuck() {
        return stuck;
    }
    
    public void resetToCenter(){
        //we memorized gridCenterCell at game initialization(constructor)
        row = gridCenterCell.getRow();
        column = gridCenterCell.getColumn();
        direction = 0;
        gridCenterCell.mark();
        stuck = false;
    }
}
