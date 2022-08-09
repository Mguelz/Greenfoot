import greenfoot.Actor;

import java.util.ArrayList;
import java.util.List;

public class Rock extends Actor implements FlyAble, BloodAble {
    // 速度
    private int speed;
    // 方向
    private Direct direct;
    // 血量
    private int HP;
    private int HP_MAX;

    // 旋转角度
    private final boolean b = Static.getRandom(2);

    // 排除打击的对象
    private List<Class<?>> excludes = new ArrayList<>();

    public void act() {
        // 旋转
        turn((b ? -1 : 1));
        // 飞行
        fly();
        // 碰撞检测
        collision();
        // 边缘检测
        edge();
        // 死亡检测
        die();
    }

    private void collision() {
        List<Actor> actors = getIntersectingObjects(Actor.class);
        // 排除
        Static.exclude(actors, excludes);

        // 接下来是碰撞的
        for (Actor a : actors) {
            if (a instanceof BloodAble) {
                ((BloodAble) a).reduceHP(100);
            }
            // 减少血量
            reduceHP(100);
            // 创建碰撞
            createBOOM();
        }
    }

    private void createBOOM() {
        SImage image = new SImage(Static.Boom_, Del.loop_once);
        //image.setMoved(false);
        getWorld().addObject(image, getX(), getY());
    }

    private void edge() {
        if (isAtEdge()) {
            getWorld().removeObject(this);
        }
    }

    private void die() {
        // 如果血量在一半, 切换图片
        if (HP <= (HP_MAX / 2)) {
            setImage("Rock_1_1.png");
        }
        // 如果血量为0, 死亡
        if (getWorld() != null) {
            if (HP <= 0) {
                SImage image = new SImage();
                image.setMoved(true);
                image.setImages(Static.Rock_Break);
                image.setDieControl(Del.loop_once);
                image.setDirect(direct);
                image.setSpeed(speed);
                image.setPlayTime(230);
                image.setEdge(true);
                getWorld().addObject(image, getX(), getY());
                getWorld().removeObject(this);
            }
        }
    }

    public void fly() {
        Static.fly(this, direct);
    }


    // 构造方法
    public Rock() {
        setImage("Rock_1.png");
        // 添加默认排除打击的对象
        excludes.addAll(Static.rock_excludes);
    }


    // setter and getter
    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public int getHP() {
        return HP;
    }

    @Override
    public void setHP(int HP) {
        this.HP = HP;
    }

    @Override
    public int getHP_MAX() {
        return HP_MAX;
    }

    @Override
    public boolean reduceHP(int rHP) {
        HP -= rHP;
        return HP <= 0;
    }

    @Override
    public boolean addHP(int aHP) {
        HP += aHP;
        if (HP >= aHP) {
            HP = aHP;
            return true;
        }
        return false;
    }

    @Override
    public void setHP_MAX(int HP_MAX) {
        this.HP_MAX = HP_MAX;
    }

    public Direct getDirect() {
        return direct;
    }

    public void setDirect(Direct direct) {
        this.direct = direct;
    }

    public List<Class<?>> getExcludes() {
        return excludes;
    }

    public void setExcludes(List<Class<?>> excludes) {
        this.excludes = excludes;
    }

    public void addExclude(Class<?> aClass){
        excludes.add(aClass);
    }
    public void addExcludeAll(List<Class<?>> classes){
        excludes.addAll(classes);
    }
}
