import java.awt.*;

public class Cell {
    
    public static final Color[] STATE_COLORS = {Color.DARK_GRAY, Color.GREEN};
    private final int x;
    private final int y;
    private final int size;
    private int state;
    
    public Cell(int x, int y, int size){
        this.x = x;
        this.y = y;
        this.size = size;
        state = 0;
    }
    
    
    public boolean isOn(){
        return state == 1;
    }
    
    public boolean isOff(){
        return state == 0;
    }
    
    public void turnOff(){
        state = 0;
    }
    
    public void turnOn(){
        state = 1;
    }
}
