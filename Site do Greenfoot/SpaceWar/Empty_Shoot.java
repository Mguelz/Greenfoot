import greenfoot.World;

import java.util.Date;

public class Empty_Shoot extends Empty implements ShootAble{
    // 当前时间
    private long nowTime = new Date().getTime();
    // 射击的时间戳
    private long shootBeatTime = nowTime;
    // 射击的频率(每隔几毫秒射击一次)
    private long shootPlayTime;

    // 射击位置
    private int shootX;
    private int shootY;
    // 是否射击
    private boolean isShoot = true;

    public void act() {
        // 刷新
        flush();
        // 移动
        fly();
        // 碰撞检测
        collision();
        // 射击
        if (nowTime - shootBeatTime >= shootPlayTime) {
            if (isShoot) {
                shoot();
            }
            shootBeatTime = nowTime;
        }
        // 边缘检测
        edge();
        // 死亡检测
        die();
    }

    private void flush() {
        // 刷新时间
        nowTime = new Date().getTime();
        // 更新射击位置
        Static.setShootXY(this, Direct.UP);
    }

    @Override
    public void shoot() {
        Bullet bullet = new Bullet();
        bullet.setImage("Bullet_1.png");
        bullet.setrHP(100);
        bullet.setSpeed(getSpeed()*3);
        bullet.setDirect(Direct.DOWN);
        bullet.setBeatN(1);
        // 子弹打击排除项添加
        bullet.addExclude(Empty.class);
        bullet.addExclude(Empty_Shoot.class);
        bullet.addExclude(Empty_Special.class);
        bullet.addExclude(Empty_Special1.class);

        getWorld().addObject(bullet, shootX, shootY);
    }

    @Override
    protected void addedToWorld(World world) {
        // 设置射击坐标
        Static.setShootXY(this, getDirect());
    }

    // 构造方法
    public Empty_Shoot() {}

    // setter and getter
    public long getShootPlayTime() {
        return shootPlayTime;
    }

    public void setShootPlayTime(long shootPlayTime) {
        this.shootPlayTime = shootPlayTime;
    }

    public boolean isShoot() {
        return isShoot;
    }

    public void setShoot(boolean shoot) {
        isShoot = shoot;
    }

    @Override
    public int getShootX() {
        return shootX;
    }

    @Override
    public void setShootX(int shootX) {
        this.shootX = shootX;
    }

    @Override
    public int getShootY() {
        return shootY;
    }

    @Override
    public void setShootY(int shootY) {
        this.shootY = shootY;
    }
}
