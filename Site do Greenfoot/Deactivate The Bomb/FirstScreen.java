import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.List;

/**
 * Write a description of class FirstScreen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FirstScreen extends World
{
    private Key playButton = new Key("button");
    private Key myInfoButton = new Key("button");
    private Key guideButton = new Key("button");
    private Key exitGuideButton = new Key();
    
    private Board guideBoard = new Board();
    private Board myInfoBoard = new Board();
    private GreenfootImage exitButtonImage = exitGuideButton.getImage();
    
    private GreenfootSound backgroundSound = new GreenfootSound("mixkit-percussion-tick-tock-timer-1047.wav");
    private GreenfootSound clickSound = new GreenfootSound("Mouse_Click_2-fesliyanstudios.com.wav");
    
    /**
     * Constructor for objects of class FirstScreen.
     * 
     */
    public FirstScreen()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1);
        prepare();
        //backgroundSound.playLoop();
        if (UserInfo.isStorageAvailable()) {
            UserInfo myInfo = UserInfo.getMyInfo();
            myInfo.setInt(0, myInfo.getInt(0) + 1);
            myInfo.store();
        }
    }
    
    public void act(){
        checkClicks();
        checkKeyPress();
    }
    
    private void checkClicks(){
        if(Greenfoot.mouseClicked(null) && Greenfoot.getMouseInfo().getActor() != null){
            if(Greenfoot.getMouseInfo().getActor().getClass() == Key.class){
                clickSound.play();
                Key key = (Key)Greenfoot.getMouseInfo().getActor();
                if(key.equals(playButton)){
                    Greenfoot.setWorld(new MyWorld());
                    backgroundSound.pause();
                } else if(key.equals(myInfoButton)){
                    GreenfootImage img = myInfoBoard.getImage();
                    img.setColor(new Color(125, 177, 215));
                    img.fill();
                    img.setColor(new Color(50, 10, 255));
                    img.setFont(new Font(35));
                    img.drawString("Profile", 5 , 30);
                    img.setFont(new Font(30));
                    img.drawString("Records", img.getWidth()/40, 180);
                    img.fillRect(165, 0, 106, 106);
                    
                    img.setColor(Color.BLUE);
                    img.fillRect((int)(getWidth()/2.2) - 3, -5, 6, getHeight() + 3);
                    img.setColor(Color.BLUE);
                    img.setFont(new Font(25));
                    img.drawString("Deactivate\nExplode\nPlayed\nAborted Play\nBest Time\nLast Time", img.getWidth()/40, 210);
                    if (UserInfo.isStorageAvailable()) {
                        UserInfo myInfo = UserInfo.getMyInfo();
                        int deactivations = myInfo.getInt(2);
                        int explosions =  myInfo.getInt(3);
                        int plays = myInfo.getInt(1);
                        int aborts = plays - (deactivations + explosions);
                        int bestTime = myInfo.getInt(4);
                        img.drawString(""+ deactivations + "x\n" + explosions + "x\n" + plays + "x\n" + aborts + "x\n" + bestTime + "s\n" + myInfo.getInt(5) + "s", img.getWidth()/2 - img.getWidth()/6, 210);
                        img.drawString("Name:\n" + myInfo.getUserName(), 5, 110);
                        img.setFont(new Font(20));
                        img.drawString("Lucky Trials: " + myInfo.getInt(7) + "x", 5, 70);
                        img.setFont(new Font(25));
                        GreenfootImage myImg = myInfo.getUserImage();
                        myImg.scale(100, 100);
                        img.drawImage(myImg, 168, 3);
                    } else {
                        img.drawString("- - -" + "\n" +  "- - -" + "\n" +  "- - -\n- - -\n- - -\n- - -", img.getWidth()/2 - img.getWidth()/6, 210);
                    }
                    img.drawString("Leaderboard", 280, 25);
                    img.drawString("Name", 280, 50);
                    img.setFont(new Font(17));
                    img.drawString("Best Time", 528, 50);
                    img.setFont(new Font(25));
                    
                    //displayRankBoard(img, 280, 80);
                    
                    addObject(myInfoBoard, getWidth()/2, getHeight()/2);
                    addObject(exitGuideButton, getWidth() - exitButtonImage.getWidth()/2, exitButtonImage.getHeight()/2);
                } else if(key.equals(guideButton)){
                    guideBoard.displayGuide();
                    addObject(guideBoard, getWidth()/2, getHeight()/2);
                    addObject(exitGuideButton, getWidth() - exitButtonImage.getWidth()/2, exitButtonImage.getHeight()/2);
                } else if(key.equals(exitGuideButton)){
                    removeObject(exitGuideButton);
                    removeObject(guideBoard);
                    removeObject(myInfoBoard);
                }
            }
        }
    }
    
    public void displayRankBoard(GreenfootImage img, int x, int y){
        if (UserInfo.isStorageAvailable()) {
            UserInfo myInfo = UserInfo.getMyInfo();
            List user = UserInfo.getTop(13);
            boolean amAmongTop = false;
            int a = user.size();
            for(int i = 0; i < a; i++){
                UserInfo playerData = (UserInfo)user.get(i);
                int score = playerData.getScore();
                if(score != 0){
                    if(myInfo != null && playerData.getUserName().equals(myInfo.getUserName())){
                        img.drawRect(x - 2, (y - 20)+ (i * 25), 320, 25);
                        amAmongTop = true;
                    }
                }
            }
            
            if(!amAmongTop){
                user = UserInfo.getTop(8);
                a = user.size();
            }
            
            for(int i = 0; i < a; i++){
                UserInfo playerData = (UserInfo)user.get(i);
                int rank =playerData.getRank();
                int score = playerData.getScore();
                if(score < 0){
                    if(myInfo != null && playerData.getUserName().equals(myInfo.getUserName())){
                        img.drawRect(x - 2, (y - 20)+ (i * 25), 320, 25);
                        amAmongTop = true;
                    }
                    img.drawString(rank + ". " + playerData.getUserName(), x, y + (i * 25));
                    img.drawString("" + Math.abs(score) + "s", x + 278, y + (i * 25));
                }
            }
            
            if(!amAmongTop && myInfo.getScore() < 0){
                List neighbors = UserInfo.getNearby(3);
                y = 315;
                int b = neighbors.size();
                img.drawString("My Rank", x, y - 27);
                for(int j = 0; j < b; j++){
                    UserInfo playerData = (UserInfo)neighbors.get(j);
                    int rank = playerData.getRank();
                    int score = playerData.getScore();
                    if(score != 0){
                        if(myInfo != null && playerData.getUserName().equals(myInfo.getUserName())){
                            img.drawRect(x - 2, (y - 20)+ (j * 25), 320, 25);
                            if(score == -100){
                                img.drawString(rank + ". " + playerData.getUserName(), x, y + (j * 25));
                                img.drawString("" + "--", x + 278, y + (j * 25));
                                continue;
                            }
                        }
                        img.drawString(rank + ". " + playerData.getUserName(), x, y + (j * 25));
                        img.drawString("" + Math.abs(score) + "s", x + 278, y + (j * 25));
                    }
                }
            }
        }
    }
    
    private void checkKeyPress(){
        String key = Greenfoot.getKey();
        if(key != null){
            if(key.equals("space") && getObjects(Board.class).isEmpty()){
                Greenfoot.setWorld(new MyWorld());
                backgroundSound.pause();
            } else if(key.equals("space") && !getObjects(Board.class).isEmpty()){
                removeObject(exitGuideButton);
                removeObject(guideBoard);
                removeObject(myInfoBoard);
            }
        }
    }
        
    private void prepare(){
        GreenfootImage playButtonImg = playButton.getImage();
        playButtonImg.setColor(new Color(136, 0 ,21));
        playButtonImg.setFont(new Font(24));
        playButtonImg.drawString("PLAY", playButtonImg.getWidth()/4, playButtonImg.getHeight() - 10);
        playButton.setImage(playButtonImg);
        addObject(playButton, 130, 260);
        
        GreenfootImage myInfoButtonImg = myInfoButton.getImage();
        myInfoButtonImg.setColor(new Color(136, 0 ,21));
        myInfoButtonImg.setFont(new Font(24));
        myInfoButtonImg.drawString("RANK", myInfoButtonImg.getWidth()/4, myInfoButtonImg.getHeight() - 10);
        myInfoButton.setImage(myInfoButtonImg);
        addObject(myInfoButton, 130, 270 + myInfoButtonImg.getHeight());
        
        GreenfootImage guideButtonImg = guideButton.getImage();
        guideButtonImg.setColor(new Color(136, 0 ,21));
        guideButtonImg.setFont(new Font(24));
        guideButtonImg.drawString("GUIDE", guideButtonImg.getWidth()/4, guideButtonImg.getHeight() - 10);
        guideButton.setImage(guideButtonImg);
        addObject(guideButton, 130, 320 + guideButtonImg.getHeight());
        
        exitButtonImage.scale(exitButtonImage.getWidth()*2, exitButtonImage.getHeight()*2);
        exitButtonImage.setColor(Color.RED);
        exitButtonImage.setFont(new Font(23));
        exitButtonImage.drawString("X", exitButtonImage.getWidth()/4, exitButtonImage.getHeight() - 5);
        exitGuideButton.setImage(exitButtonImage);
        
        Greenfoot.start();
    }
}
