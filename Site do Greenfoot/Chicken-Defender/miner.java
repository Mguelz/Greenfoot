import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class miner extends shooter
{
    int timer;
    public miner()
    {
        setup();
        timer = 100;
    }
    
    public void act() 
    {
        place();
        timer--;
        if(timer < 0)
        {
            //getWorld().addObject(new dia(), getX(), getY());
            getWorldOfType(MyWorld.class).clickeddia();
            timer = 100;
        }
    }    
}
