package net.lab1024.sa.admin.util.excel;

import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.date.DateUtil;
import lombok.Data;

import javax.swing.*;
import java.awt.*;
import java.util.Date;


/**
 * @author liuzhexian
 * @since 2024-04-16
 * @email 1037512352@qq.com
 */
@Data
public class Watermark {

    public Watermark(String content) {
        this.content = content;
        init();
    }

    public Watermark(String content, Color color, Font font, double angle) {
        this.content = content;
        this.color = color;
        this.font = font;
        this.angle = angle;
        init();
    }

    /**
     * 根据水印内容长度自适应水印图片大小，简单的三角函数
     */
    private void init() {
        FontMetrics fontMetrics = new JLabel().getFontMetrics(this.font);
        int stringWidth = fontMetrics.stringWidth(this.content);
        int charWidth = fontMetrics.charWidth('A');
        this.width = (int) Math.abs(stringWidth * Math.cos(Math.toRadians(this.angle))) + 2 * charWidth;
        this.height = (int) Math.abs(stringWidth * Math.sin(Math.toRadians(this.angle))) + 2 * charWidth;
        this.yAxis = this.height;
        this.xAxis = charWidth;
    }

    /**
     * 水印内容
     */
    private String content;

    /**
     * 画笔颜色
     */
    private Color color = new Color(239,239,239);

    /**
     * 字体样式
     */
    private Font font = new Font("Microsoft YaHei", Font.BOLD, 15);

    /**
     * 水印宽度
     */
    private int width;

    /**
     * 水印高度
     */
    private int height;

    /**
     * 倾斜角度，非弧度制
     */
    private double angle = 25;

    /**
     * 字体的y轴位置
     */
    private int yAxis = 50;

    /**
     * 字体的X轴位置
     */
    private int xAxis;

    /**
     * 水平倾斜度
     */
    private double shearX = 0.1;

    /**
     * 垂直倾斜度
     */
    private double shearY = -0.26;
}
