import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 *Quando este personagem passar pelo meio da tela e vira de ponta cabeça
 *Quando ele voltar, volta ao normal
 */
public class Person extends Actor
{
    /**
     * Act - do whatever the Person wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        if (Greenfoot.isKeyDown("d")) {
            setLocation(getX() + 5,getY());
        }
        if (Greenfoot.isKeyDown("a")) {
            setLocation(getX() - 5,getY());
        }
        if (Greenfoot.isKeyDown("w")) {
            setLocation(getX() ,getY() - 5);
        }
        if (Greenfoot.isKeyDown("s")) {
            setLocation(getX() ,getY() + 5);
        }
        if (getX() > 500) {
            setRotation(180);
        }
        if (getX() < 500) {
            setRotation(0);
        }
        getWorld().showText("Person x: " + getX() ,70 ,20);
        getWorld().showText("Person y: " + getY() ,70 ,40);
    }
}
