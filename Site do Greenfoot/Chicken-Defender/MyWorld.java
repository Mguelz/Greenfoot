import greenfoot.*;

public class MyWorld extends World
{
    int timer, newtime, suntimer, timeprogress;
    Actor dia, progress;
    int dias;
    boolean dead = false;
    int[] spiderlogs = new int[8];
    
    public MyWorld()
    {
        super(800, 550, 1);
        
        addObject(new shop(), 350, 40);
        showText("100", 350, 90);
        addObject(new redshop(), 450, 40);
        showText("300", 450, 90);
        addObject(new greenshop(), 550, 40);
        showText("500", 550, 90);
        addObject(new minershop(), 650, 40);
        showText("50", 650, 90);
        
        progress = new progress();
        addObject(progress, 400, 525);
        progress.getImage().scale(700, 25);
        showText("Progress", 400, 525);
        
        
        
        timer = 250 + Greenfoot.getRandomNumber(200); //250
        newtime = 400;
        suntimer = Greenfoot.getRandomNumber(100);
        dias = 0;
        timeprogress = 0;
        
        Greenfoot.setSpeed(45);
    }
    
    public void act()
    {
       timer--;
       suntimer--;
       timeprogress++;
       progress.getImage().setColor(Color.MAGENTA);
       progress.getImage().fillRect(0, 0, (int)(timeprogress/10), 50);
       showText("Diamonds: " + dias, 200, 50);
       
       if(!dead)
       {
          if(timer < 0 && timeprogress < 7000)
          {
               int y = Greenfoot.getRandomNumber(8) * 50 + 125;
               Actor zomb;
               if(timeprogress >= 3000)
               {
                   if(timeprogress >= 5000)
                   {
                       if(Greenfoot.getRandomNumber(5) == 1)
                       {
                           addObject(new Spiderpink(), 750, y);
                       }
                       else if(Greenfoot.getRandomNumber(2) == 1)
                       {
                           zomb = new Spiderblue();
                           addObject(new Spiderblue(), 750, y);
                       }
                       else
                       {
                           zomb = new Spiderblack();
                           addObject(new Spiderblack(), 750, y);
                       }
                   }
                   else if(Greenfoot.getRandomNumber(2) == 1)
                   {
                       zomb = new Spiderblue();
                       addObject(new Spiderblue(), 750, y);
                   }
                   else
                   {
                       zomb = new Spiderblack();
                       addObject(new Spiderblack(), 750, y);
                   }
               }
               else
               {
                   zomb = new Spiderblack();
                   addObject(new Spiderblack(), 750, y);
               }
               y = (y-125)/50;
               spiderlogs[y] = spiderlogs[y] + 1;
               
               timer = newtime;
               if(newtime > 60)
               {
                   newtime -= 20;
               }
               else
               {
                   if(newtime > 40)
                   {
                       newtime--;
                   }
               }
          }
          if(suntimer < 0)
          {
               addObject(new dia(), Greenfoot.getRandomNumber(800), Greenfoot.getRandomNumber(500));
               suntimer = Greenfoot.getRandomNumber(100) + 100;
          }
          if(timeprogress > 7000 && checkwon())
          {
              Greenfoot.setWorld(new EndWorld(true));
          }
       }
    }
    
    public void clickeddia()
    {
        dias += 25;
    }
    
    public int getdias()
    {
        return dias;
    }
    
    public void setsol(int costs)
    {
        dias -= costs;
    }
    
    
    public boolean gibdead()
    {
        return dead;
    }
    
    public boolean checkwon()
    {
        for(int i = 0; i < 8; i++)
        {
            if(spiderlogs[i] != 0)
            {
               return false; 
            }
        }
        
        return true;
    }
}
