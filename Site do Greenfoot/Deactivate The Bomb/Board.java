import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Board here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Board extends Actor
{
    public void displayInfo(int countDownTime,int time, int min, int sec, int millis, int trialsRemaining){
        int playTime = countDownTime - time;
        int playMinutes = playTime/60;
        int playSeconds = playTime%60;
        int playMillis = 99 - millis;
        int trials = 10 - trialsRemaining;
        
        GreenfootImage img = getImage();
        img.setColor(new Color(50, 50, 255));
        img.setFont(new Font(50));
        img.drawString("Bomb Deactivated", img.getWidth()/8 , 90);
        
        img.setColor(Color.BLUE);
        img.setFont(new Font(30));
        img.drawString("Time\n\nTrials\n\nBest Time", img.getWidth()/7, 180);
        if (UserInfo.isStorageAvailable()) {
            UserInfo myInfo = UserInfo.getMyInfo();
            img.drawString("0 : "+ playMinutes + " : " + playSeconds + "." + playMillis + "\n\n" + trials + "\n\n" + myInfo.getInt(4) + "s", img.getWidth()/2 + img.getWidth()/7, 180);
        } else {
            img.drawString("0 : "+ playMinutes + " : " + playSeconds + "." + playMillis + "\n\n" + trials + "\n\n- - -", img.getWidth()/2 + img.getWidth()/7, 180);
        }
    }
    
    public void displayGuide(){
        setImage("guideScreen.png");
    }
}
