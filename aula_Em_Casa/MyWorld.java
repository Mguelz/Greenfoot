import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Fazer com que quando o cavaleiro encostar no mago perguntar o nome do cavaleiro;
 * E gaurdar o nome do cavaleiro;
 * E exibir o nome do cavaleiro em cima dele.
 */
public class MyWorld extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(600, 400, 1); 
        prepare();
    }
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Cavaleiro cavaleiro = new Cavaleiro();
        addObject(cavaleiro,181,271);;
        Mago mago = new Mago();
        addObject(mago,443,104);
    }
}
