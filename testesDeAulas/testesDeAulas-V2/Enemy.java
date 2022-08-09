import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setRotation(90);
        move(Greenfoot.getRandomNumber(10)); 
        if (isAtEdge()) {
            setLocation(Greenfoot.getRandomNumber(1200),(Greenfoot.getRandomNumber(100)));
            setLocation(Greenfoot.getRandomNumber(1200), Greenfoot.getRandomNumber(100));
        }
        if (isTouching(Bee.class)) {
            removeTouching(Bee.class);
            Greenfoot.stop();
        }
    }
}
