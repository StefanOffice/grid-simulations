package main.java.component;

import java.awt.*;

public class Cell {
    
    // double pink is by design
    // state 2 marks that cell is occupied by ant and it's off
    // state 3 marks that cell is occupied by ant and it's on
    public static final Color[] STATE_COLORS = {Color.DARK_GRAY, Color.GREEN,Color.PINK, Color.PINK};
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
    
    public void draw(Graphics graphics){
        graphics.setColor(STATE_COLORS[state]);
        graphics.fillRect(x + 1, y + 1, size - 1, size -1);
    }
    
    
    public boolean isOn(){
        return state == 1 || state == 3;
    }
    
    public boolean isOff(){
        return state == 0 || state == 2;
    }
    
    public void turnOff(){
        state = 0;
    }
    
    public void turnOn(){
        state = 1;
    }
    
    public int getRow(){
        return y / size;
    }
    
    public int getColumn(){
        return x / size;
    }
    
    public void mark(){
        if(state == 1)
            state = 3;
        if(state == 0)
            state = 2;
    }
}
