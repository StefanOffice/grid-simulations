# Grid Simulations

Hello, welcome. :smile:  
In this project i will try to demonstrate how a simulation program can be built using java.  
You can find my implementations of two popular simulation programs here:
+ Conway's game of life
+ Langton's ant

The goal of this project is also to demonstrate code re-use as both of these programs are build with the same base (AbstractGridGame), use the same components (Cell, CellGridPanel) and are displayed using the same GameView which takes the specified game as its constructor param. 

## Conway's game of life

### Introduction


_The Game of Life, is a cellular automaton devised by the British mathematician John Horton Conway in 1970.   
It is a zero-player game, meaning that its evolution is determined by its initial state, requiring no further input._(Although in this implementation further input can be provided to change the state of any of the cells in the grid)  
_You can interact with the Game of Life by creating an initial configuration and observing how it evolves._  

I encourage you to explore the [patterns](https://www.google.com/search?q=conway%27s+game+of+life+patterns&hl=en-US&source=lnms&tbm=isch&sa=X&ved=2ahUKEwjHjJyalqTyAhWUuosKHQMVA8IQ_AUoAXoECAEQAw&biw=1858&bih=967) before running the simulation.



### Rules

Playfield of the game is two-dimensional orthogonal grid of square cells.  
Each of those cells can be 'live'(green) or 'dead'(default) (or populated and unpopulated, respectively).  
Each cell also interacts with its eight neighbours ( the cells that are horizontally, vertically, or diagonally adjacent.)  
At each step(tick) in the simulation, the following transitions occur:   
+ For a space that is populated:

        Each cell with one or no neighbors dies, as if by solitude.

        Each cell with four or more neighbors dies, as if by overpopulation.

        Each cell with two or three neighbors survives.
+ For a space that is empty or unpopulated

        Each cell with three neighbors becomes populated.
    
## Langton's ant

### Introduction

In this project you can also find my implementation of the Langton's ant simulation.

_Langton's ant is a two-dimensional universal Turing machine with a very simple set of rules but complex emergent behavior.  
It was invented by Chris Langton in 1986 and runs on a grid of cells.   
The idea has been generalized in several different ways, such as turmites which add more colors and more states._   
-from [wikipedia](https://en.wikipedia.org/wiki/Langton%27s_ant)

### Rules

Squares on a plane are colored variously either dark gray or green. The pink square represents the "ant".   
The "ant" moves according to the following rules:

+ At a dark gray square, turn 90° clockwise, flip the color of the square, move forward one unit
+ At a green square, turn 90° counter-clockwise, flip the color of the square, move forward one unit


## Setup (for both)
After cloning the repo, open it with you favorite IDE  
In the main.java.launcher package is the simple Launcher.java class that is in charge of starting up the simulation.

```java
public static void main(String[] args) {
        AbstractGridGame conway = new ConwayGameOfLife(30,30,40);
        AbstractGridGame langton = new LangtonsAntTuringMachine(20,20,30);
        GameView view = new GameView(conway, 100);
    }
```

Size of the grid panel is specified in the constructor of the desired simulation. 
```java
public ConwayGameOfLife(int numRows, int numColumns, int cellSize){
        super(numRows, numColumns, cellSize);
        title = "Conway's Game of Life";
    }
```

Pass the required arguments(number of rows, number of columns, and the size of the cell) to the game's constructor and then pass that game to the GameView constructor.  
Second parameter in GameView constructor is the delay between game steps in miliseconds, the smaller the delay, the faster the simulation will run. However i do recommend to keep this at atleast 70.  

After setting up, run the launcher.  

Once the grid is shown, click on any cell to turn it on/off and once you're ready, click step or play to advance the simulation.   
If you want to switch any additional cells on or off at any point i recommend that you pause the simulation first, using the pause button, before clicking on any of the cells.

## Thank you!

Thank you for checking out my project, i hope it has served you well, and that you found it interesting in some way. :wink:

***All the best,  
Stefan Stefanovic***
