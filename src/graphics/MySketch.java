package graphics;
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import processing.core.PApplet;
import processing.core.PImage;

/**
* A shooting game set in an ancient Chinese legend.
* @author 342628146
* @since 2025-06-15
* @version 1.0
*/

public class MySketch extends PApplet {
    
    // declare objects
    private Sun sun1, sun2, sun3, sun4, sun5, sun6, sun7, sun8, sun9;
    private finalSun sun10;
    private Wall box;
    private Person char1, emperor, guard1, guard2;
    private Arrow arrow;
    
    // images for bgs, arrows, dialog boxes and suns
    PImage stages[] = new PImage[9]; // stage images
    PImage boxes[] = new PImage[28]; //dialog box images
    
    // misc variables
    int dialogBox = 0; // the # of dialog box
    boolean mouseHandled = false;
    public boolean showInfo; // to show the character's info
    public boolean eShowInfo; // to show emperor's info
    public boolean gShowInfo; // to show guard's info
    int stage = 0;
    int arrowCount = 20;
    boolean arrowFired = false;
    int ticks = 0; // timer
    
    // movement variables
    boolean upPressed = false;
    boolean downPressed = false;
    boolean leftPressed = false;
    boolean rightPressed = false;
    
    /**
    * Sets the size of the window.
    */
    public void settings(){
	//sets the size of the window
        size (400,400);
    }
    
    /**
    * Initiates PIages and objects.
    */
    public void setup(){
	//sets the background colour using R,G,B
        background(2, 50, 5);
        textSize(20);
        
        // setup characters
        char1 = new Person(this, 200, 200, "Hou Yi", 35, "images/person.png");
        emperor = new Person(this, 180, 220, "Emperor Yao", 75, "images/emperor.png");
        guard1 =  new Person(this, 500, 200, "Guard", 30, "images/guard.png");
        guard2 =  new Person(this, 500, 200, "Guard", 30, "images/guard.png");
        
        // loop to load stage images into an array
        for (int i = 0; i <= 7; i++) {
            stages[i] = loadImage("images/stage" + (i + 1) + ".png");
        }
        
        // loop to load dialog box images into an array
        for (int i = 1; i <= 27; i++) {
            boxes[i] = loadImage("images/box" + i + ".png");
        }
        
        // set extra dialog box image
        boxes[0] = loadImage("images/box4.1.png");
        
        // set suns info
        sun1 = new Sun(this, 40, 30, "images/sun.png");
        sun2 = new Sun(this, 40, 100, "images/sun.png");
        sun3 = new Sun(this, 40, 170, "images/sun.png");
        sun4 = new Sun(this, 40, 30, "images/sun.png");
        sun5 = new Sun(this, 40, 100, "images/sun.png");
        sun6 = new Sun(this, 40, 170, "images/sun.png");
        sun7 = new Sun(this, 40, 30, "images/smallSun.png");
        sun8 = new Sun(this, 40, 100, "images/smallSun.png");
        sun9 = new Sun(this, 40, 170, "images/smallSun.png");
        sun10 = new finalSun(this, 450, 100, "images/sun.png");
        
        // set arrow object
        arrow = new Arrow(this, 0, 0, "images/arrow.png");
    }
    
    /**
    * Draws and updates all levels of the game, controls object movement, 
    * updates images and handles directional movement.
    */
    public void draw() {
        background(200);
        
        // add char1 x position to file
        try (FileWriter writer = new FileWriter("file.txt", false)) {
            writer.write(char1.x() + " ");
        } catch (IOException e) {
            System.err.println("Java Exception: " + e);
        }
        
        //////////////STAGE 0/////////////////
        if (stage == 0) {
            //display entered text
            fill(0);
            text("You have received a summon", 20, 50);
            text("from the emperor...", 20, 80);
            text("Press any key to proceed.", 20, 110);
            if (keyPressed)
                stage = 1;
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
            image(stages[0], 0, 0);
            
            // hitbox to move to next stage
            box = new Wall(this, 200, 100, 10, 10);
            
            char1.draw();
            
            // change stage if user leaves the room
            if (char1.isCollidingWith(box)) {
                stage = 2;
                char1.moveTo(200, 380); // move char to bottom
            }
        } // end if
        
        //////////////////////STAGE 2//////////////////////////////
        if (stage == 2) {
            
            //load background
            image(stages[1], 0, 0);
            
            // player boundaries
            char1.moveConstraint(2);
            
            // hitbox to move to next stage
            box = new Wall(this, 390, 330, 10, 10);
            
            // draw char
            char1.draw();
            
            // display character's current x value
            try {
                Scanner file = new Scanner(new File("file.txt"));
                String line = "";
                
                while (file.hasNext()) {
                    line = file.nextLine();
                    System.out.println(line);
                }
            } catch (IOException e) {
                System.err.println("Java Exception: " + e);
            }
            
            // activate dialog based on dialogBox int
            if (char1.y() == 300) {
                switch (dialogBox) {
                    case 0:
                        image(boxes[1], 0, 0);
                        break;
                    case 1:
                        image(boxes[2], 0, 0);
                        break;
                }
                
                // code to update dialogBox
                // doesn't let it update if dialog has finished
                // mouseHandled ensures clicks don't carry over to next dialog box
                // written with assistance from ChatGPT
                if (mousePressed && !mouseHandled && dialogBox < 2) {
                    dialogBox++;
                    mouseHandled = true;
                }
                if (!mousePressed) {
                    mouseHandled = false;
                }
                
            } // end if
            
            // go to next level
            // only allow advance if player finished dialog
            if (char1.isCollidingWith(box) && dialogBox == 2) {
                stage = 3;
                char1.moveTo(200, 300); // move char to bottom
            }
            
        } // end if
        
        //////////////////////STAGE 3//////////////////////////////
        if (stage == 3) {
            
            // player boundaries
            char1.moveConstraint(3);
            
            // load background
            image(stages[2], 0, 0);
            
            // hitbox to move to next stage
            box = new Wall(this, 0, 380, 400, 10);
            
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
                        image(boxes[3], 0, 0);
                        break;
                    case 3:
                        image(boxes[4], 0, 0);
                        break;
                    case 4:
                        image(boxes[0], 0, 0);
                        break;
                    case 5:
                        image(boxes[5], 0, 0);
                        break;
                    case 6:
                        image(boxes[6], 0, 0);
                        break;
                    case 7:
                        image(boxes[7], 0, 0);
                        break;
                    case 8:
                        image(boxes[8], 0, 0);
                        break;
                    case 9:
                        image(boxes[9], 0, 0);
                        break;
                    case 10:
                        image(boxes[10], 0, 0);
                        break;
                    case 11:
                        image(boxes[11], 0, 0);
                        break;

                }
                
                // code to update dialogBox
                // doesn't let it update if dialog has finished
                // mouseHandled ensures clicks don't carry over to next dialog box
                // written with assistance from ChatGPT
                if (mousePressed && !mouseHandled && dialogBox < 12) {
                    dialogBox++;
                    mouseHandled = true;
                }
                if (!mousePressed) {
                    mouseHandled = false;
                }
            } // close if
            
            // change stage if user leaves the room
            // only allow advance if player finished dialog
            if (char1.isCollidingWith(box) && dialogBox == 12) {
                stage = 4;
                char1.moveTo(200, 290); // move char to bottom
            } // end if
        } // end if
        
        //////////////////////STAGE 4//////////////////////////////
        if (stage == 4) {
            // player boundaries
            char1.moveConstraint(4);
            
            // load background
            image(stages[3], 0, 0);

            char1.draw();
            
            // activate dialog
            if (char1.y() == 270) {
                switch (dialogBox) {
                    case 12:
                        image(boxes[12], 0, 0);
                        break;
                    case 13:
                        image(boxes[13], 0, 0);
                        break;
                    case 14:
                        image(boxes[14], 0, 0);
                        break;
                    case 15:
                        image(boxes[15], 0, 0);
                        break;
                    case 16:
                        image(boxes[16], 0, 0);
                        break;
                    case 17:
                        image(boxes[17], 0, 0);
                        break;
                    case 18:
                        image(boxes[18], 0, 0);
                        break;
                    case 19:
                        image(boxes[19], 0, 0);
                        break;
                } //end switch
                
                // code to update dialogBox
                // doesn't let it update if dialog has finished
                // mouseHandled ensures clicks don't carry over to next dialog box
                // written with assistance from ChatGPT
                if (mousePressed && !mouseHandled && dialogBox < 20) {
                    dialogBox++;
                    mouseHandled = true;
                }
                if (!mousePressed) {
                    mouseHandled = false;
                }
            } // close if
            
            // change stage if user leaves the room
            if (char1.x() > 370) {
                stage = 5;
                char1.moveTo(200, 290); // move char to bottom
            } // end if
        } // end if
        
        //////////////////////STAGE 5//////////////////////////////
        if (stage == 5) {
            
            // player boundaries
            char1.moveConstraint(4);
            
            // load background
            image(stages[4], 0, 0);
            
            // draw suns
            sun1.draw();
            sun2.draw();
            sun3.draw();
            
            // move char
            char1.draw();
            
            // move suns
            sun1.move(4, 0);
            sun2.move(3, 0);
            sun3.move(5, 0);
            
            // increment timer
            ticks++;
            
            // display time left in seconds
            text("Time remaining: " + (60 - (ticks/60)), 20, 40);
            
            // display arrows left
            text("Arrows left: " + arrowCount, 20, 70);
            
            // end game if arrows run out
            if (arrowCount == 0) {
                stage = 11;
            }
            
            // end game if timer runs out
            if (ticks == 3600) {
                stage = 11;
            }
            
            // display arrows left
            text("Arrows left: " + arrowCount, 20, 70);
            
            // arrow fire (written with assistance from ChatGPT)
            // fires another arrow only if last one is done flying and arrows are not used up
            if (mousePressed && !mouseHandled && !arrowFired && arrowCount > 0) {
                // Set arrow to char position
                arrow.setX(char1.x() + 24);
                arrow.setY(char1.y());
                // set arrowFired to true (handled in following statement)
                arrowFired = true;
                arrowCount--;
                mouseHandled = true;
            }

            if (!mousePressed) {
                mouseHandled = false;
            }

            // Move and draw arrow
            if (arrowFired) {
                arrow.move(0, -6);
                arrow.draw();

                // remove arrow when it reaches the top, and allow next arrow
                if (arrow.y() < 16) {
                    // allow next arrow to be fired
                    arrowFired = false;
                    // move arrow offscreen (to avoid killing topmost sun while hidden and stopped)
                    arrow.moveTo(500, 500);
                }
                
                // remove arrow when it collides with a sun, and allow next arrow
                if (arrow.isCollidingWith(sun1) || arrow.isCollidingWith(sun2) || arrow.isCollidingWith(sun3)) {
                    
                    // if arrow collides with sun, move it offscreen
                    if (arrow.isCollidingWith(sun1)) {
                        sun1.moveTo(-500, 500);
                        // decrease sun count
                        sun1.decreaseNumSuns();
                    }
                    if (arrow.isCollidingWith(sun2)) {
                        sun2.moveTo(-500, 500);
                        // decrease sun count
                        sun1.decreaseNumSuns();
                    }
                    if (arrow.isCollidingWith(sun3)) {
                        sun3.moveTo(-500, 500);
                        // decrease sun count
                        sun1.decreaseNumSuns();
                    }

                    // move arrow offscreen (to avoid killing other suns while hidden and stopped)
                    arrow.moveTo(500, 500);
                    // allow next arrow
                    arrowFired = false;
                    
                } // end if (collision)
            } // end if (arrowFired)
            
            // change stage if user goes right
            if (char1.x() > 370) {
                stage = 6;
                char1.moveTo(200, 380); // move char to bottom
            } // end if
            
        } // end of stage 5
        
        /////////////////STAGE 6///////////////////////////////////
        if (stage == 6) {
            
            // player boundaries
            char1.moveConstraint(4);
            
            // load background
            image(stages[4], 0, 0);
            
            // draw suns
            sun4.draw();
            sun5.draw();
            sun6.draw();
            
            // move char
            char1.draw();
            
            // move suns
            sun4.move(4, 1);
            sun5.move(3, -1);
            sun6.move(5, 1);
            
            // increment timer
            ticks++;
            
            // display time left in seconds
            text("Time remaining: " + (60 - (ticks/60)), 20, 40);
            
            // display arrows left
            text("Arrows left: " + arrowCount, 20, 70);
            
            // end game if arrows run out
            if (arrowCount == 0) {
                stage = 11;
            }
            
            // end game if timer runs out
            if (ticks == 3600) {
                stage = 11;
            }
            
            // arrow fire (written with assistance from ChatGPT)
            // fires another arrow only if last one is done flying and arrows are not used up
            if (mousePressed && !mouseHandled && !arrowFired && arrowCount > 0) {
                // Set arrow to char position
                arrow.setX(char1.x() + 24);
                arrow.setY(char1.y());
                // set arrowFired to true (handled in following statement)
                arrowFired = true;
                arrowCount--;
                mouseHandled = true;
            }

            if (!mousePressed) {
                mouseHandled = false;
            }

            // Move and draw arrow
            if (arrowFired) {
                arrow.move(0, -6);
                arrow.draw();

                // remove arrow when it reaches the top, and allow next arrow
                if (arrow.y() < 16) {
                    // allow next arrow to be fired
                    arrowFired = false;
                    // move arrow offscreen (to avoid killing topmost sun while hidden and stopped)
                    arrow.moveTo(500, 500);
                }
                
                // remove arrow when it collides with a sun, and allow next arrow
                if (arrow.isCollidingWith(sun4) || arrow.isCollidingWith(sun5) || arrow.isCollidingWith(sun6)) {
                    
                    // if arrow collides with sun, move it offscreen
                    if (arrow.isCollidingWith(sun4)) {
                        sun4.moveTo(-500, 500);
                        // decrease sun count
                        sun1.decreaseNumSuns();
                    }
                    if (arrow.isCollidingWith(sun5)) {
                        sun5.moveTo(-500, 500);
                        // decrease sun count
                        sun1.decreaseNumSuns();
                    }
                    if (arrow.isCollidingWith(sun6)) {
                        sun6.moveTo(-500, 500);
                        // decrease sun count
                        sun1.decreaseNumSuns();
                    }

                    // move arrow offscreen (to avoid killing other suns while hidden and stopped)
                    arrow.moveTo(500, 500);
                    // allow next arrow
                    arrowFired = false;
                    
                } // end if (collision)
            } // end if (arrowFired)
            
            // change stage if user leaves the room
            if (char1.x() > 370) {
                stage = 7;
                char1.moveTo(200, 380); // move char to bottom
            } // end if
            
            // go back 1 stage if user goes left
            if (char1.x() < 50) {
                stage = 5;
                char1.moveTo(200, 380); // move char to bottom
            } // end if
            
        } // end of stage 6
        
        ///////////////////////STAGE 7//////////////////////////////
        if (stage == 7) {

            // player boundaries
            char1.moveConstraint(4);
            
            // load background
            image(stages[4], 0, 0);
            
            // draw suns
            sun9.draw();
            sun7.draw();
            sun8.draw();
            
            // move char
            char1.draw();
            
            // move suns
            sun9.move(6, -1);
            sun7.move(7, 1);
            sun8.move(5, -1);
            
            // increment timer
            ticks++;
            
            // display time left in seconds
            text("Time remaining: " + (60 - (ticks/60)), 20, 40);
            
            // display arrows left
            text("Arrows left: " + arrowCount, 20, 70);
            
            // end game if arrows run out
            if (arrowCount == 0 && sun1.getNumSuns() != 0) {
                stage = 11;
            }
            
            // end game if timer runs out
            if (ticks == 3600) {
                stage = 11;
            }
            
            // display arrows left
            text("Arrows left: " + arrowCount, 20, 70);
            
            // arrow fire (written with assistance from ChatGPT)
            // fires another arrow only if last one is done flying and arrows are not used up
            if (mousePressed && !mouseHandled && !arrowFired && arrowCount > 0) {
                // Set arrow to char position
                arrow.setX(char1.x() + 24);
                arrow.setY(char1.y());
                // set arrowFired to true (handled in following statement)
                arrowFired = true;
                arrowCount--;
                mouseHandled = true;
            }

            if (!mousePressed) {
                mouseHandled = false;
            }

            // Move and draw arrow
            if (arrowFired) {
                arrow.move(0, -6);
                arrow.draw();

                // remove arrow when it reaches the top, and allow next arrow
                if (arrow.y() < 16) {
                    // allow next arrow to be fired
                    arrowFired = false;
                    // move arrow offscreen (to avoid killing topmost sun while hidden and stopped)
                    arrow.moveTo(500, 500);
                }
                
                // remove arrow when it collides with a sun, and allow next arrow
                if (arrow.isCollidingWith(sun9) || arrow.isCollidingWith(sun7) || arrow.isCollidingWith(sun8)) {
                    
                    // if arrow collides with sun, move it offscreen
                    if (arrow.isCollidingWith(sun9)) {
                        sun9.moveTo(-500, 500);
                        // decrease sun count
                        sun1.decreaseNumSuns();
                    }
                    if (arrow.isCollidingWith(sun7)) {
                        sun7.moveTo(-500, 500);
                        // decrease sun count
                        sun1.decreaseNumSuns();
                    }
                    if (arrow.isCollidingWith(sun8)) {
                        sun8.moveTo(-500, 500);
                        // decrease sun count
                        sun1.decreaseNumSuns();
                    }

                    // move arrow offscreen (to avoid killing other suns while hidden and stopped)
                    arrow.moveTo(500, 500);
                    // allow next arrow
                    arrowFired = false;
                    
                } // end if (collision)
            } // end if (arrowFired)
            
            // change stage if user leaves the room
            if (char1.x() > 370) {
                stage = 8;
                char1.moveTo(200, 380); // move char to bottom
                emperor.moveTo(600, 280); // move emperor off-screen
                guard1.moveTo(550, 280); // move guard off-screen
                guard2.moveTo(650, 280); // move guard off-screen
            } // end if
            
            // go back 1 stage if user goes left (only allow if suns still exist)
            if (char1.x() < 50 && sun1.getNumSuns() != 0) {
                stage = 6;
                char1.moveTo(200, 380); // move char to bottom
            } // end if
            
        } // end of stage 7
        
        ////////////////////STAGE 8/////////////////////////
        if (stage == 8) {
            
            // player boundaries
            char1.moveConstraint(4);
            
            // load background
            image(stages[4], 0, 0);
            
            // draw suns
            sun10.draw();
            
            // draw char
            char1.draw();
            
            // move sun into frame
            if (!(sun10.x() < 200)) {
                sun10.decreaseX();
            }
            // move emperor and guards into frame
            if (!(emperor.x() < 200)) {
                emperor.move(-1, 0);
                guard1.move(-1, 0);
                guard2.move(-1, 0);
            }
            
            // when player is in front of emperor
            if (char1.y() >= emperor.y()) {
                // draw emperor and guards first
                emperor.draw();
                guard1.draw();
                guard2.draw();
                char1.draw();
            }
            
            // when player is behind emperor
            if (char1.y() < emperor.y()) {
                // draw plahyer first
                char1.draw();
                emperor.draw();
                guard1.draw();
                guard2.draw();
            }
            
            // dialog with emperor
            if (char1.isCollidingWith(emperor)) {
                switch (dialogBox) {
                    case 20:
                        image(boxes[20], 0, 0);
                        break;
                    case 21:
                        image(boxes[21], 0, 0);
                        break;
                    case 22:
                        image(boxes[22], 0, 0);
                        break;
                    case 23:
                        image(boxes[23], 0, 0);
                        break;
                    case 24:
                        image(boxes[24], 0, 0);
                        break;
                }
                
                // code to update dialogBox
                // doesn't let it update if dialog has finished
                // mouseHandled ensures clicks don't carry over to next dialog box
                // written with assistance from ChatGPT
                if (mousePressed && !mouseHandled && dialogBox < 25) {
                    dialogBox++;
                    mouseHandled = true;
                }
                if (!mousePressed) {
                    mouseHandled = false;
                }
            } // close if
            
            // change stage if user leaves the room
            if (char1.x() > 370  && dialogBox == 25) {
                stage = 9;
                char1.moveTo(200, 300); // move char to bottom
                emperor.moveTo(180, 120); // move emperor to middle
                guard1.moveTo(94, 166); //move guard
                guard2.moveTo(256, 166); //move guard
            } // end if
            
        }
        
        /////////////////////////////STAGE 9////////////////////////////////////
        if (stage == 9) {
            // player boundaries
            char1.moveConstraint(5);
            
            // load background
            image(stages[5], 0, 0);
            
            // draw chars
            char1.draw();
            emperor.draw();
            guard1.draw();
            guard2.draw();
            
            if (char1.y() < 170) {
                switch (dialogBox) {
                    case 25:
                        image(boxes[25], 0, 0);
                        break;
                    case 26:
                        image(boxes[26], 0, 0);
                        break;
                    case 27:
                        image(boxes[27], 0, 0);
                        break;
                }
                
                // code to update dialogBox
                // doesn't let it update if dialog has finished
                // mouseHandled ensures clicks don't carry over to next dialog box
                // written with assistance from ChatGPT
                if (mousePressed && !mouseHandled && dialogBox < 28) {
                    dialogBox++;
                    mouseHandled = true;
                }
                if (!mousePressed) {
                    mouseHandled = false;
                }
            } // close if
            
            // change stage if user leaves the room
            if (char1.y() > 370  && dialogBox == 28) {
                stage = 10;
                char1.moveTo(200, 300); // move char to bottom
                emperor.moveTo(180, 120); // move emperor to middle
            } // end if
            
        }
        
        // win stage
        if (stage == 10) {
            // load background
            image(stages[6], 0, 0);
        }
        
        // lose stage
        if (stage == 11) {
            // load background
            image(stages[7], 0, 0);
        }
        
        /////////////////// END OF STAGES///////////////////
        
        if (showInfo) {
            // display the person's info if its showInfo flag is true
            char1.displayInfo(this);
        }
        if (eShowInfo) {
            // display the emperor's info if its showInfo flag is true
            emperor.displayInfo(this);
        }
        if (gShowInfo) {
            // display the guards' info if their showInfo flag is true
            guard1.displayInfo(this);
            guard2.displayInfo(this);
        }
    }
    
    /**
    * Handles mouse collision with characters.
    */
    
    public void mousePressed() {
        
        // when char is clicked
        if (char1.isClicked(mouseX, mouseY)) {
            // toggle the showInfo flag when the person is clicked
            showInfo = !showInfo;
            
        } else {
            // hide the person's info if the mouse is clicked elsewhere
            showInfo = false;
        }
        
        // when emperor is clicked
        if (emperor.isClicked(mouseX, mouseY) && stage > 2) {
            // toggle the showInfo flag when the person is clicked
            eShowInfo = !eShowInfo;
        } else {
            // hide the person's info if the mouse is clicked elsewhere
            eShowInfo = false;
        }
        
        // when guard is clicked
        if (guard1.isClicked(mouseX, mouseY) || guard2.isClicked(mouseX, mouseY)) {
            // toggle the showInfo flag when the person is clicked
            gShowInfo = !gShowInfo;
        } else {
            // hide the person's info if the mouse is clicked elsewhere
            gShowInfo = false;
        }
    }
    
    /**
    * Handles directional movement when key pressed.
    */
    public void keyPressed() {
      if (!(stage == 0)) {
            
        // character movement
        if (keyCode == UP || keyCode == 'w' || keyCode == 'W') upPressed = true;
        if (keyCode == DOWN || keyCode == 's' || keyCode == 'S') downPressed = true;
        if (keyCode == LEFT || keyCode == 'a' || keyCode == 'A') leftPressed = true;
        if (keyCode == RIGHT || keyCode == 'd' || keyCode == 'D') rightPressed = true;
            
      }
    }
    
    /**
    * Handles directional movement when key released.
    */
    public void keyReleased() {
        if (keyCode == UP || keyCode == 'w' || keyCode == 'W') upPressed = false;
        if (keyCode == DOWN || keyCode == 's' || keyCode == 'S') downPressed = false;
        if (keyCode == LEFT || keyCode == 'a' || keyCode == 'A') leftPressed = false;
        if (keyCode == RIGHT || keyCode == 'd' || keyCode == 'D') rightPressed = false;
    }
    
}

