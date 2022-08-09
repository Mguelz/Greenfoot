import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class TelaGame here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TelaGame extends World
{

    /**
     * Constructor for objects of class TelaGame.
     * 
     */
    public TelaGame()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(810, 400, 1); 
        
        addObject(new Doors(), 417, 198);
        
        showText("Escolha uma porta", 405, 20);
    }
}
