import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enenmy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bomb extends Actor
{
    /**
     * Act - do whatever the Enenmy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        move(3);
        Person a = getWorld().getObjects(Person.class).get(0);
        Person b = getWorld().getObjects(Person.class).get(0);
        turnTowards(a.getX(), a.getY());
        turnTowards(b.getX(), b.getY());
    }
}
