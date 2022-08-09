import greenfoot.Actor;

import java.util.List;

public class Bullet_Mine extends Bullet{
    protected void collision() {
        List<Actor> actors = getIntersectingObjects(Actor.class);
        // 排除排除打击的对象
        Static.exclude(actors, getExcludes());

        // 剩下的是要打击的对象
        for (Actor a : actors) {
            /*
                如果是BloodAble, 那么减去rHP,
                不是BloodAble的, 当作无敌

                如果是Bullet, 减去可以击打的次数
             */
            if (a instanceof BloodAble) {
                // 减去血量
                if (((BloodAble) a).reduceHP(getrHP())) {
                    getWorldOfType(Space.class).setKilledN(getWorldOfType(Space.class).getKilledN()+1);
                }
            }
            if (a instanceof Bullet) {
                ((Bullet) a).setBeatN(((Bullet) a).getBeatN()-1);
            }
            // 生成爆炸
            createBOOM();
            // 减去可以击打的次数
            setBeatN(getBeatN()-1);
            // 如果可以击打的次数为0, 那么退出循环
            if (getBeatN() <= 0) break;
        }
    }

    // 构造方法
    public Bullet_Mine() {
        // 添加默认 排除打击的对象
        getExcludes().addAll(Static.bullet_excludes);
        addExclude(Bullet_Mine.class);
        addExclude(Cover_Mine.class);
    }
}
