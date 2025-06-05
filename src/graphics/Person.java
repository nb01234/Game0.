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

public class Person {
    private int x, y;
    private String name;
    private int age;
    private PApplet app;
    private PImage image;
    
    public Person(PApplet p, int x, int y, String name, int age, String imagePath) {
        this.app = p;
        this.x = x;
        this.y = y;
        this.name = name;
        this.age = age;
        this.image = app.loadImage(imagePath);
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
        // calculates the center of this image
        int centerX = x + (image.pixelWidth / 2);
        int centerY = y + (image.pixelHeight / 2);

        // calculates the center of the other image
        int otherCenterX = other.x + (other.image.pixelWidth / 2);
        int otherCenterY = other.y + (other.image.pixelHeight / 2);

        // calculates the distance between the two center points
        float d = PApplet.dist(otherCenterX, otherCenterY, centerX, centerY);

        // returns true if the distance between the 2 center points is
        // less than 32 pixels
        return d < 32;
    }

    public boolean isClicked(int mouseX, int mouseY) {
        // calculates distance from mouse click at mouseX and mouseY to center
        // of image since (x,y) of image is positioned at the top left corner
        // we use x+(image.pixelWidth/2), y+(image.pixelHeight/2)) to get center
        int centerX = x + (image.pixelWidth / 2);
        int centerY = y + (image.pixelHeight / 2);
        float d = PApplet.dist(mouseX, mouseY, centerX, centerY);

        // gives us the dimensions of the image 32px by 32px
        System.out.println("image height=" + image.pixelHeight);
        System.out.println("image width=" + image.pixelWidth);

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
    
    public void draw() {
        app.image(image, x, y);
    }
}
