import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Screen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Screen extends Actor
{
    private GreenfootImage baseImage = new GreenfootImage(getImage());
    private int typedValue;
    
    public Screen(){
    }
    public Screen(int screenNum){
        setImage("screen" + screenNum + ".png");
        baseImage = getImage();
    }
    
    public void updateImage(String text, int x, int y){
        GreenfootImage screenImg = new GreenfootImage(getBaseImage());
        screenImg.setColor(Color.RED);
        screenImg.setFont(new Font(20));
        screenImg.drawString(text, x, y);
        setImage(screenImg);
    }
    
    public GreenfootImage getBaseImage(){
        return baseImage;
    }
    
    public int getTypedValue(){
        return typedValue;
    }
    public void setTypedValue(int val){
        typedValue = val;
    }
}
