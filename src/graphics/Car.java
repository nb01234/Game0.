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

public class Car {
    private int x;
    private int y;
    private PApplet app;
    private int speed;
    private PImage image;
    
    public Car(PApplet p, int x, int y, int speed, String imagePath) {
        this.app = p;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.image = app.loadImage(imagePath);
    }
    
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    public int getSpeed() {
        return speed;
    }
    
    public void move(int dx, int dy) {
        y += dy;
        x += dx;
    }
        
    public void moveTo(int dx, int dy) {
        x=dx;
        y=dy;
    }
    
    public void draw() {
        app.image(image, x, y);
    }
}
