import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class dia extends Actor
{
    int timer;
    public dia()
    {
        getImage().scale(50, 50);
        timer = 150;
    }
    
    public void act() 
    {
        timer--;
        setLocation(getX(), getY() + 1);
        if(Greenfoot.mouseClicked(this) && timer > 1)
        {
            getWorldOfType(MyWorld.class).clickeddia();
            getWorld().removeObject(this);
        }
        if(timer < 0)
        {
            getWorld().removeObject(this);
        }
    }    
}
