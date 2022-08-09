import greenfoot.*;

public class How extends World
{
    public How()
    {    
        super(800, 550, 1);
    }
    
    public void act()
    {
        String key = Greenfoot.getKey();
        if("enter".equals(key))
        {
            Greenfoot.setWorld(new Startscreen());
        }
    }
}
