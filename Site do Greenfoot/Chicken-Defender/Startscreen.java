import greenfoot.*;

public class Startscreen extends World
{
    int auswahl;
    boolean pushed;
    public Startscreen()
    {          
        super(800, 550, 1);
        Greenfoot.setSpeed(50);
        auswahl = 1;
        pushed = true;
    }
    
    public void act()
    {
        String key = Greenfoot.getKey();
        
        if(("s".equals(key) || "down".equals(key)))
        {
            if(auswahl == 3)
            {
                auswahl = 1;
            }
            else
            {
                auswahl++;
            }
            setBackground("start"+auswahl+".png");
            pushed = true;
        }
        
        if("w".equals(key) || "down".equals(key))
        {
            if(auswahl == 1)
            {
                auswahl = 3;
            }
            else
            {
                auswahl--;
            }
            setBackground("start"+auswahl+".png");
            pushed = true;
        }
        
        
        if("enter".equals(key))
        {
            switch(auswahl)
            {
                case 1:
                        Greenfoot.setWorld(new MyWorld());
                        break;
                        
                case 2:
                        Greenfoot.setWorld(new How());
                        break;
                
                case 3:
                        Greenfoot.setWorld(new Credits());
                        break;
            }
        }
    }
}
