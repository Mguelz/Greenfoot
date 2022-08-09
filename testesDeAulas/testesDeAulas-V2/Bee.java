import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bee here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bee extends Actor
{
    int score = 0;
    /**
     * Act - do whatever the Bee wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setRotation(270);
        if (Greenfoot.isKeyDown("a")) {
            setLocation(getX() - 8, getY());
        }
        if (Greenfoot.isKeyDown("d")) {
            setLocation(getX() + 8, getY());
        }
        if (isTouching(Planta.class)) {
            removeTouching(Planta.class);
            score++;
            getWorld().showText("Score: " + score, 570, 20);
        }
        if (isTouching(Planta2.class)) {
            removeTouching(Planta2.class);
            score++;
            getWorld().showText("Score: " + score, 570, 20);
        }
    }
}
