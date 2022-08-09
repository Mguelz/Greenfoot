import greenfoot.Color;
import greenfoot.Font;
import greenfoot.GreenfootImage;
import greenfoot.World;

/**
 * 可以显示一个条形的能量条的工具类<br>
 * 使用SImage
 */
public class Bar {
    // SImage对象
    private final SImage SImageObj;
    // 此SImage对象的图片
    private GreenfootImage image;

    // 能量条的宽度
    private int barWidth;
    // 能量条的高度
    private int barHeight;

    // 能量条的显示颜色
    private Color barColor;
    // 文字显示颜色
    private Color textColor;

    // 能量(0~100)
    private int energy;
    private int energy_MAX;

    // 装载方法
    public void addToWorld(){
        // 得到现在的energy所占的高度
        int height = getEnergyHeight();
        // 绘制
        draw(height);
        // 绘制数字
        drawText(String.valueOf(energy));
        // 装载图片到对象中
        SImageObj.setImages(image);
    }
    public void addToWorld(World world, int x, int y){
        // 得到现在的energy所占的高度
        int height = getEnergyHeight();

        // 重新生成图片
        image = new GreenfootImage(barWidth, barHeight+20);

        // 绘制
        draw(height);
        // 绘制数字
        drawText(String.valueOf(energy));
        // 装载图片到对象中
        SImageObj.setImages(image);
        // 添加到场景中
        world.addObject(SImageObj, x, y);
    }


    private int getEnergyHeight() {
        // 得到一个单位energy占的格子数
        double energyPxHeight = ((double) barHeight/energy_MAX);
        // 返回 得到的高度
        return (int) (energyPxHeight*energy);
    }

    private void draw(int height) {
        // 绘制底色
        image.setColor(Color.BLACK);
        image.fill();

        // 绘制边框
        image.setColor(barColor);
        image.drawRect(0, 20, barWidth-1, barHeight-1);

        // 绘制能量
        image.setColor(barColor);
        int x = 0;
        int y = barHeight - height;
        int width = barWidth;
        image.fillRect(x, y+20, width, height);
    }

    private void drawText(String text) {
        // 设置颜色
        image.setColor(textColor);
        // 设置文字
        image.setFont(new Font("Consolas", 12));

        // 绘制文字
        image.drawString(text, 1, 13);
    }

    // 构造方法
    public Bar() {
        SImageObj = new SImage();
        SImageObj.setDieControl(Del.Null);
    }

    // setter and getter
    public int getBarWidth() {
        return barWidth;
    }

    public void setBarWidth(int barWidth) {
        this.barWidth = barWidth;
    }

    public int getBarHeight() {
        return barHeight;
    }

    public void setBarHeight(int barHeight) {
        this.barHeight = barHeight;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        if (energy >= energy_MAX) {
            this.energy = energy_MAX;
        } else if (energy <= 0) {
            this.energy = 0;
        } else {
            this.energy = energy;
        }
    }

    public void addEnergy(int aEnergy){
        setEnergy(getEnergy() + aEnergy);
    }

    public void reduceEnergy(int rEnergy){
        setEnergy(getEnergy() - rEnergy);
    }

    public int getEnergy_MAX() {
        return energy_MAX;
    }

    public void setEnergy_MAX(int energy_MAX) {
        this.energy_MAX = energy_MAX;
    }

    public Color getBarColor() {
        return barColor;
    }

    public void setBarColor(Color barColor) {
        this.barColor = barColor;
    }

    public Color getTextColor() {
        return textColor;
    }

    public void setTextColor(Color textColor) {
        this.textColor = textColor;
    }
}
