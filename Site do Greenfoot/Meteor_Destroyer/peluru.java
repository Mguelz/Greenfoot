import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class peluru here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class peluru extends Actor
{
    /**
     * Act - do whatever the peluru wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        this.setLocation(getX() , getY()-10);
        peluru py = new peluru();
        if(this.getY() < 5){
            this.getWorld().removeObject(this);
            
        }
        else if(this.isTouching(Meteor.class)){
            this.removeTouching(Meteor.class);
            this.getWorld().addObject(new Meteor(), Greenfoot.getRandomNumber(300), 0);
            this.getWorld().removeObject(this);
            
        }
    }
}
