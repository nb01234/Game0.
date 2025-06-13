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
    private int width, height;
    private PApplet app;
    private PImage image;
    private int speed;
    private int direction;
    private boolean show = true;
    
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
    
    public void move(int dx, int dy) {
            x += dx * direction;
            y += dy * direction;
            // flip direction if sun hits the wall
            if (x > app.width - width || x < 0) 
                direction *= -1;
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
    
    public void hide() {
        this.show = false;
    }
    
    public boolean isCollidingWith(Arrow other) {
       // Check if the bounding boxes of the two persons intersect
       boolean isLeftOfOtherRight = x < other.x() + other.width();
       boolean isRightOfOtherLeft = x + width > other.x();
       boolean isAboveOtherBottom = y < other.y() + other.height();
       boolean isBelowOtherTop = y + height > other.y();

       return isLeftOfOtherRight && isRightOfOtherLeft 
              && isAboveOtherBottom && isBelowOtherTop;
    }
    
    public void moveConstraint(int stage) {
        switch (stage) {
            case 5:
                x = constrain(x, 20, app.width - 10);
                y = constrain(y, 20, app.height - 20);
                break;
        }
    }
    
    public void draw() {
        if (show == true) {
            app.image(image, x, y);
        }
    }
}
