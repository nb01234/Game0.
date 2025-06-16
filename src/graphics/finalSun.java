/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package graphics;

import processing.core.PApplet;
import processing.core.PImage;

/**
 * Creates a special type of Sun with a modified move method
 * that doesn't make it bounce off of or get stuck in walls.
 */
public class finalSun extends Sun{
    
    /**
    * Creates a finalSun object with specified attributes.
    *
    * @param p the PApplet to draw on
    * @param x the initial x-coordinate of the sun
    * @param y the initial y-coordinate of the sun
    * @param imagePath the path to the image of the sun
    */
    public finalSun(PApplet p, int x, int y, String imagePath) {
        super(p, x, y, imagePath);
    }
    
    /**
    * Overrides the move method so that it doesn't get stuck in a wall.
    *
    * @param dx the amount to move in the x direction
    * @param dy the amount to move in the y direction
    */
    @Override
    public void move(int dx, int dy) {
        int newX = x() + dx;
        int newY = y() + dy;
        moveTo(newX, newY);
    }
    
    /**
    * Moves the object left by 1 pixel.
    */
    public void decreaseX() {
        move(-1, 0);
    }
}
