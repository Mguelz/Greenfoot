import greenfoot.Actor;
import greenfoot.GreenfootImage;

import java.util.Date;
import java.util.List;

public class BlackHole extends Actor implements FlyAble{
    // 速度
    private int speed;
    // 方向
    private Direct direct;

    // 帧
    private List<GreenfootImage> images = Static.BlackHole_;
    // 指针
    private int N = -1;
    // 播放的节奏时间
    private long playBeatTime = new Date().getTime();
    // 限定的帧速(一帧占多少秒)
    private long playTime;

    public void act() {
        // 移动
        fly();
        // 碰撞检测
        collision();
        // 边缘检测
        edge();

        // 指针在图片范围内循环
        pointerCycle();
        // 切换帧
        if (new Date().getTime() - playBeatTime >= playTime) {
            setImage(images.get(N));
            playBeatTime = new Date().getTime();
        } else {
            N--;
        }
    }

    private void pointerCycle() {
        if (N >= images.size() - 1) {
            N = 0;
        } else {
            N++;
        }
    }

    private void collision() {
        List<Actor> actors = getIntersectingObjects(Actor.class);
        // 排除
        Static.exclude(actors, SImage.class, MAG.class);

        for (Actor a : actors) {
            getWorld().removeObject(a);
        }
    }

    private void edge() {
        if (isAtEdge()) {
            getWorld().removeObject(this);
        }
    }

    public void fly() {
        Static.fly(this, direct);
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

    public Direct getDirect() {
        return direct;
    }

    public void setDirect(Direct direct) {
        this.direct = direct;
    }

    public List<GreenfootImage> getImages() {
        return images;
    }

    public void setImages(List<GreenfootImage> images) {
        this.images = images;
    }

    public long getPlayTime() {
        return playTime;
    }

    public void setPlayTime(long playTime) {
        this.playTime = playTime;
    }
}
