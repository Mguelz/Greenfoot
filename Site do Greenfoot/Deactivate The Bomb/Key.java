import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Keys here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Key extends Actor
{
    private int keyValue;
    public Key(){
    }
    public Key(int keyNum){
        keyValue = keyNum;
        if(keyNum < 0 || keyNum > 9){
            throw new IllegalArgumentException("Key Number must be 0-9");
            }
        setImage(keyNum + "key.png");
    }
    public Key(String keyName){
        setImage(keyName + "Key.png");
    }
    
    public int getKeyValue(){
        return keyValue;
    }
}
