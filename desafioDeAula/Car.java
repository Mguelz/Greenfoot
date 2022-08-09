import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Car here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Car extends Actor
{
    /**
     * Act - do whatever the Car wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (Greenfoot.isKeyDown("w")) {
            setRotation (270);
            setLocation(getX(), getY() - 10);
        }
        if (Greenfoot.isKeyDown("s")) {
            setRotation (90);
            setLocation(getX(), getY() + 10);
        }
        if (Greenfoot.isKeyDown("a")) {
            setRotation (180);
            setLocation(getX() - 10, getY());
        }
        if (Greenfoot.isKeyDown("d")) {
            setRotation (0);
            setLocation(getX() + 10, getY());
        }
        getWorld().showText("Car x: " + getX() ,920 ,20);
        getWorld().showText("Car y: " + getY() ,920 ,40);
    }
}
