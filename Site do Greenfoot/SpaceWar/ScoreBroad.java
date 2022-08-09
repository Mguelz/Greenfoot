import greenfoot.Color;
import greenfoot.Font;
import greenfoot.GreenfootImage;
import greenfoot.World;

/**
 * 记分版
 */
public class ScoreBroad {
    // SImage对象
    private final SImage SImageObj;
    // 此SImage对象的图片
    private GreenfootImage image;

    // 显示的文本
    private String text;
    // 文本字体
    private Font font;
    // 文本颜色
    private Color color;

    // 装载方法
    public void addToWorld(){
        // 清空
        image.clear();

        // 写
        write();

        // 装载图片到对象中
        SImageObj.setImages(image);
    }
    public void addToWorld(World world, int x, int y){
        addToWorld();
        // 添加到场景中
        world.addObject(SImageObj, x, y);
    }


    private void write() {
        int height = image.getHeight();

        image.setFont(font);
        image.setColor(color);

        image.drawString(text, 1, height/2);
    }


    // 构造方法
    public ScoreBroad() {
        SImageObj = new SImage();
        SImageObj.setDieControl(Del.Null);
        image = new GreenfootImage(100, 20);
    }

    // setter and getter
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public SImage getSImageObj() {
        return SImageObj;
    }

    public GreenfootImage getImage() {
        return image;
    }

    public void setImage(GreenfootImage image) {
        this.image = image;
    }

    public Font getFont() {
        return font;
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
