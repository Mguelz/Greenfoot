import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class shooter extends Actor
{
    boolean pressed;
    int timer, oldtimer, y;
    public shooter()
    {
        timer = 70;
        setup();
    }
    
    public void act()
    {
        place();
        shoot(new whiteegg());
    }
    
    public void setup()
    {
        getImage().scale(50, 50);
        pressed = false;
        oldtimer = timer;
    }
    
    public void place()
    {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null)
        {
            if(mouse.getButton() == 1 && mouse.getY() > 100 && mouse.getX() > 50 && mouse.getY() < 500)
            {
                if(getOneIntersectingObject(shooter.class) == null)
                {
                    pressed = true;
                    y = (getY()-125)/50;
                }
            }
            
            if(!pressed)
            {
                setLocation(calcX(mouse), calcY(mouse));
            }
        }
    }
    
    public void shoot(Actor egg)
    {
        if(pressed)
        { 
            if(getWorldOfType(MyWorld.class).spiderlogs[y] > 0)
            {
                timer --;
                if(timer < 0 && !getWorldOfType(MyWorld.class).gibdead())
                {
                    timer = oldtimer;
                    getWorld().addObject(egg, getX(), getY());
                }
            }
        }
    }
    
    public int calcX(MouseInfo mouse)
    {
        int x = mouse.getX();    
        int y = mouse.getY();
        
        if(y > 100 && x > 50 && y < 500)
        {
            return (int)Math.ceil(x / 50) * 50 + 25;
        }
        else
        {
            return x;
        }
    }  
    
    public int calcY(MouseInfo mouse)
    {
        int y = mouse.getY();
        int x = mouse.getX();
        
        if(y > 100 && x > 50 && y < 500)
        {
            return (int)Math.ceil(y / 50) * 50 + 25;
        }
        else
        {
            return y;
        }
    }  
}