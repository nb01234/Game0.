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
* Creates a person object that can be controlled by the user,
* be rendered on a PApplet
* and detect collisions with other person objects.
*/

public class Person {
    private int x, y;
    private String name;
    private int age;
    private int width, height;
    private PApplet app;
    private PImage image;
    
    /**
    * Constructs a Person object with specified attributes.
    *
    * @param p the PApplet to be drawn on
    * @param x the person's initial x-coordinate
    * @param y the person's initial y-coordinate
    * @param name the person's name
    * @param age the person's age
    * @param imagePath the path to the image of the person
    */
    public Person(PApplet p, int x, int y, String name, int age, String imagePath) {
        this.app = p;
        this.x = x;
        this.y = y;
        this.name = name;
        this.age = age;
        this.image = app.loadImage(imagePath);
        this.width = image.width;
        this.height = image.height;
    }
    
    /**
    * Constructs a Person object without an age.
    *
    * @param p the PApplet to be drawn on
    * @param x the person's initial x-coordinate
    * @param y the person's initial y-coordinate
    * @param name the person's name
    * @param imagePath the path to the png of the person
    */
    public Person(PApplet p, int x, int y, String name, String imagePath) {
        this.app = p;
        this.x = x;
        this.y = y;
        this.name = name;
        this.image = app.loadImage(imagePath);
        this.width = image.width;
        this.height = image.height;
    }
    
    /**
    * Moves the person in x and y directions.
    *
    * @param dx the amount to move in the x direction
    * @param dy the amount to move in the y direction
    */
    public void move(int dx, int dy) {
        y += dy;
        x += dx;
    }
    
    /**
    * Moves the person to a specified coordinate.
    *
    * @param dx the new x-coordinate
    * @param dy the new y-coordinate
    */
    public void moveTo(int dx, int dy) {
        x=dx;
        y=dy;
    }
    
    /**
    * Sets the person's name.
    *
    * @param name the name to be set.
    */
    public void setName(String name) {
        this.name = name;
    }
    
    /**
    * Gets the x-coordinate of the person.
    *
    * @return the x-coordinate
    */
    public int x() {
        return x;
    }
    
    /**
    * Gets the y-coordinate of the person.
    *
    * @return the y-coordinate
    */
    public int y() {
        return y;
    }
    
    /**
    * Checks if this person is colliding with another person.
    *
    * @param other the person to check for collision
    * @return true if the person is colliding, false otherwise
    */
    public boolean isCollidingWith(Person other) {
       // Check if the bounding boxes of the two persons intersect
       boolean isLeftOfOtherRight = x < other.x() + other.width;
       boolean isRightOfOtherLeft = x + width > other.x;
       boolean isAboveOtherBottom = y < other.y() + other.height;
       boolean isBelowOtherTop = y + height > other.y;

       return isLeftOfOtherRight && isRightOfOtherLeft 
              && isAboveOtherBottom && isBelowOtherTop;
    }
    
    /**
    * Checks if this arrow is colliding with a wall/hitbox.
    *
    * @param other the hitbox to check for collision
    * @return true if the person is colliding with the box, false otherwise
    */
    public boolean isCollidingWith(Wall other) {
       // Check if the bounding boxes of the two persons intersect
       boolean isLeftOfOtherRight = x < other.x() + other.width();
       boolean isRightOfOtherLeft = x + width > other.x();
       boolean isAboveOtherBottom = y < other.y() + other.height();
       boolean isBelowOtherTop = y + height > other.y();

       return isLeftOfOtherRight && isRightOfOtherLeft 
              && isAboveOtherBottom && isBelowOtherTop;
    }

    /**
    * Checks if the person was clicked by the mouse.
    *
    * @param mouseX the x-coordinate of the mouse
    * @param mouseY the y-coordinate of the mouse
    * @return true if the click was within 16 pixels of the person, false otherwise
    */
    public boolean isClicked(int mouseX, int mouseY) {
        // calculates distance from mouse click to center
        // of image since (x,y) of image is positioned at the top left corner
        // we use x+(image.pixelWidth/2), y+(image.pixelHeight/2)) to get center
        int centerX = x + (image.pixelWidth / 2);
        int centerY = y + (image.pixelHeight / 2);
        float d = PApplet.dist(mouseX, mouseY, centerX, centerY);

        // returns true if mouse clicked is within 16px from the center of image
        // we use 16px because the image is 32px by 32px
        return d < 16;
    }   

    /**
     * Displays the person's name and age above their head.
     *
     * @param p the PApplet used to draw
     */
    public void displayInfo(PApplet p) {
        app.fill(0); // set the fill color to black
        // display the name and age above the person's position
        app.text("Name: " + name, x, y - 50);
        app.text("Age: " + age, x, y - 30);
    }
    
    /**
     * Restricts the user's movement based on the current level.
     *
     * @param stage the stage determining the bounds
     */
    public void moveConstraint(int stage) {
        switch (stage) {
            case 1:
                x = constrain(x, 40, app.width - 90);
                y = constrain(y, 70, app.height - 110);
                break;
            case 2:
                x = constrain(x, 0, app.width - 16);
                y = constrain(y, 300, app.height - 20);
                break;
            case 3:
                x = constrain(x, 20, app.width - 10);
                y = constrain(y, 166, app.height-20);
                break;
            case 4:
                x = constrain(x, 20, app.width - 20);
                y = constrain(y, 270, app.height - 100);
                break;
            case 5:
                x = constrain(x, 80, app.width - 130);
                y = constrain(y, 166, app.height-20);
                break;
        }
        
    }
    
    /**
     * Draws the person on the PApplet.
     */
    public void draw() {
        app.image(image, x, y);
    }
}
