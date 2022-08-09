import greenfoot.*;

/**
 * Write a description of class Bee here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bee extends Actor
{
    int score = 0;
    public void act()
    {
        setRotation(270);
        if (Greenfoot.isKeyDown("a")) {
            setLocation(getX() - 8, getY());
        }
        if (Greenfoot.isKeyDown("d")) {
            setLocation(getX() + 8, getY());
        }
        if (isTouching(Planta.class)) {
            removeTouching(Planta.class);
            score++;
            getWorld().showText("Score: " + score, 570, 20);
        }
        if (isTouching(Planta2.class)) {
            removeTouching(Planta2.class);
            score++;
            getWorld().showText("Score: " + score, 570, 20);
        }
        //quando clicar na tecla sotlar um tiro
        //mais pra frente sera utilziado o mouse para a ação
        if(Greenfoot.isKeyDown("e")) {
            //addedToWorld(_world_);
        }
    }
}
