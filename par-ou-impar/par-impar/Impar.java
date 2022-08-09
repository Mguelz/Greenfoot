import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Impar extends Actor
{
    int aleatorio;
    
    public void act()
    {
        if (Greenfoot.isKeyDown("i")) {
            aleatorio = Greenfoot.getRandomNumber(2);
            if (aleatorio == 1) {
                getWorld().showText("Voce Ganhou", 500, 300);
                
                getWorld().showText("" + aleatorio, 500, 340);
            }
            if (aleatorio == 0) {
                getWorld().showText("Voce Perdeu", 500, 300);
                
                getWorld().showText("" + aleatorio, 500, 340);
            }
        }
    }
}
