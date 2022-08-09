public class Cover_Mine extends Cover{
    public Cover_Mine() {
        setImage("Cover_1.png");
        // 添加默认排除打击的对象
        getExcludes().addAll(Static.cover_excludes);
        addExclude(Mine.class);
        addExclude(Bullet_Mine.class);
        addExclude(Cover_Mine.class);
    }
}
