import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Planta2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Planta2 extends Actor
{
    /**
     * Act - do whatever the Planta2 wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setRotation(90);
        move(Greenfoot.getRandomNumber(8));
        if (isAtEdge()) {
            setLocation(Greenfoot.getRandomNumber(1200), (Greenfoot.getRandomNumber(10)));
        }
    }
}
