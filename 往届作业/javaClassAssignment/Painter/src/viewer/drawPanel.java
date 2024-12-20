package viewer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import static java.lang.Math.abs;

public class drawPanel extends JPanel {
    public int x, x1, x2;
    public int y, y1, y2;
    private Color backcol; // 用来存储背景颜色
    private BufferedImage image; // 用于保存图形的BufferedImage
    private String currentShape; // 存储当前图形类型
    private Color currentLineColor; // 存储当前线条颜色

    // 图形类型常
    public static final String LINE = "Line";
    public static final String RECTANGLE = "Rectangle";
    public static final String OVAL = "Oval";
    public static final String TRIANGLE = "Triangle";
    public static final String ROUND = "Round";

    // Constructor
    public drawPanel() {
        super();
        backcol = Color.WHITE; // 默认背景颜色为白色
        currentLineColor = Color.BLACK; // 默认线条颜色为黑色
        setPreferredSize(new java.awt.Dimension(500, 500)); // 设置面板大小
        setBackground(backcol); // 初始化时设置背景颜色

        // 创建一个BufferedImage来保存图形
        image = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        g.setColor(backcol);
        g.fillRect(0, 0, 500, 500); // 填充背景色

        // 初始化为默认的图形类型
        currentShape = LINE; // 默认绘制线条

        // 添加鼠标监听事件，用来捕获绘制的起始和结束位置
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                x1 = e.getX();
                y1 = e.getY();
                // repaint(); // 重新绘制
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent e) {
                x1 = e.getX();
                y1 = e.getY();
                // repaint();
            }

            @Override
            public void mouseReleased(java.awt.event.MouseEvent e) {
                x2 = e.getX();
                y2 = e.getY();
                drawShape(); // 调用虚方法绘制图形
                repaint();
            }

            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                x1 = e.getX();
                y1 = e.getY();
                // repaint();
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                x = e.getX();
                y = e.getY();
                // repaint();
            }
        });
    }

    // 更新背景颜色的方法
    public void changebackcol(Color nColor) {
        backcol = nColor; // 更改背景颜色
        setBackground(backcol); // 设置面板背景色
        image = new BufferedImage(500, 500, BufferedImage.TYPE_INT_ARGB);
        Graphics g = image.getGraphics();
        g.setColor(backcol);
        g.fillRect(0, 0, 500, 500); // 填充新的背景色
        repaint(); // 重新绘制面板
    }

    // 设置当前绘制的图形类型
    public void setCurrentShape(String shapeType) {
        currentShape = shapeType; // 更新当前图形类型
    }

    // 设置线条的颜色
    public void setLineColor(Color color) {
        currentLineColor = color; // 更新线条颜色
    }

    // 使用 paintComponent 来绘制组件内容，确保背景颜色的正常显示
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // 调用父类的方法，确保背景色正常显示
        g.drawImage(image, 0, 0, null); // 绘制保存的BufferedImage
    }

    // 保存绘制的图形到文件
    public void saveImage(File file) {
        try {
            ImageIO.write(image, "PNG", file); // 保存为PNG格式的文件
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 虚拟绘制方法
    private void drawShape() {
        Graphics g = image.getGraphics(); // 获取BufferedImage的Graphics对象
        g.setColor(currentLineColor); // 使用当前的线条颜色

        switch (currentShape) {
            case LINE:
                drawLine(g);
                break;
            case RECTANGLE:
                drawRectangle(g);
                break;
            case OVAL:
                drawOval(g);
                break;
            case TRIANGLE:
                drawTriangle(g);
                break;
            case ROUND:
                drawRound(g);
                break;
            default:
                break;
        }
    }

    // 绘制线条
    private void drawLine(Graphics g) {
        g.drawLine(x1, y1, x2, y2);
    }

    // 绘制矩形
    private void drawRectangle(Graphics g) {
        g.drawRect(x1, y1, abs(x1 - x2), abs(y1 - y2));
    }

    // 绘制椭圆 (Oval)
    private void drawOval(Graphics g) {
        g.drawOval(x1, y1, abs(x1 - x2), abs(y1 - y2));
    }

    // 绘制三角形
    private void drawTriangle(Graphics g) {
        int[] xPoints = {x1, x2, (x1 + x2) / 2};
        int[] yPoints = {y1, y2, (y1 + y2) / 2};
        g.drawPolygon(xPoints, yPoints, 3);
    }

    // 绘制圆角矩形
    private void drawRound(Graphics g) {
        g.drawRoundRect(x1, y1, abs(x1 - x2), abs(y1 - y2), 10, 10);
    }
}
