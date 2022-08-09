import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Hero here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Hero extends Actor
{
    /**
     * Act - do whatever the Hero wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    int jeda = 0;
    public void act()
    {
        // Add your action code here.
        if(Greenfoot.isKeyDown("up")){
            this.setLocation(this.getX(), this.getY()-5);
        }
        if(Greenfoot.isKeyDown("down")){
            this.setLocation(this.getX(), this.getY()+5);
        }
        if(Greenfoot.isKeyDown("left")){
            this.setLocation(this.getX()-5, this.getY());
        }
        if(Greenfoot.isKeyDown("right")){
            this.setLocation(this.getX()+5, this.getY());
        }
        
        jeda++;
        if(jeda >= 100){
            peluru pelu = new peluru();
            this.getWorld().addObject(pelu, this.getX(),this.getY());
            jeda = 0;
            
        }
        mati();
        
    }
    public void mati()
    {
         if(this.isTouching(Alien.class))
        {
            this.removeTouching(Hero.class);
             this.getWorld().removeObject(this);
        }
    }
}
