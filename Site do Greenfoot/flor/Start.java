import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Start here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Start extends World
{

    /**
     * Constructor for objects of class Start.
     * 
     */
    public Start()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(640, 640, 1); 
        showText("Pressione 1 para jogar", 330, 450);
        showText("Pressione 1 para jogar", 330, 160);
        showText("Press space for start the game", 330, 180);
    }
    
    public void act() {
         if(Greenfoot.isKeyDown("1")) {
            Greenfoot.setWorld(new Level_1());
            }   
    }
}
