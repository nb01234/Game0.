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

public class Sun {
    private int x, y;
    private int age;
    private int width, height;
    private PApplet app;
    private PImage image;
    
    public Sun(PApplet p, int x, int y, int age, String imagePath) {
        this.app = p;
        this.x = x;
        this.y = y;
        this.age = age;
        this.image = app.loadImage(imagePath);
        this.width = image.width;
        this.height = image.height;
    }
    
    public Sun(PApplet p, int x, int y, String imagePath) {
        this.app = p;
        this.x = x;
        this.y = y;
        this.image = app.loadImage(imagePath);
        this.width = image.width;
        this.height = image.height;
    }
    
    public void move(int dx, int dy) {
        y += dy;
        x += dx;
    }
    
    public void moveTo(int dx, int dy) {
        x=dx;
        y=dy;
    }
    
    public int x() {
        return x;
    }
    
    public int y() {
        return y;
    }
    
    public int width() {
        return width;
    }
    
    public int height() {
        return height;
    }
    
    public boolean isCollidingWith(Sun other) {
       // Check if the bounding boxes of the two persons intersect
       boolean isLeftOfOtherRight = x < other.x() + other.width;
       boolean isRightOfOtherLeft = x + width > other.x;
       boolean isAboveOtherBottom = y < other.y() + other.height;
       boolean isBelowOtherTop = y + height > other.y;

       return isLeftOfOtherRight && isRightOfOtherLeft 
              && isAboveOtherBottom && isBelowOtherTop;
    }
    
    public void moveConstraint(int stage) {
        switch (stage) {
            case 1:
                x = constrain(x, 40, app.width - 80);
                y = constrain(y, 80, app.height - 90);
                break;
            case 2:
                x = constrain(x, 0, app.width - 16);
                y = constrain(y, 320, app.height - 20);
                break;
            case 3:
                x = constrain(x, 20, app.width - 10);
                y = constrain(y, 20, app.height-20);
                break;
        }
        
    }
    
    public void draw() {
        app.image(image, x, y);
    }
}
