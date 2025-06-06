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

public class Wall {
    private int x, y;
    private int width, height;
    private PApplet app;
    private PImage image;
    
    public Wall(PApplet p, int x, int y, int width, int height) {
        this.app = p;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
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

        // returns true if mouse clicked is within 16px from the center of image
        // we use 16px because the image is 32px by 32px
        return d < 16;
    }   
    
    public void draw() {
        app.fill(0);
        app.rect(x, y, width, height);
    }
}
