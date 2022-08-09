import greenfoot.*;

public class Credits extends World
{    
    public Credits()
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
