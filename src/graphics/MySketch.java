package graphics;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import processing.core.PApplet;
import processing.core.PImage;

public class MySketch extends PApplet {
    
    private Person char1;
    private Person char2;
    String userInput = "";
    public boolean showInfo;
    int stage = 0;
    PImage backgroundImage;
    
    // movement variables
    boolean upPressed = false;
    boolean downPressed = false;
    boolean leftPressed = false;
    boolean rightPressed = false;

    
    public void settings(){
	//sets the size of the window
        size (400,400);
    }
    
    public void setup(){
	//sets the background colour using R,G,B
        background(2, 50, 5);
        textSize(20);
        char1 = new Person(this, 200, 200, "Mr. Lu", 99, "images/person.png");
        char2 = new Person(this, 100, 200, "Mr. Loo", 77, "images/person.png");
        
        backgroundImage = loadImage("images/stage1.png");
    }
    
    public void draw() {
        background(200);
        
        if (stage == 0) {
            //display entered text
            fill(0);
            text("You are dead.", 20, 30);
            text("Enter your name:", 20, 50);
            text(userInput, 20, 100);
        } else {
            // enable directional movement as long as stage isn't 0
            int dx = 0;
            int dy = 0;

            if (leftPressed) dx -= 2;
            if (rightPressed) dx += 2;
            if (upPressed) dy -= 2;
            if (downPressed) dy += 2;

            char1.move(dx, dy);
            char1.draw();
        }
        
        if (stage == 1) {
            //load background
            image(backgroundImage, 0, 0);
            
            char1.draw();
            
            // change stage if user leaves the room
            if (char1.y() < 20) {
                stage = 2;
                char1.moveTo(200, 320); // move char to bottom
            }
        }
        
        if (stage == 2) {
        }
            
        if (showInfo) {
            // display the person's info if the showInfo flag is true
            char1.displayInfo(this);
        }
        
        //display char2
        char2.draw();
        
        // if characters are touching
        if (char1.isCollidingWith(char2)) {
            fill(255, 0, 0);
            this.text("ouuch", char2.x(), char2.y());
        }
    }
    
    public void mousePressed() {
        if (char1.isClicked(mouseX, mouseY)) {
            // toggle the showInfo flag when the person is clicked
            showInfo = !showInfo;
        } else {
            // hide the person's info if the mouse is clicked elsewhere
            showInfo = false;
        }
    }
    
    public void keyPressed() {
      if (stage == 0) {
        if (keyCode == ENTER) {
            
          stage = 1; // Move to next stage
          char1.setName(userInput); // set the user's name
          
        } else if (key != CODED) {
            
          userInput += key; // update userInput
          
        }
      } else {
            
        // character movement
        if (keyCode == UP) upPressed = true;
        if (keyCode == DOWN) downPressed = true;
        if (keyCode == LEFT) leftPressed = true;
        if (keyCode == RIGHT) rightPressed = true;
            
      }
    }
    
    public void keyReleased() {
        if (keyCode == UP) upPressed = false;
        if (keyCode == DOWN) downPressed = false;
        if (keyCode == LEFT) leftPressed = false;
        if (keyCode == RIGHT) rightPressed = false;
    }
    
}

