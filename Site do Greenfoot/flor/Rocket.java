import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Rocket here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Rocket extends Actor
{
    int score = 0;
    /**
     * Act - do whatever the Rocket wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act()
    {
        mover();
        valdiarUsoDoMouse();
        capiturarPlanta();
    }
    
    public void mover() {
        if (Greenfoot.isKeyDown("a")) {
            setLocation(getX() - 8, getY());
        }
        if (Greenfoot.isKeyDown("d")) {
            setLocation(getX() + 8, getY());
        }
    }
    
    public void capiturarPlanta() {
        if (isTouching(Planta.class)) {
            removeTouching(Planta.class);
            score++;
            getWorld().showText("Score: " + score, 750, 30);
        }
        if (isTouching(Planta2.class)) {
            removeTouching(Planta2.class);
            score++;
            getWorld().showText("Score: " + score, 750, 30);
        }
    }
    
    /**
         * adiciona uma variavel para pegar as informacoes do mouse(getMosueInfo)
         * atirar com o mose clicando com o botao esquerdo (1) / (se fosse o botao direito seria (3))
         * adicionar o foguete na tela
         */
    public void valdiarUsoDoMouse() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null) {
            if(mouse.getButton() == 1) {
                getWorld().addObject(new Tiro(), getX(), getY() - 40);
            }
        }
    }
}
