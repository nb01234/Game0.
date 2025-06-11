package graphics;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import processing.core.PApplet;
import processing.core.PImage;

public class MySketch extends PApplet {
    
    private Person char1, emperor;
    String userInput = "";
    public boolean showInfo;
    int stage = 0;
    PImage stage1, stage2, stage3, stage4, stage5, stage6, stage7; // stage images
    PImage box1, box2, box3, box4, box4b, box5, box6, box7, box8, box9, box10, box11, box11b, box11c, box11d, box12, box13; //dialog box images
    Wall box;
    int dialogBox = 0;
    boolean mouseHandled = false;
    
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
        emperor = new Person(this, 200, 200, "Emperor", 75, "images/emperor.png");
        
        stage1 = loadImage("images/stage1.png");
        stage2 = loadImage("images/stage2.png");
        stage3 = loadImage("images/stage3.png");
        stage4 = loadImage("images/stage4.png");
//        stage5 = loadImage("images/stage5.png");
//        stage6 = loadImage("images/stage6.png");
//        stage7 = loadImage("images/stage7.png");
//        stage8 = loadImage("images/stage8.png");
//        stage9 = loadImage("images/stage9.png");
//        stage10 = loadImage("images/stage10.png");
//        stage11 = loadImage("images/stage11.png");
        
        box1 = loadImage("images/box1.png");
        box2 = loadImage("images/box2.png");
        box3 = loadImage("images/box3.png");
        box4 = loadImage("images/box4.png");
        box4b = loadImage("images/box4.1.png");
        box5 = loadImage("images/box5.png");
        box6 = loadImage("images/box6.png");
        box7 = loadImage("images/box7.png");
        box8 = loadImage("images/box8.png");
        box9 = loadImage("images/box9.png");
        box10 = loadImage("images/box10.png");
        box11 = loadImage("images/box11.png");
        box11b = loadImage("images/box11.1.png");
        box11c = loadImage("images/box11.2.png");
        box11d = loadImage("images/box11.3.png");
        box12 = loadImage("images/box12.png");
        box13 = loadImage("images/box13.png");
        
    }
    
    public void draw() {
        background(200);
        
        if (stage == 0) {
            //display entered text
            fill(0);
            text("Enter your name:", 20, 50);
            text(userInput, 20, 100);
        } else {
            // directional movement including wall physics
            int dx = 0;
            int dy = 0;

            if (leftPressed) {
                dx -= 2;
            }
            if (rightPressed) {
                dx += 2;
            }
            if (upPressed) {
                dy -= 2;
            }
            if (downPressed) {
                dy += 2;
            }

            char1.move(dx, dy);
            char1.draw();
        }
        
        //////////////////////STAGE 1///////////////////////////////
        if (stage == 1) {
            
            // player boundaries
            char1.moveConstraint(1);
            
            //load background
            image(stage1, 0, 0);
            
            // hitbox to move to next stage
            box = new Wall(this, 200, 100, 10, 10);
            box.draw();
            
            char1.draw();
            
            // change stage if user leaves the room
            if (char1.isCollidingWith(box)) {
                stage = 2;
                char1.moveTo(200, 380); // move char to bottom
            }
        }
        
        //////////////////////STAGE 2//////////////////////////////
        if (stage == 2) {
            
            //load background
            image(stage2, 0, 0);
            
            // player boundaries
            char1.moveConstraint(2);
            
            // hitbox to move to next stage
            box = new Wall(this, 390, 330, 10, 10);
            box.draw();
            
            char1.draw();
            
            // activate dialog based on dialogBox int
            if (char1.y() == 320) {
                switch (dialogBox) {
                    case 0:
                        image(box1, 0, 0);
                        break;
                    case 1:
                        image(box2, 0, 0);
                        break;
                }
                
                // code to update dialogBox
                // doesn't let it update if dialog has finished
                // mouseHandled ensures clicks don't carry over to next dialog box
                if (mousePressed && !mouseHandled && dialogBox < 2) {
                    dialogBox++;
                    mouseHandled = true;
                }
                if (!mousePressed) {
                    mouseHandled = false;
                }
                
            } // end if
            
            // go to next level
            if (char1.isCollidingWith(box)) {
                stage = 3;
                char1.moveTo(200, 300); // move char to bottom
            }
            
        } // end if
        
        //////////////////////STAGE 3//////////////////////////////
        if (stage == 3) {
            
            // player boundaries
            char1.moveConstraint(3);
            
            // load background
            image(stage3, 0, 0);
            
            // hitbox to move to next stage
            box = new Wall(this, 380, 380, 10, 10);
            box.draw();
            
            // when player is in front of emperor
            if (char1.y() >= emperor.y()) {
                emperor.draw();
                char1.draw();
            }
            
            // when player is behind emperor
            if (char1.y() < emperor.y()) {
                char1.draw();
                emperor.draw();
            }
            
            // activate dialog
            if (char1.isCollidingWith(emperor)) {
                switch (dialogBox) {
                    case 2:
                        image(box3, 0, 0);
                        break;
                    case 3:
                        image(box4, 0, 0);
                        break;
                    case 4:
                        image(box4b, 0, 0);
                        break;
                    case 5:
                        image(box5, 0, 0);
                        break;
                    case 6:
                        image(box6, 0, 0);
                        break;
                    case 7:
                        image(box7, 0, 0);
                        break;
                    case 8:
                        image(box8, 0, 0);
                        break;
                    case 9:
                        image(box9, 0, 0);
                        break;
                    case 10:
                        image(box10, 0, 0);
                        break;
                    case 11:
                        image(box11, 0, 0);
                        break;

                }
                
                // code to update dialogBox
                // doesn't let it update if dialog has finished
                // mouseHandled ensures clicks don't carry over to next dialog box
                if (mousePressed && !mouseHandled && dialogBox < 12) {
                    dialogBox++;
                    mouseHandled = true;
                }
                if (!mousePressed) {
                    mouseHandled = false;
                }
            } // close if
            
            // change stage if user leaves the room
            if (char1.isCollidingWith(box)) {
                stage = 4;
                char1.moveTo(200, 380); // move char to bottom
            } // end if
        }
        
        //////////////////////STAGE 4//////////////////////////////
        if (stage == 4) {
            // player boundaries
            char1.moveConstraint(3);
            
            // load background
            image(stage4, 0, 0);
            
            // hitbox to move to next stage
            box = new Wall(this, 380, 380, 10, 10);
            box.draw();

                char1.draw();
            
            // activate dialog
            if (char1.isCollidingWith(emperor)) {
                switch (dialogBox) {
                    case 12:
                        image(box11b, 0, 0);
                        break;
                    case 13:
                        image(box11c, 0, 0);
                        break;
                    case 14:
                        image(box11d, 0, 0);
                        break;
                    case 15:
                        image(box12, 0, 0);
                        break;
                    case 16:
                        image(box13, 0, 0);
                        break;
                } //end switch
                
                // code to update dialogBox
                // doesn't let it update if dialog has finished
                // mouseHandled ensures clicks don't carry over to next dialog box
                if (mousePressed && !mouseHandled && dialogBox < 12) {
                    dialogBox++;
                    mouseHandled = true;
                }
                if (!mousePressed) {
                    mouseHandled = false;
                }
            } // close if
            
            // change stage if user leaves the room
            if (char1.isCollidingWith(box)) {
                stage = 3;
                char1.moveTo(200, 380); // move char to bottom
            } // end if
        } // end if
        
        if (showInfo) {
            // display the person's info if the showInfo flag is true
            char1.displayInfo(this);
        }
        
        // COLLISION (UNUSED)
//        if (char1.isCollidingWith(char2)) {
//            fill(255, 0, 0);
//            this.text("ouuch", char2.x(), char2.y());
//        }
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

