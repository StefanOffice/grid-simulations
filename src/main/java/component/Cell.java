package main.java.component;

import java.awt.*;

public class Cell {
    
    private static final int DEFAULT_STATE = 0;
    // double pink is by design
    // state 2 marks that cell is occupied by ant and it's off
    // state 3 marks that cell is occupied by ant and it's on
    public static final Color[] STATE_COLORS = {Color.DARK_GRAY, Color.GREEN, Color.PINK, Color.PINK};
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
        //fill color a bit smaller than cell size to see the grid around the cell
        //without messing with cells x and y coordinates
        graphics.fillRect(x + 1, y + 1, size - 1, size -1);
    }
    
    public void mark(){
        if(state == 1)
            state = 3;
        if(state == 0)
            state = 2;
    }
    
    public void clear(){
        state = DEFAULT_STATE;
    }
    
    public boolean isOn(){
        return state == 1 || state == 3;
    }
    
    public boolean isOff(){
        return state == 0 || state == 2;
    }
    
    public void switchOnOff(){
        if(isOn())
            turnOff();
        else
            turnOn();
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
    
    public int getState(){
        return state;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public int getSize() {
        return size;
    }
    
}
