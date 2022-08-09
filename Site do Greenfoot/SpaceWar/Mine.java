import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.Greenfoot;
import greenfoot.World;

import java.util.Date;
import java.util.List;

public class Mine extends Ship implements ShootAble{
    // 速度
    private int speed;
    // 血量
    private int HP;
    private int HP_MAX;

    // 射击位置
    private int shootX;
    private int shootY;

    // 当前时间
    private long nowTime = new Date().getTime();
    // 发射普通子弹的时间戳
    private long shootBullet1_StampTime = nowTime;
    // 发射大子弹的时间戳
    private long shootBullet2_StampTime = nowTime;

    // 能量
    private int energy;
    private int energy_MAX;
    // 能量槽
    private Bar energyBar;
    // 更新能量的时间戳
    private long upDate_StampTime = nowTime;

    // 保护罩
    private Cover cover;
    // 创建保护罩的时间戳
    private long createCover_StampTime;


    public void act() {
        // 更新
        flush();
        // 移动
        fly();
        // 检测碰撞
        collision();
        // 检测射击
        shoot();
        // 检测生成保护罩
        createCover();

        // 检测死亡
        die();
    }

    private void flush() {
        // 更新当前时间
        nowTime = new Date().getTime();

        // 更新能量(每隔0.5s)
        if (nowTime - upDate_StampTime >= 0.50*1000) {
            if (++energy >= energy_MAX) {
                energy = energy_MAX;
            } else {
                if (energy <= (energy_MAX * 0.16)) {
                    energyBar.setBarColor(Color.RED);
                    energyBar.setTextColor(Color.RED);
                } else {
                    energyBar.setBarColor(Color.GREEN);
                    energyBar.setTextColor(Color.GREEN);
                }
            }
            upDate_StampTime = nowTime;
        }
        energyBar.setEnergy(energy);
        energyBar.addToWorld();

        // 刷新保护罩
        if (cover != null) {
            if (cover.getWorld() == null) {
                cover = null;
            }
        }

        // 更新射击位置
        Static.setShootXY(this, Direct.UP);
    }

    /**
     * 对于一切Mine对象来说, 下面的对象是参与碰撞的(会和mine对象碰撞)<br/>
     * 1. Empty<br/>
     * 2. Rock<br/>
     * 3. Gift<br/>
     * <br/>
     * 不需要BlackHole, 因为BlackHole对象自己会不断检测碰撞.
     */
    private void collision() {
        List<Actor> actors = getIntersectingObjects(Actor.class);
        Static.include(actors, Empty.class, Rock.class, Gift.class);

        // 那么接下来的就是要碰撞的了
        for (Actor a : actors) {
            if (a instanceof BloodAble) {
                ((BloodAble) a).reduceHP(100);
            }
            if (a instanceof Gift) {
                ((Gift) a).open(this);
                return;
            }
            // 每一个扣100HP
            reduceHP(100);
            // 生成爆炸
            createBOOM();
        }
    }

    private void createCover() {
        // 按下"o"生成保护罩, 消耗15能量, 每隔3s
        if (Greenfoot.isKeyDown("o") && (nowTime - createCover_StampTime >= 3.0*1000)) {
            if (reduceEnergy(15)) {
                Cover cover = new Cover_Mine();
                cover.setFollower(this);
                cover.setHP(1100);
                cover.setHP_MAX(1100);
                this.cover = cover;
                getWorld().addObject(cover, getX(), getY());
            }
            createCover_StampTime = nowTime;
        }
    }

    public void shoot() {
        /*
            1. 按下"j"发射普通子弹, 每隔0.15s
            2. 按下"k"发射大子弹, 每隔0.3s

               按下"m"提升energy
         */
        if (Greenfoot.isKeyDown("j") && (nowTime - shootBullet1_StampTime >= 0.15*1000)) {
            if (reduceEnergy(1)) {
                Bullet bullet = new Bullet_Mine();
                bullet.setImage("Bullet_1.png");
                bullet.setrHP(100);
                bullet.setSpeed(speed * 4);
                bullet.setDirect(Direct.UP);
                bullet.setBeatN(1);
                bullet.addExclude(Mine.class);
                getWorld().addObject(bullet, shootX, shootY);
            }
            shootBullet1_StampTime = nowTime;
        } else
        if (Greenfoot.isKeyDown("k") && (nowTime - shootBullet2_StampTime >= 0.30*1000)) {
            if (reduceEnergy(5)) {
                Bullet bullet = new Bullet_Mine();
                bullet.setImage("Bullet_2.png");
                bullet.setrHP(150);
                bullet.setSpeed(speed*3);
                bullet.setDirect(Direct.UP);
                bullet.setBeatN(5);
                bullet.addExclude(Mine.class);
                getWorld().addObject(bullet, shootX, shootY);
            }
            shootBullet2_StampTime = nowTime;
        }

        if (Greenfoot.isKeyDown("m")) {
            addEnergy(1);
        }
    }

    private void die() {
        /*
            如果此mine的HP小于等于0, 那么判断死亡
         */
        if (HP <= 0) {
            getWorld().removeObject(this);
        }
    }

    public void fly() {
        /*
            Mine对象的移动受到键盘控制
         */
        if (Greenfoot.isKeyDown("w")) {
            setLocation(getX(), getY() - getSpeed());
        } else if (Greenfoot.isKeyDown("s")){
            setLocation(getX(), getY() + getSpeed());
        }
        if (Greenfoot.isKeyDown("a")) {
            setLocation(getX() - getSpeed(), getY());
        } else if (Greenfoot.isKeyDown("d")){
            setLocation(getX() + getSpeed(), getY());
        }
    }

    private void createBOOM() {
        SImage image = new SImage(Static.Boom_, Del.loop_once);
        //image.setMoved(false);
        getWorld().addObject(image, getX(), getY());
    }

    @Override
    protected void addedToWorld(World world) {
        // 设置射击位置
        Static.setShootXY(this, Direct.UP);
    }

    // 构造方法
    public Mine() {}

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
    public boolean reduceHP(int rHP) {
        if (cover == null) {
            HP -= rHP;
            return HP <= 0;
        } else {
            // 如果有保护罩, 那么免疫攻击
            return false;
        }
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

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public Bar getEnergyBar() {
        return energyBar;
    }

    public void setEnergyBar(Bar energyBar) {
        this.energyBar = energyBar;
    }

    /**
     *
     * @return 如果减去energy之后energy不小于零, 那么返回true
     */
    public boolean reduceEnergy(int rEnergy){
        if ((energy - rEnergy) < 0) {
            return false;
        } else {
            energy -= rEnergy;
            return true;
        }
    }

    /**
     * @return 如果增加energy之后energy不大于energy_MAX, 那么返回true
     */
    public boolean addEnergy(int aEnergy){
        if ((energy + aEnergy) > energy_MAX) {
            return false;
        } else {
            energy += aEnergy;
            return true;
        }
    }

    public int getEnergy_MAX() {
        return energy_MAX;
    }

    public void setEnergy_MAX(int energy_MAX) {
        this.energy_MAX = energy_MAX;
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
