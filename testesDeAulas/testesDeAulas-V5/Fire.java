import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Fire extends Actor
{
    public void act()
    {
        setLocation(getX(), getY() - 5);

        tocarEnemy();
        tocarBorda();
    }

    public void tocarEnemy() {
        if (isTouching(Enemy.class)){
            removeTouching(Enemy.class);
            
            //remover o projetil do tiro quando tocar no Enemy
            //getWorld().removeObject(this);
        }
    }
    
    public void tocarBorda() {
        if (isAtEdge()){
            getWorld().removeObject(this);
        }
    }

}
