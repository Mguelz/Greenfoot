import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Start extends World
{
    public Start()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 600, 1); 
        
        addObject(new BotaoStart(), 853,340);

        
        //showText("Pressione o botão para jogar", 155, 20);
        //showText("Press the button for start", 117, 40);
        showText("Pressione o botão para jogar", 829, 179);
        showText("Press the button for start", 810, 200);
    }
    
    public void act() {
        if(Greenfoot.isKeyDown("space")) {
            Greenfoot.setWorld(new Level1());
        }
    }
}
