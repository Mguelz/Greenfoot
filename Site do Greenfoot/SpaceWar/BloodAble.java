public interface BloodAble {
    void setHP(int HP);
    int getHP();

    void setHP_MAX(int HP_MAX);
    int getHP_MAX();

    /**
     * 如果减少血量之后, 血量为0, 返回ture
     */
    boolean reduceHP(int rHP);
    /**
     * 如果增加血量之后, 血量为0, 返回ture
     */
    boolean addHP(int aHP);
}
