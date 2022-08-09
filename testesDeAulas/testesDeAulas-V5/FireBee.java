import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class FireBee extends Actor
{
    /**
     * Act - do whatever the FireBee wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        move(2);
        //atirar na direção em que o lagarto esta mirando, ao invés de seguir a Bee
        //fazer com que o Lagarto atire automaticamente a cada # segundos
        if (getWorld().getObjects(Bee.class).size() > 0 ) {
            turnTowards(getWorld().getObjects(Bee.class).get(0).getX(), getWorld().getObjects(Bee.class).get(0).getY());
        }
    }
}
