package de.moooooooe.main.ConwayUtils;

import java.awt.*;

public class Cell {

    private int x;
    private int y;
    private boolean alive;
    private boolean nextRound;
    public static final int CELL_SIZE = 10;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.drawRect(x*CELL_SIZE, y*CELL_SIZE, CELL_SIZE, CELL_SIZE);

        if(alive) {
            g.setColor(Color.black);
        } else {
            g.setColor(Color.white);
        }
        g.fillRect(x*CELL_SIZE+1, y*CELL_SIZE+1, CELL_SIZE-1, CELL_SIZE-1);
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void setNextRound(boolean nextRound) {
        this.nextRound = nextRound;
    }

    public void nextRound() {
        alive = nextRound;
    }
}