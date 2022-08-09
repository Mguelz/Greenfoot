import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Par extends Actor
{
    int aleatorio;

    public void act()
    {
        if (Greenfoot.isKeyDown("p")) {
            Greenfoot.getRandomNumber(11);

            aleatorio = Greenfoot.getRandomNumber(3);
            if (aleatorio == 2) {
                getWorld().showText("Voce Ganhou", 500, 300);
                
                getWorld().showText("" + aleatorio, 500, 340);
            }
            
            if (aleatorio == 1) {
                getWorld().showText("Voce Perdeu", 500, 300);
                
                getWorld().showText("" + aleatorio, 500, 340);
            }
        }
    }
}
