import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Sheep extends Actor
{
    int speed=5;
    int turning=0;
    boolean loadingShot = false;
    int loadShotTime = 0;
    int loadShotDELAY = 25;
    int bulletsRowsLoaded = 0;
    int shootCooldown=0;
    int inviCostDelay;
    int inviTime;
    boolean canInvi=true;
    int energy= 0;
    int score = 0;
    GreenfootImage image;
    int width=65, height=50;
    int transDelay = 100;
    boolean visible = true;
    boolean spawning = true;
    int trans = 0;
    
    public Sheep()
    {
        image = getImage();
        image.setTransparency(0);
        image.scale(width, height);
    }
    public void act() 
    {
        MyWorld world = (MyWorld)getWorld();
        if (spawning)
        {
            spawn();
        }
        if (world.game)
        {
            move(speed);
            turnSheep();
            eatBanana();
            dash();
            invisibility();
            shoot();
        }
    }
    public void spawn()
    {
        trans += 2;
        if (trans>255)
        {
            trans = 255;
            spawning = false;
        }
        getImage().setTransparency(trans);
    }
    public void turnSheep() 
    {
        if (Greenfoot.isKeyDown("left"))
        {
            if (turning > -5)
            {
                turning-=1;
            }    
        }
        else if (Greenfoot.isKeyDown("right"))
        {
            if (turning < 5)
            {
                turning+=1;
            }
        }
        else
        {
            turning=0;
        }
        turn(turning);
    }
    public void eatBanana()
    {
        MyWorld world = (MyWorld)getWorld();
        if ( isTouching(Banana.class) )
        {
            Banana ateBanana = (Banana)getOneIntersectingObject(Banana.class);
            energy+=world.costs.get("banana")*ateBanana.nBananas;
            world.removeObject(ateBanana);
        }   
    }
    public void dash()
    {
        MyWorld world = (MyWorld)getWorld();
        if (speed>5) {
            speed--;
            setImage("sheep.png");
            getImage().scale(width-speed, height-speed);
            if (!visible)
                getImage().setTransparency(100);
        }
        else
        {
            if (Greenfoot.isKeyDown("z") && energy>=-world.costs.get("dash"))
            {
                speed+=18;
                energy+=world.costs.get("dash");
            }
        }
    }
    public void invisibility()
    {
        MyWorld world = (MyWorld)getWorld();
        if (Greenfoot.isKeyDown("a") && visible && canInvi)
        {
            visible = false;
            canInvi = false;
            inviTime = 0;
        }
        if ((Greenfoot.isKeyDown("a") && (!visible) && canInvi) || (energy<=0) || visible || loadingShot)
        {
            getImage().setTransparency(255);
            visible = true;
            canInvi = false;
        }
        if (!visible)
        {
            getImage().setTransparency(100);
            if (inviTime>(inviCostDelay))
            {
                energy --;
                inviTime = 0;
            }
            if (world.time%10==0 && inviCostDelay>1)
                inviCostDelay --;
            inviTime++;
        }
        else
        {
            if (world.time%10==0 && inviCostDelay<-world.costs.get("furtive"))
                inviCostDelay ++;
        }
        if ((!canInvi) && (!Greenfoot.isKeyDown("a")))
            canInvi = true;
    }
    public void shoot()
    {
        MyWorld world = (MyWorld)getWorld();
        if (Greenfoot.isKeyDown("space"))
        {
            loadingShot = true;
            int bulletsToLoad = (bulletsRowsLoaded==0 || bulletsRowsLoaded==4) ? 1 : 2;
            if ((loadShotTime%loadShotDELAY)==0 && bulletsRowsLoaded<5 && energy>=bulletsToLoad)
            {
                for (int i = 0; i<bulletsToLoad ; i++)
                {
                    world.spawnBullet(getX(), getY(),(i==0 ? 1 : -1)*(bulletsRowsLoaded*45), bulletsRowsLoaded==0);
                    energy--;
                }
                bulletsRowsLoaded ++;
            }
            loadShotTime ++;
        }
        else if (loadingShot)
        {
            loadingShot = false;
            bulletsRowsLoaded = 0;
            loadShotTime = 0;
        }
    }
}