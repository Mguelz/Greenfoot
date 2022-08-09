import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class BotaoStart here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class BotaoStart extends Actor
{
    public BotaoStart() {
        GreenfootImage startImage = new GreenfootImage ("botaoStart.png");
        startImage.scale(150, 150);
        setImage(startImage);
    }

    public void act()
    {
        clicarNoAtorParaTrocarMundo();
    }

    public void clicarNoAtorParaTrocarMundo() {
        MouseInfo mouse = Greenfoot.getMouseInfo();

        if (mouse != null){
            if(mouse.getActor() != null) {
                if (mouse.getActor().getClass() == BotaoStart.class && mouse.getButton() == 1) {
                    Greenfoot.setWorld(new Level1());
                }
            }
        }
    }
}
