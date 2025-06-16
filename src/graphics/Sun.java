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
import static processing.core.PApplet.constrain;
import processing.core.PImage;

/**
* Creates a sun object that can move, be drawn on a PApplet
* and detect collisions with an arrow object.
*/

public class Sun {
    
    //attributes
    private int x, y;
    private int width, height;
    private PApplet app;
    private PImage image;
    private int speed;
    private int direction;
    private boolean show = true;
    private static int numOfSuns = 9;
    
    /**
    * Constructs a Sun object with default speed and direction.
    *
    * @param p the PApplet used to draw the object
    * @param x the initial x-coordinate of the sun
    * @param y the initial y-coordinate of the sun
    * @param imagePath the path to the png image of the sun
    */
    public Sun(PApplet p, int x, int y, String imagePath) {
        this.app = p;
        this.x = x;
        this.y = y;
        this.image = app.loadImage(imagePath);
        this.width = image.width;
        this.height = image.height;
        this.speed = 3;
        this.direction = 1;
    }
    
    /**
    * Constructs a Sun object based on parameters.
    *
    * @param p the PApplet used to draw the sun
    * @param x the initial x-coordinate of the sun
    * @param y the initial y-coordinate of the sun
    * @param imagePath the path to the .png image of the sun
    * @param speed the speed of the movement
    * @param direction the direction of movement (1 for normal, -1 for reversed)
    */
    public Sun(PApplet p, int x, int y, String imagePath, int speed, int direction) {
        this.app = p;
        this.x = x;
        this.y = y;
        this.image = app.loadImage(imagePath);
        this.width = image.width;
        this.height = image.height;
        this.speed = speed;
        this.direction = direction;
    }
    
    /**
    * Moves the sun based on x and y values and direction
    * If the sun hits an edge, its direction flips.
    *
    * @param dx the horizontal movement
    * @param dy the vertical movement
    */
    public void move(int dx, int dy) {
            x += dx * direction;
            y += dy * direction;
            // flip direction if sun hits the wall
            if (x > app.width - width || x < 0) 
                direction *= -1;
    }       
    
    /**
    * Moves the sun to a specified coordinate.
    *
    * @param dx the new x-coordinate
    * @param dy the new y-coordinate
    */
    public void moveTo(int dx, int dy) {
        x=dx;
        y=dy;
    }
    
    /**
    * Gets the x-coordinate of the sun.
    *
    * @return the x-coordinate
    */
    public int x() {
        return x;
    }
    
    /**
    * Gets the y-coordinate of the sun.
    *
    * @return the y-coordinate
    */
    public int y() {
        return y;
    }
    
    /**
    * Gets the width of the sun.
    *
    * @return the width
    */
    public int width() {
        return width;
    }
    
    /**
    * Gets the height of the sun.
    *
    * @return the height
    */
    public int height() {
        return height;
    }
    
    /**
    * Hides the sun by setting the show variable to false.
    */
    public void hide() {
        this.show = false;
    }
    
    /**
    * Gets the number of sun objects remaining.
    *
    * @return the current number of sun objects
    */
    public int getNumSuns() {
        return numOfSuns;
    }
    
    /**
    * Decreases the number of sun objects by 1.
    */
    public void decreaseNumSuns() {
        numOfSuns--;
    }
    
    /**
    * Checks if this sun is colliding with an arrow object.
    *
    * @param other the arrow object to check for collision
    * @return true if the sun is colliding with the arrow, false otherwise
    */
    public boolean isCollidingWith(Arrow other) {
       // Check if the bounding boxes of the two persons intersect
       boolean isLeftOfOtherRight = x < other.x() + other.width();
       boolean isRightOfOtherLeft = x + width > other.x();
       boolean isAboveOtherBottom = y < other.y() + other.height();
       boolean isBelowOtherTop = y + height > other.y();

       return isLeftOfOtherRight && isRightOfOtherLeft 
              && isAboveOtherBottom && isBelowOtherTop;
    }
    
    /**
    * Draws the sun on the PApplet.
    */
    public void draw() {
        if (show == true) {
            app.image(image, x, y);
        }
    }
}
