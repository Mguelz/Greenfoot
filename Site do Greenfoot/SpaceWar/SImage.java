import greenfoot.Actor;
import greenfoot.GreenfootImage;
import greenfoot.World;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 更聪明的图像
 * 可以播放动画
 */
public class SImage extends Actor implements FlyAble{
    // 帧
    private List<GreenfootImage> images;
    // 指针
    private int N = 0;
    // 已经循环的次数(每当N为0, 自加一)
    private int cycle = 0;
    // 选择删除此图像的条件
    private Del dieControl;
    // 此对象自添加以来存在的时间
    private long brithTime;
    // 限定的时间(dieControl = Del.over_time时), 以毫秒为单位
    private long restrictTime;

    // 播放的节奏时间
    private long playBeatTime = new Date().getTime();
    // 限定的帧速(一帧占多少秒)
    private long playTime;

    // 移动速度
    private int speed;
    // 移动方向
    private Direct direct;
    // 移动吗(默认为false)
    private boolean isMoved = false;
    // 是否进行边缘检测(碰到边缘消失)
    private boolean isEdge = false;

    public void act() {
        // 移动
        if (isMoved) fly();
        // 边缘检测
        if (isEdge) edge();
        // 指针在图片范围内循环
        pointerCycle();
        // 切换帧
        if (new Date().getTime() - playBeatTime >= playTime) {
            setImage(images.get(N));
            playBeatTime = new Date().getTime();
        } else {
            N--;
            return;
        }
        // 如果N为0, 则判断循环一次
        if (N == 0) cycle++;
        // 根据删除条件来判断
        boolean isDie = tryDie();
        if (isDie) {
            getWorld().removeObject(this);
        }
    }

    private void edge() {
        if (isAtEdge()) {
            getWorld().removeObject(this);
        }
    }

    private boolean tryDie() {
        if (dieControl == null) {
            Static.note("对象:" + this + " 的dieControl变量是null!", "错误");
        }

        boolean isDie = false;
        switch (dieControl) {
            case Null:
                // 不用做什么
                break;

            case loop_once:
                // 如果循环了一次, 那么删除
                if (cycle >= 1){
                    isDie = true;
                }
                break;

            case over_time:
                // 如果超过限定时间, 那么删除
                long nowTime = new Date().getTime();
                if (brithTime + restrictTime <= nowTime) {
                    isDie = true;
                }

                break;

        }
        return isDie;
    }

    private void pointerCycle() {
        if (N >= images.size() - 1) {
            N = 0;
        } else {
            N++;
        }
    }

    @Override
    public void fly() {
        Static.fly(this, direct);
    }

    // 当被添加到世界中时
    protected void addedToWorld(World world) {
        setImage(images.get(0));
        // 初始化时间
        brithTime = new Date().getTime();
    }

    // 构造方法
    public SImage() {}
    public SImage(List<GreenfootImage> images, Del dieControl) {
        this.images = images;
        this.dieControl = dieControl;
    }

    public SImage(List<GreenfootImage> images, int restrictTime) {
        this.images = images;
        this.dieControl = Del.over_time;
        this.restrictTime = restrictTime;
    }
    public SImage(GreenfootImage image, Del dieControl){
        this.images = new ArrayList<>();
        this.images.add(image);
        this.dieControl = dieControl;
    }

    public SImage(List<GreenfootImage> images, Del dieControl, long playTime) {
        this.images = images;
        this.dieControl = dieControl;
        this.playTime = playTime;
    }

    // setter and getter
    public Del getDieControl() {
        return dieControl;
    }

    public void setDieControl(Del dieControl) {
        this.dieControl = dieControl;
    }

    public long getPlayTime() {
        return playTime;
    }

    public void setPlayTime(long playTime) {
        this.playTime = playTime;
    }

    public List<GreenfootImage> getImages() {
        return images;
    }

    public void setImages(List<GreenfootImage> images) {
        this.images = images;
    }
    public void setImages(GreenfootImage image) {
        this.images = new ArrayList<>();
        images.add(image);
    }

    public long getRestrictTime() {
        return restrictTime;
    }

    public void setRestrictTime(int restrictTime) {
        this.restrictTime = restrictTime;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Direct getDirect() {
        return direct;
    }

    public void setDirect(Direct direct) {
        this.direct = direct;
    }

    public boolean isMoved() {
        return isMoved;
    }

    public void setMoved(boolean moved) {
        isMoved = moved;
    }

    public boolean isEdge() {
        return isEdge;
    }

    public void setEdge(boolean edge) {
        isEdge = edge;
    }
}

enum Del{
    /**
     * 没有条件
     */
    Null,
    /**
     * 循环一次
     */
    loop_once,

    /**
     * 超过限定的时间
     */
    over_time
}