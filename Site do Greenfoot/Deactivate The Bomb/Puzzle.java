import greenfoot.*;
import java.util.*;
/**
 * Write a description of class Puzzle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Puzzle  
{
    private int rand = (int)(Math.random()*100);
    private int n = 10;
    
    public boolean isDeactivated = false;
    
    private Screen screen2;
    private Screen screen3;
    
    public Puzzle(Screen screen2, Screen screen3){
        this.screen2 = screen2;
        this.screen3 = screen3;
    }
   private void playPuzzle(int guess){
        if(rand == 0){
            rand = 99;
        }
        if(n>0){
            n--;
            GreenfootImage screen2Img = new GreenfootImage(screen2.getImage());
            if(n > 5)screen2Img.setColor(Color.GREEN);
            else if (n > 3)screen2Img.setColor(Color.ORANGE);
            else screen2Img.setColor(Color.RED);
            screen2Img.drawString("" + n, 2, 17);
            screen2.setImage(screen2Img);
            if (guess == rand)
            {
                isDeactivated = true;
                screen3.setImage("deactivated.png");
            }
            else {
                int m = Math.abs(guess-rand);
                if (m<=10)
                {
                    screen3.setImage("soClose.png");
                }else if(m<=20)
                {
                    screen3.setImage("close.png");
                }else{
                    screen3.setImage("tryAgain.png");
                }
            }
        }
    }
   
   public void checkGuess(int guess){
       playPuzzle(guess);
   }
   
   public int getTrials(){
       return n;
   }
}
