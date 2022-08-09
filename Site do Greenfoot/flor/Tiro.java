import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class teste here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tiro extends Actor
{
    /**
     * Act - do whatever the teste wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setLocation(getX(), getY() - 8);
        if(isTouching(Enemy.class)){
            removeTouching(Enemy.class);
            //getWorld().removeObject(this);
        }
        if (isAtEdge()){
            getWorld().removeObject(this);
        }
    }
}
