import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class greenshop extends shop
{
    public greenshop()
    {
        cooldown = 100;
        setup();
    }
    
    public void act() 
    {
        loop(new greenshooter(), 500);
    }    
}
