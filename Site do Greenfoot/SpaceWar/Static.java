import greenfoot.Actor;
import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * 一些公开的资源
 */
public class Static {
    // 子弹爆炸的帧序列
    public static final List<GreenfootImage> Boom_ = new ArrayList<>();
    public static final List<GreenfootImage> Boom_BIG = new ArrayList<>();
    public static final List<GreenfootImage> Boom_SMALL = new ArrayList<>();

    // 黑洞动画
    public static final List<GreenfootImage> BlackHole_ = new ArrayList<>();

    // 陨石破碎动画
    public static final List<GreenfootImage> Rock_Break = new ArrayList<>();

    // Empty_3敌机爆炸动画
    public static final List<GreenfootImage> Empty_3_Break = new ArrayList<>();

    // 子弹默认排除打击的对象
    public static List<Class<?>> bullet_excludes = new ArrayList<>();
    // 保护罩默认排除打击的对象
    public static List<Class<?>> cover_excludes = new ArrayList<>();
    // 陨石默认排除打击的对象
    public static List<Class<?>> rock_excludes = new ArrayList<>();


    /**
     * 从actors中排除是classes的对象
     */
    public static void exclude(List<Actor> actors, Class<?>... classes) {
        List<Actor> excludes = new ArrayList<>();

        for (Actor a : actors) {
            for (Class<?> c : classes) {
                if (a.getClass().equals(c)) {
                    excludes.add(a);
                }
            }
        }

        actors.removeAll(excludes);
    }

    public static void exclude(List<Actor> actors, List<Class<?>> classes) {
        List<Actor> excludes = new ArrayList<>();

        for (Actor a : actors) {
            for (Class<?> c : classes) {
                if (a.getClass().equals(c)) {
                    excludes.add(a);
                }
            }
        }

        actors.removeAll(excludes);
    }

    /**
     * 从actors中排除不是classes的对象
     */
    public static void include(List<Actor> actors, Class<?>... classes) {
        List<Actor> excludes = new ArrayList<>(actors);

        for (Actor a : actors) {
            for (Class<?> c : classes) {
                if (a.getClass().equals(c)) {
                    excludes.remove(a);
                }
            }
        }

        actors.removeAll(excludes);
    }

    public static void include(List<Actor> actors, List<Class<?>> classes) {
        List<Actor> excludes = new ArrayList<>(actors);

        for (Actor a : actors) {
            for (Class<?> c : classes) {
                if (a.getClass().equals(c)) {
                    excludes.remove(a);
                }
            }
        }

        actors.removeAll(excludes);
    }

    // 通用的移动方法
    public static void fly(Actor actor, Direct direct) {
        /*
            需要符合下面的条件才能移动
            1. actor在World中
            2. actor实现了FlyAble接口
            3. actor的速度不为0
            4. direct不为空
         */
        if (actor.getWorld() != null) {
            if (actor instanceof FlyAble) {
                if (direct != null) {
                    if (((FlyAble) actor).getSpeed() > 0) {
                        int x = actor.getX();
                        int y = actor.getY();
                        int speed = ((FlyAble) actor).getSpeed();
                        switch (direct) {
                            case UP:
                                actor.setLocation(x, y - speed);
                                break;
                            case DOWN:
                                actor.setLocation(x, y + speed);
                                break;
                            case LEFT:
                                actor.setLocation(x - speed, y);
                                break;
                            case RIGHT:
                                actor.setLocation(x + speed, y);
                                break;
                        }
                    } else {
                        // 程序到这里, 说明actor的速度为零
                        note("对象:" + actor + " 试图移动, 但是speed为0", "警告");
                    }
                } else {
                    // 程序到这里, 说明direct还未定义
                    note("对象:" + actor + " 试图移动, 但是direct未定义", "警告");
                }
            } else {
                // 程序到这里, 说明actor不是一个可以飞行的对象
                note("对象:" + actor + " 没有实现FlyAble接口", "警告");
            }
        } else {
            // 程序到这里, 说明actor不在场景中
            note("对象:" + actor + " 不在此场景中!", "警告");
        }
    }

    // 设置射击坐标
    public static void setShootXY(Actor actor, Direct direct) {
        /*
            前提:
            1. 此对象可以射击
            2. 此对象在世界中
         */
        if (actor instanceof ShootAble) {
            if (actor.getWorld() != null) {
                if (direct != null) {
                    // 此actor的坐标
                    int aX = actor.getX();
                    int aY = actor.getY();
                    // 此actor图片的宽度和高度
                    int width = actor.getImage().getWidth();
                    int height = actor.getImage().getHeight();
                    // shootXY
                    int shootX = 0;
                    int shootY = 0;

                    // 计算出shootX, shootY
                    switch (direct) {
                        case UP:
                            shootX =
                                    (aX);
                            shootY =
                                    (int) (aY - height * 0.2 * 0.4);
                            break;
                        case DOWN:
                            shootX =
                                    (aX);
                            shootY =
                                    (int) (aY + height * 0.2 * 0.4);
                            break;
                        case LEFT:
                            shootX =
                                    (int) (aX - width * 0.2 * 0.4);
                            shootY =
                                    (aY);
                            break;
                        case RIGHT:
                            shootX =
                                    (int) (aX + width * 0.2 * 0.4);
                            shootY =
                                    (aY);
                            break;
                    }

                    // 赋值
                    ((ShootAble) actor).setShootX(shootX);
                    ((ShootAble) actor).setShootY(shootY);
                } else {
                    Static.note("对象: " + actor + " 在设置它的shootX和shootY时, direct为null!", "错误");
                }
            } else {
                Static.note("对象: " + actor + " 试图设置它的shootX和shootY, 但是它不在World中!", "错误");
            }
        } else {
            Static.note("对象: " + actor + " 试图设置它的shootX和shootY, 但是它没有实现ShootAble接口!", "错误");
        }
    }

    // 播放声音
    public static void play(String type) {
        GreenfootSound sound;
        switch (type) {
            case "shoot":
                sound = new GreenfootSound("Shoot_1.wav");
                sound.setVolume(70);
                sound.play();
                break;
        }
    }

    // 得到随机的布尔类型
    public static boolean getRandom() {
        int i = new Random().nextInt(100000);
        return i < 3900;
    }

    public static boolean getRandom(int i) {
        int j = new Random().nextInt(100 * i);
        return j <= 100;
    }

    // 输出
    public static void note(String s, String type) {
        System.out.print("[" + type + "] ");
        System.out.print(s + "\n");
    }

    static {
        Boom_.add(new GreenfootImage("Boom_0.png"));
        Boom_.add(new GreenfootImage("Boom_1.png"));
        Boom_.add(new GreenfootImage("Boom_2.png"));
        Boom_.add(new GreenfootImage("Boom_3.png"));
        Boom_.add(new GreenfootImage("Boom_4.png"));
        Boom_.add(new GreenfootImage("Boom_5.png"));
        Boom_.add(new GreenfootImage("Boom_6.png"));
        Boom_.add(new GreenfootImage("Boom_7.png"));
        Boom_BIG.add(new GreenfootImage("Boom_BIG_0.png"));
        Boom_BIG.add(new GreenfootImage("Boom_BIG_1.png"));
        Boom_BIG.add(new GreenfootImage("Boom_BIG_2.png"));
        Boom_BIG.add(new GreenfootImage("Boom_BIG_3.png"));
        Boom_BIG.add(new GreenfootImage("Boom_BIG_4.png"));
        Boom_BIG.add(new GreenfootImage("Boom_BIG_5.png"));
        Boom_BIG.add(new GreenfootImage("Boom_BIG_6.png"));
        Boom_BIG.add(new GreenfootImage("Boom_BIG_7.png"));
        Boom_SMALL.add(new GreenfootImage("Boom_SMALL_0.png"));
        Boom_SMALL.add(new GreenfootImage("Boom_SMALL_1.png"));
        Boom_SMALL.add(new GreenfootImage("Boom_SMALL_2.png"));
        Boom_SMALL.add(new GreenfootImage("Boom_SMALL_3.png"));
        Boom_SMALL.add(new GreenfootImage("Boom_SMALL_4.png"));
        Boom_SMALL.add(new GreenfootImage("Boom_SMALL_5.png"));
        Boom_SMALL.add(new GreenfootImage("Boom_SMALL_6.png"));
        Boom_SMALL.add(new GreenfootImage("Boom_SMALL_7.png"));
        BlackHole_.add(new GreenfootImage("BlackHole_1.png"));
        BlackHole_.add(new GreenfootImage("BlackHole_2.png"));
        BlackHole_.add(new GreenfootImage("BlackHole_3.png"));
        BlackHole_.add(new GreenfootImage("BlackHole_4.png"));
        BlackHole_.add(new GreenfootImage("BlackHole_5.png"));
        Rock_Break.add(new GreenfootImage("Rock_Break_0.png"));
        Rock_Break.add(new GreenfootImage("Rock_Break_1.png"));
        Rock_Break.add(new GreenfootImage("Rock_Break_2.png"));
        Rock_Break.add(new GreenfootImage("Rock_Break_3.png"));
        Rock_Break.add(new GreenfootImage("Rock_Break_4.png"));
        Rock_Break.add(new GreenfootImage("Rock_Break_5.png"));
        Rock_Break.add(new GreenfootImage("Rock_Break_6.png"));
        bullet_excludes.add(Star.class);
        bullet_excludes.add(SImage.class);
        bullet_excludes.add(BlackHole.class);
        bullet_excludes.add(Gift.class);
        cover_excludes.add(Star.class);
        cover_excludes.add(SImage.class);
        cover_excludes.add(BlackHole.class);
        cover_excludes.add(Gift.class);
        rock_excludes.add(Star.class);
        rock_excludes.add(SImage.class);
        rock_excludes.add(BlackHole.class);
        rock_excludes.add(Gift.class);
        Empty_3_Break.add(new GreenfootImage("Empty_3_Break_0.png"));
        Empty_3_Break.add(new GreenfootImage("Empty_3_Break_1.png"));
        Empty_3_Break.add(new GreenfootImage("Empty_3_Break_2.png"));
        Empty_3_Break.add(new GreenfootImage("Empty_3_Break_3.png"));
        Empty_3_Break.add(new GreenfootImage("Empty_3_Break_4.png"));
        Empty_3_Break.add(new GreenfootImage("Empty_3_Break_5.png"));
        Empty_3_Break.add(new GreenfootImage("Empty_3_Break_6.png"));
        Empty_3_Break.add(new GreenfootImage("Empty_3_Break_7.png"));
        Empty_3_Break.add(new GreenfootImage("Empty_3_Break_8.png"));
    }
}
