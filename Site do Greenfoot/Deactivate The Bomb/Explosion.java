import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Explosion here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Explosion extends Actor
{
    private GreenfootImage explosionImg;
    
    private int x = 1;
    
    public Explosion(){
        GreenfootImage img = getImage();
        img.scale(img.getWidth()/10, img.getHeight()/10);
        setImage(img);
        explosionImg = img;
    }
    public void addedToWorld(World world){
        Greenfoot.playSound("explosion.wav");
    }
    /**
     * Act - do whatever the Explosion wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        explode();
    }
    
    private void explode(){
        if(x < 4){
            explosionImg.scale(explosionImg.getWidth() * x, explosionImg.getHeight() * x);
            setImage(explosionImg);
            x++;
        } else if(x == 4){
            setImage("explosion1.png");
            x++;
        }
        GreenfootImage background = getWorld().getBackground();
        background.setColor(new Color(136, 0 ,21));
        background.setFont(new Font(35));
        background.drawString("BOMB EXPLODED", 10, 50);
        getWorld().setBackground(background);
    }
}
