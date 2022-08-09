import greenfoot.Actor;

import java.util.List;

public class Empty extends Ship{
    // 速度
    private int speed;
    // 方向(默认为向下)
    private Direct direct = Direct.DOWN;
    // 血量
    private int HP;
    private int HP_MAX;

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

    /**
     * 对于一切Empty对象来说, 下面的对象是需要碰撞的<br>
     * 1. Mine<br>
     * 2. Rock
     */
    protected void collision() {
        List<Actor> actors = getIntersectingObjects(Actor.class);
        // 排除对象
        Static.include(actors, Mine.class, Rock.class);

        // 接下来的是要碰撞的
        for (Actor a : actors) {
            if (a instanceof BloodAble) {
                ((BloodAble) a).reduceHP(100);
            }
            // 每一个扣100HP
            reduceHP(100);
            // 生成爆炸
            createBOOM();
        }
    }

    protected void edge() {
        if (isAtEdge()) {
            getWorld().removeObject(this);
        }
    }

    protected void die() {
        // 如果生命为0及以下, 那么判定死亡
        if (getWorld() != null) {
            if (HP <= 0) {
                getWorld().removeObject(this);
            }
        }
    }

    public void fly() {
        Static.fly(this, direct);
    }

    private void createBOOM() {
        SImage image = new SImage(Static.Boom_, Del.loop_once);
        //image.setMoved(false);
        getWorld().addObject(image, getX(), getY());
    }

    // getter and setter
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
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
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

    public Direct getDirect() {
        return direct;
    }

    public void setDirect(Direct direct) {
        this.direct = direct;
    }
}
