import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;

public class Banana extends Actor
{
    int width, height;
    int firstFrames;
    int nBananas = 3;
    GreenfootImage image;
    
    public Banana()
    {
        Random randomNo = new Random();
        //nBananas = randomNo.nextInt(3)+1;
        setImage("banana"+nBananas+".png");
        image = getImage();
        image.setTransparency(0);
        height = image.getHeight()-10;
        width = image.getWidth()-10;
        firstFrames = height-1;
    }
    public void act()
    {
        if (firstFrames>0)
        {
            setImage("banana"+nBananas+".png");
            getImage().scale(height-firstFrames,width-firstFrames);
            getImage().setTransparency(255);
            firstFrames--;
        }
    }
}
