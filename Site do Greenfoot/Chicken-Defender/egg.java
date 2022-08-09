import greenfoot.*;

public class egg extends Actor
{   
    public egg()
    {
        
    }
    
    public void loop()
    {
        if(!getWorldOfType(MyWorld.class).gibdead())
        {
            setLocation(getX() + 5, getY());
        }
        if(getX() > 790)
        {
            getWorld().removeObject(this);
        }
    }
}
