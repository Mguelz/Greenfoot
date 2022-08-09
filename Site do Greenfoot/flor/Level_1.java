import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Level_1 extends World
{

    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public Level_1()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1000, 600, 1); 
        showText("Score: 0", 750, 30);
        addObject (new Enemy(), 400, 700);
        //addObject (new Teste(), Greenfoot.getRandomNumber(1000) )
        prepare();
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Rocket rocket = new Rocket();
        addObject(rocket,709,747);
        Enemy enemy = new Enemy();
        addObject(enemy,272,202);
        Enemy enemy2 = new Enemy();
        addObject(enemy2,854,101);
        Enemy enemy3 = new Enemy();
        addObject(enemy3,536,126);
        Enemy enemy4 = new Enemy();
        addObject(enemy4,1460,159);
        Enemy enemy5 = new Enemy();
        addObject(enemy5,1139,154);
        Planta planta = new Planta();
        addObject(planta,72,53);
        Planta planta2 = new Planta();
        addObject(planta2,388,220);
        Planta planta3 = new Planta();
        addObject(planta3,312,55);
        Planta planta4 = new Planta();
        addObject(planta4,751,89);
        Planta planta5 = new Planta();
        addObject(planta5,838,217);
        Planta planta6 = new Planta();
        addObject(planta6,1177,358);
        Planta planta7 = new Planta();
        addObject(planta7,647,255);
        Planta planta8 = new Planta();
        addObject(planta8,392,438);
        Planta planta9 = new Planta();
        addObject(planta9,1450,306);
        Planta planta10 = new Planta();
        addObject(planta10,1268,63);
        Planta planta11 = new Planta();
        addObject(planta11,1085,48);
        Planta planta12 = new Planta();
        addObject(planta12,978,287);
        Planta planta13 = new Planta();
        addObject(planta13,680,406);
        Planta planta14 = new Planta();
        addObject(planta14,415,277);
        Planta planta15 = new Planta();
        addObject(planta15,168,325);
        Planta planta16 = new Planta();
        addObject(planta16,113,224);
        Planta planta17 = new Planta();
        addObject(planta17,463,142);
        Planta planta18 = new Planta();
        addObject(planta18,547,69);
        Planta planta19 = new Planta();
        addObject(planta19,1032,133);
        Planta planta20 = new Planta();
        addObject(planta20,1307,227);
        Planta planta21 = new Planta();
        addObject(planta21,1438,66);
        Planta2 planta22 = new Planta2();
        addObject(planta22,1569,118);
        Planta2 planta23 = new Planta2();
        addObject(planta23,1319,139);
        Planta2 planta24 = new Planta2();
        addObject(planta24,1499,214);
        Planta2 planta25 = new Planta2();
        addObject(planta25,1290,352);
        Planta2 planta26 = new Planta2();
        addObject(planta26,1112,262);
        Planta2 planta27 = new Planta2();
        addObject(planta27,978,384);
        Planta2 planta28 = new Planta2();
        addObject(planta28,787,330);
        Planta2 planta29 = new Planta2();
        addObject(planta29,742,207);
        Planta2 planta210 = new Planta2();
        addObject(planta210,566,161);
        Planta2 planta211 = new Planta2();
        addObject(planta211,489,237);
        Planta2 planta212 = new Planta2();
        addObject(planta212,524,343);
        Planta2 planta213 = new Planta2();
        addObject(planta213,289,309);
        Planta2 planta214 = new Planta2();
        addObject(planta214,226,148);
        Planta2 planta215 = new Planta2();
        addObject(planta215,123,146);
        Planta2 planta216 = new Planta2();
        addObject(planta216,479,39);
        Planta2 planta217 = new Planta2();
        addObject(planta217,341,111);
        Planta2 planta218 = new Planta2();
        addObject(planta218,105,55);
        Planta2 planta219 = new Planta2();
        addObject(planta219,198,56);
        Planta2 planta220 = new Planta2();
        addObject(planta220,650,58);
        Planta2 planta221 = new Planta2();
        addObject(planta221,986,54);
        Planta2 planta222 = new Planta2();
        addObject(planta222,986,170);
        Planta planta30 = new Planta();
        addObject(planta30,1386,62);
        Planta planta31 = new Planta();
        addObject(planta31,1195,58);
        Planta planta32 = new Planta();
        addObject(planta32,1207,200);
        Planta planta33 = new Planta();
        addObject(planta33,1551,62);
        Planta planta34 = new Planta();
        addObject(planta34,660,154);
        planta.setLocation(49,87);
    }
}
