import greenfoot.Actor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 保护罩
 */
public class Cover extends Actor implements FlyAble, BloodAble{
    // 速度
    private int speed;
    // 血量
    private int HP;
    private int HP_MAX;

    // 跟踪的对象
    private Actor follower;
    // 排除碰撞的对象
    private List<Class<?>> excludes = new ArrayList<>();

    // 转圈角度
    private final int turnN = (new Random().nextBoolean()?1:-1);
    // 是否血量过半
    private boolean isHP_half = false;

    public void act() {
        // 移动
        fly();
        // 检测碰撞
        collision();
        // 死亡检测
        die();

        // 转圈
        turn(turnN);
    }

    private void die() {
        if ((!isHP_half) && HP <= (HP_MAX * 0.5)) {
            setImage("Cover_1_1.png");
            isHP_half = true;
        }
        if (HP <= (HP_MAX * 0.25)) {
            getImage().setTransparency(126);
        }
        if (HP <= 0) {
            getWorld().removeObject(this);
        }
    }

    private void collision() {
        List<Actor> actors = getIntersectingObjects(Actor.class);
        Static.exclude(actors, excludes);

        // 那么接下来的就是要碰撞的了
        for (Actor a : actors) {
            if (a instanceof BloodAble) {
                ((BloodAble) a).reduceHP(100);
            }
            // 每一个扣100HP
            reduceHP(100);
        }
    }

    public void fly() {
        /*
            前提:
            1. 跟着的对象在场景中
         */
        if (follower.getWorld() != null) {
            setLocation(follower.getX(), follower.getY());
        }
    }

    // 构造方法
    public Cover() {
        setImage("Cover_1.png");
        // 添加默认排除打击的对象
        excludes.addAll(Static.cover_excludes);
    }

    // setter and getter
    @Deprecated
    public int getSpeed() {
        return speed;
    }

    @Deprecated
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
    public void setHP_MAX(int HP_MAX) {
        this.HP_MAX = HP_MAX;
    }

    @Override
    public boolean reduceHP(int rHP) {
        HP -= rHP;
        return HP <= 0;
    }

    @Override
    public boolean addHP(int aHP) {
        HP += aHP;
        if (HP >= HP_MAX) {
            HP = HP_MAX;
            return true;
        }
        return false;
    }

    public Actor getFollower() {
        return follower;
    }

    public void setFollower(Actor follower) {
        this.follower = follower;
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
