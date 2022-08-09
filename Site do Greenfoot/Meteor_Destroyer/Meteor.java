import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Meteor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Meteor extends Actor
{
    /**
     * Act - do whatever the Meteor wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        // Add your action code here.
        this.setLocation(this.getX(), this.getY() +
            Greenfoot.getRandomNumber(2));
            
        if(this.getY() > 395){
            Meteor aln = new Meteor();
            this.getWorld().addObject(aln,
                Greenfoot.getRandomNumber(300), 
                Greenfoot.getRandomNumber(100));
            this.getWorld().removeObject(this);
        }
            
    }
    
}
