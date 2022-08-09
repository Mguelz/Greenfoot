import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class MyWorld extends World
{
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(810, 400, 1); 

        //spawnar os actors aqui
        addObject(new BotaoGame(), 600, 50);
        
        showText("Press enter", 600, 90);
    }

    public void act() {
        tocarMusicaFundo();
    }
    
    public void tocarMusicaFundo() {
        //criado a variavel para utilizar metodos
        GreenfootSound musicaFundo = new GreenfootSound("musica_fundo.mp3");

        musicaFundo.playLoop();
        musicaFundo.setVolume(20);
    }
}