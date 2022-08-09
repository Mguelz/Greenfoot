import greenfoot.GreenfootImage;

public class Empty_Special extends Empty_Shoot{
    // 血量不足的图片
    private GreenfootImage bloodShort;

    @Override
    protected void die() {
        // 如果生命为一半, 更换图片
        if (getHP() <= (getHP_MAX() / 2)) {
            setImage(bloodShort);
        }
        // 如果生命为0及以下, 那么判定死亡
        if (getWorld() != null) {
            if (getHP() <= 0) {
                getWorld().removeObject(this);
            }
        }
    }

    public GreenfootImage getBloodShort() {
        return bloodShort;
    }

    public void setBloodShort(GreenfootImage bloodShort) {
        this.bloodShort = bloodShort;
    }
}
