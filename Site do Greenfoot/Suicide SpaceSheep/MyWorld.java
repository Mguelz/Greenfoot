import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.util.Random;
import java.util.Map;
import java.util.HashMap;
//import java.awt.Toolkit;
import java.util.List;

public class MyWorld extends World
{
    Sheep sheep1;
    boolean game = false;
    String gameState = "Launch";
    int highscore = 0;
    int time;
    int timeBetweenWaves = 300;
    int waveCount;
    boolean keyUp = false;
    boolean godMod = false;
    Map<String, Integer> costs;
    //private static int width = Toolkit.getDefaultToolkit().getScreenSize().width;
    //private static int height = Toolkit.getDefaultToolkit().getScreenSize().height;
    private static int width = 1432-2, height = 816-65;

    public MyWorld()
    {    
        super(width, height, 1);
        initialize();
        updateState();
    }
    public void act()
    {
        if (game)
        {
            showStats(sheep1.energy, sheep1.score);
            newWave();
            time++;
        }
        else
        {
            updateState();
        }
    }
    public void initialize()
    {
        Greenfoot.setSpeed(50);
        setActOrder(MyWorld.class, Sheep.class, Banana.class, Bullet.class, Badguy.class);
        setActOrder(MyWorld.class, Banana.class, Bullet.class, Badguy.class, Sheep.class);
        costs = new HashMap<String, Integer>();
        costs.put("banana", 1);
        costs.put("dash", -1);
        costs.put("furtive", -24); 
        costs.put("shot", -1);
    }
    public void updateState()
    {
        switch(gameState)
        {
            case "Launch":
                showMessage("center", "S U I C I D E\nS P A C E S H E E P\n\n"+
                "Press [►Run] at the bottom of\nthe screen to launch the game");
                gameState = "SetStart";
                break;
            case "SetStart":
                sheep1 = new Sheep();
                waveCount = 1;
                addObject(sheep1, getWidth()/4, getHeight()/2);
                showMessage("center", "S U I C I D E\nS P A C E S H E E P\n\nPress [enter] to PLAY\nSession highscore: "+highscore+
                "\n\nControls (+energy cost)\n──────────────────"+
                "\n◄ ► : turn left and right"+
                "\n[hold space] : load bullets ("+costs.get("shot")+"/bullet)"+
                "\n[release space] : shoot bullets"+
                "\n[A] : furtive mode (-/sec)"+
                "\n[Z] : dash ahead ("+costs.get("dash")+")"+
                "\nbanana (+"+costs.get("banana")+")");
                showStats(0, 0);
                gameState = "Start";
                break;
            case "Start":
                if (Greenfoot.isKeyDown("enter") && keyUp)
                {
                    time = 0;
                    showText("", getWidth()/2, getHeight()/2);
                    spawnFruits();   
                    gameState = "Playing";
                    game = true;
                    if (Greenfoot.isKeyDown("g"))
                    {
                        godMod = true;
                        showMessage("center", "God Mod");
                    }
                    else
                    {
                        clearMessage("center");
                    }
                }
                if ((!keyUp) && (!Greenfoot.isKeyDown("enter")))
                {
                    keyUp = true;
                }
                break;
            case "SetGameOver":
                showMessage("center", "GAME OVER\nScore: "+sheep1.score+"\n\nPress [enter]\nto REPLAY");
                if (sheep1.score > highscore)
                {
                    highscore = sheep1.score;
                }
                gameState = "GameOver";
                break;
            case "GameOver":
                if (Greenfoot.isKeyDown("enter"))
                {
                    gameState = "SetStart";
                    keyUp = false;
                    removeObjects(getObjects(null));
                }
                break;
        }
    }
    public void spawnFruits()
    {
        Random randomNo = new Random();
        for (int i=0; i<10; i++) 
        {
            addObject(new Banana(), randomNo.nextInt(getWidth()), randomNo.nextInt(getHeight()));
        }

    }
    public void spawnBullet(int x, int y, int rotation, boolean first)
    {
        addObject(new Bullet(rotation, first), x, y);
    }
    public void spawnEnnemies(int nGuys)
    {
        Random randomNo = new Random();
        for (int i=0;i<nGuys;i++)
        {
            int x=0, y=0;
            switch ((randomNo.nextInt(3)<1) ? "Sides" : "UpDown" )
            {
                case "UpDown":
                    x = randomNo.nextInt(getWidth());
                    y = (randomNo.nextInt(2)>0 ? 0 : getHeight());
                    break;
                case "Sides":
                    y = randomNo.nextInt(getHeight());
                    x = (randomNo.nextInt(2)>0 ? 0 : getWidth());
                    break;
            }
            addObject(new Badguy(), x, y);
        }
    }
    public void showMessage(String position, String text)
    {
        int[] coord = getCoordFromPos(position);
        int x = coord[0]; int y = coord[1];
        showText(text, x-1, y);
        showText(text, x+1, y);
        showText(text, x, y);
    }
    public void clearMessage(String position)
    {
        int[] coord = getCoordFromPos(position);
        int x = coord[0]; int y = coord[1];
        showText("", x-1, y);
        showText("", x+1, y);
        showText("", x, y);
    }
    public int[] getCoordFromPos(String position)
    {
        int[] coord = new int[2];
        switch(position)
        {
            case "center":
                coord[0] = width/2; coord[1] = height/2;
                break;
            case "energy":
                coord[0] = 64; coord[1] = 18;
                break;
            case "score":
                coord[0] = width-64; coord[1] = 18;
                break;
        }
        return coord;
    }
    public void showStats(int energy, int score)
    {
        showMessage("energy", "Energy: "+energy);
        showMessage("score", "Score: "+score);
    }
    public void newWave()
    {    
        if ((time%timeBetweenWaves)==0)
        {
            spawnEnnemies(waveCount);
            waveCount++;
        }
    }
}