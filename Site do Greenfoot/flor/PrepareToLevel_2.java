import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class PrepareToLevel_2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class PrepareToLevel_2 extends World
{

    /**
     * Constructor for objects of class PrepareToLevel_2.
     * 
     */
    public PrepareToLevel_2()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(640, 640, 1); 
        showText("Poderes a mais para o Level 2", 330, 450);
        showText("Clicando Space voce tera um boost!", 330, 460);
        showText("Pressione 2 para jogar", 330, 160);
    }
    
    public void act() {
         if(Greenfoot.isKeyDown("1")) {
            Greenfoot.setWorld(new Level_2());
            }   
    }
}
