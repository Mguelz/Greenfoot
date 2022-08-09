import greenfoot.*; 
import java.util.*;

public class Spider extends Actor
{
    int leben, speed;
    boolean start;
    Actor healthbar;
    double scale;
    
    public Spider()
    {
        leben = 100;
    }
    
    public void setup()
    {
        getImage().scale(50, 50);
        start = false;
        healthbar = new healthbar();
        scale = 1/(double)(leben);
    }
    
    public void loop()
    {
        if(!start)
        {
            getWorld().addObject(healthbar, getX(), getY() - 20);
            healthbar.getImage().drawRect(0, 0, 69, 6);
            healthbar.getImage().setColor(Color.RED);
            healthbar.getImage().fillRect(1, 1, 68, 5);
            start = !start;
        }
        
        if(!getWorldOfType(MyWorld.class).gibdead())
        {
            move(-speed);
        }
        
        if(getX() < 49)
        {
            Greenfoot.setWorld(new EndWorld(false));
        }
        
        Actor hit = getOneIntersectingObject(egg.class);
        
        if(isTouching(whiteegg.class))
        {
            leben -= 25;
            getWorld().removeObject(hit);
            changehealthbar();
        }
        
        if(isTouching(redegg.class))
        {
            leben -= 34;
            getWorld().removeObject(hit);
            changehealthbar();
        }
        
        if(isTouching(greenegg.class))
        {
            leben -= 20;
            getWorld().removeObject(hit);
            changehealthbar();
        }
        
        healthbar.setLocation( getX() , getY() - 20);
        
        if(leben <= 0)
        {
            int y = (getY()-125)/50;
            getWorldOfType(MyWorld.class).spiderlogs[y] = getWorldOfType(MyWorld.class).spiderlogs[y] -1;
            getWorld().removeObject(healthbar);
            getWorld().removeObject(this);
        }
    }
    
    public int health()
    {
        return leben;
    }
    
    public void changehealthbar()
    {
        if(leben > 0)
        {
            healthbar.getImage().clear();
            healthbar.getImage().setColor(Color.BLACK);
            healthbar.getImage().drawRect(0, 0, 69, 6);
            healthbar.getImage().setColor(Color.RED);
            healthbar.getImage().fillRect(1, 1, (int)(68 * scale * leben), 5);
            //healthbar.getImage().scale((int)(70 * scale * leben + 1), 7);
        }
    }
}
