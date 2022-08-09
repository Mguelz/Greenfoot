
import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
import java.util.List;

public class Bullet extends Actor
{
    int score = 0;
    boolean kill = false;
    int speed = 4;
    int loadTime = 0;
    boolean loaded = false;
    int rotationAdd;
    boolean shot = false;
    boolean die = false;
    int strenght = 1;
    boolean deadly = false;
    int deadlyTimer = 0;
    
    public Bullet(int rotation, boolean first)
    {
        getImage().setTransparency(0);
        updateSprite();
        rotationAdd = rotation;
        if (first)
        {
            loaded = true;
        }
    }
    public void act() 
    {
        MyWorld world = (MyWorld)getWorld();
        if (world.game)
        {
            if (!shot)
            {
                loading();
            }
            if (shot)
            {
                if (!deadly)
                {
                    updateDeadliness();
                }
                mouvement();
                borderDetection();
            }
        }
        if (die)
        {
            world.removeObject(this);
        }
    }    
    public void loading()
    {
        MyWorld world = (MyWorld)getWorld();
        Sheep sheep = world.sheep1;
        getImage().setTransparency(0);
        setLocation(sheep.getX(), sheep.getY());
        setRotation(sheep.getRotation());
        move(5);
        setRotation(sheep.getRotation()+rotationAdd);
        move(35);
        turn(-45);
        if (!loaded)
        {
            getImage().setTransparency(90);
            if (loadTime>sheep.loadShotDELAY)
            {
                loaded = true;
            }
            if (!sheep.loadingShot)
            {
                die = true;
            }
        }
        else
        {
            if (sheep.loadingShot)
            {
                if (loadTime!=0 && loadTime%sheep.loadShotDELAY==0 && strenght<4)
                {
                    strenght ++;
                    updateSprite();
                }
            }
            else
            {
                shot = true;
            }
            getImage().setTransparency(210);
        }
        loadTime ++;
    }
    public void updateDeadliness()
    {
        if (deadlyTimer>5)
        {
            getImage().setTransparency(255);
            deadly = true;
        }
        else
        {
            getImage().setTransparency(210+deadlyTimer*3);
        }
        deadlyTimer ++;
    }
    public void mouvement()
    {
        setRotation(getRotation()+45);
        move(speed);
        if (speed<20)
        {
            speed+=2;
        }
        setRotation(getRotation()-45);
        Random randomNo = new Random();
        turn(randomNo.nextInt(3)-1);
    }
    public void borderDetection()
    {
        if (isAtEdge())
        {
            MyWorld world = (MyWorld)getWorld();
            world.removeObject(this);
        }
    }
    public void updateSprite()
    {
        setImage("anchor.png");
        getImage().scale(30+strenght*5, 30+strenght*5);
    }
}