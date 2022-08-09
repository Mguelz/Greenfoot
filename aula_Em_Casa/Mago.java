import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Mago here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Mago extends Actor
{
    /**
     * Act - do whatever the Mago wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        turnTowards(getX(), getY());
    }
}
