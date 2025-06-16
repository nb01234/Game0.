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
* Creates an arrow object that can move, be drawn on a PApplet
* and detect collisions with a Sun object.
*/

public class Arrow {
    private int x, y;
    private int width, height;
    private PApplet app;
    private PImage image;
    
    /**
    * Constructs an Arrow object.
    *
    * @param p the PApplet to draw the arrow on
    * @param x the arrow's initial x-coordinate
    * @param y the arrow's initial y-coordinate
    * @param imagePath the path to the png image of the arrow
    */
    public Arrow(PApplet p, int x, int y, String imagePath) {
        this.app = p;
        this.x = x;
        this.y = y;
        this.image = app.loadImage(imagePath);
        this.width = image.width;
        this.height = image.height;
    }
    
    /**
    * Moves the arrow in x and y directions.
    *
    * @param dx the amount to move in x direction
    * @param dy the amount to move in y direction
    */
    public void move(int dx, int dy) {
        y += dy;
        x += dx;
    }
    
    /**
    * Moves the arrow to a specified coordinate.
    *
    * @param dx the new x-coordinate
    * @param dy the new y-coordinate
    */
    public void moveTo(int dx, int dy) {
        x=dx;
        y=dy;
    }
    
    /**
    * Gets the x-coordinate of the arrow.
    *
    * @return the x-coordinate
    */
    public int x() {
        return x;
    }
    
    /**
    * Gets the y-coordinate of the arrow.
    *
    * @return the y-coordinate
    */
    public int y() {
        return y;
    }
    
    /**
    * Sets the x-coordinate of the arrow.
    *
    * @param x the new x-coordinate
    */
    public void setX(int x) {
        this.x = x;
    }
    
    /**
    * Sets the y-coordinate of the arrow.
    *
    * @param y the new y-coordinate
    */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
    * Gets the width of the arrow.
    *
    * @return the width
    */
    public int width() {
        return width;
    }
    
    /**
    * Gets the height of the arrow.
    *
    * @return the height
    */
    public int height() {
        return height;
    }
    
    /**
    * Checks if this arrow is colliding with a Sun object.
    *
    * @param other the Sun object to check for collision
    * @return true if the arrow is colliding with the Sun, false otherwise
    */
    public boolean isCollidingWith(Sun other) {
       // Check if the bounding boxes of the two persons intersect
       boolean isLeftOfOtherRight = x < other.x() + other.width();
       boolean isRightOfOtherLeft = x + width > other.x();
       boolean isAboveOtherBottom = y < other.y() + other.height();
       boolean isBelowOtherTop = y + height > other.y();

       return isLeftOfOtherRight && isRightOfOtherLeft 
              && isAboveOtherBottom && isBelowOtherTop;
    }
    
    /**
    * Draws the arrow on the PApplet.
    */
    public void draw() {
        app.image(image, x, y);
    }
}
