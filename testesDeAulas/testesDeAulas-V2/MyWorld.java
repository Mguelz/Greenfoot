import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Nesta versão, foi adicionado variedade com que as flores caem no mundo.
 * foi adicionado o score.
 * 
 */
public class MyWorld extends World
{
    //para a v3 adicionar o turnTowards no código do jogo
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld()
    {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels.
        super(1200, 600, 1); 
        showText("Score: 0", 570, 20);
        prepare();
    }
    
    private void prepare()
    {
        Bee bee = new Bee();
        addObject(bee,147,187);
        bee.setLocation(330,412);
        bee.setLocation(584,539);
        bee.setLocation(573,537);
        Planta planta = new Planta();
        addObject(planta,97,48);
        Planta planta2 = new Planta();
        addObject(planta2,297,47);
        Planta planta3 = new Planta();
        addObject(planta3,506,41);
        Planta planta4 = new Planta();
        addObject(planta4,660,64);
        Planta planta5 = new Planta();
        addObject(planta5,878,43);
        Planta planta6 = new Planta();
        addObject(planta6,1087,35);
        Planta planta7 = new Planta();
        addObject(planta7,1001,145);
        Planta planta8 = new Planta();
        addObject(planta8,709,149);
        Planta planta9 = new Planta();
        addObject(planta9,966,312);
        Planta planta10 = new Planta();
        addObject(planta10,492,200);
        Planta planta11 = new Planta();
        addObject(planta11,335,172);
        Planta planta12 = new Planta();
        addObject(planta12,229,240);
        Planta planta13 = new Planta();
        addObject(planta13,158,222);
        Planta planta14 = new Planta();
        addObject(planta14,506,129);
        Planta planta15 = new Planta();
        addObject(planta15,245,125);
        Planta planta16 = new Planta();
        addObject(planta16,364,215);
        Planta planta17 = new Planta();
        addObject(planta17,135,138);
        Planta planta18 = new Planta();
        addObject(planta18,605,154);
        Planta planta19 = new Planta();
        addObject(planta19,715,217);
        Planta planta20 = new Planta();
        addObject(planta20,846,192);
        Planta planta21 = new Planta();
        addObject(planta21,836,125);
        Planta planta22 = new Planta();
        addObject(planta22,403,95);
        Planta planta23 = new Planta();
        addObject(planta23,969,57);
        Planta planta24 = new Planta();
        addObject(planta24,778,41);
        Planta planta25 = new Planta();
        addObject(planta25,1173,103);
        Planta planta26 = new Planta();
        addObject(planta26,1086,212);
        Planta planta27 = new Planta();
        addObject(planta27,902,154);
        Planta planta28 = new Planta();
        addObject(planta28,1082,125);
        Enemy enemy = new Enemy();
        addObject(enemy,170,45);
        Enemy enemy2 = new Enemy();
        addObject(enemy2,401,23);
        Enemy enemy3 = new Enemy();
        addObject(enemy3,612,26);
        Enemy enemy4 = new Enemy();
        addObject(enemy4,1014,29);
        enemy3.setLocation(584,82);
        enemy4.setLocation(916,96);
        planta21.setLocation(766,107);
        enemy4.setLocation(875,101);
        planta27.setLocation(923,177);
        enemy4.setLocation(896,132);
        planta22.setLocation(422,106);
        enemy2.setLocation(368,75);
        planta22.setLocation(410,98);
        Planta2 planta29 = new Planta2();
        addObject(planta29,1108,496);
        removeObject(planta27);
        removeObject(planta21);
        removeObject(planta14);
        removeObject(planta15);
        removeObject(planta17);
        planta13.setLocation(163,225);
        removeObject(planta13);
        removeObject(planta11);
        removeObject(planta8);
        removeObject(planta23);
        removeObject(planta25);
        planta29.setLocation(967,42);
        addObject(planta27,443,142);
        Planta2 planta210 = new Planta2();
        addObject(planta210,263,129);
        Planta2 planta211 = new Planta2();
        addObject(planta211,107,155);
        Planta2 planta212 = new Planta2();
        addObject(planta212,234,347);
        Planta2 planta213 = new Planta2();
        addObject(planta213,432,276);
        Planta2 planta214 = new Planta2();
        addObject(planta214,669,254);
        Planta2 planta215 = new Planta2();
        addObject(planta215,828,292);
        Planta2 planta216 = new Planta2();
        addObject(planta216,656,161);
        Planta2 planta217 = new Planta2();
        addObject(planta217,571,231);
        Planta2 planta218 = new Planta2();
        addObject(planta218,481,104);
        Planta2 planta219 = new Planta2();
        addObject(planta219,339,164);
        Planta2 planta220 = new Planta2();
        addObject(planta220,177,109);
        Planta2 planta221 = new Planta2();
        addObject(planta221,596,42);
        Planta planta30 = new Planta();
        addObject(planta30,765,109);
        Planta planta31 = new Planta();
        addObject(planta31,943,226);
        Planta planta32 = new Planta();
        addObject(planta32,1148,63);
        Planta planta33 = new Planta();
        addObject(planta33,81,234);
        planta27.setLocation(484,136);
        planta218.setLocation(466,80);
        removeObject(planta218);
        planta27.setLocation(486,118);
        planta221.setLocation(620,36);
        planta221.setLocation(623,32);
        planta4.setLocation(684,63);
        planta216.setLocation(686,141);
    }
}
