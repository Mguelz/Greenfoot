import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Ssssss here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Start extends World
{

    /**
     * Constructor for objects of class Ssssss.
     * 
     */
    public Start()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(860, 447, 1); 
    }
    
    public void act() {
        if(Greenfoot.isKeyDown("space")) {
            Greenfoot.setWorld(new Game());
        }
    }
}
