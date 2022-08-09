import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class redshooter extends shooter
{  
   public redshooter()
   {
       timer = 70;
       setup();
   }
    
   public void act() 
   {
       place();
       shoot(new redegg());
   }    
}
