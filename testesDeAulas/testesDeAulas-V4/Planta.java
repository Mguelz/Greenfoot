import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Planta here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Planta extends Actor
{
    int planta = 0;
    /**
     * Act - do whatever the Planta wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        World w = getWorld();
        setRotation(90);
        move(Greenfoot.getRandomNumber(7)); 
        if (isAtEdge()) {
            //w.removeObject(this);
            setLocation(Greenfoot.getRandomNumber(1200),(Greenfoot.getRandomNumber(200)));
        }
        //if (isTouching(Bee.class)) {
            //removeTouching(Bee.class);
            //getWorld().showText("A planta encostou em você" + planta, 550, 300);
            //Greenfoot.stop();
        //}
    }
}
