import greenfoot.*;

import java.util.Date;
import java.util.Random;

public class Space extends World {
    // 场景的长和高
    private static final int worldWidth = 700;
    private static final int worldHeight = 750;

    // 游戏节奏
    private int beat = 100;

    // 当前时间
    private long nowTime = new Date().getTime();
    // 生成星星的时间戳
    private long createStart_StampTime = nowTime;
    // 生成敌机的时间戳
    private long createEmpty_StampTime = nowTime;
    // 生成随机时间的时间戳
    private long createRandom_StampTime = nowTime;

    // Mine对象击杀的敌人数
    private int killedN = 0;
    // 计分板(击杀人数)
    private final ScoreBroad broad_killedN;

    public void act() {
        // 刷新
        flush();

        // 生成星星(每隔0.5s)
        if (nowTime - createStart_StampTime >= 0.50 * 1000) {
            createStar();
            createStart_StampTime = nowTime;
        }
        // 生成敌机(每隔0.1s)
        if (nowTime - createEmpty_StampTime >= 0.55 * 1000) {
            createEmpty();
            createEmpty_StampTime = nowTime;
        }
        // 生成随机事件(每隔2s)
        if (nowTime - createRandom_StampTime >= 2.00*1000) {
            createRandom();
            createRandom_StampTime = nowTime;
        }
    }

    private void flush() {
        // tempo de atualização
        nowTime = new Date().getTime();
        // atuaizar o placar
        broad_killedN.setText("killed=" + killedN);
        broad_killedN.addToWorld();
    }

    public Space() {
        super(worldWidth, worldHeight, 1);
        // 设置背景
        setBackground("Background.png");
        // Defina a ordem de execução do método act()
        setActOrder(Star.class, Bullet.class, Empty.class, SImage.class, Mine.class);
        // defina a ordem do desenho
        setPaintOrder(MAG.class, SImage.class, Empty.class, Mine.class,
                Bullet.class, Rock.class, Gift.class, Star.class);

        // 生成星星
        createStar(50);
        // 生成我方飞机
        Mine mine = new Mine();
        mine.setSpeed(4);
        mine.setImage("Mine_1.png");
        mine.setHP(100);
        mine.setHP_MAX(100);
        mine.setEnergy(0);
        mine.setEnergy_MAX(100);
        addObject(mine, worldWidth / 2, worldHeight - mine.getImage().getHeight());
        // 添加能量条
        Bar bar = new Bar();
        bar.setBarWidth(24);
        bar.setBarHeight(60);
        bar.setEnergy(0);
        bar.setEnergy_MAX(100);
        bar.setBarColor(Color.RED);
        bar.setTextColor(Color.RED);
        bar.addToWorld(this, 50, worldHeight-100);
        mine.setEnergyBar(bar);

        // 添加计分板
        broad_killedN = new ScoreBroad();
        broad_killedN.setColor(Color.GREEN);
        broad_killedN.setFont(new Font("Consolas", 14));
        broad_killedN.setText("killed=" + killedN);
        broad_killedN.addToWorld(this, 70, worldHeight - 30);
    }

    // 生成敌机
    private void createEmpty() {
        // 计算游戏难度(由游戏节奏, 击杀敌人数决定)
        long hard = getGameDifficulty();
        /*
            当 0  <hard<5  时, 进入第一阶段
            当 28 <hard<70 时, 进入第二阶段
            当 71 <hard<134时, 进入第三阶段
            当 134<hard<200时, 进入加强阶段
            当 201<hard<251时, 进入第三阶段
            当 251<hard<400时, 进入第四阶段
            当 401<hard<500时, 进入第五阶段
        */
        if (hard > 0 && hard <= 5) {
            createEmpty_1(7);

        } else if (hard >= 6 && hard < 70) {
            createEmpty_1(8);
            createEmpty_2(3);

        } else if (hard >= 70 && hard < 134) {
            createEmpty_1(9);
            createEmpty_2(4);
            createEmpty_3(3);
        } else if (hard >= 134 && hard < 200) {
            createEmpty_1(13);
            createEmpty_2(8);
            createEmpty_3(7);
            // 额外补给
            Mine m = getObjects(Mine.class).get(0);
            m.addEnergy(1);
        } else if (hard >= 200 && hard < 251) {
            createEmpty_1(9);
            createEmpty_2(10);
            createEmpty_3(3);
        } else if (hard >= 252 && hard < 400) {
            Greenfoot.stop();
            showText("win!", worldWidth/2, worldHeight/2);

        } else if (hard >= 401 && hard < 500) {

        }
    }

    private long getGameDifficulty() {
        return (beat / 100 + killedN);
    }

    // 生成随机事件
    private void createRandom() {
        /*
            1. 生成黑洞(向下)
            2. 生成陨石(向右)
            3. 生成超级敌机
            4. 生成gift(向下)
            5. 偶然获得加成
         */
        boolean isOK = Static.getRandom();
        if (isOK) {
            BlackHole blackHole = new BlackHole();
            blackHole.setDirect(Direct.DOWN);
            blackHole.setSpeed(1);
            blackHole.setPlayTime(250);
            int x = new Random().nextInt(worldWidth);
            addObject(blackHole, x, 0);
        }

        isOK = Static.getRandom();
        if (isOK) {
            Rock rock = new Rock();
            rock.setDirect(Direct.RIGHT);
            rock.setSpeed(1);
            rock.setHP(new Random().nextInt(3500)+3500);
            rock.setHP_MAX(7000);
            int y = new Random().nextInt((int) (worldHeight*0.7));
            addObject(rock, 0, y);
        }

        isOK = Static.getRandom();
        if (isOK) {
            Empty_Special empty = new Empty_Special1();
            empty.setImage("Empty_3.png");
            empty.setBloodShort(new GreenfootImage("Empty_3_1.png"));
            empty.setShootPlayTime(750);
            empty.setDirect(Direct.DOWN);
            empty.setSpeed(2);
            empty.setHP(1500);
            empty.setHP_MAX(1500);
            int x = new Random().nextInt(worldWidth);
            addObject(empty, x, 0);
        }

        isOK = Static.getRandom();
        if (isOK) {
            Gift gift = new Gift();
            gift.setDirect(Direct.DOWN);
            gift.setSpeed(1);
            int x = new Random().nextInt(worldWidth);
            addObject(gift, x, 0);
        }

        isOK = Static.getRandom();
        if (isOK) {
            Mine mine = getObjects(Mine.class).get(0);
            mine.addEnergy(10);
            SImage sImage = new SImage();
            GreenfootImage image = new GreenfootImage(30, 20);
            image.setColor(Color.GREEN);
            image.setFont(new Font("Comic Sans MS", true, false,12));
            image.drawString("+10 ", 1, 10);
            sImage.setImages(image);
            sImage.setRestrictTime(1755);
            sImage.setDieControl(Del.over_time);
            addObject(sImage, mine.getX()-30, mine.getY());
        }
    }

    // 生成敌机
    private void createEmpty_1(int emptyNum) {
        // 根据公式(节奏-敌机数量的差除以94), 得出需要创建的敌机的数量(向下取整)
        int cemptyNum = (beat - getEmptyNum(0)) / (100 - emptyNum);
        if (cemptyNum <= 0) return;

        for (int i = 0; i < cemptyNum; i++) {
            // 得到敌机的图片
            GreenfootImage image = new GreenfootImage("Empty_0.png");
            // 得到敌机的横坐标
            int x = new Random().nextInt(worldWidth);
            // 得到敌机的纵坐标
            int y = -image.getHeight();
            // 创建敌机
            Empty empty = new Empty();
            empty.setDirect(Direct.DOWN);
            empty.setSpeed(2);
            empty.setHP(100);
            empty.setHP_MAX(100);
            empty.setImage(image);
            // 创建在场景上
            addObject(empty, x, y);
        }
    }

    private void createEmpty_2(int emptyNum) {
        // 根据公式(节奏-敌机数量的差除以94), 得出需要创建的敌机的数量(向下取整)
        int cemptyNum = (beat - getEmptyNum(1)) / (100 - emptyNum);
        if (cemptyNum <= 0) return;

        for (int i = 0; i < cemptyNum; i++) {
            // 得到敌机的图片
            GreenfootImage image = new GreenfootImage("Empty_1.png");
            // 得到敌机的横坐标
            int x = new Random().nextInt(worldWidth);
            // 得到敌机的纵坐标
            int y = -image.getHeight();
            // 创建敌机
            Empty_Shoot empty = new Empty_Shoot();
            empty.setDirect(Direct.DOWN);
            empty.setSpeed(2);
            empty.setHP(100);
            empty.setHP_MAX(100);
            empty.setImage(image);
            empty.setShootPlayTime(1000);
            // 创建在场景上
            addObject(empty, x, y);
        }
    }

    private void createEmpty_3(int emptyNum) {
        // 根据公式(节奏-敌机数量的差除以94), 得出需要创建的敌机的数量(向下取整)
        int cemptyNum = (beat - getEmptyNum(2)) / (100 - emptyNum);
        if (cemptyNum <= 0) return;

        for (int i = 0; i < cemptyNum; i++) {
            // 得到敌机的图片
            GreenfootImage image = new GreenfootImage("Empty_2.png");
            GreenfootImage image1 = new GreenfootImage("Empty_2_1.png");
            // 得到敌机的横坐标
            int x = new Random().nextInt(worldWidth);
            // 得到敌机的纵坐标
            int y = -image.getHeight();
            // 创建敌机
            Empty_Special empty = new Empty_Special();
            empty.setDirect(Direct.DOWN);
            empty.setSpeed(2);
            empty.setHP(200);
            empty.setHP_MAX(200);
            empty.setImage(image);
            empty.setBloodShort(image1);
            empty.setShootPlayTime(2000);
            // 创建在场景上
            addObject(empty, x, y);
        }
    }

    private int getEmptyNum(int i) {
        switch (i) {
            case 0:
                return getObjects(Empty.class).size();
            case 1:
                return getObjects(Empty_Shoot.class).size();
            case 2:
                return getObjects(Empty_Special.class).size();
        }
        return -1;
    }

    // 生成星星
    private void createStar() {
        for (int i = 5; i > 0; i--) {
            int x = new Random().nextInt(worldWidth);
            int y = 0;
            addObject(new Star(), x, y);
        }
    }

    private void createStar(int i) {
        for (; i > 0; i--) {
            int x = new Random().nextInt(worldWidth);
            int y = new Random().nextInt(worldHeight);
            addObject(new Star(), x, y);
        }
    }

    // setter and getter
    public int getKilledN() {
        return killedN;
    }

    public void setKilledN(int killedN) {
        this.killedN = killedN;
    }

    public int getBeat() {
        return beat;
    }

    public void setBeat(int beat) {
        this.beat = beat;
    }
}
