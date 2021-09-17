package de.moooooooe.main.ConwayUtils;

import de.moooooooe.main.GUI.GUIFrame;

import java.awt.*;
import java.util.Random;

/**
 * Rules for Conway's Game of Life:
 *  - 0 living neighbours: Cell dies
 *  - 2 living neighbours: Alive cell stays alive, dead cell stays dead
 *  - 3 living neighbours: Alive cell stays alive, dead cell becomes alive
 *  - >3 living neighbours: Cell dies
 */
public class Simulation {

    private static Cell[][] cells;
    private static final int WIDTH = GUIFrame.getWIDTH()/Cell.CELL_SIZE;
    private static final int HEIGHT = GUIFrame.getHEIGHT()/Cell.CELL_SIZE;
    private static int generation;

    static Random random; // For randomly determining where to set living cells

    public Simulation() {
        random = new Random();
        initSimulation();
    }

    public static void initSimulation() {
        cells = new Cell[WIDTH][HEIGHT];
        generation = 0;
        for(int x = 0; x < WIDTH; x++) {
            for(int y = 0; y < HEIGHT; y++) {
                cells[x][y] = new Cell(x,y);
                cells[x][y].setAlive(random.nextBoolean());
            }
        }
    }

    /**
     * Updates the cells according to the rules of "Conway's Game of Life".
     */
    public void update() {
        generation++;
        for(int x = 0; x < WIDTH; x++) {
            for(int y = 0; y < HEIGHT; y++) {
                int mx = x-1 < 0 ? WIDTH-1 : x-1; // Check if we are out of bounds.
                int my = y-1 < 0 ? HEIGHT-1 : y-1;
                int gx = (x+1) % WIDTH;
                int gy = (y+1) % HEIGHT;

                int aliveCount = 0;
                if(cells[mx][my].isAlive()) aliveCount++;
                if(cells[mx][y].isAlive()) aliveCount++;
                if(cells[mx][gy].isAlive()) aliveCount++;
                if(cells[x][my].isAlive()) aliveCount++;
                if(cells[x][gy].isAlive()) aliveCount++;
                if(cells[gx][my].isAlive()) aliveCount++;
                if(cells[gx][y].isAlive()) aliveCount++;
                if(cells[gx][gy].isAlive()) aliveCount++;
                if(aliveCount < 2 || aliveCount > 3) {
                    cells[x][y].setNextRound(false);
                } else if(aliveCount == 3) {
                    cells[x][y].setNextRound(true);
                }
            }
        }

        for(int x = 0; x < WIDTH; x++) {
            for(int y = 0; y < HEIGHT; y++) {
                cells[x][y].nextRound();
            }
        }
    }

    public void draw(Graphics g) {
        for(int x = 0; x < WIDTH; x++) {
            for(int y = 0; y < HEIGHT; y++) {
                cells[x][y].draw(g);
            }
        }
    }

    public static Cell[][] getCells() {
        return cells;
    }

    public static int getGeneration() {
        return generation;
    }
}
