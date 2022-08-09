import greenfoot.*;

public class shop extends Actor
{
    MouseInfo mouse;
    int cooldown, oldcooldown;
    double scale;
    Actor display;
    boolean start;
    
    public shop()
    {
        oldcooldown = 100;
        setup();
    }
    
    public void act() 
    {
        loop(new shooter(), 100);
    }
    
    public void setup()
    {
        scale = 1/(double)(oldcooldown);
        getImage().scale(60, 60);
        mouse = Greenfoot.getMouseInfo();
        cooldown = 0;
        display = new cooldown();
        start = false;   
    }
    
    public void loop(Actor buyedshooter, int costs)
    {
        if(!start)
        {
            start = true;
            getWorld().addObject(display, getX() + 40, getY());
        }
        
        display.getImage().clear();
        display.getImage().fillRect(0, 0, 6,(int)(70 * scale * cooldown));
        display.getImage().drawRect(0,0,6,69);
        
        if(cooldown > 0)
        {
            cooldown--;
        }
        
        mouse = Greenfoot.getMouseInfo();
        if(Greenfoot.mouseClicked(this) && getWorldOfType(MyWorld.class).getdias() >= costs && cooldown <= 0)
        {
            getWorld().addObject(buyedshooter, mouse.getX(), mouse.getY());
            getWorldOfType(MyWorld.class).setsol(costs);
            cooldown = oldcooldown;
        }
    }
}
