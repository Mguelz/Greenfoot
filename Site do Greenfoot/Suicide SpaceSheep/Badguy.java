import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
import java.util.List;

public class Badguy extends Actor
{
    int stop = 0;
    boolean spawning = true;
    boolean firstFrame = true;
    int trans;
    int turning = 0;
    int spawnTime = 0;
    public Badguy()
    {
        getImage().setTransparency(0);
    }
    public void act() 
    {
        MyWorld world = (MyWorld)getWorld();
        if (world.game)
        {
            if (spawning)
            {
                spawn();
            }
            else
            {
                mouvement();
                touchSheep();
                die();
            }
        }
    }   
    public void spawn()
    {
        MyWorld world = (MyWorld)getWorld();
        if (firstFrame)
        {
            Sheep sheep = world.sheep1;
            trans = 0;
            turnTowards(sheep.getX(), sheep.getY());
            firstFrame = false;
        }
        else
        {
            trans+=((spawnTime)%40)/2-8;
            if (trans<0)
                trans = 0;
            if (trans>255)
            {
                spawning = false;
                getImage().setTransparency(255);
            }
            else
            {
                getImage().setTransparency(trans);
            }
        }
        if (isTouching(Bullet.class))
        {
            trans = 0;
        }
        spawnTime ++;
    }
    public void mouvement()
    {
        MyWorld world = (MyWorld)getWorld();
        world.showText("", getX(), getY());
        move(2);
        Sheep sheep = (Sheep)((MyWorld)getWorld()).sheep1;
        int oldRotation = getRotation();
        List<Badguy> objs = getObjectsInRange(60, Badguy.class);
        if (objs.size()>0)
        {
            Badguy target = objs.get(0);
            turnTowards(target.getX(), target.getY());
            turn(180);
        }
        else
        {
            turnTowards(sheep.getX(), sheep.getY());
        }
        int newRotation = getRotation();
        setRotation(oldRotation);
        Random rndm = new Random();
        
        if (sheep.visible)
        {
            int clockDist = newRotation - oldRotation;
            if (clockDist<0)
                clockDist+=360;
            int invertDist = oldRotation - newRotation;
            if (invertDist<0)
                invertDist+=360;
            int angle = (Math.abs(clockDist-invertDist)<45) ? rndm.nextInt(1) : rndm.nextInt(2)+1;
            turn((clockDist>invertDist) ? -angle : angle);
        }
        else
        {
            turning += (rndm.nextInt(3)-1);
            turning -= turning/30;
            turn(turning/5);
        }
        
    }
    public void touchSheep()
    {
        MyWorld world = (MyWorld)getWorld();
        List<Sheep> objs = getObjectsInRange(60, Sheep.class);
        if ( objs.size()>0 && (world.sheep1.visible))
        {
            if (stop>3 && !world.godMod)
            {
                world.gameState="SetGameOver";
                world.game = false;
            }
            stop++;
        }
    }
    public void die()
    {
        MyWorld world = (MyWorld)getWorld();
        if (isTouching(Bullet.class))
        {
            Bullet deadlyBullet = (Bullet)getOneIntersectingObject(Bullet.class);
            if (deadlyBullet.shot && deadlyBullet.deadly)
            {
                deadlyBullet.strenght -= 1;
                if (deadlyBullet.strenght<=0)
                {
                    world.removeObject(deadlyBullet);
                }
                world.addObject(new Banana(), getX(), getY());
                world.sheep1.score++;
                world.removeObject(this);
            }
        } 
    }
}
    