

public class ConwayGameOfLife {
    public CellGridPanel cellGridPanel;
    
    public ConwayGameOfLife(){
        //hard-coding sample cells to test rendering
        cellGridPanel = new CellGridPanel(9, 7, 30);
        
        cellGridPanel.getCell(5,1).turnOn();
        cellGridPanel.getCell(6,1).turnOn();
        cellGridPanel.getCell(7,1).turnOn();
        
        cellGridPanel.getCell(1,4).turnOn();
        cellGridPanel.getCell(2,4).turnOn();
        cellGridPanel.getCell(3,4).turnOn();
    }
    
}
