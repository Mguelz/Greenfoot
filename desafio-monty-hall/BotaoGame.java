import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class BotaoGame extends Actor
{
    public void act()
    {
        botaoStartGame();
        mudarDeMundo();
    }
    
    public void botaoStartGame() {
        GreenfootImage botaoStart = new GreenfootImage("botaoStart.png");
        botaoStart.scale(60, 60);
        setImage(botaoStart);
    }
    
    public void mudarDeMundo() {
        if (Greenfoot.isKeyDown("enter")) {
            Greenfoot.setWorld(new TelaGame());
        }
    }
}
