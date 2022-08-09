import greenfoot.*;

public class Bee extends Actor
{
    int score = 0;
    public void act()
    {
        setRotation(270);
        
        validarTeclado();
        validarMouse();
        tocarPlanta();
        ProximoLevel();
    }
    
    public void validarTeclado() {
        if (Greenfoot.isKeyDown("a")) {
            setLocation(getX() - 8, getY());
        }
        if (Greenfoot.isKeyDown("d")) {
            setLocation(getX() + 8, getY());
        }
    }
    public void validarMouse() {
        MouseInfo mouse = Greenfoot.getMouseInfo();
        if(mouse != null) {
            if(mouse.getButton() == 1) {
                getWorld().addObject(new Fire(), getX(), getY() - 40);
            }
        }
    }
    
    public void tocarPlanta() {
        if (isTouching(Planta.class)) {
            removeTouching(Planta.class);
            score++;
            getWorld().showText("Score: " + score, 570, 20);
        }
        if (isTouching(Planta2.class)) {
            removeTouching(Planta2.class);
            score++;
            getWorld().showText("Score: " + score, 570, 20);
        }
    }
    
    public void ProximoLevel() {
        if(score == 35) {
            getWorld().showText("Parabéns você ganhou!", 600, 300); //getWorld().getWidht() - 60 para pegar o tamanho do mundo
            getWorld().showText("Congratulations you win!", 600, 320);
            Greenfoot.stop();
        }
    }
}
