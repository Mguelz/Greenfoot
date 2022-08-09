import greenfoot.Actor;

public class Star extends Actor implements FlyAble{
    private int speed;

    @Override
    public void act() {
        fly();
        if (isAtEdge()) {
            getWorld().removeObject(this);
        }
    }

    @Override
    public void fly() {
        Static.fly(this, Direct.DOWN);
    }

    public Star() {
        setImage("Star.png");
        setSpeed(1);
    }
    public Star(int speed) {
        super();
        this.speed = speed;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
