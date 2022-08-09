import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.MouseInfo;

import java.util.List;

public class MAG extends Actor {
    private boolean isClicked = false;

    @Override
    public void act() {
        MAG();
        if (Greenfoot.mouseClicked(this)) {
            isClicked = !isClicked;
        }

        MouseInfo mouseInfo = Greenfoot.getMouseInfo();
        if (isClicked) {
            setLocation(mouseInfo.getX(), mouseInfo.getY());
        }
    }

    private void MAG() {
        List<Actor> actors = getWorld().getObjects(Actor.class);
        Static.exclude(actors, Mine.class, Star.class, SImage.class, Gift.class);

        for (Actor a : actors) {
            a.setLocation(getX(), getY());
        }
    }

    public MAG() {
        setImage("MAG_1.png");
    }
}
