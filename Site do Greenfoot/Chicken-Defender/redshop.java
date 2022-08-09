import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class redshop extends shop
{
    public redshop()
    {
        setup();
    }
    
    public void act() 
    {
        loop(new redshooter(), 300);
    }    
}
