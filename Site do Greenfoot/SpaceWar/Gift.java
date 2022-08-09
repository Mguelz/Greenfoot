import greenfoot.Actor;
import greenfoot.Color;
import greenfoot.Font;
import greenfoot.GreenfootImage;

import java.util.Random;

/**
 * 道具
 */
public class Gift extends Actor implements FlyAble {
    // 移动速度
    private int speed;
    // 方向
    private Direct direct;

    // 打开了吗
    private boolean isOpen = false;

    public void act() {
        // 移动
        fly();
        // 死亡检测
        die();
    }

    private void die() {
        if (isAtEdge()) {
            getWorld().removeObject(this);
        }
    }

    public void fly() {
        Static.fly(this, direct);
    }

    public void open(Mine mine) {
        if (isOpen) return;
        // 得到好处
        int funN = new Random().nextInt(10);
        // 挑选好处
        switch (funN) {
            case 1:
                // 加energy
                mine.addEnergy(35);
                SImage sImage = new SImage();
                GreenfootImage image = new GreenfootImage(30, 20);
                image.setColor(Color.ORANGE);
                image.setFont(new Font("Comic Sans MS", false, false, 12));
                image.drawString("+35 ", 1, 10);
                sImage.setImages(image);
                sImage.setRestrictTime(1750);
                sImage.setDieControl(Del.over_time);
                getWorld().addObject(sImage, mine.getX() - 30, mine.getY());
                break;
            case 2:
                // 加energy
                mine.addEnergy(55);
                SImage sImage2 = new SImage();
                GreenfootImage image2 = new GreenfootImage(30, 20);
                image2.setColor(Color.ORANGE);
                image2.setFont(new Font("Comic Sans MS", false, false, 12));
                image2.drawString("+35 ", 1, 10);
                sImage2.setImages(image2);
                sImage2.setRestrictTime(1750);
                sImage2.setDieControl(Del.over_time);
                getWorld().addObject(sImage2, mine.getX() - 30, mine.getY());
                break;
            case 3:
                // 加energy
                mine.addEnergy(105);
                SImage sImage3 = new SImage();
                GreenfootImage image3 = new GreenfootImage(30, 20);
                image3.setColor(Color.ORANGE);
                image3.setFont(new Font("Comic Sans MS", false, false, 12));
                image3.drawString("+35 ", 1, 10);
                sImage3.setImages(image3);
                sImage3.setRestrictTime(1750);
                sImage3.setDieControl(Del.over_time);
                getWorld().addObject(sImage3, mine.getX() - 30, mine.getY());
                break;
            case 4:
                // 添加敌机
                Space space = mine.getWorldOfType(Space.class);
                int i;
                for (i = 0; i < new Random().nextInt(30); i++) {
                    // 得到敌机的图片
                    GreenfootImage image9 = new GreenfootImage("Empty_1.png");
                    // 得到敌机的横坐标
                    int x = new Random().nextInt(space.getWidth());
                    // 得到敌机的纵坐标
                    int y = -image9.getHeight();
                    // 创建敌机
                    Empty_Shoot empty = new Empty_Shoot();
                    empty.setDirect(Direct.DOWN);
                    empty.setSpeed(2);
                    empty.setHP(100);
                    empty.setHP_MAX(100);
                    empty.setImage(image9);
                    empty.setShootPlayTime(1000);
                    // 创建在场景上
                    space.addObject(empty, x, y);
                }
                SImage sImage6 = new SImage();
                GreenfootImage image6 = new GreenfootImage(130, 20);
                image6.setColor(Color.ORANGE);
                image6.setFont(new Font("Comic Sans MS", false, false, 14));
                image6.drawString("Empty +" + i + "", 1, 10);
                sImage6.setImages(image6);
                sImage6.setRestrictTime(1750);
                sImage6.setDieControl(Del.over_time);
                getWorld().addObject(sImage6, mine.getX() - 30, mine.getY());

                break;
            case 5:
                // 将飞机的energy上限调高100(到达999为止)
                if (mine.getEnergy_MAX() < 900) {
                    mine.setEnergy_MAX(mine.getEnergy_MAX() + 100);
                    mine.getEnergyBar().setEnergy_MAX(mine.getEnergyBar().getEnergy_MAX() + 100);
                } else if (mine.getEnergy_MAX() == 900) {
                    mine.setEnergy_MAX(999);
                    mine.getEnergyBar().setEnergy_MAX(999);
                }
                SImage sImage4 = new SImage();
                GreenfootImage image4 = new GreenfootImage(130, 20);
                image4.setColor(Color.ORANGE);
                image4.setFont(new Font("Comic Sans MS", false, false, 14));
                image4.drawString("energy_Max +100", 1, 10);
                sImage4.setImages(image4);
                sImage4.setRestrictTime(1750);
                sImage4.setDieControl(Del.over_time);
                getWorld().addObject(sImage4, mine.getX() - 30, mine.getY());
                break;
            case 9:
                // 提高飞机的HP
                if (mine.getHP_MAX() < 500) {
                    mine.setHP_MAX(mine.getHP_MAX() + 100);
                    mine.addHP(100);
                }
                SImage sImage5 = new SImage();
                GreenfootImage image5 = new GreenfootImage(100, 20);
                image5.setColor(Color.ORANGE);
                image5.setFont(new Font("Comic Sans MS", false, false, 14));
                image5.drawString("HP +100", 1, 10);
                sImage5.setImages(image5);
                sImage5.setRestrictTime(1750);
                sImage5.setDieControl(Del.over_time);
                getWorld().addObject(sImage5, mine.getX() - 30, mine.getY());
                break;
            default:
                SImage sImage1 = new SImage();
                GreenfootImage image1 = new GreenfootImage(30, 20);
                image1.setColor(Color.GREEN);
                image1.setFont(new Font("Comic Sans MS", false, false, 12));
                image1.drawString("=) ", 1, 10);
                sImage1.setImages(image1);
                sImage1.setRestrictTime(1750);
                sImage1.setDieControl(Del.over_time);
                getWorld().addObject(sImage1, mine.getX() - 30, mine.getY());
                break;
        }
        isOpen = true;
        setImage("Gift_1_1.png");
    }

    // 构造方法
    public Gift() {
        setImage("Gift_1.png");
    }

    // getter and setter
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
}
