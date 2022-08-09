import greenfoot.*; 

public class EndWorld extends World
{
    public EndWorld(boolean won)
    {    
        super(800, 550, 1);
        
        if(won)
        {
            addObject(new Endgood(), 400, 200);
        }
        else
        {
            addObject(new Endbad(), 400, 200);
        }
        
        addObject(new Back(), 400, 400);
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
