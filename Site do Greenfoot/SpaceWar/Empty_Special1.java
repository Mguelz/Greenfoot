import greenfoot.GreenfootImage;

import java.util.List;

public class Empty_Special1 extends Empty_Special{
    // 被摧毁时的动画
    private List<GreenfootImage> images;

    @Override
    protected void die() {
        // 如果生命为一半, 更换图片
        if (getHP() <= (getHP_MAX() / 2)) {
            setImage(getBloodShort());
        }
        // 如果生命为0及以下, 那么判定死亡
        if (getWorld() != null) {
            if (getHP() <= 0) {
                SImage image = new SImage();
                image.setMoved(true);
                image.setImages(Static.Empty_3_Break);
                image.setDieControl(Del.loop_once);
                image.setDirect(getDirect());
                image.setSpeed(getSpeed());
                image.setPlayTime(50);
                image.setEdge(true);
                getWorld().addObject(image, getX(), getY());
                getWorld().removeObject(this);
            }
        }
    }

    // getter and setter
    public List<GreenfootImage> getImages() {
        return images;
    }

    public void setImages(List<GreenfootImage> images) {
        this.images = images;
    }
}
