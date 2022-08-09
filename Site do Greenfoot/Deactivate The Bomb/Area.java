import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Area here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Area extends World
{
    private boolean hasExploded = false;
    
    private Key playAgainButton = new Key("button");
    private Key mainMenuButton = new Key("button");
    
    private SimpleTimer timer = new SimpleTimer();
    private GreenfootSound clickSound = new GreenfootSound("Mouse_Click_2-fesliyanstudios.com.wav");
    
    /**
     * Constructor for objects of class Area.
     * 
     */
    public Area()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        
        if (UserInfo.isStorageAvailable()) {
            UserInfo myInfo = UserInfo.getMyInfo();
            myInfo.setInt(3, myInfo.getInt(3) + 1);
            myInfo.store();
        }
    }
    
    public void act(){
        explode();
        checkClicks();
        checkKeyPress();
    }
    
    private void explode(){
        if(hasExploded != true){
            Greenfoot.delay(100);
            addObject(new Explosion(), 430, 280);
            
            GreenfootImage playAgainButtonImg = playAgainButton.getImage();
            playAgainButtonImg.setColor(new Color(136, 0 ,21));
            playAgainButtonImg.setFont(new Font(24));
            playAgainButtonImg.drawString("PLAY AGAIN", 5, playAgainButtonImg.getHeight() - 10);
            addObject(playAgainButton, playAgainButtonImg.getWidth()/2, getHeight() - playAgainButtonImg.getHeight()/2);
            
            GreenfootImage mainMenuButtonImg = mainMenuButton.getImage();
            mainMenuButtonImg.setColor(new Color(136, 0 ,21));
            mainMenuButtonImg.setFont(new Font(24));
            mainMenuButtonImg.drawString("MAIN MENU", 6, playAgainButtonImg.getHeight() - 10);
            addObject(mainMenuButton, (int)(mainMenuButtonImg.getWidth() * 1.6), getHeight() - mainMenuButtonImg.getHeight()/2);
            
            hasExploded = true;
        }
    }
    
    private void checkClicks(){
        if(Greenfoot.mouseClicked(null) && Greenfoot.getMouseInfo().getActor() != null){
            if(Greenfoot.getMouseInfo().getActor().getClass() == Key.class){
                clickSound.play();
                Key key = (Key)Greenfoot.getMouseInfo().getActor();
                if(key.equals(playAgainButton)){
                    Greenfoot.setWorld(new MyWorld());
                } else if(key.equals(mainMenuButton)){
                    Greenfoot.setWorld(new FirstScreen());
                }
            }
        }
    }
    
    private void checkKeyPress(){
        String key = Greenfoot.getKey();
        if(key != null && key.equals("space")){
            Greenfoot.setWorld(new MyWorld());
        }
    }
}
