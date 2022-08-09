import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Bomb here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Bomb extends Actor
{
    private int countDownTime = 63;
    private int time = countDownTime;
    private boolean isCounting = false;
    
    private int countInMinutes = time/60;
    private int countInSeconds = time % 60;
    private int millis;
    
    private Screen screen = new Screen();
    private Screen screen2 = new Screen(2);
    private Screen screen3 = new Screen(3);
    
    private Key key = new Key(1);
    private Key key2 = new Key(2);
    private Key key3 = new Key(3);
    private Key key4 = new Key(4);
    private Key key5 = new Key(5);
    private Key key6 = new Key(6);
    private Key key7 = new Key(7);
    private Key key8 = new Key(8);
    private Key key9 = new Key(9);
    private Key key10 = new Key(0);
    private Key key11 = new Key("enter");
    private Key key12 = new Key("delete");
    private Key key13 = new Key("reset");
    private Key resultButton = new Key("button");
    private Key playAgainButton = new Key("button");
    private Key mainMenuButton = new Key("button");
    
    private SimpleTimer timer = new SimpleTimer();
    private Puzzle puzzle = new Puzzle(screen2, screen3);
    private Board endScreen = new Board();
    private GreenfootSound timerSound = new GreenfootSound("mixkit-alarm-clock-beep-988(1).wav");
    private GreenfootSound clickSound = new GreenfootSound("Mouse_Click_2-fesliyanstudios.com.wav");
    private GreenfootSound backgroundSound = new GreenfootSound("mixkit-percussion-tick-tock-timer-1047.wav");
    
    private SimpleTimer initTimer = new SimpleTimer();
    private int x = 1;
    
    public Bomb(){
        if (UserInfo.isStorageAvailable()) {
            UserInfo myInfo = UserInfo.getMyInfo();
            myInfo.setInt(1, myInfo.getInt(1) + 1);
            myInfo.store();
        }
    }
    public void addedToWorld(World world){
        
        getWorld().addObject(screen, getX() - 25, getY() - 60);
        getWorld().addObject(screen2, getX() - 75, getY() + 25);
        getWorld().addObject(screen3, getX() + 30, getY() + 20);

        getWorld().addObject(key,293,205);
        getWorld().addObject(key2,320,205);
        getWorld().addObject(key3,347,205);
        getWorld().addObject(key4,374,205);
        getWorld().addObject(key5,401,205);
        getWorld().addObject(key6,293,228);
        getWorld().addObject(key7,320,228);
        getWorld().addObject(key8,347,228);
        getWorld().addObject(key9,374,228);
        getWorld().addObject(key10,401,228);
        getWorld().addObject(key11,237,219);
        getWorld().addObject(key12,262,211);
        getWorld().addObject(key13,262,227);
        
        GreenfootImage screenImg = new GreenfootImage(screen.getBaseImage());
        screenImg.setColor(Color.RED);
        screenImg.setFont(new Font(20));
        screenImg.drawString("00:0" + countInMinutes + ":0" + countInSeconds + "." + "0" + millis, 1, 18);
        screen.setImage(screenImg);
        
        GreenfootImage screen2Img = new GreenfootImage(screen2.getBaseImage());
        screen2Img.setColor(Color.RED);
        screen2Img.setFont(new Font(20));
        screen2Img.drawString("" + 0 + ""+ 0, 15, 25);
        screen2.setImage(screen2Img);
    }
    
    /**
     * Act - do whatever the Bomb wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        count();
        checkClicks();
        checkKeyPress();
    }
    
    private void count(){
        if(!isCounting){
            if(initTimer.millisElapsed() > 500){
                if(x == 0){
                    isCounting = true;
                }
                x--;
                initTimer.mark();
            }
        } else if(!puzzle.isDeactivated){
            if(time == 0 && millis == 0){
                timerSound.stop();
                Greenfoot.setWorld(new Area());
            } else if(timer.millisElapsed() > 5){
                if(countInSeconds == 0 && countInMinutes != 0 && millis == 0){
                    timerSound.play();
                    countInMinutes--;
                    time--;
                    millis += 99;
                    countInSeconds += 59;
                }
                if(millis == 0){
                    if(time > 3)timerSound.play();
                    else timerSound.playLoop();
                    countInSeconds--;
                    time--;
                    millis += 99;
                } else millis--;
                if(String.valueOf(countInMinutes).length() == 1 && String.valueOf(countInSeconds).length() == 1){
                    screen.updateImage("00:0" + countInMinutes + ":0" + countInSeconds + "." + millis, 1, 18);
                } else if(String.valueOf(countInMinutes).length() == 1 && String.valueOf(countInSeconds).length() == 2){
                    screen.updateImage("00:0" + countInMinutes + ":" + countInSeconds + "." + millis, 1, 18);
                } else if(String.valueOf(countInMinutes).length() == 2 && String.valueOf(countInSeconds).length() == 1){
                    screen.updateImage("00:" + countInMinutes + ":0" + countInSeconds + "." + millis, 1, 18);
                } else screen.updateImage("00:" + countInMinutes + ":" + countInSeconds + "." + millis, 1, 18);
                timer.mark();
            }
        }
    }
    
    private void checkClicks(){
        if(Greenfoot.mouseClicked(null) && Greenfoot.getMouseInfo().getActor() != null){
            if(Greenfoot.getMouseInfo().getActor().getClass() == Key.class){
                int a = String.valueOf(screen2.getTypedValue()).length();
                Key key = (Key)Greenfoot.getMouseInfo().getActor();
                if(!puzzle.isDeactivated && (screen2.getTypedValue() != 0) && (key.equals(key11) || key.equals(key12) || key.equals(key13))){
                    if(key.equals(key11)){
                        puzzle.checkGuess(screen2.getTypedValue());
                        checkBombStatus();
                    } else if(key.equals(key12)){
                        if(a == 1){
                            screen2.setTypedValue(0);
                            screen2.updateImage("" + "0" + screen2.getTypedValue(), 15, 25);
                        } else if(a == 2){
                            screen2.setTypedValue(screen2.getTypedValue()/10);
                            screen2.updateImage("" + "0" + screen2.getTypedValue(), 15, 25);
                        }
                    } else if (key.equals(key13)){
                        screen2.setTypedValue(0);
                        screen2.updateImage("" + "0" + screen2.getTypedValue(), 15, 25);
                    }
                } else if(puzzle.isDeactivated){
                    clickSound.play();
                    if(key.equals(resultButton)){
                        getWorld().addObject(endScreen, getWorld().getWidth()/2, getWorld().getHeight()/2);
                        endScreen.displayInfo(countDownTime, time, countInMinutes, countInSeconds, millis, puzzle.getTrials());
                        
                        GreenfootImage playAgainButtonImg = playAgainButton.getImage();
                        playAgainButtonImg.setColor(new Color(136, 0 ,21));
                        playAgainButtonImg.setFont(new Font(24));
                        playAgainButtonImg.drawString("PLAY AGAIN", 5, playAgainButtonImg.getHeight() - 10);
                        getWorld().addObject(playAgainButton, getWorld().getWidth() - (int)(playAgainButtonImg.getWidth() * 1.6), getWorld().getHeight() - playAgainButtonImg.getHeight()/2);
                        
                        GreenfootImage mainMenuButtonImg = mainMenuButton.getImage();
                        mainMenuButtonImg.setColor(new Color(136, 0 ,21));
                        mainMenuButtonImg.setFont(new Font(24));
                        mainMenuButtonImg.drawString("MAIN MENU", 7, mainMenuButtonImg.getHeight() - 10);
                        getWorld().addObject(mainMenuButton, getWorld().getWidth() - mainMenuButtonImg.getWidth()/2, getWorld().getHeight() - mainMenuButtonImg.getHeight()/2);

                    } else if(key.equals(playAgainButton)){
                        backgroundSound.stop();
                        Greenfoot.setWorld(new MyWorld());
                    } else if(key.equals(mainMenuButton)){
                        backgroundSound.stop();
                        Greenfoot.setWorld(new FirstScreen());
                    }
                } else if(a < 2){
                    if(screen2.getTypedValue() == 0){
                        screen2.setTypedValue(key.getKeyValue());
                        screen2.updateImage("" + "0" + screen2.getTypedValue(), 15, 25);
                    } else if(screen2.getTypedValue() < 100){
                        screen2.setTypedValue(screen2.getTypedValue()*10 + key.getKeyValue());
                        if(screen2.getTypedValue() < 100){
                            screen2.updateImage("" + screen2.getTypedValue(), 15, 25);
                        }
                    }
                }
            }
        }
    }
    
    private void checkKeyPress(){
        String key = Greenfoot.getKey();
        if(key != null){
            int a = String.valueOf(screen2.getTypedValue()).length();
            if(!puzzle.isDeactivated && screen2.getTypedValue() != 0 && !isNumericalKey(key)){
                if(key.equals("enter")){
                    puzzle.checkGuess(screen2.getTypedValue());
                    checkBombStatus();
                } else if(key.equals("backspace")){
                    if(a == 1){
                        screen2.setTypedValue(0);
                        screen2.updateImage("" + "0" + screen2.getTypedValue(), 15, 25);
                    } else if(a == 2){
                        screen2.setTypedValue(screen2.getTypedValue()/10);
                        screen2.updateImage("" + "0" + screen2.getTypedValue(), 15, 25);
                    }
                } else if (key.equals("space")){
                    screen2.setTypedValue(0);
                    screen2.updateImage("" + "0" + screen2.getTypedValue(), 15, 25);
                } 
            }else if(a < 2){
                if(isNumericalKey(key)){
                    int keyValue = Integer.parseInt(key);
                    if(screen2.getTypedValue() == 0){
                        screen2.setTypedValue(keyValue);
                        screen2.updateImage("" + "0" + screen2.getTypedValue(), 15, 25);
                    } else if(screen2.getTypedValue() < 100){
                        screen2.setTypedValue(screen2.getTypedValue()*10 + keyValue);
                        if(screen2.getTypedValue() < 100){
                            screen2.updateImage("" + screen2.getTypedValue(), 15, 25);
                        }
                    }
                }
            }
            if(puzzle.isDeactivated){
                if(key.equals("space") && getWorld().getObjects(Board.class).isEmpty()){
                    getWorld().addObject(endScreen, getWorld().getWidth()/2, getWorld().getHeight()/2);
                    endScreen.displayInfo(countDownTime, time, countInMinutes, countInSeconds, millis, puzzle.getTrials());
                    
                    GreenfootImage playAgainButtonImg = playAgainButton.getImage();
                    playAgainButtonImg.setColor(new Color(136, 0 ,21));
                    playAgainButtonImg.setFont(new Font(24));
                    playAgainButtonImg.drawString("PLAY AGAIN", 5, playAgainButtonImg.getHeight() - 10);
                    getWorld().addObject(playAgainButton, getWorld().getWidth() - (int)(playAgainButtonImg.getWidth() * 1.6), getWorld().getHeight() - playAgainButtonImg.getHeight()/2);
                    
                    GreenfootImage mainMenuButtonImg = mainMenuButton.getImage();
                    mainMenuButtonImg.setColor(new Color(136, 0 ,21));
                    mainMenuButtonImg.setFont(new Font(24));
                    mainMenuButtonImg.drawString("MAIN MENU", 7, mainMenuButtonImg.getHeight() - 10);
                    getWorld().addObject(mainMenuButton, getWorld().getWidth() - mainMenuButtonImg.getWidth()/2, getWorld().getHeight() - mainMenuButtonImg.getHeight()/2);
                } else if(key.equals("space") && !getWorld().getObjects(Board.class).isEmpty()){
                    backgroundSound.stop();
                    Greenfoot.setWorld(new MyWorld());
                }
            }
        }
    }
    
    private void checkBombStatus(){
        GreenfootImage img = resultButton.getImage();
        img.setColor(new Color(136, 0 ,21));
        img.setFont(new Font(30));
        if(puzzle.isDeactivated){
            backgroundSound.playLoop();
            img.drawString("RESULT", 13, img.getHeight() - 10);
            getWorld().addObject(resultButton, getWorld().getWidth() - resultButton.getImage().getWidth()/2, getWorld().getHeight() - resultButton.getImage().getHeight()/2);
            if (UserInfo.isStorageAvailable()) {
                UserInfo myInfo = UserInfo.getMyInfo();
                int time = countDownTime - this.time;
                myInfo.setInt(2, myInfo.getInt(2) + 1);
                myInfo.setInt(5, time);
                myInfo.setInt(6, 10 - puzzle.getTrials());
                if(myInfo.getInt(4) == 0){
                    myInfo.setInt(4, time);
                } else if(time < myInfo.getInt(4)){
                    myInfo.setInt(4, time);
                }
                if(puzzle.getTrials() == 9){
                    myInfo.setInt(7, myInfo.getInt(7) + 1);
                }
                myInfo.store(); 
                myInfo.setScore(-myInfo.getInt(4));
                myInfo.store();
             }
        } else if(puzzle.getTrials() == 0){
            timerSound.stop();
            Greenfoot.setWorld(new Area());
        }
    }
    
    private boolean isNumericalKey(String key){
        return key.equals("1") || key.equals("2") || key.equals("3") || key.equals("4") || key.equals("5") || key.equals("6") || key.equals("7") || key.equals("8") || key.equals("9") || key.equals("0");
    }
    /*
     * int[] ints = {0- plays, 1- plays With play Again, 2- deactivations, 3- explosions, 4- best tme, 5- last time, 6- last trials, 7- lucky win};
     */

}