import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Planta2 extends Actor
{

    public void act()
    {
        int planta = 0;
        World w = getWorld();
        setRotation(90);
        move(Greenfoot.getRandomNumber(5)); 
        
        tocarBordaVoltar();
    }
    
    public void tocarBordaVoltar() {
        if (isAtEdge()) {
            setLocation(Greenfoot.getRandomNumber(1200),(Greenfoot.getRandomNumber(50)));
        }
    }
}
