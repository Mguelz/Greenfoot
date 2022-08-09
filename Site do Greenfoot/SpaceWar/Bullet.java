import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.util.ArrayList;
import java.util.List;

public class Bullet extends Actor implements FlyAble{
    // 速度
    private int speed;
    // 方向
    private Direct direct;

    // 此子弹排除打击的对象
    private List<Class<?>> excludes = new ArrayList<>();
    // 此子弹可以击打的次数(默认为1)
    private int beatN = 1;
    // 此子弹可以造成的伤害
    private int rHP;

    // 此子弹创造爆炸时的帧序列
    private List<GreenfootImage> booms = Static.Boom_;

    public void act() {
        // 移动
        fly();
        // 碰撞检测
        collision();
        // 边缘检测
        edge();
        // 死亡检测
        die();
    }

    protected void collision() {
        List<Actor> actors = getIntersectingObjects(Actor.class);
        // 排除排除打击的对象
        Static.exclude(actors, excludes);

        // 剩下的是要打击的对象
        for (Actor a : actors) {
            /*
                如果是BloodAble, 那么减去rHP,
                不是BloodAble的, 当作无敌

                如果是Bullet, 减去可以击打的次数
             */
            if (a instanceof BloodAble) {
                // 减去血量
                ((BloodAble) a).reduceHP(rHP);
            }
            if (a instanceof Bullet) {
                ((Bullet) a).setBeatN(((Bullet) a).getBeatN()-1);
            }
            // 生成爆炸
            createBOOM();
            // 减去可以击打的次数
            beatN--;
            // 如果可以击打的次数为0, 那么退出循环
            if (beatN <= 0) break;
        }
    }

    protected void edge() {
        if (isAtEdge()) {
            getWorld().removeObject(this);
        }
    }

    protected void die() {
        if (getWorld() != null) {
            if (beatN <= 0) {
                getWorld().removeObject(this);
            }
        }
    }

    public void fly() {
        Static.fly(this, direct);
    }

    protected void createBOOM() {
        SImage image = new SImage(booms, Del.loop_once);
        //image.setMoved(false);
        getWorld().addObject(image, getX(), getY());
    }

    // 构造方法
    public Bullet() {
        // 添加默认 排除打击的对象
        excludes.addAll(Static.bullet_excludes);
    }

    // setter and getter
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

    public int getBeatN() {
        return beatN;
    }

    public void setBeatN(int beatN) {
        this.beatN = beatN;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getrHP() {
        return rHP;
    }

    public void setrHP(int rHP) {
        this.rHP = rHP;
    }

    public List<GreenfootImage> getBooms() {
        return booms;
    }

    public void setBooms(List<GreenfootImage> booms) {
        this.booms = booms;
    }

    /**
     * 不建议使用, 容易出现不易察觉的BUG
     */
    @Deprecated
    public void addExclude(Actor actor){
        excludes.add(actor.getClass());
    }
    public void addExclude(Class<?> aClass){
        excludes.add(aClass);
    }
    public void addExcludeAll(List<Class<?>> classes){
        excludes.addAll(classes);
    }
}
