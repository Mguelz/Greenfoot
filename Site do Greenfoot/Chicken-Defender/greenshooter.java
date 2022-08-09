import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class greenshooter extends shooter
{
    public greenshooter()
    {
        timer = 30;
        setup();
    }
    
    public void act() 
    {
        place();
        shoot(new greenegg());
    }    
}
