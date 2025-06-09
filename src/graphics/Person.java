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

public class Person {
    private int x, y;
    private String name;
    private int age;
    private int width, height;
    private PApplet app;
    private PImage image;
    
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
    
    public Person(PApplet p, int x, int y, String name, String imagePath) {
        this.app = p;
        this.x = x;
        this.y = y;
        this.name = name;
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
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int x() {
        return x;
    }
    
    public int y() {
        return y;
    }
    
    public boolean isCollidingWith(Person other) {
       // Check if the bounding boxes of the two persons intersect
       boolean isLeftOfOtherRight = x < other.x() + other.width;
       boolean isRightOfOtherLeft = x + width > other.x;
       boolean isAboveOtherBottom = y < other.y() + other.height;
       boolean isBelowOtherTop = y + height > other.y;

       return isLeftOfOtherRight && isRightOfOtherLeft 
              && isAboveOtherBottom && isBelowOtherTop;
    }
    
    public boolean isCollidingWith(Wall other) {
       // Check if the bounding boxes of the two persons intersect
       boolean isLeftOfOtherRight = x < other.x() + other.width();
       boolean isRightOfOtherLeft = x + width > other.x();
       boolean isAboveOtherBottom = y < other.y() + other.height();
       boolean isBelowOtherTop = y + height > other.y();

       return isLeftOfOtherRight && isRightOfOtherLeft 
              && isAboveOtherBottom && isBelowOtherTop;
    }

    public boolean isClicked(int mouseX, int mouseY) {
        // calculates distance from mouse click to center
        // of image since (x,y) of image is positioned at the top left corner
        // we use x+(image.pixelWidth/2), y+(image.pixelHeight/2)) to get center
        int centerX = x + (image.pixelWidth / 2);
        int centerY = y + (image.pixelHeight / 2);
        float d = PApplet.dist(mouseX, mouseY, centerX, centerY);

        // gives us the dimensions of the image 32px by 32px
        System.out.println("image height=" + image.pixelHeight);
        System.out.println("image width=" + image.pixelWidth);
        
        // give x and y value
        System.out.println(x);
        System.out.println(y);

        // returns true if mouse clicked is within 16px from the center of image
        // we use 16px because the image is 32px by 32px
        return d < 16;
    }   

    public void displayInfo(PApplet p) {
        app.fill(0); // set the fill color to black
        // display the name and age above the person's position
        app.text("Name: " + name, x, y - 50);
        app.text("Age: " + age, x, y - 30);
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
        }
        
    }
    
    public void draw() {
        app.image(image, x, y);
    }
}
