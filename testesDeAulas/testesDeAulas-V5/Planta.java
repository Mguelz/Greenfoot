import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Planta extends Actor
{
    int planta = 0;

    public void act()
    {
        World w = getWorld();
        setRotation(90);
        move(Greenfoot.getRandomNumber(7)); 
        
        tocarBordaVoltar();
        
    }
    
    public void tocarBordaVoltar() {
        if (isAtEdge()) {
            setLocation(Greenfoot.getRandomNumber(1200),(Greenfoot.getRandomNumber(50)));
        }
    }
}
