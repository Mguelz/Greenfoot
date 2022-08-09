import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class minershop extends shop
{
    public minershop()
    {
        oldcooldown = 350;
        setup();
    }
    
    public void act() 
    {
        loop(new miner(), 50);
    }    
}
