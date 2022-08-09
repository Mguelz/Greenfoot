import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class healthbar extends Actor
{
    public healthbar()
    {
        GreenfootImage image = getImage();
        getImage().scale(70, 7);
        image.clear();
        image.setColor(Color.BLACK);
    }  
}
