/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphics;

/**
 *
 * @author 342628146
 */

import processing.core.PApplet;
import processing.core.PImage;

/**
* Creates a hitbox that lets the user advance levels.
*/

public class Wall {
    private int x, y;
    private int width, height;
    private PApplet app;
    private PImage image;
    
    /**
    * Constructs a wall object.
    *
    * @param p the PApplet to draw the box on
    * @param x the arrow's initial x-coordinate
    * @param y the arrow's initial y-coordinate
    * @param width the width of the box
    * @param height the height of the box
    */
    public Wall(PApplet p, int x, int y, int width, int height) {
        this.app = p;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }
    
    /**
    * Moves the box in x and y directions.
    *
    * @param dx the amount to move in x direction
    * @param dy the amount to move in y direction
    */
    public void move(int dx, int dy) {
        y += dy;
        x += dx;
    }
    
    /**
    * Moves the wall to a specified coordinate.
    *
    * @param dx the new x-coordinate
    * @param dy the new y-coordinate
    */
    public void moveTo(int dx, int dy) {
        x=dx;
        y=dy;
    }
    
    /**
    * Gets the x-coordinate of the wall.
    *
    * @return the x-coordinate
    */
    public int x() {
        return x;
    }
    
    /**
    * Gets the y-coordinate of the wall.
    *
    * @return the y-coordinate
    */
    public int y() {
        return y;
    }
    
    /**
    * Gets the width of the wall.
    *
    * @return the width
    */
    public int width() {
        return width;
    }
    
    /**
    * Gets the height of the wall.
    *
    * @return the height
    */
    public int height() {
        return height;
    }
    
    /**
    * Colors the wall black and draws it on the PApplet.
    */
    public void draw() {
        app.fill(0);
        app.rect(x, y, width, height);
    }
}
