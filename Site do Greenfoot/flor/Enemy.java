import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Enemy here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Enemy extends Actor
{
    /**
     * Act - do whatever the Enemy wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        setRotation(90);
        move(Greenfoot.getRandomNumber(20));
        // ira subir a pedra para a o topo da tela para poder cair novamente
        if (isAtEdge()) {
            setLocation(Greenfoot.getRandomNumber(1600), (Greenfoot.getRandomNumber(10)));
            getWorld().addObject(new Enemy(), Greenfoot.getRandomNumber(1600), Greenfoot.getRandomNumber(10)); // adicionar outro enemy para continuar caindo 
                                                              //dando a impressao que tem muito mais inimigos caindo
        }
        if (isTouching(Rocket.class)) {
            removeTouching(Rocket.class);
            Greenfoot.stop();
            getWorld().showText("Game Over", 800, 400);
        }
    }
}
